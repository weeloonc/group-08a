package kwic;

import java.util.List;

public class Pipeline {
	
	private InputPump pump;
	private OutputSink sink;
	private List<Filter> filters;
	
	public void start() {
		
		pump.run();
		sink.run();
		
		for (Filter filter : filters) {
			filter.run();
		}
	}
	
	public void stop() {
		
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
