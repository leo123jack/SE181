import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawValidatorTest {

	WithdrawValidator withdrawValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		bank.addAccount("Checking", "12345678", 0);
		bank.addAccount("Savings", "87654321", 0);
		bank.addCDAccount("CD", "12340987", 0, 1000);
		withdrawValidator = new WithdrawValidator(bank);
	}

	@Test
	void withdrawing_a_valid_amount_in_a_checking_account() {
		assertTrue(withdrawValidator.validate("withdraw 12345678 100"));
	}

	@Test
	void withdrawing_a_valid_amount_in_a_saving_account() {
		assertTrue(withdrawValidator.validate("withdraw 87654321 500"));
	}

	@Test
	void withdrawing_command_with_camel_case() {
		assertTrue(withdrawValidator.validate("wIthDRaw 87654321 500"));
	}

	@Test
	void withdrawing_from_a_CD_account() {
		assertFalse(withdrawValidator.validate("withdraw 12340987 1"));
	}

	@Test
	void withdrawing_a_invalid_amount_in_a_checking_account() {
		assertFalse(withdrawValidator.validate("withdraw 12345678 401"));
	}

	@Test
	void withdrawing_a_invalid_amount_in_a_checking_account_two() {
		assertFalse(withdrawValidator.validate("withdraw 12345678 0"));
	}

	@Test
	void withdrawing_a_invalid_amount_in_a_saving_account() {
		assertFalse(withdrawValidator.validate("withdraw 87654321 1001"));
	}

	@Test
	void withdrawing_a_nothing_into_a_saving_account() {
		assertFalse(withdrawValidator.validate("withdraw 87654321 0"));
	}

	@Test
	void withdrawing_a_negative_amount() {
		assertFalse(withdrawValidator.validate("withdraw 87654321 -100"));
	}

	@Test
	void withdrawing_into_a_valid_account_without_withdraw_command() {
		assertFalse(withdrawValidator.validate("12345678 100"));
	}

	@Test
	void withdrawing_nothing_into_a_valid_account() {
		assertFalse(withdrawValidator.validate("withdraw 12345678"));
	}

}
