package hochberger.utilities.files.checker;

import hochberger.utilities.files.checker.aspects.FileAspect;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileChecker {

    private final List<File> files;
    private final List<FileAspect> aspects;
    private final List<String> errorDescriptions;

    public FileChecker(final File... files) {
        super();
        this.files = Arrays.asList(files);
        this.aspects = new LinkedList<FileAspect>();
        this.errorDescriptions = new LinkedList<String>();
    }

    public void addFileAspect(final FileAspect aspect) {
        this.aspects.add(aspect);
    }

    public boolean check() {
        for (FileAspect aspect : this.aspects) {
            if (!aspect.appliesTo(this.files)) {
                this.errorDescriptions.add(aspect.getErrorDescription());
                return false;
            }
        }
        return true;
    }

    public List<String> getResultDescriptions() {
        if (!this.errorDescriptions.isEmpty()) {
            return this.errorDescriptions;
        }
        return Arrays.asList("Files are okay.");
    }
}
