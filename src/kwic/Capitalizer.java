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
			String[] words = line.split("\\s");  // spliting line with whitespaces
			
			//make the first letter upper case
			words[0] = words[0].substring(0, 1).toUpperCase() + words[0].substring(1).toLowerCase();
			
			//loop through the words and make all lower case
			StringBuilder processedWord = new StringBuilder();
			processedWord.append(words[0]);
			for (int i = 1; i < words.length; i++){
				words[i].toLowerCase();
				processedWord.append(words[i]);
			}

			int endIndex = processedWord.length() - 1;  // trim last space
			getOutput().writeLine(processedWord.substring(0, endIndex));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			interrupt();
		}

		
	}
}
