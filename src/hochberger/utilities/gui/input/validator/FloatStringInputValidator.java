package hochberger.utilities.gui.input.validator;

public class FloatStringInputValidator implements InputValidator<String> {

    public FloatStringInputValidator() {
        super();
    }

    @Override
    public boolean isValid(final String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
