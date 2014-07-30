package hochberger.utilities.files.checker.aspects;

import hochberger.utilities.text.Text;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileExistsFileAspect extends FileAspectAdapter {

    private final List<String> missingFiles;

    public FileExistsFileAspect() {
        super();
        this.missingFiles = new LinkedList<>();
    }

    @Override
    public String getErrorDescription() {
        return "The following files do not exist: "
                + Text.fromIterable(this.missingFiles, ", ");
    }

    @Override
    protected boolean performCheckOn(final File file) {
        if (file.exists()) {
            return true;
        }
        this.missingFiles.add(file.getAbsolutePath());
        return false;
    }

}
