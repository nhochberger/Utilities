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

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.Border;
import javax.swing.text.Document;

public class SelfHighlightningValidatingTextField extends ValidatingTextField {

	private static final long serialVersionUID = 4209312487666260178L;
	private Color validForegroundColor;
	private Color validBackgroundColor;
	private Color invalidForegroundColor;
	private Color invalidBackgroundColor;
	private Border validBorder;
	private Border invalidBorder;

	public SelfHighlightningValidatingTextField() {
		this(null, null, 0);
	}

	public SelfHighlightningValidatingTextField(final Document doc, final String text, final int columns) {
		super(doc, text, columns);
		addKeyListener(createKeyListener());
		this.validBackgroundColor = getBackground();
		this.validForegroundColor = getForeground();
		this.invalidBackgroundColor = getBackground();
		this.invalidForegroundColor = Color.RED;
		this.validBorder = getBorder();
		this.invalidBorder = getBorder();
	}

	public SelfHighlightningValidatingTextField(final int columns) {
		this(null, null, columns);
	}

	public SelfHighlightningValidatingTextField(final String text, final int columns) {
		this(null, text, columns);
	}

	public SelfHighlightningValidatingTextField(final String text) {
		this(null, text, 0);
	}

	protected KeyListener createKeyListener() {
		return new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				super.keyTyped(e);
				if (validateInput()) {
					setValidSettings();
					return;
				}
				setInvalidSettings();
			}
		};
	}

	private void setInvalidSettings() {
		setForeground(SelfHighlightningValidatingTextField.this.invalidForegroundColor);
		setBackground(SelfHighlightningValidatingTextField.this.invalidBackgroundColor);
		setBorder(SelfHighlightningValidatingTextField.this.invalidBorder);
	}

	private void setValidSettings() {
		setForeground(SelfHighlightningValidatingTextField.this.validForegroundColor);
		setBackground(SelfHighlightningValidatingTextField.this.validBackgroundColor);
		setBorder(SelfHighlightningValidatingTextField.this.validBorder);
	}

	public Color getValidForegroundColor() {
		return this.validForegroundColor;
	}

	public Color getValidBackgroundColor() {
		return this.validBackgroundColor;
	}

	public Color getInvalidForegroundColor() {
		return this.invalidForegroundColor;
	}

	public Color getInvalidBackgroundColor() {
		return this.invalidBackgroundColor;
	}

	public void setValidForegroundColor(final Color validForegroundColor) {
		this.validForegroundColor = validForegroundColor;
	}

	public void setValidBackgroundColor(final Color validBackgroundColor) {
		this.validBackgroundColor = validBackgroundColor;
	}

	public void setInvalidForegroundColor(final Color invalidForegroundColor) {
		this.invalidForegroundColor = invalidForegroundColor;
	}

	public void setInvalidBackgroundColor(final Color invalidBackgroundColor) {
		this.invalidBackgroundColor = invalidBackgroundColor;
	}

	public Border getValidBorder() {
		return this.validBorder;
	}

	public void setValidBorder(final Border validBorder) {
		this.validBorder = validBorder;
	}

	public Border getInvalidBorder() {
		return this.invalidBorder;
	}

	public void setInvalidBorder(final Border invalidBorder) {
		this.invalidBorder = invalidBorder;
	}
}
