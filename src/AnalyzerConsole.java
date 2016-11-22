import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * a console program for setting the analyzer
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
	
	private void entry() {
		
		for (;;){
			switch(prompt("Select a command%n"
					+ "1 : set working directory%n"
					+ "2 : starting analyzing%n"
					+ "3 : quit%n")) {
					case "1" :
						setDir();
						break;
					case "2" :
						analyze();
						break;
					case "3" :
						return ;
			}
		}
	}
	
	private String prompt(String message) {
		System.out.printf(message);
		return keyboard.nextLine();
	}
	
	private void analyze() {
		ArrayList<Integer>id = new ArrayList<>();
 		id.add(-1);
		
		System.out.println();
		for (int i = 0 ; i < files.length ; i++){
			String filename = files[i].getName();
			if (filename.contains(".txt")) {
				System.out.printf("%d : %s%n", id.size(),filename);
				id.add(i);
			}
		}
		
		for(;;){
			
			try {
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
	
	private void setDir() {
		
		String pathDir = prompt("entry the path of the new working directory%n");
		
		dir = new File(pathDir);
		files = dir.listFiles();
		System.out.printf("%s/%n",pathDir);
		
		for (File f : files) {
			System.out.println(f.getName());
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
