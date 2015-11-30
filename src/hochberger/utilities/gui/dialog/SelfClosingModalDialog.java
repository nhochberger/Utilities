package hochberger.utilities.gui.dialog;

import hochberger.utilities.text.i18n.I18N;

import java.util.Timer;
import java.util.TimerTask;

public class SelfClosingModalDialog extends BasicModalDialog {

    private final long showingTimeInMilis;

    public SelfClosingModalDialog(final I18N title, final I18N text,
            final I18N commitText, final I18N cancelText,
            final long showingTimeInMilis) {
        super(title, text, commitText, cancelText);
        this.showingTimeInMilis = showingTimeInMilis;
    }

    public SelfClosingModalDialog(final I18N title, final I18N text,
            final long showingTimeInMilis) {
        super(title, text);
        this.showingTimeInMilis = showingTimeInMilis;
    }

    @Override
    public void show() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                hide();
            }
        }, this.showingTimeInMilis);
        super.show();
    }
}
