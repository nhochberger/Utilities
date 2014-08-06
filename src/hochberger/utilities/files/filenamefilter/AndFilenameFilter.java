package hochberger.utilities.files.filenamefilter;

import java.io.File;
import java.io.FilenameFilter;

public class AndFilenameFilter implements FilenameFilter {

    private final FilenameFilter filter1;
    private final FilenameFilter filter2;

    public AndFilenameFilter(final FilenameFilter filter1,
            final FilenameFilter filter2) {
        super();
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    @Override
    public boolean accept(final File dir, final String name) {
        return this.filter1.accept(dir, name) && this.filter2.accept(dir, name);
    }
}
