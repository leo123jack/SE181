public class DepositProcessor {

	private final Bank bank;

	public DepositProcessor(Bank bank) {
		this.bank = bank;
	}

	public void make(String command) {
		String[] parts = command.split(" ");
		String accountId = parts[1];
		double amountToDeposit = Double.parseDouble(parts[2]);

		double initialBalance = bank.getAccountID(accountId).getBalance();
		bank.depositByID(accountId, amountToDeposit);
		double finalBalance = bank.getAccountID(accountId).getBalance();

		if (finalBalance > initialBalance) {
			bank.getCommandStorage().addValidCommand(command);
		}
	}
}