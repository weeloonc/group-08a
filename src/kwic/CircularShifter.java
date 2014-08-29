package kwic;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class CircularShifter extends Filter {
	 
	private Queue<String> q; 

	public CircularShifter(Pipe input, Pipe output) {
		super(input, output);
		q =  new LinkedList<String>();
	}

	@Override
	protected void transform() {
		try {
			
			q.clear();
			
			String line = getInput().readLine();
			
			if (line.equals(Pipe.END_OF_INPUT)) {
				getOutput().writeLine(Pipe.END_OF_INPUT);
				interrupt();
				return;
			} 
			
			String[] words = line.split("\\s");  // spliting line with whitespaces
			
			for (int i = 0; i < words.length; i++){
				if (words[i].equals("")) continue;
				q.add(words[i]); //add the words without spaces into the q
			}
			
			for (int j = 0; j < q.size(); j++){
				String tempRemovedWord = q.poll();
				StringBuilder processedWord = new StringBuilder();
				q.add(tempRemovedWord); //add the first word to the back of the q and remove the first word

				for (String s : q){ //iterate the queue and make it into a word
					processedWord.append(s);
					processedWord.append(" ");
				}

				int endIndex = processedWord.length() - 1;  // trim last space
				getOutput().writeLine(processedWord.substring(0, endIndex));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			interrupt();
		} 
		
		
	}

}
