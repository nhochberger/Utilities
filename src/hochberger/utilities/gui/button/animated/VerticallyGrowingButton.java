package hochberger.utilities.gui.button.animated;

import hochberger.utilities.application.ActionListenerHandler;
import hochberger.utilities.text.Text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.Timer;

import edt.EDT;

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
        GROW(1), SHRINK(-1);

        private final int directionFactor;

        private Direction(final int factor) {
            this.directionFactor = factor;
        }

        public int calculateNextStep(int currentValue, final int step) {
            return currentValue += this.directionFactor * step;
        }

    }

    public VerticallyGrowingButton(final String text, final Color buttonColor,
            final Color textColor, final Color textShadowColor) {
        this(text, NO_IMAGE, buttonColor, textColor, textShadowColor);
    }

    public VerticallyGrowingButton(final Image image, final Color buttonColor,
            final Color textColor, final Color textShadowColor) {
        this(Text.empty(), image, buttonColor, textColor, textShadowColor);
    }

    public VerticallyGrowingButton(final String text, final Image image,
            final Color buttonColor, final Color textColor,
            final Color textShadowColor) {
        this(text, image, buttonColor, textColor, textShadowColor,
                DEFAULT_WIDTH, DEFAULT_MIN_HEIGHT, DEFAULT_MAX_HEIGHT,
                DEFAULT_FONT);
    }

    public VerticallyGrowingButton(final String text, final Image image,
            final Color buttonColor, final Color textColor,
            final Color textShadowColor, final int width, final int minHeight,
            final int maxHeight, final Font font) {
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

    public void addActionListener(final ActionListener listener) {
        this.listeners.addActionListener(listener);
    }

    public void removeActionListener(final ActionListener listener) {
        this.listeners.removeActionListener(listener);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D graphics = (Graphics2D) g.create();
        setButtonColorOn(graphics);
        graphics.setClip(0, 0, this.buttonWidth, this.maxHeight);
        graphics.fillRect(0, this.maxHeight, this.buttonWidth,
                -this.currentHeight);
        drawImageOn(graphics);
        drawTextOn(graphics);
        graphics.dispose();
    }

    private void drawTextOn(final Graphics2D graphics) {
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        setFontOn(graphics);
        final int distanceToLowerBottom = determineBottomMargin();
        final int distanceToLeftBorder = determineLeftMargin();
        graphics.setColor(this.textShadowColor);
        final int shadowOffset = 1;
        graphics.drawString(this.text, (distanceToLeftBorder + shadowOffset),
                distanceToLowerBottom - shadowOffset);
        setFontColorOn(graphics);
        graphics.drawString(this.text, distanceToLeftBorder,
                distanceToLowerBottom);
    }

    // WORKAROUND
    /**
     * Note that this yields only an approximation of the desired value
     * getAscend / 2 may not be correct for some fonts but should yield
     * sufficiently exact values for most fonts
     */
    private int determineBottomMargin() {
        return this.maxHeight
                - ((this.minHeight / 2) - (getFontMetrics(getFont())
                        .getAscent() / 2))
                - ((this.currentHeight - this.minHeight) / 2);
    }

    private int determineLeftMargin() {
        int margin = 10;
        if (NO_IMAGE == this.image) {
            return margin;
        }
        margin += this.image.getWidth(null);
        return margin;
    }

    private void setFontColorOn(final Graphics2D graphics) {
        if (isEnabled()) {
            graphics.setColor(this.textColor);
            return;
        }
        graphics.setColor(Color.GRAY);
    }

    private void setButtonColorOn(final Graphics2D graphics) {
//        if (isEnabled()) {
            graphics.setColor(this.buttonColor);
//            return;
//        }
//        graphics.setColor(Color.LIGHT_GRAY);
    }

    private void drawImageOn(final Graphics2D graphics) {
        if (NO_IMAGE == this.image) {
            return;
        }
        final int imageHeight = this.image.getHeight(null);
        final int bottomMargin = this.maxHeight
                - ((this.currentHeight - this.minHeight) / 2)
                - (3 * imageHeight / 2);
        graphics.drawImage(this.image, 10, bottomMargin, null);
    }

    private void setFontOn(final Graphics2D graphics) {
        if (DEFAULT_FONT == this.textFont) {
            return;
        }
        graphics.setFont(this.textFont);
    }

    private class TimerActionListener implements ActionListener {

        private final TimerListener listener;

        public TimerActionListener(final TimerListener listener) {
            super();
            this.listener = listener;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            this.listener.timerActionPerformed();
        }
    }

    private class GrowingButtonMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(final MouseEvent e) {
            super.mouseClicked(e);
            if (!isEnabled()) {
                return;
            }
            VerticallyGrowingButton.this.listeners.invokeActionPerformed(this,
                    ActionEvent.ACTION_FIRST, Text.empty());
        }

        @Override
        public void mouseEntered(final MouseEvent e) {
            EDT.only();
            super.mouseEntered(e);
            if (!isEnabled()) {
                return;
            }
            VerticallyGrowingButton.this.direction = Direction.GROW;
            VerticallyGrowingButton.this.timer.start();
        }

        @Override
        public void mouseExited(final MouseEvent e) {
            if (!isEnabled()) {
                return;
            }
            super.mouseExited(e);
            VerticallyGrowingButton.this.direction = Direction.SHRINK;
            VerticallyGrowingButton.this.timer.start();
        }
    }

}
