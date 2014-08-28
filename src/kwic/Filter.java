package kwic;

public abstract class Filter implements Runnable {

	private volatile boolean isRunning;
	private Pipe input;
	private Pipe output;
	
	public Filter(Pipe input, Pipe output) {
		this.input = input;
		this.output = output;
	}
	
	protected abstract void transform();

	@Override
	public final void run() {
		
		isRunning = true;
		
		while (isRunning) {
			transform();
		}
	}
	
	public final void interrupt() {
		isRunning = false;
	}
	
	protected final Pipe getInput() {
		return input;
	}
	
	protected final Pipe getOutput() {
		return output;
	}

}
