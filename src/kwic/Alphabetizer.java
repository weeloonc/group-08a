package kwic;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Alphabetizer extends Filter {
	
	private TreeMap<String, Integer> outputStore;
	
	public Alphabetizer(Pipe input, Pipe output) {
		super(input, output);
		outputStore = new TreeMap<String, Integer>();
	}

	@Override
	protected void transform() {
		try {
			String str = getInput().readLine();
			
			while (str != null) {  // let's assume that readline give null to signal nothing to read
				insertIntoOutputStore(str);
				str = getInput().readLine();
			}
			
			outputToPipe();
			
		} catch (IOException ex) {
			
		}
	}
	
	private void insertIntoOutputStore(String str) {
		
		Integer count = outputStore.get(str);
		
		if (count == null) {
			outputStore.put(str, 1);
			
		} else {
			outputStore.put(str, count + 1);
		}
	}
	
	private void outputToPipe() throws IOException {
		for (Map.Entry<String, Integer> entry : outputStore.entrySet()) {
			for (int i = 0; i < entry.getValue(); i++) {
				getOutput().writeLine(entry.getKey());
			}
		}
	}

}
