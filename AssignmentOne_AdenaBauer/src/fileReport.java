
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class fileReport {
	
	String[][] fileSentences = new String[2][1000];
	int lines[] = {0,0}; 
	int numFreq[] = {1,1};
	int numFreqWord[] = {1,1};
	File files[] = new File[2];
	int currMax[] = new int[2];
	int currMin[] = new int[2];
	int longestSent[] = new int[2];
	int shortestSent[]= new int[2];
	String[][] fileWords = new String[2][10000];
	Map<String, Integer> sentenceCount1 = new HashMap<String, Integer>();
	Map<String, Integer> sentenceCount2 = new HashMap<String, Integer>();

	Map<String, Integer> wordFreq1 = new HashMap<String, Integer>();
	Map<String, Integer> wordFreq2 = new HashMap<String, Integer>();
	
	ArrayList<Object> listObject1 = new ArrayList<Object>();
	ArrayList <Object> listObject2 = new ArrayList<Object>();

	int numFiles;
	
	public double fileSize(File f){
		return f.length();
	}
	
	public int characterCount(int n){
		int charCount = 0;
		for(int i = 0; i < lines[n]; i++){
			for(int j = 0; j < fileSentences[n][i].length(); j++){
				if(Character.isLetterOrDigit(fileSentences[n][i].charAt(j))){
					charCount++; 
				}
			}	
		}
		return charCount;
	}
	
	public char mostFreqChar(int n){
		
		char freqChar = fileSentences[n][0].charAt(0);
		Map<Character, Integer> charIntMap = new HashMap<Character, Integer>();
		
		for(int i = 0; i < lines[n]; i++){
			for(int j = 0; j < fileSentences[n][i].length(); j++){
				char x = fileSentences[n][i].charAt(j);

				if(Character.isLetterOrDigit(x)){
					if(charIntMap.containsKey(x)){
						Integer val = charIntMap.get(x) + 1;
						charIntMap.put(x, val);

						if (val > numFreq[n]){
							freqChar = x;
							numFreq[n] = val;

						}
					}
					else{
						charIntMap.put(x, 1);

					}
				}
			}
		}
		return freqChar;
	}
	
	public int wordCount(int n){
		int wordCount = 0;
		
		for(int i = 0; i < lines[n]; i++){
			String words = fileSentences[n][i];
			StringTokenizer st = new StringTokenizer(words);
			wordCount = wordCount + st.countTokens();
			
			while (st.hasMoreTokens()) {
				String t = st.nextToken();
				if(t.length() > 1){
					char last = t.charAt(t.length()-1);
					
					while(!Character.isLetterOrDigit(last) && t.length() > 1 ){
						  t = t.substring(0, t.length()-1);
						  last = t.charAt(t.length()-1);
					}	
				}
				if(n == 0){
					if(!wordFreq1.isEmpty() && wordFreq1.containsKey(t)){
			        	 Integer val = wordFreq1.get(t) + 1;
			        	 wordFreq1.put(t, val);
			        }
			        else{
			        	 wordFreq1.put(t, 1);
			        }
					
				}
				if(n == 1){
					if(!wordFreq2.isEmpty() && wordFreq2.containsKey(t)){
			        	 Integer val = wordFreq2.get(t) + 1;
			        	 wordFreq2.put(t, val);
			        }
			        else{
			        	 wordFreq2.put(t, 1);
			        }
				}
		     }
		}	
		return wordCount;
	}
	
	public String mostFreqWord(int n){
		String s = fileSentences[n][0];
		
		String arr[] = s.split(" ", 2);
		
		String mostUsedWord = arr[0];
		
		if(n == 0){
			for (Map.Entry<String, Integer> entry : wordFreq1.entrySet()) {
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    
			    if(value > numFreqWord[n]){
			    	numFreqWord[n] = value;
			    	mostUsedWord = key;
			    }
			}	
		}
		if(n == 1){
			for (Map.Entry<String, Integer> entry : wordFreq2.entrySet()) {
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    
			    if(value > numFreqWord[n]){
			    	numFreqWord[n] = value;
			    	mostUsedWord = key;
			    }
			}
		}	
		return mostUsedWord;
	}
	
	public int avgWordLength(int n){
		int pos = 0; 
		if(n == 0){
			for (Map.Entry<String, Integer> entry : wordFreq1.entrySet()) {

			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    
			    for(int j = 0; j < value; j++){
			    	fileWords[n][j+pos] = key;
			    }
			    pos = pos + value;
			}		
		}
		
		if(n == 1){
			for (Map.Entry<String, Integer> entry : wordFreq2.entrySet()) {
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    
			    for(int j = 0; j < value; j++){
			    	fileWords[n][j+pos] = key;
			    }
			    pos = pos + value;
			}
		}
		
		int avg = 0;
		int totalLen = 0;
		for(int i = 0; i < wordCount(n); i++){
			totalLen = totalLen + fileWords[n][i].length();
		}
		avg = totalLen/wordCount(n);	
		return avg;
	}
	
	public String longestWord(int n){
		currMax[n] = fileWords[n][0].length();
		String longestWordStr = fileWords[n][0];
		for(int i = 0; i < wordCount(n); i++){
			if (fileWords[n][i].length() > currMax[n]){
				longestWordStr = fileWords[n][i];
				currMax[n] = fileWords[n][i].length();
			}
		}	
		return longestWordStr;
	}
	public String shortestWord(int n){
		currMin[n] = fileWords[n][0].length();
		String shortestWordStr = fileWords[n][0];
		for(int i = 0; i < wordCount(n); i++){
			if (fileWords[n][i].length() < currMin[n]){ 
				shortestWordStr = fileWords[n][i];
				currMin[n] = fileWords[n][i].length();
			}
		}	
		return shortestWordStr;
	}
	
	public int sentenceNum(int n){
		String longSent = fileSentences[n][0];
		
		for(int i = 1; i < lines[n]; i++){
			longSent = longSent + fileSentences[n][i];
		}
		String delims = "[.?!]+";
		String[] tokens = longSent.split(delims);
		
		for(int i = 0; i < tokens.length; i++){
			int val = tokens[i].length();
			
			if(n == 0){
				sentenceCount1.put(tokens[i],val);

			}
			if(n == 1){
				sentenceCount2.put(tokens[i],val);
			}
		}		
		return tokens.length; 
	}
	
	public int avgSentLength(int n){
		int sentenceLength = 0; 
		int counter = 0;
		
		if(n == 0){
			for (Map.Entry<String, Integer> entry : sentenceCount1.entrySet()) {
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    	
			   sentenceLength = sentenceLength + value; 
			   counter++;
			}
			sentenceLength = sentenceLength/counter; 	
		}
		
		if(n == 1){
			for (Map.Entry<String, Integer> entry : sentenceCount2.entrySet()) {
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    	
			   sentenceLength = sentenceLength + value; 
			   counter++;
			}
			sentenceLength = sentenceLength/counter; 
			
		}		
		return sentenceLength; 
	}
	
	public String longestSent(int n){
		String longest = fileSentences[n][0];
		longestSent[n] = 0; 
		
		if(n == 0){
			for (Map.Entry<String, Integer> entry : sentenceCount1.entrySet()) {
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    	
			   if(value > longestSent[n]){
				   longestSent[n] = value;
				   longest = key;
			   }
			}			
		}
		if(n == 1){
			for (Map.Entry<String, Integer> entry : sentenceCount2.entrySet()) {
			    String key = entry.getKey();
			    Integer value = entry.getValue();
			    	
			   if(value > longestSent[n]){
				   longestSent[n] = value;
				   longest = key;
			   }
			}
		}
		
		return longest; 
	}
	public String shortestSent(int n){
		String shortest = fileSentences[n][0];
		shortestSent[n] = longestSent[n]; 
			
		if(n == 0){
			for (Map.Entry<String, Integer> entry : sentenceCount1.entrySet()) {
			    String key = entry.getKey();
				Integer value = entry.getValue();
		 	
			   if(value < shortestSent[n]){
				   shortestSent[n] = value;
				   shortest = key;
			   }
			}
		}
		if(n == 1){
			for (Map.Entry<String, Integer> entry : sentenceCount2.entrySet()) {
			    String key = entry.getKey();
				Integer value = entry.getValue();
		 	
			   if(value < shortestSent[n]){
				   shortestSent[n] = value;
				   shortest = key;
			   }
			}
		}	
		return shortest; 
	}
	
	public String linesInBoth(){
		String sharedLines = "";	
		
		for (Map.Entry<String, Integer> entry : sentenceCount1.entrySet()) {
		    String key1 = entry.getKey();
		    
		    char first = key1.charAt(0);
			while(!Character.isLetterOrDigit(first) && key1.length() > 1 ){
				  key1 = key1.substring(1, key1.length());
				  first = key1.charAt(0);
			}
		    
			for (Map.Entry<String, Integer> entry2 : sentenceCount2.entrySet()) {
			    String key2 = entry2.getKey();
			    
			    char first2 = key2.charAt(0);
				while(!Character.isLetterOrDigit(first2) && key2.length() > 1 ){
					  key2 = key2.substring(1, key2.length());
					  first2 = key2.charAt(0);
				}
			   			    
			    if(key1.equals(key2)){
					sharedLines = sharedLines + key1 + "  ";
			    }

			}
		}
		
		if(sharedLines.equals("")){
			sharedLines = "None.";
		}
		
		return sharedLines;
	}
	
	public String commonWord(){
		String w = "";
		
		Map<String, Integer> commonWordMap = new HashMap <String, Integer>();
		Map<String, Boolean> inBoth = new HashMap <String, Boolean>();

		
		for(int i = 0; i < 2; i ++){
			
			for(int j = 0; j < wordCount(i); j++){
				
				String word = fileWords[i][j];
				
				if(commonWordMap.containsKey(word)){
					
					if(i == 1){
						inBoth.put(word, true);
					}
					
					int x = commonWordMap.get(word) + 1;
					commonWordMap.put(word, x);
				}
				else{
					if(i != 1){
						commonWordMap.put(word, 1);
					}
				}

			}
		}
		int max = 0;
		String maxWord = "";
		for (Map.Entry<String, Integer> entry : commonWordMap.entrySet()) {
		    String key = entry.getKey();
			int val = entry.getValue();
			
			if(inBoth.containsKey(key)){
				if(val > max){
					max = val;
					maxWord = key;
					
				}
			}
		}		    
		
		if(maxWord.equals("")){
			maxWord = "None.";
		}
			
		return maxWord; 
	}
	
	public void saveCompFiles(){
		
		FileWriter fw;
		PrintWriter pw;
		
		try {
			fw = new FileWriter("Report-Compare-files.txt");
			pw = new PrintWriter(fw);
			
			pw.println("\nFILE COMPARISON REPORT: \n---------------------\n");
			pw.println("Lines Shared Between Files: "+linesInBoth() + "\n"); //lines contained in both files
			pw.println("Most Common Word Between Files: " + commonWord() + "\n"); //most common word between files
			pw.println(" \n* * Side By Side File Reports * * \n\n"); //start the side by side
			
			pw.println("File Size: "+ listObject1.get(0)+ " | " + listObject2.get(0) + "\n"); //size of file
			pw.println("Character Count: " + listObject1.get(1)+ " | " + listObject2.get(1) + "\n"); //character count	
			pw.println("Most Frequent Character: " + listObject1.get(2)+ " | " + listObject2.get(2) + "\n"); //most frequent character
			pw.println("Total Word Count: " + listObject1.get(3)+ " | " + listObject2.get(3) + "\n"); //total word count
			pw.println("Most Frequent Word: " + listObject1.get(4)+ " | " + listObject2.get(4) + "\n"); //most frequent word
			pw.println("Average Word Length: " + listObject1.get(5)+ " | " + listObject2.get(5) + "\n"); //average word length
			pw.println("Longest Word: " + listObject1.get(6)+ " | " + listObject2.get(6) + "\n"); //longest word
			pw.println("Shortest Word: " + listObject1.get(7)+ " | " + listObject2.get(7) + "\n"); //shortest word
			pw.println("Number of Sentences: " + listObject1.get(8)+ " | " + listObject2.get(8) + "\n"); //number of sentences
			pw.println("Avg Sent Length: " + listObject1.get(9)+ " | " + listObject2.get(9) + "\n"); //avg sentences length
			pw.println("Longest Sentence: " + listObject1.get(10)+ " | " + listObject2.get(10) + "\n"); //longest sentence
			pw.println("Shortest Sentence: " + listObject1.get(11)+ " | " + listObject2.get(11) + "\n"); //shortest sentence
			pw.println("\n\n");
			
			pw.flush();	
		} 
		catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		} 
	}
	
	public void saveFile(String fileName, File f){
		
		FileWriter fw;
		PrintWriter pw;
		
		try {
			fw = new FileWriter("Report-files.txt");
			pw = new PrintWriter(fw);
			pw.println("\nFILE REPORT: \n------------\n");
			pw.println("File Size: "+fileSize(f)+ "\n"); //size of file
			pw.println("Character Count:" + characterCount(0) + "\n"); //character count	
			pw.println("Most Frequent Character: " + mostFreqChar(0)+ "\n"); //most frequent character
			pw.println("Total Word Count: " + wordCount(0)+ "\n"); //total word count
			pw.println("Most Frequent Word: " + mostFreqWord(0)+ "\n"); //most frequent word
			pw.println("Average Word Length: " + avgWordLength(0)+"\n"); //average word length
			pw.println("Longest Word: " + longestWord(0)+ "\n"); //longest word
			pw.println("Shortest Word: " + shortestWord(0)+ "\n"); //shortest word
			pw.println("Number of Sentences: " + sentenceNum(0)+ "\n"); //number of sentences
			pw.println("Avg Sent Length: " + avgSentLength(0)+ " (Includes Whitespace & Punct)\n"); //avg sentences length
			pw.println("Longest Sentence: " + longestSent(0)+ "\n"); //longest sentence
			pw.println("Shortest Sentence: " + shortestSent(0)+ "\n"); //shortest sentence	
			pw.println("\n\n");
			pw.flush();
			
		} 
		catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		} 
	}
		
	
	public void makeReport(File f) throws IOException{
		//THIS IS ONLY FOR ONE FILE REPORT
		
		System.out.print("\nFILE REPORT: \n------------\n");
		System.out.print("File Size: "+fileSize(f)+ "\n"); //size of file
		System.out.print("Character Count:" + characterCount(0) + "\n"); //character count	
		System.out.print("Most Frequent Character: " + mostFreqChar(0)+ "\n"); //most frequent character
		System.out.print("Total Word Count: " + wordCount(0)+ "\n"); //total word count
		System.out.print("Most Frequent Word: " + mostFreqWord(0)+ "\n"); //most frequent word
		System.out.print("Average Word Length: " + avgWordLength(0)+"\n"); //average word length
		System.out.print("Longest Word: " + longestWord(0)+ "\n"); //longest word
		System.out.print("Shortest Word: " + shortestWord(0)+ "\n"); //shortest word
		System.out.print("Number of Sentences: " + sentenceNum(0)+ "\n"); //number of sentences
		System.out.print("Avg Sent Length: " + avgSentLength(0)+ " (Includes Whitespace & Punct)\n"); //avg sentences length
		System.out.print("Longest Sentence: " + longestSent(0)+ "\n"); //longest sentence
		System.out.print("Shortest Sentence: " + shortestSent(0)+ "\n"); //shortest sentence	
		System.out.print("\n\n");
		
		boolean noGoodAnswer = true;

		while(noGoodAnswer){
			
			try{					
				System.out.print("Would you like to save the file (y/n): ");
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String answer = br.readLine();
				
				if(answer.equals("y")){
					saveFile(f.getName(), f);
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
	public void compareFiles(){
			
			listObject1.add(fileSize(files[0]));
			listObject1.add(characterCount(0));
			listObject1.add(mostFreqChar(0));
			listObject1.add(wordCount(0));
			listObject1.add(mostFreqWord(0));
			listObject1.add(avgWordLength(0));
			listObject1.add(longestWord(0));
			listObject1.add(shortestWord(0));
			listObject1.add(sentenceNum(0));
			listObject1.add(avgSentLength(0));
			listObject1.add(longestSent(0));
			listObject1.add(shortestSent(0));	

			listObject2.add(fileSize(files[1])); //0
			listObject2.add(characterCount(1)); //1
			listObject2.add(mostFreqChar(1)); //2
			listObject2.add(wordCount(1)); //3
			listObject2.add(mostFreqWord(1)); //4
			listObject2.add(avgWordLength(1)); //5
			listObject2.add(longestWord(1)); //6
			listObject2.add(shortestWord(1)); //7
			listObject2.add(sentenceNum(1));//8
			listObject2.add(avgSentLength(1));//9
			listObject2.add(longestSent(1));//10
			listObject2.add(shortestSent(1));//11			
		
		System.out.print("\nFILE COMPARISON REPORT: \n---------------------\n");
		System.out.print("Lines Shared Between Files: "+linesInBoth() + "\n"); //lines contained in both files
		System.out.print("Most Common Word Between Files: " + commonWord() + "\n"); //most common word between files
		System.out.print(" \n* * Side By Side File Reports * * \n\n"); //start the side by side
		
		System.out.print("File Size: "+ listObject1.get(0)+ " | " + listObject2.get(0) + "\n"); //size of file
		System.out.print("Character Count: " + listObject1.get(1)+ " | " + listObject2.get(1) + "\n"); //character count	
		System.out.print("Most Frequent Character: " + listObject1.get(2)+ " | " + listObject2.get(2) + "\n"); //most frequent character
		System.out.print("Total Word Count: " + listObject1.get(3)+ " | " + listObject2.get(3) + "\n"); //total word count
		System.out.print("Most Frequent Word: " + listObject1.get(4)+ " | " + listObject2.get(4) + "\n"); //most frequent word
		System.out.print("Average Word Length: " + listObject1.get(5)+ " | " + listObject2.get(5) + "\n"); //average word length
		System.out.print("Longest Word: " + listObject1.get(6)+ " | " + listObject2.get(6) + "\n"); //longest word
		System.out.print("Shortest Word: " + listObject1.get(7)+ " | " + listObject2.get(7) + "\n"); //shortest word
		System.out.print("Number of Sentences: " + listObject1.get(8)+ " | " + listObject2.get(8) + "\n"); //number of sentences
		System.out.print("Avg Sent Length: " + listObject1.get(9)+ " | " + listObject2.get(9) + "\n"); //avg sentences length
		System.out.print("Longest Sentence: " + listObject1.get(10)+ " | " + listObject2.get(10) + "\n"); //longest sentence
		System.out.print("Shortest Sentence: " + listObject1.get(11)+ " | " + listObject2.get(11) + "\n"); //shortest sentence
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
	
	public void readFile(int n) throws IOException{
		
		numFiles = n;
		InputStreamReader isr = new InputStreamReader(System.in);
			
		for(int i = 0; i < numFiles + 1; i++){
			boolean fileDoesntWork = true;
			while(fileDoesntWork){
				if(numFiles == 0){
					System.out.print("Please enter a file name or path: ");
				}
				if(numFiles == 1){
					int x = i + 1; 
					System.out.print("Please enter the file name or path for file " + x + ": ");
				}
			
				try {
					BufferedReader br = new BufferedReader(isr);
					String file_path = br.readLine();
					
					if(new String(file_path).equals("exit")){
						return;
					}												
					files[i]= new File(file_path);
					FileReader fr = new FileReader(files[i].getAbsolutePath());
					BufferedReader br2 = new BufferedReader(fr);
					boolean endOfFile = false;
					
					int counter = 0; 
					while(endOfFile == false){
						String line = br2.readLine();
						
						if (line == null){
							endOfFile = true;
							lines[i] = counter;
							break;
						}
						
						fileSentences[i][counter] = line;									
						counter++;
					}						
					fileDoesntWork = false;

					if(numFiles == 0){
						makeReport(files[i]);

					}
					if(numFiles == 1 && i == 1){
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
			}	
		}
		return;	
	}
}