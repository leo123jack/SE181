import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateValidatorTest {

	CreateValidator createValidator;

	@BeforeEach
	void setUp() {
		createValidator = new CreateValidator(new Bank());
	}

	@Test
	void creating_a_valid_saving_account() {
		assertTrue(createValidator.validate("Create savings 12345678 0.1"));
	}

	@Test
	void creating_a_valid_checking_account() {
		assertTrue(createValidator.validate("Create checking 12345678 10"));
	}

	@Test
	void creating_a_valid_cd_account() {
		assertTrue(createValidator.validate("Create cd 87654321 0.5 1000"));
	}

	@Test
	void creating_a_invalid_cd_account_with_0_balance() {
		assertFalse(createValidator.validate("Create cd 87654321 10 0"));
	}

	@Test
	void creating_a_invalid_cd_account_with_no_balance() {
		assertFalse(createValidator.validate("Create cd 87654321 0.5"));
	}

	@Test
	void creating_a_invalid_cd_account_with_low_balance() {
		assertFalse(createValidator.validate("Create cd 87654321 10 999"));
	}

	@Test
	void creating_a_invalid_cd_account_with_high_balance() {
		assertFalse(createValidator.validate("Create cd 87654321 10 10001"));
	}

	@Test
	void creating_a_invalid_cd_account_with_bad_apr_value_and_account_balance() {
		assertFalse(createValidator.validate("Create cd 87654321 -2 100"));
	}

	@Test
	void creating_a_invalid_cd_account_with_bad_id_number() {
		assertFalse(createValidator.validate("Create cd 8765 0.5 1000"));
	}

	@Test
	void creating_a_invalid_cd_account_with_bad_id_number_two() {
		assertFalse(createValidator.validate("Create cd 876543210 0.5 2000"));
	}

	@Test
	void creating_a_invalid_cd_account_with_negative_balance() {
		assertFalse(createValidator.validate("Create cd 87654321 5 -1000"));
	}

	@Test
	void creating_a_invalid_saving_account_with_a_balance() {
		assertFalse(createValidator.validate("Create savings 12345678 0.1 1000"));
	}

	@Test
	void creating_a_invalid_command_by_adding_2_empty_accounts() {
		assertFalse(createValidator.validate("Create savings checking 12345678 0.5"));
	}

	@Test
	void creating_a_invalid_saving_account_with_bad_apr_value() {
		assertFalse(createValidator.validate("Create savings 12345678 11"));
	}

	@Test
	void creating_a_invalid_saving_account_with_bad_id_number() {
		assertFalse(createValidator.validate("Create savings 1234 1"));
	}

	@Test
	void creating_a_invalid_saving_account_with_bad_id_number_and_apr_value() {
		assertFalse(createValidator.validate("Create savings 1234 11"));
	}

	@Test
	void creating_a_invalid_saving_account_with_negative_apr_value() {
		assertFalse(createValidator.validate("Create savings 12345678 -1"));
	}

	@Test
	void creating_a_invalid_saving_account_with_no_create_command() {
		assertFalse(createValidator.validate("savings 12345678 0.1"));
	}

	@Test
	void creating_a_invalid_checking_account_with_a_balance() {
		assertFalse(createValidator.validate("Create checking 12345568 0.1 1000"));
	}

	@Test
	void creating_a_invalid_checking_account_with_no_apr_value() {
		assertFalse(createValidator.validate("Create checking 12345678"));
	}

	@Test
	void creating_a_invalid_checking_account_with_no_id() {
		assertFalse(createValidator.validate("Create checking 0.6"));
	}

	@Test
	void creating_a_invalid_checking_account_with_negative_id() {
		assertFalse(createValidator.validate("Create checking -12345678 0.5"));
	}

}
