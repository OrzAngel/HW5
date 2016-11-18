import java.io.FileNotFoundException;

class Splitter {

	private FileReader in;
	private String line;
	private String[] cache;
	private int index;
	private boolean quote; // if the word in process is in quote
	private boolean isSingleQuote; // in case quote in quote.
	private boolean quoteBuffer;
	private boolean hasNextWord;

	public Splitter(String path) throws FileNotFoundException {
		in = new FileReader(path);
		quote = false;
		isSingleQuote = false;
		quoteBuffer = false;
		hasNextWord = true;
		readNextLine();
	}

	public boolean isInQuotation() {
		return quote;
	}
	
	public boolean hasNextWord() {
		return hasNextWord;
	}

	public String nextWord() throws NoNextWordException {

		quote = quoteBuffer;
		
		for (;;){
			
			if (index >= cache.length) {
				if (!in.hasNextLine()) {
					throw new NoNextWordException();
				}
				readNextLine();
			}

			String temp = cache[index];
			index++;
			
			if (temp.length() < 1) {
				continue;
			}
			
			int i = 0;
			int j = temp.length() - 1;

			while (i <= j && !Character.isLetter(temp.charAt(i))) {

				if (temp.charAt(i) == '\'' && !quote) {
					quote = true;
					quoteBuffer = true;
					isSingleQuote = true;
				}

				if (temp.charAt(i) == '\"' && !quote) {
					quote = true;
					quoteBuffer = true;
					isSingleQuote = false;
				}	

				i++;
			}

			if (i > j) {
				continue;
			}
			
			while (!Character.isLetter(temp.charAt(j))) {

				if (temp.charAt(j) == '\'' && quote && 	isSingleQuote) {
					quoteBuffer = false;
				}

				if (temp.charAt(j) == '\"' && quote && !isSingleQuote) {
					quoteBuffer = false;

				}				

				j--;
			}

			return temp.substring(i, j+1);
		}

	}
	
	private void readNextLine() {
		line = in.nextLine().toLowerCase();
		line.replaceAll("--", "\\s");
		cache = line.split("[\\s]+");
		index = 0;
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		
		String path = "files/les-mis.txt";
		Splitter in = new Splitter(path);
		
		for(;;){
			try {
				path = in.nextWord();
			} catch (NoNextWordException e) {
				// TODO Auto-generated catch block
				System.out.println(path);
				break;
			}
		}
		
		
	}

}