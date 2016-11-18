import java.io.FileNotFoundException;

public class CharArray {
	
	private int[] count;
	
	public CharArray() {
		count = new int[26];
	}
	
	public int getCharCount(char c) throws InvalidCharacterException {
		if (!Character.isLowerCase(c)) {
			throw new InvalidCharacterException();
		}
		return count[getIndex(c)];
	}
	
	public void addWord(String word) {
		
		for (int i = 0 ; i < word.length() ; i++) {
			char c = word.charAt(i);
			if (Character.isLetter(c)) {
				count[getIndex(c)]++;
			}	
		}
	}
	
	private int getIndex(char c) {
		return (int)c - (int)'a';
	}
	
	public static void main(String[] args) throws InvalidCharacterException, FileNotFoundException {
		// TODO Auto-generated method stub
		String path = "files/metamorphosis.txt";
		FileReader input = new FileReader(path);
		Splitter sp = new Splitter();
		CharArray ca = new CharArray();
		for(;;){
			try {
				path = sp.nextWord();
				ca.addWord(path);
			} catch (NeedNewLineException e) {

				System.out.println(path);
				if (!input.hasNextLine()) {
					break;
				}
				sp.readNextLine(input.nextLine());
			}
		}
		path = "";
	}	

}
