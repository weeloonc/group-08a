package kwic;

import java.io.IOException;
import java.util.Scanner;

public class InputPump implements Runnable {
	
	private Pipe output;
	
	public InputPump(Pipe output) {
		this.output = output;
	}

	@Override
	public final void run() {
		readInput();
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
			System.err.println(KwicApp.ERROR_MSG);
			System.exit(1);
		}
	}

}
