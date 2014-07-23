package hochberger.utilities.application.parameter.checker;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ParameterChecker {

    private final List<ParameterAspect> aspects;
    private final List<String> params;
    private final List<String> errorDescriptions;

    public ParameterChecker(final String... params) {
        super();
        this.params = Arrays.asList(params);
        this.aspects = new LinkedList<ParameterAspect>();
        this.errorDescriptions = new LinkedList<String>();
    }

    public boolean check() {
        boolean result = true;
        for (ParameterAspect aspect : this.aspects) {
            if (!aspect.appliesTo(this.params)) {
                result = false;
                this.errorDescriptions.add(aspect.getErrorDescription());
            }
        }
        return result;
    }

    public void addParameterAspect(final ParameterAspect aspect) {
        this.aspects.add(aspect);
    }

    public List<String> getResultDescription() {
        if (!this.errorDescriptions.isEmpty()) {
            return this.errorDescriptions;
        }
        return Arrays.asList("Parameters are okay.");
    }

    public static interface ParameterAspect {

        public boolean appliesTo(List<String> args);

        public String getErrorDescription();
    }
}
