import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CDTest {

	public static final String ACCOUNTYPE = "CD";
	public static final String ACCOUNTID = "12345678";
	public static final double BALANCE = 100;
	public static final double APR = 5;
	CD cd;

	@BeforeEach
	void setUp() {
		this.cd = new CD(ACCOUNTYPE, ACCOUNTID, APR, BALANCE);
	}

	@Test
	void account_balance_is_100() {
		assertEquals(100, this.cd.getBalance());
	}

	@Test
	void return_apr_amount() {
		assertEquals(5, cd.getApr());
	}

	@Test
	void depositing_10_dollars_to_cd_account() {
		cd.deposit(15);
		assertEquals(115, this.cd.getBalance());
	}

	@Test
	void depositing_twice_to_cd_account() {
		depositing_10_dollars_to_cd_account();
		cd.deposit(5.5);
		assertEquals(120.5, this.cd.getBalance());
	}

	@Test
	void withdrawing_money_more_than_cd_account_amount() {
		cd.withdraw(1000);
		assertEquals(0, this.cd.getBalance());
	}

	@Test
	void withdrawing_twice_in_saving_account() {
		cd.withdraw(25);
		cd.withdraw(10);
		assertEquals(65, this.cd.getBalance());
	}
}
