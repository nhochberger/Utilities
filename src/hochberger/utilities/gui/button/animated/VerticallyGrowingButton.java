package hochberger.utilities.gui.button.animated;

import hochberger.utilities.application.ActionListenerHandler;
import hochberger.utilities.text.Text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.Timer;

public class VerticallyGrowingButton extends JComponent implements
		TimerListener {

	public static int DEFAULT_WIDTH = 100;
	public static int DEFAULT_MIN_HEIGHT = 30;
	public static int DEFAULT_MAX_HEIGHT = 75;
	public static Image NO_IMAGE = null;
	public static Font DEFAULT_FONT = null;

	private static final long serialVersionUID = -5405847459642248388L;
	private final int maxHeight;
	private final int minHeight;
	private int currentHeight;
	private final int buttonWidth;
	private final int step = 3;
	private final Timer timer;
	private Direction direction;
	private final ActionListenerHandler listeners;
	private final Color buttonColor;
	private final Color textColor;
	private final Color textShadowColor;
	private final String text;
	private final Font textFont;
	private final Image image;

	private enum Direction {
		GROW(1),
		SHRINK(-1);

		private final int directionFactor;

		private Direction(int factor) {
			this.directionFactor = factor;
		}

		public int calculateNextStep(int currentValue, int step) {
			return currentValue += this.directionFactor * step;
		}

	}

	public VerticallyGrowingButton(String text,
			Color buttonColor,
			Color textColor,
			Color textShadowColor) {
		this(text,
				NO_IMAGE,
				buttonColor,
				textColor,
				textShadowColor);
	}

	public VerticallyGrowingButton(Image image,
			Color buttonColor,
			Color textColor,
			Color textShadowColor) {
		this(Text.empty(),
				image,
				buttonColor,
				textColor,
				textShadowColor);
	}

	public VerticallyGrowingButton(String text,
			Image image,
			Color buttonColor,
			Color textColor,
			Color textShadowColor) {
		this(text,
				image,
				buttonColor,
				textColor,
				textShadowColor,
				DEFAULT_WIDTH,
				DEFAULT_MIN_HEIGHT,
				DEFAULT_MAX_HEIGHT,
				DEFAULT_FONT);
	}

	public VerticallyGrowingButton(String text,
			Image image,
			Color buttonColor,
			Color textColor,
			Color textShadowColor,
			int width,
			int minHeight,
			int maxHeight,
			Font font) {
		super();
		this.listeners = new ActionListenerHandler();
		this.text = text;
		this.textFont = font;
		this.image = image;
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
		this.currentHeight = minHeight;
		this.buttonWidth = width;
		this.buttonColor = buttonColor;
		this.textColor = textColor;
		this.textShadowColor = textShadowColor;
		this.timer = new Timer(4, new TimerActionListener(this));
		this.direction = Direction.GROW;
		addMouseListener(new GrowingButtonMouseListener());
		setSize(this.buttonWidth, this.maxHeight);
		setPreferredSize(getSize());
	}

	@Override
	public void timerActionPerformed() {
		if (this.currentHeight >= this.maxHeight
				&& Direction.GROW == this.direction) {
			this.timer.stop();
			return;
		}
		if (this.currentHeight <= this.minHeight
				&& Direction.SHRINK == this.direction) {
			this.timer.stop();
			return;
		}
		this.currentHeight = this.direction.calculateNextStep(
				this.currentHeight, this.step);
		repaint();
	}

	public void addActionListener(ActionListener listener) {
		this.listeners.addActionListener(listener);
	}

	public void removeActionListener(ActionListener listener) {
		this.listeners.removeActionListener(listener);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g.create();
		setButtonColorOn(graphics);
		graphics.setClip(0, 0, this.buttonWidth, this.maxHeight);
		graphics.fillRect(0, this.maxHeight, this.buttonWidth,
				-this.currentHeight);
		drawImageOn(graphics);
		drawTextOn(graphics);
		graphics.dispose();
	}

	private void drawTextOn(Graphics2D graphics) {
		setFontOn(graphics);
		graphics.setColor(this.textShadowColor);
		graphics.drawString(this.text, 11, this.maxHeight - 9);
		setFontColorOn(graphics);
		graphics.drawString(this.text, 10, this.maxHeight - 10);
	}

	private void setFontColorOn(Graphics2D graphics) {
		if (isEnabled()) {
			graphics.setColor(this.textColor);
			return;
		}
		graphics.setColor(Color.GRAY);
	}

	private void setButtonColorOn(Graphics2D graphics) {
		if (isEnabled()) {
			graphics.setColor(this.buttonColor);
			return;
		}
		graphics.setColor(Color.LIGHT_GRAY);
	}

	private void drawImageOn(Graphics2D graphics) {
		if (NO_IMAGE == this.image) {
			return;
		}
		graphics.drawImage(this.image, 10, 0, null);
	}

	private void setFontOn(Graphics2D graphics) {
		if (DEFAULT_FONT == this.textFont) {
			return;
		}
		graphics.setFont(this.textFont);
	}

	private class TimerActionListener implements ActionListener {

		private final TimerListener listener;

		public TimerActionListener(TimerListener listener) {
			super();
			this.listener = listener;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.listener.timerActionPerformed();
		}
	}

	private class GrowingButtonMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (!isEnabled()) {
				return;
			}
			VerticallyGrowingButton.this.listeners.invokeActionPerformed(this,
					ActionEvent.ACTION_FIRST,
					Text.empty());
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			if (!isEnabled()) {
				return;
			}
			VerticallyGrowingButton.this.direction = Direction.GROW;
			VerticallyGrowingButton.this.timer.start();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (!isEnabled()) {
				return;
			}
			super.mouseExited(e);
			VerticallyGrowingButton.this.direction = Direction.SHRINK;
			VerticallyGrowingButton.this.timer.start();
		}
	}

}
