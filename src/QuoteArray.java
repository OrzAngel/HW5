import java.io.FileNotFoundException;
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
				isInQuote = false;
			}
			return;
		}
		if (!isInQuote) {
			currentQuote = new Quote();
			isInQuote = true;
		}
		currentQuote.addWord(word);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

	}
}
