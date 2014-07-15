package hochberger.utilities.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActionListenerHandler {

	private final List<ActionListener> listeners;

	public ActionListenerHandler() {
		super();
		this.listeners = new ArrayList<ActionListener>();
	}

	public void addActionListener(final ActionListener listener) {
		this.listeners.add(listener);
	}

	public void removeActionListener(final ActionListener listener) {
		this.listeners.remove(listener);
	}

	public void invokeActionPerformed(final ActionEvent event) {
		for (final ActionListener listener : this.listeners) {
			listener.actionPerformed(event);
		}
	}

	public void invokeActionPerformed(final Object source, final int id, final String command) {
		invokeActionPerformed(new ActionEvent(source, id, command));
	}
}
