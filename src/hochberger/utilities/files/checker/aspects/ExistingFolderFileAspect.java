package hochberger.utilities.files.checker.aspects;

import hochberger.utilities.text.Text;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ExistingFolderFileAspect extends FileAspectAdapter {

    private final List<String> errorFiles;

    public ExistingFolderFileAspect() {
        super();
        this.errorFiles = new LinkedList<>();
    }

    @Override
    protected boolean performCheckOn(final File file) {
        if (!file.isDirectory()) {
            this.errorFiles.add(file.getAbsolutePath());
            return false;
        }
        return true;
    }

    @Override
    public String getErrorDescription() {
        return "The following files cannot be identified as existing folders: "
                + Text.fromIterable(this.errorFiles, ", ");
    }

}
