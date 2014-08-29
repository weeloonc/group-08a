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
			
			String[] words = getInput().readLine().split("\\s");  // spliting line with whitespaces
			
			
			for (int i = 0; i < words.length; i++){
				if (words[i].equals("")) continue;
				q.add(words[i]); //add the words without spaces into the q
			}
			
			for (int j = 0; j < q.size()-1; j++){
				String tempRemovedWord = q.poll();
				String processedWord = "";
				q.add(tempRemovedWord); //add the first word to the back of the q and remove the first word

				for (String s : q){ //iterate the queue and make it into a word
					processedWord += s + " ";
				}

				getOutput().writeLine(processedWord);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			interrupt();
		} 
		
		
	}

}
