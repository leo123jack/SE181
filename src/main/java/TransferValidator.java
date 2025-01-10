public class TransferValidator {

	private final Bank bank;

	public TransferValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parts = command.split(" ");
		if (parts.length == 4 && parts[0].equalsIgnoreCase("transfer")) {
			String fromAccountId = parts[1];
			String toAccountId = parts[2];
			double transferAmount = Double.parseDouble(parts[3]);

			return isValidTransfer(fromAccountId, toAccountId, transferAmount);
		}
		return false;
	}

	private boolean isValidTransfer(String fromAccountId, String toAccountId, double transferAmount) {
		Account fromAccount = bank.getAccountID(fromAccountId);
		Account toAccount = bank.getAccountID(toAccountId);

		if (fromAccount == null || toAccount == null) {
			return false;
		}

		if ("CD".equals(fromAccount.getAccountType()) || "CD".equals(toAccount.getAccountType())) {
			return false;
		}

		return fromAccount.canWithdraw(transferAmount);
	}
}