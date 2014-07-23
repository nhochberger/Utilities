package hochberger.utilities.application.parameter.checker.aspects;

import hochberger.utilities.application.parameter.checker.ParameterChecker.ParameterAspect;
import hochberger.utilities.text.Text;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ExistingFiles implements ParameterAspect {

    private final List<String> missingFiles;

    public ExistingFiles() {
        super();
        this.missingFiles = new LinkedList<String>();
    }

    @Override
    public boolean appliesTo(final List<String> args) {
        boolean result = true;
        for (String param : args) {
            File file = new File(param);
            if (!file.isFile()) {
                result = false;
                this.missingFiles.add(file.getAbsolutePath());
            }
        }
        return result;
    }

    @Override
    public String getErrorDescription() {
        return "Given filenames contain non existent files: "
                + Text.fromIterable(this.missingFiles, ", ");
    }

}
