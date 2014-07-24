package hochberger.utilities.files.checker.aspects;

import java.io.File;
import java.util.List;

public interface FileAspect {
    public boolean appliesTo(List<File> files);

    public String getErrorDescription();
}
