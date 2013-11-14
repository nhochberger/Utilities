package hochberger.utilities.gui;

public abstract class UndecoratedEDTSafeFrame extends EDTSafeFrame {

	public UndecoratedEDTSafeFrame(String title) {
		super(title);
	}

	@Override
	protected void preBuildHooks() {
		undecorated();
		super.preBuildHooks();
	}

	private void undecorated() {
		frame().setUndecorated(true);
	}
}
