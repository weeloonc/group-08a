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
			
			//String[] words = line.split("\\s");  // spliting line with whitespaces
			
			//make the first letter upper case
			System.out.println(line);
			line = line.substring(0, 1).toUpperCase() + line.substring(1).toLowerCase();
			
			//loop through the words and make all lower case
//			StringBuilder processedWord = new StringBuilder();
//			processedWord.append(words[0]);
//			for (int i = 1; i < words.length; i++){
//				words[i].toLowerCase();
//				processedWord.append(words[i]);
//			}
			System.out.println(line);
//			int endIndex = processedWord.length() - 1;  // trim last space
			getOutput().writeLine(line);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			interrupt();
		}

		
	}
}
