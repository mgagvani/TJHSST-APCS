// Name:   Manav Gagvani   
// Date:   12-9-21
// This program takes a text file, creates an index (by line numbers)
// for all the words in the file and writes the index
// into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
      keyboard.close(); // Note: Scanner objects should ALWAYS be closed!!! or there could be a resource leak!!!
   }
   
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}

class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
   public DocumentIndex() {
      super();
   }
   public DocumentIndex(int size) {
      super(size);
   }

      
  /** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord().  In this situation, a good way to 
 extract while also skipping punctuation is to use String's split method, 
 e.g., str.split("[., \"!?]")       */
   public void addAllWords(String str, int lineNum) 
   {
      for(String s: str.split("[., \"!?]")) {
         addWord(s, lineNum);
      }
   }
    
   /** calls foundOrInserted, which returns a position.  At that position,  
   updates that IndexEntry's list of line numbers with lineNum. */
   public void addWord(String word, int lineNum)
   {
      int index = foundOrInserted(word);
      get(index).add(lineNum);
   }
        
    /** linear-search this DocumentIndex, comparing word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
      int first = 0;
      int last = size() - 1;
      while(first <= last) {
         int middle = (first + last) / 2;
         if(get(middle).getWord().compareTo(word) == 0) return middle; // returning found pos

         else if(get(middle).getWord().compareTo(word) < 0) last = middle + 1;

         else if(get(middle).getWord().compareTo(word) > 0) last = middle - 1;

         else System.out.println("ERROR");
         // System.out.println(first +  " " + last);
      }
      add(new IndexEntry(word));
      return size() - 1; // returning inserted pos 
   }
}
   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
   private String word;
   private ArrayList<Integer> numsList;
     //constructors
   public IndexEntry(String str) {
      word = str.toUpperCase();
      numsList = new ArrayList<Integer>(0);
   }
   
   
     /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
      if(!(numsList.contains(num))) numsList.add(num);
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      return word;
   }
      
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
      String s = word + " ";
      for(Integer i: numsList) s += i.toString() + ", ";
      return s;
   }


   public int compareTo(IndexEntry o) {
      return getWord().compareTo(o.getWord());
   }
}

