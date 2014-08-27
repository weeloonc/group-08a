package kwic;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Pipe {

	private PipedReader out;
	private PipedWriter in;
	
	public Pipe() throws IOException {
		out = new PipedReader();
		in = new PipedWriter(out);
	}
	
	public String readLine() throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		for (char c = (char) out.read(); c != '\n'; c = (char) out.read()) {
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	public void writeLine(String str) throws IOException {
		in.write(str + "\n");
	}

}