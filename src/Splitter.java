import java.io.FileNotFoundException;
/**
 * takes in a string,
 * split the string into lower case words using white space and double dashes (--)
 * all punctuation are ignored. </br>
 * words with single dash (oh-no, ha-ha) are considered as one word</br>
 * words with single quotation mark (this's, they're) are considered as one word.</br>
 * the quotation inside a quotation are not considered as a new one, they are part of the outside quotation
 * @author CJC
 *
 */
class Splitter {

	private String[] cache;
	private int index;
	private boolean quote; // true if the word in process is in quote
	private boolean isSingleQuote; // true if the current quote starts with a single quotation mark. in case quote in quote.
	private boolean quoteBuffer;// true if the previous word is in quote. 

	/**
	 * create a new object
	 */
	public Splitter(){
		quote = false;
		isSingleQuote = false;
		quoteBuffer = false;
		
		index = 2;
		cache = new String[1];
		// bad design but for convenience 
	}
	
	/**
	 * 
	 * @return true if the word which nextWord() just returned is in quotation
	 */
	public boolean isInQuotation() {
		return quote;
	}
	
	/**
	 * get the next word in the string.
	 * @return the next word
	 * @throws NeedNewLineException when all the words in the string are all extracted
	 */
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
	
	/**
	 * take in a new line of string, transfer it to lower case, replace all the "--" with white space.
	 * Then split the string and restart the index
	 * @param line
	 */
	public void readNextLine(String line) {
		line = line.toLowerCase();
		line = line.replaceAll("--", " ");
		cache = line.split("[\\s]+");
		index = 0;
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		
		String path = "files/metamorphosis.txt";
		FileReader input = new FileReader(path);
		Splitter sp = new Splitter();
		
		sp.readNextLine("places huck--sometimes on islands sometimes_!");
		
		for(;;){
			try {
				path = sp.nextWord();
			} catch (NeedNewLineException e) {
				break;
			}
		}
		
		
	}

}