package hochberger.utilities.files.filenamefilter;

import java.io.File;
import java.io.FilenameFilter;

public class AlwaysFalseFilter implements FilenameFilter {

    public AlwaysFalseFilter() {
        super();
    }

    @Override
    public boolean accept(final File dir, final String name) {
        return false;
    }
}
