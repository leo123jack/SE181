import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {

	CommandStorage commandStorage;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandStorage = new CommandStorage(bank);
	}

	@Test
	void command_storage_is_empty() {
		assertEquals(0, commandStorage.getInvalidCommand().size());
	}

	@Test
	void adding_invalid_command() {
		commandStorage.addInvalidCommand("reate savings 12345678 5");
		assertEquals(1, commandStorage.getInvalidCommand().size());
	}

	@Test
	void adding_two_invalid_commands() {
		commandStorage.addInvalidCommand("reate savings 12345678 5");
		commandStorage.addInvalidCommand("create saving 1234 5");
		assertEquals(2, commandStorage.getInvalidCommand().size());
	}

	@Test
	void adding_one_valid_command_and_two_invalid_commands() {
		commandStorage.addValidCommand("create savings 12345678 5");
		commandStorage.addInvalidCommand("reate savings 12345678 5");
		commandStorage.addInvalidCommand("create saving 1234 5");
		assertEquals(2, commandStorage.getInvalidCommand().size());
	}

}
