package hochberger.utilities.application.session;

import org.apache.log4j.Logger;

public abstract class SessionBasedObject {

	private final BasicSession session;

	protected SessionBasedObject(final BasicSession session) {
		super();
		this.session = session;
	}

	public BasicSession session() {
		return session;
	}

	public Logger logger() {
		return session().getLogger();
	}
}
