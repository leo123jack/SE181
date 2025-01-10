import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
	}

	@Test
	void no_accounts_in_bank_when_bank_is_created() {
		assertTrue(bank.getAccounts().isEmpty());
	}

	@Test
	void add_one_account_to_bank() {
		bank.addAccount("Checking", "12345678", 3.0);
		assertEquals(1, bank.getAccounts().size());
	}

	@Test
	void add_two_accounts_to_bank() {
		bank.addAccount("Checking", "12345678", 3.0);
		bank.addAccount("Saving", "87654321", 5.0);
		assertEquals(2, bank.getAccounts().size());
	}

	@Test
	void retrieving_one_account() {
		bank.addAccount("Checking", "12345678", 3.0);
		bank.addAccount("Saving", "87654321", 5.0);

		Account retrievedAccount = bank.getAccountID("12345678");
		assertEquals("12345678", retrievedAccount.getAccountId());
	}

	@Test
	void depositing_money_once_to_the_bank_by_id() {
		bank.addAccount("Checking", "12345678", 3.0);
		bank.depositByID("12345678", 50.0);

		Account account = bank.getAccountID("12345678");
		assertEquals(50.0, account.getBalance());
	}

	@Test
	void depositing_money_twice_by_id_to_the_bank() {
		bank.addAccount("Checking", "12345678", 3.0);
		bank.depositByID("12345678", 50.0);
		bank.depositByID("12345678", 150.0);

		Account account = bank.getAccountID("12345678");
		assertEquals(200.0, account.getBalance());
	}

	@Test
	void withdrawing_money_once_by_id_to_the_bank() {
		bank.addAccount("Checking", "12345678", 3.0);
		bank.depositByID("12345678", 100.0);
		bank.withdrawByID("12345678", 50.0);

		Account account = bank.getAccountID("12345678");
		assertEquals(50.0, account.getBalance());
	}

	@Test
	void withdrawing_money_twice_by_id_to_the_bank() {
		bank.addAccount("Checking", "12345678", 3.0);
		bank.depositByID("12345678", 100.0);
		bank.withdrawByID("12345678", 50.0);
		bank.withdrawByID("12345678", 25.0);

		Account account = bank.getAccountID("12345678");
		assertEquals(25.0, account.getBalance());
	}
}