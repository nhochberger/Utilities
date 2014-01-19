package hochberger.utilities.gui.font;

import hochberger.utilities.files.Closer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

	private FontLoader() {
		super();
	}

	public static Font loadFrom(String filePath) {
		InputStream inputStream = ClassLoader
				.getSystemResourceAsStream(filePath);
		Font result = null;
		try {
			result = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Closer.close(inputStream);
		}
		return result;
	}
}
