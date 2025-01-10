import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingTest {

	public static final String ACCOUNTYPE = "Savings";
	public static final String ACCOUNTID = "12345678";
	public static final double APR = 2;
	Saving saving;

	@BeforeEach
	void setUp() {
		this.saving = new Saving(ACCOUNTYPE, ACCOUNTID, APR);
	}

	@Test
	void saving_account_balance_is_zero() {
		assertEquals(0.0, this.saving.getBalance());
	}

	private void add_20_dollars_into_account() {
		saving.deposit(20);
	}

	@Test
	void return_apr_amount() {
		assertEquals(2, this.saving.getApr());
	}

	@Test
	void depositing_20_dollars_to_saving_account() {
		add_20_dollars_into_account();
		assertEquals(20, this.saving.getBalance());
	}

	@Test
	void depositing_twice_to_saving_account() {
		depositing_20_dollars_to_saving_account();
		saving.deposit(5.5);
		assertEquals(25.5, this.saving.getBalance());
	}

	@Test
	void withdrawing_money_when_balance_is_zero_to_saving_account() {
		saving.withdraw(100);
		assertEquals(0, this.saving.getBalance());
	}

	@Test
	void withdrawing_money_when_balance_is_more_than_zero_in_saving_account() {
		add_20_dollars_into_account();
		saving.withdraw(10.5);
		assertEquals(9.5, this.saving.getBalance());
	}

	@Test
	void withdrawing_twice_in_saving_account() {
		withdrawing_money_when_balance_is_more_than_zero_in_saving_account();
		saving.withdraw(5);
		assertEquals(4.5, this.saving.getBalance());
	}
}
