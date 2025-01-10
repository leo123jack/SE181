import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingTest {

	public static final String ACCOUNTYPE = "Checking";
	public static final String ACCOUNTID = "87654321";
	public static final double APR = 1;
	Checking checking;

	@BeforeEach
	void setUp() {
		this.checking = new Checking(ACCOUNTYPE, ACCOUNTID, APR);
	}

	private void add_20_dollars_into_account() {
		checking.deposit(20);
	}

	@Test
	void checking_account_balance_is_zero() {
		assertEquals(0.0, this.checking.getBalance());
	}

	@Test
	void return_apr_amount() {
		assertEquals(1, this.checking.getApr());
	}

	@Test
	void depositing_10_dollars_to_checking_account() {
		checking.deposit(10);
		assertEquals(10, this.checking.getBalance());
	}

	@Test
	void depositing_twice_to_checking_account() {
		depositing_10_dollars_to_checking_account();
		checking.deposit(5.5);
		assertEquals(15.5, this.checking.getBalance());
	}

	@Test
	void withdrawing_money_when_balance_is_zero_to_checking_account() {
		checking.withdraw(10);
		assertEquals(0, this.checking.getBalance());
	}

	@Test
	void withdrawing_money_when_balance_is_more_than_zero_in_checking_account() {
		add_20_dollars_into_account();
		checking.withdraw(10);
		assertEquals(10, this.checking.getBalance());
	}

	@Test
	void withdrawing_twice_in_checking_account() {
		withdrawing_money_when_balance_is_more_than_zero_in_checking_account();
		checking.withdraw(5);
		assertEquals(5, this.checking.getBalance());
	}
}