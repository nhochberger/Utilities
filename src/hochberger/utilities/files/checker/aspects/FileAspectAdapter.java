package hochberger.utilities.files.checker.aspects;

import java.io.File;
import java.util.List;

public abstract class FileAspectAdapter implements FileAspect {

    public FileAspectAdapter() {
        super();
    }

    @Override
    public boolean appliesTo(final List<File> files) {
        boolean result = true;
        for (File file : files) {
            result = performCheckOn(file);
        }
        return result;
    }

    protected abstract boolean performCheckOn(File file);
}
