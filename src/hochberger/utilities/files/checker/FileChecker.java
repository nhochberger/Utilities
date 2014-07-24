package hochberger.utilities.files.checker;

import hochberger.utilities.files.checker.aspects.FileAspect;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileChecker {

    private final List<File> files;
    private final List<FileAspect> aspects;

    public FileChecker(final File... files) {
        super();
        this.files = Arrays.asList(files);
        this.aspects = new LinkedList<FileAspect>();
    }

    public void addFileAspect(final FileAspect aspect) {
        this.aspects.add(aspect);
    }

    public boolean check() {
        for (FileAspect aspect : this.aspects) {
            if (!aspect.appliesTo(this.files)) {
                return false;
            }
        }
        return true;
    }
}
