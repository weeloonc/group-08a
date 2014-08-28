package kwic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Pipe {

	public static final String END_OF_INPUT = "";
	private static final char PRINTABLE_CHAR = 32;

	private PipedReader out;
	private PipedWriter in;
	
	public Pipe() throws IOException {
		out = new PipedReader();
		in = new PipedWriter(out);
	}
	
	public String readLine() throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		int c = out.read();
		while (c >= PRINTABLE_CHAR) {
			sb.append((char) c);
			c = out.read();
		}
		
		return sb.toString();
	}
	
	public void writeLine(String str) throws IOException {
		in.write(str + "\n");
		in.flush();
	}
	
	public void closeWriter() throws IOException {
		in.close();
	}

}
