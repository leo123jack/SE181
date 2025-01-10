public class CommandProcessor {

	private final Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void processCommand(String command) {
		String[] parts = command.split(" ");

		if (parts[0].equalsIgnoreCase("create")) {
			CreateProcessor process = new CreateProcessor(bank);
			process.make(command);
		} else if (parts[0].equalsIgnoreCase("deposit")) {
			DepositProcessor process = new DepositProcessor(bank);
			process.make(command);
		} else if (parts[0].equalsIgnoreCase("withdraw")) {
			WithdrawProcessor process = new WithdrawProcessor(bank);
			process.make(command);
		} else if (parts[0].equalsIgnoreCase("transfer")) {
			TransferProcessor process = new TransferProcessor(bank);
			process.make(command);
		} else if (parts[0].equalsIgnoreCase("pass")) {
			PassTimeProcessor process = new PassTimeProcessor(bank);
			process.make(command);
		}

	}
}
