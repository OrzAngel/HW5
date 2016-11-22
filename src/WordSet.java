import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * store word frequency in HashSet
 * @author CJC
 *
 */
public class WordSet {
	
	private HashMap<String, Integer> words;
	private ArrayList<String> max;
	
	/**
	 * create a new object
	 */
	public WordSet() {

		words = new HashMap<>();
		max = new ArrayList<>();
		max.add("");
		
	}
	
	/**
	 * add a word and update its frequency
	 * @param word
	 */
	public void addWord(String word) {
		words.put(word,words.getOrDefault(word, 0) + 1);
	}
	
	/**
	 * get the frequency of the word
	 * @param word
	 * @return its frequency
	 */
	public int getWordCount(String word) {
		word = word.toLowerCase();
		return words.getOrDefault(word, 0);
	}
	
	public int size() {
		return words.size();
	}
	
	/**
	 * find the word with highest frequency
	 * @param num
	 * @return the num_th top frequent word 
	 */
	public String getMax(int num) {
		
		for (int i = max.size(); i <= num ; i++) {			
			String key = "";		
			for (String word : words.keySet()) {
				
				if (words.get(word) > words.getOrDefault(key,0) && !max.contains(word)) {
					key = word;
				}				
			}		
			max.add(key);		
		}	
		return max.get(num);
	}
	
	/**
	 * wild card
	 * @param reg the special pattern
	 * @return all the words contains the string reg
	 */
	public ArrayList<String> getWordContains(String reg) {
		
		ArrayList<String> ans = new ArrayList<>();
		for (String word : words.keySet()) {
			if (word.contains(reg)) {
				ans.add(word);
			}
		}
		return ans;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String path = "files/tom-sawyer.txt";
		FileReader input = new FileReader(path);
		Splitter sp = new Splitter();
		WordSet ws = new WordSet();
		
		for(;;){
			try {
				path = sp.nextWord();
				ws.addWord(path);
			} catch (NeedNewLineException e) {
				if (!input.hasNextLine()) {
					break;
				}
				sp.readNextLine(input.nextLine());
			}
		}
		
		System.out.println(ws.getMax(9)+""+ws.getWordCount(ws.getMax(9)));
		
	}
	

}
