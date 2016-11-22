import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * store quotes in an array list
 * @author CJC
 *
 */
public class QuoteArray {
	
	private ArrayList<Quote> quoteList;
	private ArrayList<Integer> max;
	private ArrayList<Integer> min;
	
	private boolean alreadyInQuote;
	private Quote currentQuote;
	
	/**
	 * create a new object
	 */
	public QuoteArray() {
		quoteList = new ArrayList<>();
		max = new ArrayList<>();
		min = new ArrayList<>();
		max.add(0);
		min.add(0);
		alreadyInQuote = false;
	}
	
	/**
	 * take in a word, check whether the word belongs to an existing quotation, 
	 * a new quotation or out of the quotation. And then act accordingly  
	 * @param word the word
	 * @param isQuote true if the current word is in an quotation 
	 */
	public void addWord(String word, boolean isQuote) {
		
		if (!isQuote) {
			if (alreadyInQuote) {
				if (!quoteList.contains(currentQuote)){
					quoteList.add(currentQuote);
				}
				alreadyInQuote = false;
			}
			return;
		}
		if (!alreadyInQuote) {
			currentQuote = new Quote();
			alreadyInQuote = true;
		}
		currentQuote.addWord(word);
	}
	
	/**
	 * find the quotation with longest word length
	 * @param num 
	 * @return the num_th longest quotation
	 */
	public Quote getMax(int num) {
		
		quoteList.add(Quote.minLength());
		
		for (int i = max.size() ; i <= num; i++) {
			
			int id = quoteList.size()-1;
			for (int k = 0 ; k < quoteList.size() - 1 - 1 ; k++) {
				if (quoteList.get(k).getWordLength() > quoteList.get(id).getWordLength() && !max.contains(k)) {
					id = k;
				}
			}
			max.add(id);
		}
		
		quoteList.remove(quoteList.size() - 1);
		return quoteList.get(max.get(num));
	}
	
	/**
	 * find the quotation with shortest word length
	 * @param num
	 * @return the num_th shortest quotation
	 */
	public Quote getMin(int num) {
		
		quoteList.add(Quote.maxLength());
		
		for (int i = min.size() ; i <= num; i++) {
			
			int id = quoteList.size()-1;
			for (int k = 0 ; k < quoteList.size() - 1 - 1 ; k++) {
				if (quoteList.get(k).getWordLength() < quoteList.get(id).getWordLength() && !min.contains(k)) {
					id = k;
				}
			}
			min.add(id);
		}
		
		quoteList.remove(quoteList.size() - 1);
		
		return quoteList.get(min.get(num));
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String path = "files/tom-sawyer.txt";
		FileReader input = new FileReader(path);
		Splitter sp = new Splitter();
		QuoteArray qa = new QuoteArray();
		for(;;){
			try {
				path = sp.nextWord();
				qa.addWord(path,sp.isInQuotation());
			} catch (NeedNewLineException e) {
//				System.out.println(path);
				if (!input.hasNextLine()) {
					break;
				}
				String line = input.nextLine();
				sp.readNextLine(line);
				System.out.println(line);
			}
		}
		for (int i = 1 ; i <=10 ; i++) {
			System.out.println(qa.getMax(i));
		}
		for (int i = 1 ; i <=10 ; i++) {
			System.out.println(qa.getMin(i));
		}

	}
}
