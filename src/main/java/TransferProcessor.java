public class TransferProcessor {

	private final Bank bank;

	public TransferProcessor(Bank bank) {
		this.bank = bank;
	}

	public void make(String command) {
		String[] parts = command.split(" ");
		String fromAccountId = parts[1];
		String toAccountId = parts[2];
		double transferAmount = Double.parseDouble(parts[3]);
		bank.transferMoney(fromAccountId, toAccountId, transferAmount);
	}
}
