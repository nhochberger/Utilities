package hochberger.utilities.eventbus;

public interface EventReceiver {
	
	public <TYPE extends Event> void receive(TYPE event);
}
