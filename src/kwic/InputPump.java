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
		
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		
		while (line != "") {
			try {
				output.writeLine(line);
				line = sc.nextLine();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}

}
