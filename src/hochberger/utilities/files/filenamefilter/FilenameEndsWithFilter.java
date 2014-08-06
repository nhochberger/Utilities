package hochberger.utilities.files.filenamefilter;

import hochberger.utilities.text.Text;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameEndsWithFilter implements FilenameFilter {

    private final String suffix;

    public FilenameEndsWithFilter(final String suffix) {
        super();
        this.suffix = suffix;
    }

    @Override
    public boolean accept(final File dir, final String name) {
        return Text.emptyIfNull(name).endsWith(this.suffix);
    }

}
