/*******************************************************************************
 * Copyright (c) 2011 Nico Hochberger.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Nico Hochberger - initial API and implementation
 ******************************************************************************/
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

    public ValidatingTextField(final int columns) {
        this(null, null, columns);
    }

    public ValidatingTextField(final String text, final int columns) {
        this(null, text, columns);
    }

    public ValidatingTextField(final String text) {
        this(null, text, 0);
    }

    public ValidatingTextField(final Document doc, final String text,
            final int columns) {
        super(doc, text, columns);
        this.validators = new LinkedList<InputValidator<String>>();
    }

    public boolean validateInput() {
        for (final InputValidator<String> validator : this.validators) {
            if (!validator.isValid(getText())) {
                return false;
            }
        }
        return true;
    }

    public void addValidator(final InputValidator<String> validator) {
        this.validators.add(validator);
    }
}
