 // Name:   Manav Gagvani    
 // Date:     12/7/21
 // use for-each loops or iterators, not regular for-loops
import java.io.*;
import java.util.*;
public class IteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("\nArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      replaceNeg(numbers);
      System.out.println("Replace negative numbers: " + numbers);
      deleteZero(numbers);
      System.out.println("Delete zeros: " + numbers);
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }
      // pre: an array of int values 
   	// post: return an ArrayList containing all the values
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> arr = new ArrayList<Integer>();
      for(int item: rawNumbers) {
         arr.add(item);
      }
      return arr;

   }
      // pre: an array of just Strings  
   	// post: return an ArrayList containing all the Strings
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> myList = new ArrayList<String>();
      for ( String str : rawWords )
         myList.add( str );
      return myList;
   }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the number of negative values in the ArrayList a
   public static int countNeg(ArrayList<Integer> a)
   {
      int numNegs = 0;
      for(Integer i: a) if(i < 0) numNegs++;
      return numNegs;
   }
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the average of all values in the ArrayList a
   public static double average(ArrayList<Integer> a)
   {
      double sum = 0;
      double size = (double) a.size();
      for(Integer i: a) sum += (double) i;
      return sum/size;
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: replaces all negative values with 0 
   public static void replaceNeg(ArrayList<Integer> a)
   {
      ListIterator<Integer> it = a.listIterator();
      int temp;
      while(it.hasNext()) {
         temp = it.next();
         if(temp < 0) {
            it.set(0);
         }
      }
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: deletes all zeros in the ArrayList a
   public static void deleteZero(ArrayList<Integer> a)
   {
      ListIterator<Integer> it = a.listIterator();
      int temp;
      while(it.hasNext()) {
         temp = it.next();
         if(temp == 0) {
            it.remove();
         }
      }
   }
      // pre: ArrayList a is not empty and contains only String objects
   	// post: return a new ArrayList without duplicate movie titles
      //       the parameter a is not modified!
		// strategy: start with an empty array and add movies as needed
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {  
      // It should work but doesn't because codepost accepts a certain order 

      // seems to be the best, and most concise solution
      // Set<String> s = new HashSet<String>(a); // similar to how python sets work, no duplicate elements
      // ArrayList<String> myList = new ArrayList<String>();
      // myList.addAll(s);
      // return myList;
      ArrayList<String> n = new ArrayList<String>();
      ListIterator<String> it = a.listIterator();
      boolean dupe;
      String val;
      while(it.hasNext()) {
         dupe = false;
         val = it.next();
         for(String s: n) if(s.equals(val)) dupe = true;
         if(dupe == false) n.add(val);
      }
      return n;
   }
   
}

