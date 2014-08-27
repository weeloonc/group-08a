package kwic;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Set;
import java.util.HashSet;

public class NoiseWordReader {
	
	private static final Charset CHARSET = StandardCharsets.UTF_8;
	
	public Set<String> readNoiseWords(String filename) throws IOException {
		
		Set<String> result = new HashSet<String>();
		Path filePath = Paths.get(filename);
		
		if (!Files.exists(filePath)) {
			return result;
		}
		
		try (BufferedReader reader = Files.newBufferedReader(filePath, CHARSET)) {
			String line = reader.readLine();
			while (line != null) {
				result.add(line);
				line = reader.readLine();
			}
		}
		
		return result;
	}
}
