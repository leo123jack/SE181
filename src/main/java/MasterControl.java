import java.util.List;

public class MasterControl {

	private final CommandValidator commandValidator;
	private final CommandProcessor commandProcessor;
	private final CommandStorage commandStorage;

	public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor,
			CommandStorage commandStorage) {
		this.commandValidator = commandValidator;
		this.commandProcessor = commandProcessor;
		this.commandStorage = commandStorage;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (commandValidator.isValidCommand(command)) {
				commandProcessor.processCommand(command);
				commandStorage.addValidCommand(command);
			} else {
				commandStorage.addInvalidCommand(command);
			}
		}
		return commandStorage.output();
	}
}