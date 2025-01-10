public class WithdrawValidator {

	private final Bank bank;

	public WithdrawValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parts = command.split(" ");
		if (!isValidWithdrawalCommand(parts)) {
			return false;
		}

		String accountID = parts[1];
		double withdrawalAmount = Double.parseDouble(parts[2]);

		if (!isValidAccountID(accountID) || withdrawalAmount <= 0) {
			return false;
		}

		Account account = bank.getAccountID(accountID);

		if (account == null) {
			return false;
		}

		String accountType = account.getAccountType();

		if ("Savings".equalsIgnoreCase(accountType) && !isValidSavingsWithdrawal(account, withdrawalAmount)) {
			return false;
		} else if ("Checking".equalsIgnoreCase(accountType) && !isValidCheckingWithdrawal(withdrawalAmount)) {
			return false;
		} else {
			return !"CD".equalsIgnoreCase(accountType) || isValidCDWithdrawal(account);
		}
	}

	private boolean isValidWithdrawalCommand(String[] parts) {
		return parts.length == 3 && parts[0].equalsIgnoreCase("withdraw");
	}

	private boolean isValidSavingsWithdrawal(Account account, double withdrawalAmount) {
		return withdrawalAmount <= 1000 && account.getWithdrawalCount() < 1;
	}

	private boolean isValidCheckingWithdrawal(double withdrawalAmount) {
		return withdrawalAmount <= 400;
	}

	private boolean isValidCDWithdrawal(Account account) {
		return account.getAge();
	}

	private boolean isValidAccountID(String accountID) {
		return accountID.matches("\\d{8}");
	}
}