package hochberger.utilities.gui.dialog;

import hochberger.utilities.gui.PanelWrapper;
import hochberger.utilities.gui.WrappedComponent;
import hochberger.utilities.text.i18n.DirectI18N;
import hochberger.utilities.text.i18n.I18N;

import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class BasicModalDialog extends WrappedComponent<JDialog> {

    private final I18N cancelText;
    private final I18N title;
    private final I18N text;
    private final I18N commitText;
    private boolean closedByCommit;

    public BasicModalDialog(final I18N title, final I18N text,
            final I18N commitText, final I18N cancelText) {
        super();
        this.title = title;
        this.text = text;
        this.commitText = commitText;
        this.cancelText = cancelText;
        this.closedByCommit = false;
    }

    public BasicModalDialog(final I18N title, final I18N text) {
        this(title, text, new DirectI18N("OK"), new DirectI18N("Cancel"));
    }

    @Override
    protected void buildComponent() {
        final JDialog dialog = new JDialog();
        setComponent(dialog);
        dialog.setModalityType(ModalityType.MODELESS);
        dialog.setAlwaysOnTop(true);
        dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        dialog.setLayout(new MigLayout("", "10[center]10", "10[]5[]15[]10"));
        final JLabel titleLabel = new JLabel(this.title.toString());
        titleLabel.setFont(titleLabel.getFont().deriveFont(40f));
        final JTextArea textArea = new JTextArea(this.text.toString());
        if (480 < this.text.toString().length()) {
            textArea.setSize(new Dimension(640, 480));
        }
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        dialog.add(titleLabel, "wrap");
        dialog.add(textArea, "wrap");
        final JButton commitButton = new JButton(this.commitText.toString());
        commitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                hide();
                BasicModalDialog.this.closedByCommit = true;
            }
        });
        final JButton cancelButton = new JButton(this.cancelText.toString());
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                hide();
                BasicModalDialog.this.closedByCommit = false;
            }
        });
        final JPanel buttonPanel = PanelWrapper
                .wrap(commitButton, cancelButton);
        buttonPanel.setLayout(new MigLayout("", "push[]35[]push", "0[]0"));
        dialog.add(buttonPanel);
        dialog.pack();
        dialog.setResizable(false);
    }

    public void show() {
        if (null == component()) {
            return;
        }
        getComponent().setVisible(true);
    }

    public void hide() {
        if (null == component()) {
            return;
        }
        getComponent().setVisible(false);
    }

    public boolean wasClosedByCommit() {
        return this.closedByCommit;
    }
}
