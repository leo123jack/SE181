public class CommandValidator {

	private final CreateValidator createValidator;
	private final DepositValidator depositValidator;
	private final WithdrawValidator withdrawValidator;
	private final TransferValidator transferValidator;
	private final PassTimeValidator passTimeValidator;

	public CommandValidator(Bank bank) {
		createValidator = new CreateValidator(bank);
		depositValidator = new DepositValidator(bank);
		withdrawValidator = new WithdrawValidator(bank);
		transferValidator = new TransferValidator(bank);
		passTimeValidator = new PassTimeValidator();
	}

	public boolean isValidCommand(String command) {
		String[] parts = command.split(" ");
		String commandType = parts[0].toLowerCase();

		switch (commandType) {
		case "create":
			return createValidator.validate(command);
		case "deposit":
			return depositValidator.validate(command);
		case "withdraw":
			return withdrawValidator.validate(command);
		case "transfer":
			return transferValidator.validate(command);
		case "pass":
			return passTimeValidator.validate(command);
		default:
			return false;
		}
	}
}