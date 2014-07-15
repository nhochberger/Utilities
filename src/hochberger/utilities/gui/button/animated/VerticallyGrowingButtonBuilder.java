package hochberger.utilities.gui.button.animated;

import hochberger.utilities.text.Text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class VerticallyGrowingButtonBuilder {

    private String text;
    private Image image;
    private int width;
    private int minHeight;
    private int maxHeight;
    private Font font;
    private Color buttonColor;
    private Color textColor;
    private Color textShadowColor;

    private VerticallyGrowingButtonBuilder() {
        super();
        this.text = Text.empty();
        this.image = VerticallyGrowingButton.NO_IMAGE;
        this.width = VerticallyGrowingButton.DEFAULT_WIDTH;
        this.minHeight = VerticallyGrowingButton.DEFAULT_MIN_HEIGHT;
        this.maxHeight = VerticallyGrowingButton.DEFAULT_MAX_HEIGHT;
        this.font = VerticallyGrowingButton.DEFAULT_FONT;
        this.buttonColor = Color.BLACK;
        this.textColor = Color.WHITE;
        this.textShadowColor = Color.GRAY;
    }

    public static VerticallyGrowingButtonBuilder newButtonWithText(
            final String text) {
        return new VerticallyGrowingButtonBuilder().andText(text);
    }

    public static VerticallyGrowingButtonBuilder newButtonWithImage(
            final Image image) {
        return new VerticallyGrowingButtonBuilder().andImage(image);
    }

    public VerticallyGrowingButtonBuilder andText(final String text) {
        this.text = text;
        return this;
    }

    public VerticallyGrowingButtonBuilder andImage(final Image image) {
        this.image = image;
        return this;
    }

    public VerticallyGrowingButtonBuilder andDimensions(final int width,
            final int minHeight, final int MaxHeight) {
        this.width = width;
        this.minHeight = minHeight;
        this.maxHeight = MaxHeight;
        return this;
    }

    public VerticallyGrowingButtonBuilder andFont(final Font font) {
        this.font = font;
        return this;
    }

    public VerticallyGrowingButtonBuilder andColors(final Color buttonColor,
            final Color textColor, final Color textShadowColor) {
        this.buttonColor = buttonColor;
        this.textColor = textColor;
        this.textShadowColor = textShadowColor;
        return this;
    }

    public VerticallyGrowingButton create() {
        return new VerticallyGrowingButton(this.text, this.image,
                this.buttonColor, this.textColor, this.textShadowColor,
                this.width, this.minHeight, this.maxHeight, this.font);
    }
}
