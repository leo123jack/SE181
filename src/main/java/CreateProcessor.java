public class CreateProcessor {

	private final Bank bank;

	public CreateProcessor(Bank bank) {
		this.bank = bank;
	}

	public void make(String command) {
		String[] parts = command.split(" ");
		String accountType = parts[1];
		if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")) {
			String accountId = parts[2];
			double apr = Double.parseDouble(parts[3]);
			bank.addAccount(accountType, accountId, apr);
		} else if (accountType.equalsIgnoreCase("cd")) {
			String accountId = parts[2];
			double apr = Double.parseDouble(parts[3]);
			double balance = Double.parseDouble(parts[4]);
			bank.addCDAccount(accountType, accountId, apr, balance);
		}
	}
}