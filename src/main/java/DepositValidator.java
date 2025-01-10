public class DepositValidator {

	private final Bank bank;

	public DepositValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parts = command.split(" ");
		if (!isValidDepositCommand(parts)) {
			return false;
		}

		String accountID = parts[1];
		double depositAmount = Double.parseDouble(parts[2]);

		if (!isValidAccountNumber(accountID) || depositAmount <= 0) {
			return false;
		}

		Account account = bank.getAccountID(accountID);

		if (account == null) {
			return false;
		}

		String accountType = account.getAccountType();

		if ("Savings".equalsIgnoreCase(accountType) && !isValidSavingsDeposit(account, depositAmount)) {
			return false;
		} else if ("Checking".equalsIgnoreCase(accountType) && !isValidCheckingDeposit(depositAmount)) {
			return false;
		} else {
			return !"CD".equalsIgnoreCase(accountType);
		}
	}

	private boolean isValidDepositCommand(String[] parts) {
		return parts.length == 3 && parts[0].equalsIgnoreCase("deposit");
	}

	private boolean isValidSavingsDeposit(Account account, double depositAmount) {
		return account.getBalance() + depositAmount <= 6000 && depositAmount <= 2500;
	}

	private boolean isValidCheckingDeposit(double depositAmount) {
		return depositAmount <= 1000;
	}

	private boolean isValidAccountNumber(String accountNumber) {
		return accountNumber.matches("\\d{8}");
	}
}