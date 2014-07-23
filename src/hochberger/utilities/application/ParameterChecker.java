package hochberger.utilities.application;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ParameterChecker {

    private final List<ParameterAspect> aspects;
    private final List<String> params;

    public ParameterChecker(final String... params) {
        super();
        this.params = Arrays.asList(params);
        this.aspects = new LinkedList<ParameterAspect>();
    }

    public boolean check() {
        for (ParameterAspect aspect : this.aspects) {
            if (!aspect.appliesTo(this.params)) {
                return false;
            }
        }
        return true;
    }

    public void addParameterAspect(final ParameterAspect aspect) {
        this.aspects.add(aspect);
    }

    public static interface ParameterAspect {

        public boolean appliesTo(List<String> args);
    }
}
