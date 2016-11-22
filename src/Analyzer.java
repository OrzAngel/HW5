import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Take file oath as argument and analyze different books with one analyzer object</br>
 * 
 * contains a FileReader, a Splitter, a hashMap sotring stop list.
 * @author CJC
 *
 */
public class Analyzer {

	private FileReader fileReader;
	private Splitter splitter;
	private CharArray charSet;
	private WordSet wordSet;
	private QuoteArray quoteSet;
	private HashSet<String> stopList;
	
	/**
	 * create a analyzer with stop list
	 * @param stopListPath
	 * @throws FileNotFoundException
	 */
	public Analyzer(String stopListPath) throws FileNotFoundException {
	
		splitter = new Splitter();
		stopList = new HashSet<>();
		stopListInit(stopListPath);
	}
	/**
	 * create the stop list HashSet
	 * @param stopListPath
	 * @throws FileNotFoundException
	 */
	private void stopListInit(String stopListPath) throws FileNotFoundException {

		fileReader = new FileReader(stopListPath);
		while(fileReader.hasNextLine()) {
			stopList.add(fileReader.nextLine());
		}
	}
	
	/**
	 * analyze the book, store its letter frequency, word frequency and quotation info in different data set 
	 */
	private void init() {

		charSet = new CharArray();
		wordSet = new WordSet();
		quoteSet = new QuoteArray();
		
		String word;
		
		for(;;){
			try {
				word = splitter.nextWord();
				charSet.addWord(word);
				wordSet.addWord(word);
				quoteSet.addWord(word, splitter.isInQuotation());
			} catch (NeedNewLineException e) {
				if (!fileReader.hasNextLine()) {
					break;
				}
				splitter.readNextLine(fileReader.nextLine());
			}
		}
	}
	
	/**
	 * Analyze book at the given path and output the result to the display
	 * @param path where the book locates
	 * @throws FileNotFoundException
	 */
	public void analyze(String path) throws FileNotFoundException {
		
		int count = 0;
		ArrayList<String> wordWithStopList = new ArrayList<>();
		
		fileReader = new FileReader(path);
		System.out.println(path);
		System.out.println("============================================================================");
		init();
		System.out.println("Top 10 character");
		for (int i = 1 ; i <= 10 ; i++) {
			try {
				System.out.printf("%c : %d%n",charSet.getMax(i),charSet.getCharCount(charSet.getMax(i)));
			} catch (InvalidCharacterException e) {

			}
		}
		System.out.println("----------------");
		System.out.println("Top 10 words");
		for (int i = 1 ; i <= 10 ; i++) {
			String word = wordSet.getMax(i);
			System.out.printf("%s : %d%n",word,wordSet.getWordCount(word));
			if (!stopList.contains(word)) {
				count++;
				wordWithStopList.add(word);
			}
			
		}
		
		for (int i = 11; count <= 10 && i < wordSet.size() ; i++) {
			String word = wordSet.getMax(i);
			if (!stopList.contains(word)) {
				count++;
				wordWithStopList.add(word);
			}
		}

		System.out.println("----------------");
		System.out.println("Top 10 words with stop List");
		for (String word : wordWithStopList) {
			System.out.printf("%s : %d%n",word,wordSet.getWordCount(word));
		}
		
		System.out.println("----------------");
		System.out.println("longest 10 quotes");
		
		for (int i = 1 ; i <= 10 ; i++) {
			System.out.println(quoteSet.getMax(i));
		}
		
		System.out.println("----------------");
		System.out.println("shortest 10 quotes");
		
		for (int i = 1 ; i <= 10 ; i++) {
			System.out.println(quoteSet.getMin(i));
		}
		
	}
	

	public static void main(String[] args) throws FileNotFoundException {
	}
}
