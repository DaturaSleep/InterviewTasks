import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task {
	private File count;
	private File dev;
	private File test;

	public Task(File home, File dev, File test, File count) {
		this.count = count;
		this.dev = dev;
		this.test = test;
		ExecutorService service = Executors.newCachedThreadPool();

		while (true) {
			File[] listOfFiles = home.listFiles();

			for (File wrt : listOfFiles)
				service.submit(new MovingTask(wrt));

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	synchronized void addCount(File folder, File file) {
		BufferedWriter bw;
		String data = "File: \"" + file.getName() + "\" Folder: " + folder.getPath() + "\n";

		try {
			List<String> allLines = Files.readAllLines(count.toPath());
			data = (allLines.size() + 1) + ". " + data;
			bw = new BufferedWriter(new FileWriter(count, true));
			bw.write(data);
			bw.flush();
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private class MovingTask implements Runnable {

		private File file;
		private int hour;

		public MovingTask(File file) {
			this.file = file;
			try {
				BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
				String creationTime = attributes.creationTime().toString();
				int i = creationTime.indexOf('T');
				hour = Integer.parseInt(creationTime.substring(i + 1, i + 3));

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			String extension = getExtension(file.getName());
			switch (extension) {
			case "jar":

				if (hour % 2 == 0) {
					moveTo(dev);
					addCount(dev, file);

				} else if (hour % 2 != 0) {
					moveTo(test);
					addCount(test, file);

				}
				break;

			case "xml":
				moveTo(dev);
				addCount(dev, file);
				break;
			}

		}

		private void moveTo(File folder) {
			file.renameTo(new File(folder.getAbsolutePath() + "\\" + file.getName()));
		}

		private String getExtension(String fileName) {
			String extension = "";
			int i = fileName.lastIndexOf('.');
			extension = fileName.substring(i + 1);
			return extension;

		}

	}
}
