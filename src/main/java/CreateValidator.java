public class CreateValidator {

	private final Bank bank;

	public CreateValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if (parts.length < 4 || !isValidAccountType(parts[1]) || !isValidAccountNumber(parts[2])
				|| !isValidAPRNumber(Double.parseDouble(parts[3]))) {
			return false;
		}

		if (!isValidAccountTypeLength(parts[1], parts.length)) {
			return false;
		}

		if ("cd".equalsIgnoreCase(parts[1]) && !isValidCDBalance(Double.parseDouble(parts[4]))) {
			return false;
		}

		String accountID = parts[2];
		return bank.getAccountID(accountID) == null;
	}

	private boolean isValidAccountType(String accountType) {
		return accountType.equalsIgnoreCase("savings") || accountType.equalsIgnoreCase("checking")
				|| accountType.equalsIgnoreCase("cd");
	}

	private boolean isValidAccountNumber(String accountNumber) {
		return accountNumber.matches("\\d{8}");
	}

	private boolean isValidAPRNumber(double accountAPR) {
		return (0 <= accountAPR && accountAPR <= 10);
	}

	private boolean isValidAccountTypeLength(String accountType, int length) {
		if ("cd".equalsIgnoreCase(accountType)) {
			return length == 5;
		} else {
			return length == 4;
		}
	}

	private boolean isValidCDBalance(double accountBalance) {
		return accountBalance >= 1000 && accountBalance <= 10000;
	}
}