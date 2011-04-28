package hochberger.utilities.eventbus;

import hochberger.utilities.logging.LogToConsole;
import hochberger.utilities.threading.ThreadRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.schneide.framework.gui.swing.EDT;

public class SimpleEventBus implements EventBus {

    private final Map<Class<? extends Event>, List<EventReceiver>> receivers;

    public SimpleEventBus() {
        super();
        this.receivers = new HashMap<Class<? extends Event>, List<EventReceiver>>();
    }

    @Override
    public <TYPE extends Event> void register(final EventReceiver receiver, final Class<TYPE> eventType) {
        LogToConsole.debug("Registering object of type " + receiver.getClass().getSimpleName() + " to receive notifications of type " + eventType.getSimpleName());
        synchronized (this.receivers) {
            List<EventReceiver> list = this.receivers.get(eventType);
            if (null == list) {
                list = new ArrayList<EventReceiver>();
                this.receivers.put(eventType, list);
            }
            list.add(receiver);
        }
    }

    @Override
    public void deregister(final EventReceiver receiver) {
        // TODO Auto-generated method stub
    }

    @Override
    public <TYPE extends Event> void publish(final TYPE event) {
        EDT.never();
        synchronized (this.receivers) {
            List<EventReceiver> currentlyAddressedReceivers = this.receivers.get(event.getClass());
            if (null == currentlyAddressedReceivers) {
                return;
            }
            for (EventReceiver eventReceiver : currentlyAddressedReceivers) {
                eventReceiver.receive(event);
            }
        }
    }

    @Override
    public <TYPE extends Event> void publishFromEDT(final TYPE event) {
        EDT.only();
        ThreadRunner.startThread(new Runnable() {

            @Override
            public void run() {
                publish(event);
            }
        });
    }
}
