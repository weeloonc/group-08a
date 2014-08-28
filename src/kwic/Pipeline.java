package kwic;

import java.util.ArrayList;
import java.util.List;

public class Pipeline {
	
	private InputPump pump;
	private OutputSink sink;
	private List<Filter> filters;
	
	public Pipeline() {
		filters = new ArrayList<Filter>();
	}
	
	public void start() {
		
		new Thread(pump).start();
		new Thread(sink).start();
		
		for (Filter filter : filters) {
			new Thread(filter).start();
		}
	}
	
	// a method that blocks until pipeline is done
	// here pipeline tries to lock on a locking object of output sink
	// here pipeline calls a blocking method of outputsink
	/*public void waitUntilDone? () {
		
	}*/
	
	public void setPump(InputPump pump) {
		this.pump = pump;
	}
	
	public void setSink(OutputSink sink) {
		this.sink = sink;
	}
	
	public void addFilter(Filter filter) {
		filters.add(filter);
	}
	
}
