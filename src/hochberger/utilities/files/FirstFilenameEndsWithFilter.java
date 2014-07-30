package hochberger.utilities.files;

import hochberger.utilities.text.Text;

import java.io.File;
import java.io.FilenameFilter;

public class FirstFilenameEndsWithFilter implements FilenameFilter {

    private final String suffix;
    private boolean searchFinished;

    public FirstFilenameEndsWithFilter(final String suffix) {
        super();
        this.suffix = suffix;
        this.searchFinished = false;
    }

    @Override
    public boolean accept(final File dir, final String name) {
        if (this.searchFinished) {
            return false;
        }
        boolean matchFound = Text.emptyIfNull(name).endsWith(this.suffix);
        if (matchFound) {
            this.searchFinished = true;
        }
        return matchFound;
    }

}
