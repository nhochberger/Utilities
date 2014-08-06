package hochberger.utilities.files.filenamefilter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FilenameEndsWithFilterTest {

    private final static String EXPECTED_SUFFIX = "suffix";
    private final static String UNEXPECTED_SUFFIX = "qwertz";

    @Test
    public void fileEndsWithExpectedString() {
        String filename = "someText_" + EXPECTED_SUFFIX;
        FilenameEndsWithFilter filter = new FilenameEndsWithFilter(
                EXPECTED_SUFFIX);
        assertTrue(filter.accept(new File("none"), filename));
    }

    @Test
    public void fileDoesNotEndsWithExpectedString() {
        String filename = "someText_" + UNEXPECTED_SUFFIX;
        FilenameEndsWithFilter filter = new FilenameEndsWithFilter(
                EXPECTED_SUFFIX);
        assertFalse(filter.accept(new File("none"), filename));
    }

    @Test
    public void fileDoesNotEndsWithButContainsExpectedString() {
        String filename = "someText_" + EXPECTED_SUFFIX + "_someMoreText";
        FilenameEndsWithFilter filter = new FilenameEndsWithFilter(
                EXPECTED_SUFFIX);
        assertFalse(filter.accept(new File("none"), filename));
    }
}
