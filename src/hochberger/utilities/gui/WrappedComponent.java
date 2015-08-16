package hochberger.utilities.gui;

import java.awt.Component;

public abstract class WrappedComponent<TYPE extends Component> {

    private boolean isBuilt;
    private TYPE component;

    public WrappedComponent() {
        super();
        this.isBuilt = false;
    }

    /**
     * implement this method in subclass. remember to set the result component
     * via setComponent(...)
     */
    protected abstract void buildComponent();

    public final void build() {
        if (this.isBuilt) {
            return;
        }
        this.isBuilt = true;
        buildComponent();
    }

    protected void setComponent(final TYPE component) {
        this.component = component;
    }

    protected TYPE component() {
        return this.component;
    }

    public TYPE getComponent() {
        return component();
    }
}
