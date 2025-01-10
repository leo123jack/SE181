public class PassTimeValidator {

	public boolean validate(String command) {
		String[] parts = command.split(" ");
		if (parts.length != 2 || !parts[0].equalsIgnoreCase("pass")) {
			return false;
		}

		int months = Integer.parseInt(parts[1]);
		return isValidMonthCount(months);
	}

	private boolean isValidMonthCount(int months) {
		return months > 0 && months <= 60;
	}
}