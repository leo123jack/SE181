public class WithdrawProcessor {

	private final Bank bank;

	public WithdrawProcessor(Bank bank) {
		this.bank = bank;
	}

	public void make(String command) {
		String[] parts = command.split(" ");
		String accountId = parts[1];
		double amountToWithdraw = Double.parseDouble(parts[2]);
		bank.withdrawByID(accountId, amountToWithdraw);
	}
}
