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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleEventBusTest {

    @Test
    public void registerAndReceive() {
        EventBus bus = new SimpleEventBus();
        SimpleEventReceiver receiver = new SimpleEventReceiver();
        bus.register(receiver, SimpleTestEvent.class);
        assertEquals(false, receiver.hasReceived());
        bus.publish(new SimpleTestEvent());
        assertEquals(true, receiver.hasReceived());
    }

    protected static class SimpleTestEvent implements Event {

        @Override
        public void performEvent() {
            // TODO Auto-generated method stub
        }
    }

    protected static class SimpleEventReceiver implements EventReceiver {

        protected boolean hasReceived;

        public SimpleEventReceiver() {
            super();
        }

        @Override
        public <TYPE extends Event> void receive(TYPE event) {
            this.hasReceived = true;
        }

        public boolean hasReceived() {
            return this.hasReceived;
        }
    }
}
