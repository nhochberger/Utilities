package hochberger.utilities.application.parameter.checker.aspects;

import hochberger.utilities.application.parameter.checker.ParameterChecker;
import hochberger.utilities.application.parameter.checker.ParameterChecker.ParameterAspect;

import java.util.List;

public class SingleParameter implements ParameterAspect {

    public SingleParameter() {
        super();
    }

    @Override
    public boolean appliesTo(final List<String> args) {
        return 1 == args.size();
    }

    @Override
    public String getErrorDescription() {
        return "Exactly one parameter is required.";
    }
}
