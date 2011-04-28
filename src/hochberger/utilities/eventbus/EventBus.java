package hochberger.utilities.eventbus;

public interface EventBus {

    public <TYPE extends Event> void register(EventReceiver receiver, Class<TYPE> event);

    public void deregister(EventReceiver receiver);

    public <TYPE extends Event> void publishFromEDT(TYPE event);

    public <TYPE extends Event> void publish(TYPE event);
}
