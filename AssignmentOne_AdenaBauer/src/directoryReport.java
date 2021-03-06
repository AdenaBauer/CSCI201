import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class directoryReport {
	
	ArrayList<File> files1;
	ArrayList<File> files2;

	String smallestF1;
	String largestF1;
	String smallestF2;
	String largestF2;
	
	int totalFileSize1;
	int totalFileSize2;
	
	int numDirectories;
	
	void fileSizes(int n){
		if(n == 0){
			largestF1 = files1.get(0).getName();
			smallestF1 = files1.get(0).getName();
			
			for(int i = 0; i < files1.size(); i++){
				if(!files1.get(i).isDirectory()){
					totalFileSize1 = (int) (totalFileSize1 + files1.get(i).length());
					if(files1.get(i).length() > largestF1.length()){
						largestF1 = files1.get(i).getName();
					}
					if(files1.get(i).length() < smallestF1.length()){
						smallestF1 = files1.get(i).getName();
					}
				}
			}	
		}
			
		/////
		
		if(n == 1){
			largestF2 = files2.get(0).getName();
			smallestF2 = files2.get(0).getName();
			
			for(int i = 0; i < files2.size(); i++){
				if(!files2.get(i).isDirectory()){
					totalFileSize2 = (int) (totalFileSize2 + files2.get(i).length());
					if(files2.get(i).length() > largestF2.length()){
						largestF2 = files2.get(i).getName();
					}
					if(files2.get(i).length() < smallestF2.length()){
						smallestF2 = files2.get(i).getName();
					}
				}
			}
		}
	}
	
	public String sameNameFiles(){
		String sameFiles = "";
		
		for(int i = 0; i < files1.size(); i++){
			String s1 = files1.get(i).getName();
			for(int j = 0; j < files2.size(); j++){
				String s2 = files2.get(j).getName();

				if(s1.equals(s2)){
					sameFiles = sameFiles + " " + s1;
				}
			}
		}
		return sameFiles;	
	}
	
	public void saveFile(){
		
		FileWriter fw;
		PrintWriter pw;
		
		try {
			fw = new FileWriter("Report-files.txt");
			pw = new PrintWriter(fw);
			pw.println("\nDIRECTORY REPORT: \n------------\n");
			pw.println("Number of Files: "+files1.size() + "\n"); //number of files
			fileSizes(0); 
			pw.println("Largest File: " + largestF1 + "\n"); //largest file
			pw.println("Smallest File: " + smallestF1 + "\n"); //smallest file
			pw.println("Average File Size: " + totalFileSize1/(files1.size()) + "\n"); //average file size
			pw.println("\n\n");
			pw.flush();
			
		} 
		catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		} 
		
	}
	
	public void makeReport(){
		//for one file
		System.out.print("\nDIRECTORY REPORT: \n------------\n");
		System.out.print("Number of Files: "+files1.size() + "\n"); //number of files
		fileSizes(0); 
		System.out.print("Largest File: " + largestF1 + "\n"); //largest file
		System.out.print("Smallest File: " + smallestF1 + "\n"); //smallest file
		System.out.print("Average File Size: " + totalFileSize1/(files1.size()) + "\n"); //average file size
		System.out.print("\n\n");
		
		boolean noGoodAnswer = true;

		while(noGoodAnswer){
			
			try{					
				System.out.print("Would you like to save the file (y/n): ");
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String answer = br.readLine();
				
				if(answer.equals("y")){
					saveFile();
					System.out.print("File Saved!\n");

					noGoodAnswer = false;
					break;
				}
				if(answer.equals("n")){
					System.out.print("File not saved! \n");
					noGoodAnswer = false;
					break;
				}
				else{
					System.out.println("Answer not valid. Please state y for yes and n for no. \n");
					continue;
				}
			}
			catch (IOException ioe) {
				System.out.println("Answer not valid. Please state y for yes and n for no. \n");
				continue;
			}	
		}

	}	

	public void saveCompFiles(){
		
		FileWriter fw;
		PrintWriter pw;
		
		try {
			fw = new FileWriter("Report-files.txt");
			pw = new PrintWriter(fw);
			
			pw.println("\nDIRECTORY COMPARISON REPORT: \n--------------------\n");
			pw.println("Files with the Same Name: "+sameNameFiles() + "\n"); // files with same name
			pw.println(" \n* * Side By Side Directory Reports * * \n\n"); //start the side by side
			
			pw.println("Number of Files: "+files1.size() + " | " + files2.size() + "\n"); //number of files
			fileSizes(0); 
			fileSizes(1);
			pw.println("Largest File: " + largestF1 + " | " + largestF2 + "\n"); //largest file
			pw.println("Smallest File: " + smallestF1 + " | " + smallestF2 + "\n"); //smallest file
			pw.println("Average File Size: " + totalFileSize1/(files1.size()) + " | " + totalFileSize2/(files2.size()) + "\n"); //average file size
			pw.println("\n\n");
			pw.flush();
			
		} 
		catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		} 
		
	}
	
	public void compareFiles(){
		System.out.print("\nDIRECTORY COMPARISON REPORT: \n--------------------\n");
		System.out.print("Files with the Same Name: "+sameNameFiles() + "\n"); // files with same name
		System.out.print(" \n* * Side By Side Directory Reports * * \n\n"); //start the side by side
		
		System.out.print("Number of Files: "+files1.size() + " | " + files2.size() + "\n"); //number of files
		fileSizes(0); 
		fileSizes(1);
		System.out.print("Largest File: " + largestF1 + " | " + largestF2 + "\n"); //largest file
		System.out.print("Smallest File: " + smallestF1 + " | " + smallestF2 + "\n"); //smallest file
		System.out.print("Average File Size: " + totalFileSize1/(files1.size()) + " | " + totalFileSize2/(files2.size()) + "\n"); //average file size
		System.out.print("\n\n");
		
		boolean noGoodAnswer = true;

		while(noGoodAnswer){
			
			try{					
				System.out.print("Would you like to save the file (y/n): ");
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String answer = br.readLine();
				
				if(answer.equals("y")){
					saveCompFiles();
					System.out.print("File Saved!");

					noGoodAnswer = false;
					break;
				}
				if(answer.equals("n")){
					System.out.print("File not saved! \n");
					noGoodAnswer = false;
					break;
				}
				else{
					System.out.println("Answer not valid. Please state y for yes and n for no. \n");
					continue;
				}
			}
			catch (IOException ioe) {
				System.out.println("Answer not valid. Please state y for yes and n for no. \n");
				continue;
			}	
		}
	}
	
	public void readDirectory(int n){
		numDirectories = n; 
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		for(int j = 0; j < numDirectories + 1; j++){
			boolean fileDoesntWork = true;
			
			while(fileDoesntWork){
				
				if(n == 0){
					System.out.print("Please enter a directory name or path: ");
				}
				if(n == 1){
					int x = j + 1; 
					System.out.print("Please enter the directory name or path for Directory " + x + ": ");
				}
				
				try{
					BufferedReader br = new BufferedReader(isr);
					String dir_path = br.readLine();
					
					if(new String(dir_path).equals("exit")){
						return;
					}
					if(j == 0){
						
						File f = new File(dir_path);
						files1 = new ArrayList<File>(Arrays.asList(f.listFiles()));

						for(int i = 0; i < files1.size(); i++){
							if(files1.get(i).exists() && files1.get(i).isDirectory()){
								files1.remove(i);
							}
						}						
					}				
					if(j == 1){
						File f2 = new File(dir_path);
						files2 = new ArrayList<File>(Arrays.asList(f2.listFiles()));
						for(int i = 0; i < files2.size(); i++){
							if(files1.get(i).exists() && files1.get(i).isDirectory()){
								files1.remove(i);
							}
						}
					}			
					fileDoesntWork = false;
					
					if(numDirectories == 0){
						makeReport();
					}
					if(numDirectories == 1 && j == 1){
						compareFiles();
					}
				} 
				catch (FileNotFoundException fnfe) {
					System.out.println("File doesn't exist. \n");
					continue;
				} 
				catch (IOException ioe) {
					System.out.println("File contents are not readable.");
					continue;
				}
				catch (NullPointerException npe) {
					System.out.println("File does not exist.");
					continue;
				}
				catch(Exception e){
					System.out.println("File does not exist.");
					continue;	
				}
			}
		}
	}
}
