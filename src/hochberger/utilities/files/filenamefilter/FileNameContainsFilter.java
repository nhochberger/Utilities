package hochberger.utilities.files.filenamefilter;

import hochberger.utilities.text.Text;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameContainsFilter implements FilenameFilter {

    private final String infix;

    public FileNameContainsFilter(final String infix) {
        super();
        this.infix = infix;
    }

    @Override
    public boolean accept(final File dir, final String name) {
        return Text.emptyIfNull(name).contains(this.infix);
    }

}
