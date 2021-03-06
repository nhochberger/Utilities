/*******************************************************************************
 * Copyright (c) 2011 Nico Hochberger.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     Nico Hochberger - initial API and implementation
 ******************************************************************************/
package hochberger.utilities.eventbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edt.EDT;
import hochberger.utilities.threading.ThreadRunner;

@SuppressWarnings("rawtypes")
public class SimpleEventBus implements EventBus {

    private final Map<Class<? extends Event>, List<EventReceiver>> receivers;

    public SimpleEventBus() {
        super();
        this.receivers = new HashMap<Class<? extends Event>, List<EventReceiver>>();
    }

    @Override
    public <TYPE extends Event> void register(
            final EventReceiver<TYPE> receiver, final Class<TYPE> eventType) {
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

    @SuppressWarnings("unchecked")
    @Override
    public <TYPE extends Event> void publish(final TYPE event) {
        EDT.never();
        synchronized (this.receivers) {
            final List<EventReceiver> currentlyAddressedReceivers = this.receivers
                    .get(event.getClass());
            if (null == currentlyAddressedReceivers) {
                return;
            }
            for (final EventReceiver eventReceiver : currentlyAddressedReceivers) {
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
