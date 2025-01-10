import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandStorage {

	private final Bank bank;
	private final List<String> invalidCommands;
	private final Map<String, List<String>> validCommands;

	public CommandStorage(Bank bank) {
		this.bank = bank;
		invalidCommands = new ArrayList<>();
		validCommands = new HashMap<>();
	}

	public void addInvalidCommand(String command) {
		invalidCommands.add(command);
	}

	public List<String> getInvalidCommand() {
		return invalidCommands;
	}

	public void addValidCommand(String command) {
		String accountID = getAccountID(command);
		validCommands.computeIfAbsent(accountID, k -> new ArrayList<>()).add(command);

		if (command.startsWith("Transfer")) {
			String[] parts = command.split(" ");
			String destinationAccountID = parts.length > 2 ? parts[2] : "";
			validCommands.computeIfAbsent(destinationAccountID, k -> new ArrayList<>()).add(command);
		}
	}

	private String getAccountID(String command) {
		String[] parts = command.split(" ");
		if (parts.length > 1) {
			return parts[1];
		} else {
			return null;
		}
	}

	public List<String> output() {
		List<String> output = new ArrayList<>();

		// Process regular accounts
		for (String accountID : bank.getAccountOrder()) {
			String accountStatus = getFormattedAccountStatus(accountID);
			output.add(accountStatus);
			addValidCommands(output, accountID);
		}

		addCDCommands(output);

		output.addAll(invalidCommands);

		return output;
	}

	private void addValidCommands(List<String> output, String accountID) {
		if (validCommands.containsKey(accountID)) {
			output.addAll(validCommands.get(accountID));
		}
	}

	private void addCDCommands(List<String> output) {
		for (String accountID : validCommands.keySet()) {
			if (isCD(accountID)) {
				addValidCommands(output, accountID);
			}
		}
	}

	private boolean isCD(String accountID) {
		return bank.getAccounts().containsKey(accountID) && bank.getAccounts().get(accountID) instanceof CD;
	}

	private String getFormattedAccountStatus(String accountID) {
		Account account = bank.getAccountID(accountID);
		if (account != null) {
			return account.toString();
		} else {
			return "Account not found for ID: " + accountID;
		}
	}
}