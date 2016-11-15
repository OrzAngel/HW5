
class NextWord {
  
  private FileReader in;
  private String[] cache;
  private index;
  private quote;
  
  public Nextword(String path) throws FileNotFoundException {
    in = new FileReader(path);
    quote = false;
    cache = {};
    index = 0;
    line = "";
  }
  
  public boolean isInQuotation() {
    return quote;
  }
  
  public String nextWord() {
    
    if (index == cache.length) {
      nextLine();
    }
    
    return cache[index++];
    
  }
  
  private void nextLine() {
    cache = in.nextLine().split("\\s");
    index = 0;
  }

}