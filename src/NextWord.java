import java.io.FileNotFoundException;

class NextWord {

	private FileReader in;
	private String line;
	private int index;
	private boolean quote;

	public NextWord(String path) throws FileNotFoundException {
		in = new FileReader(path);
		quote = false;
		index = 0;
		line = "";
	}

	public boolean isInQuotation() {
		return quote;
	}

	public String nextWord() {
		
		return null;

	}
	//fdsfasfdds
	private void nextLine() {
		line = in.nextLine();
		index = 0;
	}

}