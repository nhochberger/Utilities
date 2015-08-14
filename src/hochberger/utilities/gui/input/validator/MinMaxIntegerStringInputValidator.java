package hochberger.utilities.gui.input.validator;

public class MinMaxIntegerStringInputValidator extends
IntegerStringInputValidator {

    private final int max;
    private final int min;

    public MinMaxIntegerStringInputValidator(final int min, final int max) {
        super();
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(final String input) {
        if (!super.isValid(input)) {
            return false;
        }
        int inputAsInt = Integer.valueOf(input);
        return this.min <= inputAsInt && inputAsInt <= this.max;
    }

}
