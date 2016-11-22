import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * Store letter frequency in fix size array
 * @author CJC
 *
 */
public class CharArray {
	
	private int[] count;
	private ArrayList<Integer> max;
	
	/**
	 * create a new object
	 */
	public CharArray() {
		count = new int[27];
		count[0] = 0; // this is for the sake of finding the max char.
		max = new ArrayList<>();
		max.add(0);
		
	}
	
	/**
	 * getter method for char frequency
	 * @param c the letter 
	 * @return the frequency of the letter
	 * @throws InvalidCharacterException
	 */
	public int getCharCount(char c) throws InvalidCharacterException {
		if (!Character.isLowerCase(c)) {
			throw new InvalidCharacterException();
		}
		return count[getIndex(c)];
	}
	
	/**
	 * takes in a word and update the total letter frequency
	 * @param word a string for counting letter frequency
	 */
	public void addWord(String word) {
		
		for (int i = 0 ; i < word.length() ; i++) {
			char c = word.charAt(i);
			int id = getIndex(c);
			if (id >= 1 && id <= 26) {
				count[getIndex(c)]++;
			}	
		}
	}
	
	/**
	 * find the most frequent letter.
	 * @param num 
	 * @return the letter with num_th highest frequency 
	 */
	public char getMax(int num) {

		for (int i = max.size(); i <= num ; i++) {			
			int id = 0;		
			for (int k = 1 ; k <= 26 ; k++) {
				
				if (count[k] > count[id] && !max.contains(k)) {
					id = k;
				}				
			}		
			max.add(id);		
		}	
		return toChar(max.get(num));
	}
	
	private char toChar(int index) {
		return (char)(index + (int)'a' - 1 );
	}
	
	private int getIndex(char c) {
		return (int)c - (int)'a' + 1;
	}
	
	public static void main(String[] args) throws InvalidCharacterException, FileNotFoundException {
		// TODO Auto-generated method stub
		String path = "files/tom-sawyer.txt";
		FileReader input = new FileReader(path);
		Splitter sp = new Splitter();
		CharArray ca = new CharArray();
		for(;;){
			try {
				path = sp.nextWord();
				ca.addWord(path);
			} catch (NeedNewLineException e) {

//				System.out.println(path);
				if (!input.hasNextLine()) {
					break;
				}
				sp.readNextLine(input.nextLine());
			}
		}
		ca.getMax(3) ;
		System.out.println(""+""+ ca.getCharCount(ca.getMax(1)));
//		System.out.println(ca.getMax(26) +""+ ca.getCharCount(ca.getMax(26)));
		
	}	

}
