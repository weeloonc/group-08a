package kwic;

import java.io.IOException;
import java.util.Set;

public class KwicApp {

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
			Set<String> noiseWords = null;
			
			if (filename != null) {
				noiseWords = getNoiseWords(filename);
			}
			
			// prints some message
			
			Pipeline pipeline = createPipeline(noiseWords);
			//pipeline.start();
			
			// wait for pipeline to finish
			// pipeline.waitForDone
			// pipeline.stop()
			
		} catch (IOException ex) {
			System.err.println("An error has occurred.");
			System.exit(1);
		}
	}
	
	private Set<String> getNoiseWords(String filename) throws IOException {
		return new NoiseWordReader().readNoiseWords(filename);
	}
	
	private Pipeline createPipeline(Set<String> noiseWords) throws IOException {
		
		Pipeline pipeline = new Pipeline();
		Pipe[] pipes = new Pipe[5];
		Denoiser denoiser;
		
		for (int i = 0; i < pipes.length; i++) {
			pipes[i] = new Pipe();
		}
		
		pipeline.setPump(new InputPump(pipes[0]));
		//pipeline.addFilter(new CircularShifter(pipes[0], pipes[1]));
		denoiser = new Denoiser(pipes[1], pipes[2]);
		denoiser.setNoiseWords(noiseWords);
		pipeline.addFilter(denoiser);
		//pipeline.addFilter(new Capitalizer(pipes[2], pipes[3]));
		//pipeline.addFilter(new Alphabetizer(pipes[3], pipes[4]));
		pipeline.setSink(new OutputSink(pipes[4]));
		
		return pipeline;
	}

}
