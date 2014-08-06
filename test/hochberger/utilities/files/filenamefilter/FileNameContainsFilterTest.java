package hochberger.utilities.files.filenamefilter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FileNameContainsFilterTest {

    private final static String EXPECTED_INFIX = "infix";
    private final static String UNEXPECTED_INFIX = "qwertz";

    @Test
    public void fileContainsExpectedString() {
        String filename = "someText_" + EXPECTED_INFIX + "_someMoreText";
        FileNameContainsFilter filter = new FileNameContainsFilter(
                EXPECTED_INFIX);
        assertTrue(filter.accept(new File("none"), filename));
    }

    @Test
    public void fileDoesNotEndsWithExpectedString() {
        String filename = "someText_" + UNEXPECTED_INFIX + "someMoreText";
        FileNameContainsFilter filter = new FileNameContainsFilter(
                EXPECTED_INFIX);
        assertFalse(filter.accept(new File("none"), filename));
    }

}
