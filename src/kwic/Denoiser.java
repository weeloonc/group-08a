package kwic;

import java.util.HashSet;
import java.util.Set;

public class Denoiser extends Filter {
	
	private Set<String> noiseWords;
	
	public Denoiser(Pipe input, Pipe output) {
		super(input, output);
		noiseWords = new HashSet<String>();
	}

	@Override
	protected void transform() {
		// TODO Auto-generated method stub

	}
	
	public void setNoiseWords(Set<String> noiseWords) {
		if (noiseWords != null) {
			this.noiseWords = noiseWords;
		}
	}

}
