import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		File home = new File("HOME");
		File dev = new File("DEV");
		File test = new File("TEST");
		
		home.mkdirs();
		dev.mkdirs();
		test.mkdirs();
		
		File count = new File("HOME/count.txt");
		try {
			count.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Task(home, dev, test, count);
	}
}
