package kwic;

import java.io.IOException;
import java.util.Set;

public class KwicApp {
	
	private static final String WELCOME_MSG =
			"Test test, type your titles and stop by typing empty line";
	
	public static final String ERROR_MSG = "An error has occurred";

	public static void main(String[] args) {
		
		KwicApp app = new KwicApp();
		
		if (args.length < 1) {
			app.start(null);
			
		} else {
			app.start(args[0]);
		}
	}
	
	public void start(String filename) {
		try {
			Set<String> noiseWords = getNoiseWords(filename);
			Pipeline pipeline = createPipeline(noiseWords);
			
			displayWelcomeMsg(WELCOME_MSG);
			pipeline.start();
			
		} catch (IOException ex) {
			System.err.println(ERROR_MSG);
			System.exit(1);
		}
	}
	
	private Set<String> getNoiseWords(String filename) throws IOException {
		
		if (filename == null) {
			return null;
		}
		
		return new NoiseWordReader().readNoiseWords(filename);
	}
	
	private Pipeline createPipeline(Set<String> noiseWords) throws IOException {
		
		Pipeline pipeline = new Pipeline();
		Pipe[] pipes = new Pipe[5];
		Denoiser denoiser;
		
		for (int i = 0; i < pipes.length; i++) {
			pipes[i] = new Pipe();
		}
		
		pipeline.setPump(new InputPump(pipes[1]));
		pipeline.addFilter(new CircularShifter(pipes[0], pipes[1]));
		denoiser = new Denoiser(pipes[1], pipes[2]);
		denoiser.setNoiseWords(noiseWords);
		pipeline.addFilter(denoiser);
		//pipeline.addFilter(new Capitalizer(pipes[2], pipes[3]));
		pipeline.addFilter(new Alphabetizer(pipes[2], pipes[3]));
		pipeline.setSink(new OutputSink(pipes[3]));
		
		return pipeline;
	}
	
	private void displayWelcomeMsg(String msg) {
		System.out.println(msg);
	}

}
