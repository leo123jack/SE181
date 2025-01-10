import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {

	Bank bank;
	CommandProcessor commandProcessor;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	void creating_checking_account() {
		commandProcessor.processCommand("create checking 12345678 1");
		assertEquals(1, bank.getAccounts().size());
	}

	@Test
	void creating_savings_account() {
		commandProcessor.processCommand("create savings 87654321 5");
		assertEquals(1, bank.getAccounts().size());
	}

	@Test
	void creating_cd_account() {
		commandProcessor.processCommand("create cd 11223344 3 1000");
		assertEquals(1, bank.getAccounts().size());
	}

	@Test
	void depositing_into_a_empty_checking_account() {
		commandProcessor.processCommand("create checking 12345678 1");
		bank.depositByID("12345678", 50.0);
		Account account = bank.getAccountID("12345678");
		assertEquals(50.0, account.getBalance());
	}

	@Test
	void depositing_into_a_account_checking_account_with_a_balance() {
		depositing_into_a_empty_checking_account();
		bank.depositByID("12345678", 50.0);
		Account account = bank.getAccountID("12345678");
		assertEquals(100.0, account.getBalance());
	}

	@Test
	void depositing_into_a_empty_saving_account() {
		commandProcessor.processCommand("create savings 87654321 5");
		bank.depositByID("87654321", 150.0);
		Account account = bank.getAccountID("87654321");
		assertEquals(150.0, account.getBalance());
	}

	@Test
	void depositing_into_a_account_saving_account_with_a_balance() {
		depositing_into_a_empty_saving_account();
		bank.depositByID("87654321", 200.0);
		Account account = bank.getAccountID("87654321");
		assertEquals(350.0, account.getBalance());
	}
}