package hochberger.utilities.files.filenamefilter;

import java.io.File;
import java.io.FilenameFilter;

public class AlwaysTrueFilter implements FilenameFilter {

    public AlwaysTrueFilter() {
        super();
    }

    @Override
    public boolean accept(final File dir, final String name) {
        return true;
    }
}
