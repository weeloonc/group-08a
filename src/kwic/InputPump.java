package kwic;

import java.io.IOException;
import java.util.Scanner;

public class InputPump implements Runnable {
	
	private volatile boolean isRunning;
	private Pipe output;
	
	public InputPump(Pipe output) {
		this.output = output;
	}

	@Override
	public final void run() {
		isRunning = true;
		readInput();
	}
	
	public final void interrupt() {
		isRunning = false;
	}
	
	private void readInput() {
		try {
			Scanner sc = new Scanner(System.in);
			boolean hasNextLine = true;
			
			while (hasNextLine) {
				String line = sc.nextLine();
				
				if (line.equals(Pipe.END_OF_INPUT)) {
					output.writeLine(Pipe.END_OF_INPUT);
					hasNextLine = false;
					
				} else {
					output.writeLine(line);
				}
			}
			
			sc.close();
			output.closeWriter();
			
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
