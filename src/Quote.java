/**
 * represent a quote, store its content and word length
 * @author CJC
 *
 */
public class Quote {
	private String quote;
	private int wordLength;
	
	/**
	 *create an empty quote
	 */
	public Quote() {
		quote = "";
		wordLength = 0;
	}
	
	/**
	 * add a word to this quote
	 * @param word the word to be added to the end of the quote
	 */
	public void addWord(String word) {
		quote += (word+" ");
		wordLength++;
	}

	/**
	 * @return the quote string
	 */
	public String getQuote() {
		return quote;
	}

	/**
	 * @return the word Length
	 */
	public int getWordLength() {
		return wordLength;
	}
	
	/**
	 * create a specific quote whose length is Integer.Max_Value. useful in finding the quote with shortest length
	 * @return 
	 */
	public static Quote maxLength() {
		Quote ans = new Quote();
		ans.wordLength = Integer.MAX_VALUE;
		return ans;
	}
	
	/**
	 * create a specific quote whose length is Integer.Min_Value. useful ing finding the quote with longest length
	 * @return
	 */
	public static Quote minLength() {
		Quote ans = new Quote();
		ans.wordLength = Integer.MIN_VALUE;
		return ans;
	}
	
	public boolean equals(Object o) {
		return (quote.equals(((Quote)o).getQuote()));
	}
	
	public String toString() {
		if (quote.length() <= 100) {
			return String.format("\'%s\'%nword length %d", quote.trim(), wordLength);
		}
		
		return String.format("\'%s ... %s\'%nword length %d", quote.substring(0, 50) , quote.substring(quote.length() - 50) , wordLength);
		
	}
	
}
