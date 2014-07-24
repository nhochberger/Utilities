package hochberger.utilities.application.parameter.checker.aspects;

import java.util.List;

public interface ParameterAspect {

    public boolean appliesTo(List<String> args);

    public String getErrorDescription();
}
