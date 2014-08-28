package kwic;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Denoiser extends Filter {
	
	private static final char SPACE_CHAR = ' ';
	
	private Set<String> noiseWords;
	
	public Denoiser(Pipe input, Pipe output) {
		super(input, output);
		noiseWords = new HashSet<String>();
	}

	@Override
	protected void transform() {
		try {
			String str = getInput().readLine();
			String firstWord = getFirstWord(str);
			boolean isEmptyString = str.equals("");
			
			if (!isEmptyString && !isNoiseWord(firstWord)) {
				getOutput().writeLine(str);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
			//maybe should interrupt thread here
		}
	}
	
	private String getFirstWord(String str) {
		
		int firstSpaceIndex = str.indexOf(SPACE_CHAR);
		
		if (firstSpaceIndex == -1) {
			return str;
		}
		
		return str.substring(0, firstSpaceIndex);
	}
	
	private boolean isNoiseWord(String word) {
		return noiseWords.contains(word);
	}
	
	public void setNoiseWords(Set<String> noiseWords) {
		if (noiseWords != null) {
			this.noiseWords = noiseWords;
		}
	}

}
