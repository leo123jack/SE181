import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferValidatorTest {

	TransferValidator transferValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		transferValidator = new TransferValidator(bank);
		bank.addAccount("Checking", "12345678", 0);
		bank.addAccount("Savings", "87654321", 0);
		bank.addAccount("Checking", "12457890", 0);
		bank.addAccount("Savings", "09764321", 0);
		bank.addCDAccount("CD", "12340987", 0, 1000);
		bank.depositByID("12345678", 1000);
		bank.depositByID("87654321", 2500);
		bank.depositByID("12457890", 1000);
		bank.depositByID("09764321", 2500);
	}

	@Test
	void transfer_from_checking_to_saving() {
		assertTrue(transferValidator.validate("transfer 12345678 87654321 400"));
	}

	@Test
	void transfer_from_checking_to__checking() {
		assertTrue(transferValidator.validate("transfer 12345678 12457890 400"));
	}

	@Test
	void transfer_from_saving_to_checking() {
		assertTrue(transferValidator.validate("transfer 87654321 12345678 1000"));
	}

	@Test
	void transfer_from_saving_to_saving() {
		assertTrue(transferValidator.validate("transfer 87654321 09764321 1000"));
	}

	@Test
	void transfer_to_CD_accounts() {
		assertFalse(transferValidator.validate("transfer 12345678 12340987 500"));
	}

	@Test
	void transfer_from_CD_accounts() {
		assertFalse(transferValidator.validate("transfer 12340987 12345678 500"));
	}

	@Test
	void transfer_invalid_amount_from_checking_to_saving() {
		assertFalse(transferValidator.validate("transfer 12345678 87654321 401"));
	}

	@Test
	void transfer_invalid_amount_from_checking_to__checking() {
		assertFalse(transferValidator.validate("transfer 12345678 12457890 401"));
	}

	@Test
	void transfer_invalid_amount_from_saving_to_checking() {
		assertFalse(transferValidator.validate("transfer 87654321 12345678 1001"));
	}

	@Test
	void transfer_invalid_amount_from_saving_to_saving() {
		assertFalse(transferValidator.validate("transfer 87654321 09764321 1001"));
	}
}