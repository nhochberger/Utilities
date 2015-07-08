package hochberger.utilities.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

public class EnhancedLabel extends JLabel {

    private static final long serialVersionUID = 6123375407479014506L;

    private final int tracking;
    private int leftX;
    private int leftY;
    private int rightX;
    private int rightY;
    private Color leftColor;
    private Color rightColor;

    public EnhancedLabel(final String text, final int tracking) {
        super(text);
        this.tracking = tracking;
    }

    public EnhancedLabel(final String text) {
        this(text, 0);
    }

    public void setLeftShadow(final int x, final int y, final Color color) {
        this.leftX = x;
        this.leftY = y;
        this.leftColor = color;
    }

    public void setRightShadow(final int x, final int y, final Color color) {
        this.rightX = x;
        this.rightY = y;
        this.rightColor = color;
    }

    @Override
    public Dimension getPreferredSize() {
        final String text = getText();
        final FontMetrics fm = this.getFontMetrics(getFont());

        int w = fm.stringWidth(text);
        w += (text.length() - 1) * this.tracking;
        w += this.leftX + this.rightX;
        int h = fm.getHeight();
        h += this.leftY + this.rightY;
        return new Dimension(w, h);
    }

    @Override
    public void paintComponent(final Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        final char[] chars = getText().toCharArray();
        final FontMetrics fm = this.getFontMetrics(getFont());

        final int h = fm.getAscent();
        int x = 0;
        for (int i = 0; i < chars.length; i++) {
            final char ch = chars[i];
            final int w = fm.charWidth(ch) + this.tracking;
            g.setColor(this.leftColor);
            g.drawString("" + chars[i], x - this.leftX, h - this.leftY);
            g.setColor(this.rightColor);
            g.drawString("" + chars[i], x + this.rightX, h + this.rightY);
            g.setColor(getForeground());
            g.drawString("" + chars[i], x, h);
            x += w;
        }
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
    }
}
