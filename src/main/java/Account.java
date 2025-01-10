import java.text.DecimalFormat;

public class Account {

	String accountType;
	String accountId;
	double balance;
	double apr;
	int withdrawalCount;
	int age;

	public Account(String type, String accountId, double apr) {
		this(type, accountId, apr, 0);
	}

	public Account(String type, String accountId, double apr, double balance) {
		this.accountType = type;
		this.accountId = accountId;
		this.apr = apr;
		this.balance = balance;
		this.withdrawalCount = 0;
		this.age = 0;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getAccountId() {
		return accountId;
	}

	public double getApr() {
		return apr;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double money) {
		balance += money;
	}

	public void withdraw(double money) {
		if (money >= balance) {
			withdrawalCount++;
			balance = 0;
		} else {
			withdrawalCount++;
			balance -= money;

		}
	}

	public int getWithdrawalCount() {
		return withdrawalCount;
	}

	public boolean canWithdraw(double transferAmount) {
		if (transferAmount < 0) {
			return false;
		}

		if (("CD".equals(accountType)) && !isCDAgeMature()) {
			return false;
		}

		if (transferAmount == 0) {
			return true;
		}

		if ("Savings".equals(accountType)) {
			return canWithdrawFromSavings(transferAmount);
		} else if ("Checking".equals(accountType)) {
			return canWithdrawFromChecking(transferAmount);
		} else {
			return canWithdrawFromOtherAccount(transferAmount);
		}
	}

	private boolean isCDAgeMature() {
		return "CD".equals(getAccountType()) && age >= 12;
	}

	private boolean canWithdrawFromSavings(double transferAmount) {
		return transferAmount <= 1000 && getWithdrawalCount() < 1;
	}

	private boolean canWithdrawFromChecking(double transferAmount) {
		return transferAmount <= 400;
	}

	private boolean canWithdrawFromOtherAccount(double transferAmount) {
		return transferAmount <= getBalance();
	}

	public void updateBalanceWithAPR(int months) {
		age += months;
		withdrawalCount = 0;

		double monthlyRate = (apr / 100) / 12;
		int cdMonths = months * 4;

		if ("cd".equalsIgnoreCase(accountType)) {
			for (int times = 0; times < cdMonths; times++) {
				balance += monthlyRate * balance;
			}
		} else {
			double interest = monthlyRate * balance;
			deposit(interest);
		}
	}

	public boolean getAge() {
		return age >= 12;
	}

	@Override
	public String toString() {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		String formattedBalance = decimalFormat.format(balance);
		String formattedAPR = decimalFormat.format(apr);
		return String.format("%s %s %s %s", accountType.substring(0, 1).toUpperCase() + accountType.substring(1),
				accountId, formattedBalance, formattedAPR);
	}
}
