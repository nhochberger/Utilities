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

	public void addActionListener(ActionListener listener) {
		this.listeners.add(listener);
	}

	public void removeActionListener(ActionListener listener) {
		this.listeners.remove(listener);
	}

	public void invokeActionPerformed(ActionEvent event) {
		for (ActionListener listener : this.listeners) {
			listener.actionPerformed(event);
		}
	}
}
