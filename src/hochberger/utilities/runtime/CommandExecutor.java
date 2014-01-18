package hochberger.utilities.runtime;

import java.io.IOException;

public class CommandExecutor {

	private final Runtime runtime;

	public CommandExecutor() {
		super();
		this.runtime = Runtime.getRuntime();
	}

	public void executeCommand(String command) throws IOException {
		this.runtime.exec(command);
	}
}
