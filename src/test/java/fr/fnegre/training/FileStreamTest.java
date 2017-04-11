package fr.fnegre.training;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

public class FileStreamTest {
	public static final String FILENAME = "file1.txt";
	
	@Test
	public void readFile() throws IOException, URISyntaxException {
		try(Stream<String> s = Files.lines(Paths.get(ClassLoader.getSystemResource("file1.txt").toURI()))) {
			s.forEach(System.out::println);			
		}
	}
}
