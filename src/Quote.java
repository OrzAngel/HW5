
public class Quote {
	private String quote;
	private int wordLength;
	
	public Quote() {
		quote = "";
		wordLength = 0;
	}
	
	public void addWord(String word) {
		quote += (word+" ");
		wordLength++;
	}

	/**
	 * @return the quote
	 */
	public String getQuote() {
		quote.trim();
		return quote;
	}

	/**
	 * @return the wordLength
	 */
	public int getWordLength() {
		return wordLength;
	}
	
	
}
