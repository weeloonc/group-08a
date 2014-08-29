package kwic;

import java.io.IOException;

public class Capitalizer extends Filter {

	public Capitalizer(Pipe input, Pipe output) {
		super(input, output);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void transform() {

		try {
			
			String line = getInput().readLine();	
			
			if (line.equals(Pipe.END_OF_INPUT)) {
				getOutput().writeLine(Pipe.END_OF_INPUT);
				interrupt();
				return;
			} 
			
			//make the first letter upper case
			line = line.substring(0, 1).toUpperCase() + line.substring(1).toLowerCase();
			getOutput().writeLine(line);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			interrupt();
		}

		
	}
}
