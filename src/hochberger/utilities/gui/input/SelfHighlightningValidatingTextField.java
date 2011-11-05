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

	public SelfHighlightningValidatingTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		addKeyListener(createKeyListener());
		this.validBackgroundColor = getBackground();
		this.validForegroundColor = getForeground();
		this.invalidBackgroundColor = getBackground();
		this.invalidForegroundColor = Color.RED;
		this.validBorder = getBorder();
		this.invalidBorder = getBorder();
	}

	public SelfHighlightningValidatingTextField(int columns) {
		this(null, null, columns);
	}

	public SelfHighlightningValidatingTextField(String text, int columns) {
		this(null, text, columns);
	}

	public SelfHighlightningValidatingTextField(String text) {
		this(null, text, 0);
	}

	protected KeyListener createKeyListener() {
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
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

	public void setValidForegroundColor(Color validForegroundColor) {
		this.validForegroundColor = validForegroundColor;
	}

	public void setValidBackgroundColor(Color validBackgroundColor) {
		this.validBackgroundColor = validBackgroundColor;
	}

	public void setInvalidForegroundColor(Color invalidForegroundColor) {
		this.invalidForegroundColor = invalidForegroundColor;
	}

	public void setInvalidBackgroundColor(Color invalidBackgroundColor) {
		this.invalidBackgroundColor = invalidBackgroundColor;
	}

	public Border getValidBorder() {
		return this.validBorder;
	}

	public void setValidBorder(Border validBorder) {
		this.validBorder = validBorder;
	}

	public Border getInvalidBorder() {
		return this.invalidBorder;
	}

	public void setInvalidBorder(Border invalidBorder) {
		this.invalidBorder = invalidBorder;
	}
}
