package kwic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class OutputSink implements Runnable {
	
	private Pipe input;
	
	public OutputSink(Pipe input) {
		this.input = input;
	}

	@Override
	public void run() {
		writeOutput();
	}
	
	private void writeOutput() {
		try {
			PrintWriter pr = new PrintWriter
					(new BufferedWriter(new OutputStreamWriter(System.out)));

			String str = input.readLine();
			while (!str.equals(Pipe.END_OF_INPUT)) {
				pr.println(str);
				str = input.readLine();
			}
			
			pr.close();
			
		} catch (IOException ex) {
			System.err.println(KwicApp.ERROR_MSG);
		}
	}

}
