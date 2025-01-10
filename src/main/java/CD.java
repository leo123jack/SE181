public class CD extends Account {

	public CD(String accountType, String accountId, double apr, double Balance) {
		super(accountType, accountId, apr);
		super.deposit(Balance);
	}

}