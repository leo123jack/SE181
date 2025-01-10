import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositValidatorTest {

	DepositValidator depositValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		bank.addAccount("Checking", "12345678", 0);
		bank.addAccount("Savings", "87654321", 0);
		bank.addCDAccount("CD", "12340987", 0, 1000);
		depositValidator = new DepositValidator(bank);
	}

	@Test
	void depositing_a_valid_amount_in_a_checking_account() {
		assertTrue(depositValidator.validate("deposit 12345678 1000"));
	}

	@Test
	void depositing_command_with_camel_case() {
		assertTrue(depositValidator.validate("depOsiT 12345678 1000"));
	}

	@Test
	void depositing_a_valid_amount_in_a_saving_account() {
		assertTrue(depositValidator.validate("deposit 87654321 2500"));
	}

	@Test
	void depositing_a_invalid_amount_in_a_checking_account() {
		assertFalse(depositValidator.validate("deposit 123456789 1001"));
	}

	@Test
	void depositing_a_invalid_amount_in_a_saving_account() {
		assertFalse(depositValidator.validate("deposit 87654321 2501"));
	}

	@Test
	void depositing_from_a_CD_account() {
		assertFalse(depositValidator.validate("deposit 12340987 1"));
	}

	@Test
	void depositing_a_valid_amount_in_a_invalid_account_two() {
		assertFalse(depositValidator.validate("deposit 1234 100"));
	}

	@Test
	void depositing_a_zero_into_a_valid_account() {
		assertFalse(depositValidator.validate("deposit 12345678 0"));
	}

	@Test
	void depositing_a_negative_amount_into_a_valid_account() {
		assertFalse(depositValidator.validate("deposit 12345678 -100"));
	}

	@Test
	void depositing_into_a_valid_account_without_deposit_command() {
		assertFalse(depositValidator.validate("12345678 1000"));
	}

	@Test
	void depositing_nothing_into_a_valid_account() {
		assertFalse(depositValidator.validate("deposit 12345678"));
	}
}