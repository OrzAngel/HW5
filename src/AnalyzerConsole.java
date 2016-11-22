import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * a crude console program for the analyzer
 * @author CJC
 *
 */
public class AnalyzerConsole {

	Analyzer analyzer;
	File dir;
	File[] files;
	Scanner keyboard;
	
	public AnalyzerConsole() {
		
		keyboard = new Scanner(System.in);
		init();
		entry();
		
	}
	
	/**
	 * initialize the analyzer
	 */
	private void init(){
		
		System.out.println("Analyzer starts initializing");
		String stopList;
		for(;;){
			try {
				stopList = prompt("entry the path of stop list%n");
				analyzer = new Analyzer(stopList);
				break;
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage()); 
			}
		}
		dir = new File(".");
		files = dir.listFiles();
		System.out.println("Analyzer initialized");
		System.out.println("======================================================================");
		
	}
	
	/**
	 * loop forever until user input 0
	 */
	private void entry() {
		
		for (;;){
			switch(prompt("Select a command%n"
					+ "1 : set working directory%n"
					+ "2 : starting analyzing%n"
					+ "0 : quit%n")) {
					case "1" :
						setDir();
						break;
					case "2" :
						analyze();
						break;
					case "0" :
						return ;
			}
		}
	}
	
	/**
	 * prompt the user to enter something
	 * @param message
	 * @return
	 */
	private String prompt(String message) {
		System.out.printf(message);
		return keyboard.nextLine();
	}
	
	/**
	 * prompt the user to choose a book (file with a suffix .txt) to analyze
	 */
	private void analyze() {
		ArrayList<Integer>id = new ArrayList<>();
 		id.add(-1);
		
		for (int i = 0 ; i < files.length ; i++){
			String filename = files[i].getName();
			if (filename.contains(".txt")) {
				id.add(i);
			}
		}
		
		for(;;){
			
			try {
				for (int i = 1; i <id.size() ;i++) {
					String filename = files[id.get(i)].getName();
					System.out.printf("%d : %s%n",i,filename);	
				}
				
				int decision = Integer.parseInt(prompt("Select a book in the working directory you want to analyze, or 0 if you want to quit)%n"));
				if (decision == 0 ){
					return;
				}
				if (decision >= 1 && decision < id.size()) {
					analyzer.analyze(files[id.get(decision)].getPath());
				}
			} catch (NumberFormatException e) {
				
			} catch (Exception e) {
				System.out.println("something bad happened");
				System.out.println(e.getMessage());
				return;
			}
		}
		
	}
	
	/**
	 * prompt the user to set the working directory
	 */
	private void setDir() {
		
		for (;;){
			try{
				String pathDir = prompt("entry the path of the new working directory%n");	
				dir = new File(pathDir);
				files = dir.listFiles();
				if (files == null) {
					System.out.println("invalid directory");
					continue;
				}
				System.out.printf("%s/%n",pathDir);

				for (File f : files) {
					System.out.println(f.getName());
				}
				break;
			} catch (Exception e) {
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnalyzerConsole ac = new AnalyzerConsole();
	}

}
