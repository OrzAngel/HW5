//FileReader.java

import java.util.scanner;
import java.io.File;
import java.io.FileNotFoundException;

class FileReader {
  private File input;
  private Scanner in;
  
  public FileReader(String path) throws FileNotFoundException{
    
    input = new File(path);
    in = new Scanner(input);
    
  }
  
  public boolean hasNextLine() {
    return in.hasNextLine();
  }
  
  public boolean nextLine() {
    return in.nextLine();
  }
  
}
    
