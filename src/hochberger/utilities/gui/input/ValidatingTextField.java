package hochberger.utilities.gui.input;

import hochberger.utilities.gui.input.validator.InputValidator;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class ValidatingTextField extends JTextField {

	private static final long serialVersionUID = -8523794628534788170L;
	private final List<InputValidator<String>> validators;

	public ValidatingTextField() {
		this(null, null, 0);
	}

	public ValidatingTextField(int columns) {
		this(null, null, columns);
	}

	public ValidatingTextField(String text, int columns) {
		this(null, text, columns);
	}

	public ValidatingTextField(String text) {
		this(null, text, 0);
	}

	public ValidatingTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		this.validators = new LinkedList<InputValidator<String>>();
	}

	public boolean validateInput() {
		for (InputValidator<String> validator : this.validators) {
			if (!validator.isValid(getText())) {
				return false;
			}
		}
		return true;
	}

	public void addValidator(InputValidator<String> validator) {
		this.validators.add(validator);
	}
}
