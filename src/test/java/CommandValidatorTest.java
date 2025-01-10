import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	public static final String CACCOUNTYPE = "Checking";
	public static final String SACCOUNTYPE = "Savings";
	Bank bank;
	CommandValidator commandValidator;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void testing_create_command_for_saving_account() {
		assertTrue(commandValidator.isValidCommand("create savings 12345678 1"));
	}

	@Test
	void creating_a_valid_deposit_command() {
		bank.addAccount("Checking", "12345678", 0);
		assertTrue(commandValidator.isValidCommand("deposit 12345678 1000"));
	}

	@Test
	void creating_a_command_with_alternating_cases() {
		assertTrue(commandValidator.isValidCommand("CrEaTe sAviNgS 12345678 1"));
	}

	@Test
	void creating_a_command_with_typo() {
		assertFalse(commandValidator.isValidCommand("reate 12345678 1"));
	}

	@Test
	void depositing_with_no_id() {
		assertFalse(commandValidator.isValidCommand("deposit 100"));
	}

	@Test
	void creating_two_accounts_with_same_ID() {
		bank.addAccount(CACCOUNTYPE, "12345678", 5);
		assertFalse(commandValidator.isValidCommand("create savings 12345678 1"));
	}

	@Test
	void testing_two_accounts_with_same_ID() {
		bank.addAccount(CACCOUNTYPE, "12345678", 5);
		bank.addAccount(SACCOUNTYPE, "12345678", 10);
		assertFalse(commandValidator.isValidCommand("create savings 12345678 10"));
	}
}
