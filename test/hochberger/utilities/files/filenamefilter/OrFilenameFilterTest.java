package hochberger.utilities.files.filenamefilter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;

import org.junit.Test;

public class OrFilenameFilterTest {

    @Test
    public void bothTrue() {
        FilenameFilter filter1 = new AlwaysTrueFilter();
        FilenameFilter filter2 = new AlwaysTrueFilter();
        OrFilenameFilter andFilter = new OrFilenameFilter(filter1, filter2);
        assertTrue(andFilter.accept(new File("none"), "someName"));
    }

    @Test
    public void bothFalse() {
        FilenameFilter filter1 = new AlwaysFalseFilter();
        FilenameFilter filter2 = new AlwaysFalseFilter();
        OrFilenameFilter andFilter = new OrFilenameFilter(filter1, filter2);
        assertFalse(andFilter.accept(new File("none"), "someName"));
    }

    @Test
    public void firstTrueSecondFalse() {
        FilenameFilter filter1 = new AlwaysTrueFilter();
        FilenameFilter filter2 = new AlwaysFalseFilter();
        OrFilenameFilter andFilter = new OrFilenameFilter(filter1, filter2);
        assertTrue(andFilter.accept(new File("none"), "someName"));
    }

    @Test
    public void firstFalseSecondTrue() {
        FilenameFilter filter1 = new AlwaysFalseFilter();
        FilenameFilter filter2 = new AlwaysTrueFilter();
        OrFilenameFilter andFilter = new OrFilenameFilter(filter1, filter2);
        assertTrue(andFilter.accept(new File("none"), "someName"));
    }
}
