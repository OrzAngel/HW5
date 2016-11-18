import java.io.FileNotFoundException;

class Splitter {

	private String[] cache;
	private int index;
	private boolean quote; // if the word in process is in quote
	private boolean isSingleQuote; // in case quote in quote.
	private boolean quoteBuffer;

	public Splitter(){
		quote = false;
		isSingleQuote = false;
		quoteBuffer = false;
		
		index = 2;
		cache = new String[1];
		// bad design but for convenience  at line 31 
	}

	public boolean isInQuotation() {
		return quote;
	}
	
	public String nextWord() throws NeedNewLineException {

		quote = quoteBuffer;
		
		for (;;){
			
			if (index >= cache.length) {
				throw new NeedNewLineException();
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
	
	public void readNextLine(String line) {
		line = line.toLowerCase();
		line.replaceAll("--", "\\s");
		cache = line.split("[\\s]+");
		index = 0;
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		
		String path = "files/metamorphosis.txt";
		FileReader input = new FileReader(path);
		Splitter sp = new Splitter();
		
		for(;;){
			try {
				path = sp.nextWord();
			} catch (NeedNewLineException e) {
				if (!input.hasNextLine()) {
					System.out.println(path);
					break;
				}
				sp.readNextLine(input.nextLine());
			}
		}
		
		
	}

}