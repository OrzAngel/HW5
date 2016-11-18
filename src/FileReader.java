//FileReader.java

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class FileReader {
	private File input;
	private Scanner in;

	public FileReader(String path) throws FileNotFoundException{

		input = new File(path);
		in = new Scanner(input);

	}

	public boolean hasNextLine() {
		return in.hasNextLine();
	}

	public String nextLine() {
		return in.nextLine();
	}

	public static void main(String[] args) {
		String path = "files/alice-in-wonderland.txt";
		try {
			FileReader in = new FileReader(path);
			System.out.println(in.nextLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

