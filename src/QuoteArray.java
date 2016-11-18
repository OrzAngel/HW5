import java.util.ArrayList;

public class QuoteArray {
	
	private ArrayList<Quote> quoteList;
	private boolean isInQuote;
	private Quote currentQuote;
	
	public QuoteArray() {
		quoteList = new ArrayList<>();
		isInQuote = false;
	}
	
	public void addWord(String word, boolean isQuote) {
		
		if (!isQuote) {
			if (isInQuote) {
				quoteList.add(currentQuote);
			}
			return;
		}
		if (!isInQuote) {
			currentQuote = new Quote();
		}
		currentQuote.addWord(word);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
