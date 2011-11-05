package hochberger.utilities.gui.input.validator;

public class IntegerStringInputValidator implements InputValidator<String> {

	public IntegerStringInputValidator() {
		super();
	}

	@Override
	public boolean isValid(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
