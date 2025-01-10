import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bank {

	private final Map<String, Account> accounts;
	private final List<String> accountOrder = new ArrayList<>();
	private final CommandStorage commandStorage;

	public Bank() {
		accounts = new LinkedHashMap<>();
		commandStorage = new CommandStorage(this);
	}

	public Map<String, Account> getAccounts() {
		return accounts;
	}

	public void addAccount(String accountType, String accountId, double apr) {
		accounts.put(accountId, new Account(accountType, accountId, apr));
		accountOrder.add(accountId);
	}

	public void addCDAccount(String accountType, String accountId, double apr, double balance) {
		accounts.put(accountId, new Account(accountType, accountId, apr, balance));
		accountOrder.add(accountId);
	}

	public Account getAccountID(String accountId) {
		return accounts.get(accountId);
	}

	public void depositByID(String accountId, double amountToDeposit) {
		Account account = accounts.get(accountId);
		if (account != null) {
			account.deposit(amountToDeposit);
		}
	}

	public void withdrawByID(String accountId, double amountToWithdraw) {
		Account account = accounts.get(accountId);
		if (account != null) {
			account.withdraw(amountToWithdraw);
		}
	}

	public void passTime(int months) {
		List<String> accountsToRemove = new ArrayList<>();

		for (String accountID : accounts.keySet()) {
			Account account = accounts.get(accountID);

			account.updateBalanceWithAPR(months);

			if (account.getBalance() == 0) {
				accountsToRemove.add(accountID);
			}
		}

		for (String accountID : accountsToRemove) {
			accounts.remove(accountID);
			accountOrder.remove(accountID);
		}
	}

	public void transferMoney(String fromAccountId, String toAccountId, double transferAmount) {
		Account fromAccount = getAccountID(fromAccountId);
		Account toAccount = getAccountID(toAccountId);

		if (fromAccount != null && toAccount != null && fromAccount.canWithdraw(transferAmount)) {
			fromAccount.withdraw(transferAmount);
			toAccount.deposit(transferAmount);
		}
	}

	public List<String> getAccountOrder() {
		return new ArrayList<>(accountOrder);
	}

	public CommandStorage getCommandStorage() {
		return commandStorage;
	}
}