package hochberger.utilities.text;

import java.io.InputStream;
import java.util.Scanner;

public class LoadText {

    private LoadText() {
        super();
    }

    public static String from(final String filePath) {
        StringBuilder result = new StringBuilder();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filePath);
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            result.append(scanner.nextLine());
            result.append(System.getProperty("line.separator"));
        }
        return result.toString();
    }
}
