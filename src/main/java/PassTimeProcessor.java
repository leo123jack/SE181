public class PassTimeProcessor {

	private final Bank bank;

	public PassTimeProcessor(Bank bank) {
		this.bank = bank;
	}

	public void make(String command) {
		String[] parts = command.split(" ");
		int months = Integer.parseInt(parts[1]);
		bank.passTime(months);
	}
}
