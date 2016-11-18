import java.io.FileNotFoundException;
import java.util.HashMap;

public class WordSet {
	
	private HashMap<String, Integer> words = new HashMap<>();
	
	public WordSet() {
		words = new HashMap<>();
	}

	public void addWord(String word) {
		words.put(word,words.getOrDefault(word, 0) + 1);
	}
	
	public int getWordCount(String word) {
		word = word.toLowerCase();
		return words.getOrDefault(word, 0);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String path = "files/metamorphosis.txt";
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
		
		System.out.println(ws.words.keySet().size());
		
	}
	

}
