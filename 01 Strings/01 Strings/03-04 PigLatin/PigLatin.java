// Name:   Manav Gagvani
// Date:   August 31st, 2021
import java.util.*;

import javax.sound.sampled.AudioFormat.Encoding;

import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      // part_1_using_pig();
      // part_2_using_piglatenizeFile();
      
      /*  extension only    */
      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }
      // sc.close();	// although this code is unreachable it is simply saying one should close the file
      // in order to avoid a resource leak	
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
      if(s.length() == 0)
         return "";



      String Vowels = vowels;


      String beginningPunct = "";
      String endPunct = "";
      // beginning punct
      int a = 0;
      while(punct.contains(s.substring(a,a+1))) {
         beginningPunct += s.substring(a,a+1);
         a++;
      }
      s = s.substring(a);

      String reverse = new StringBuilder(s).reverse().toString();
      // System.out.println(reverse);
// 
      // ending punct
      int b = 0;
      while(punct.contains(reverse.substring(b,b+1))) {
         endPunct += reverse.substring(b,b+1);
         b++;
      }
      // System.out.println(b);
      reverse = reverse.substring(b);
      // .out.println(reverse);
      s = new StringBuilder(reverse).reverse().toString(); // reverse again
      // System.out.println(s);

      // capitalization

      Boolean firstUppercase = false;
      if(Character.isUpperCase(s.charAt(0))) {
         s = Character.toLowerCase(s.charAt(0)) + s.substring(1);
         firstUppercase = true;
      }

      // Special Case Y
      if(s.charAt(0) == "y".charAt(0)) { // y is not already there
      }  // is treated as consonant

      // if first vowel is y treat as vowel
      else {
         Vowels += "y";
      }


      String newWord = "";
      // Part 1
      int firstVowel = -1;
      for(int i = 0; i < s.length(); i++) {
         if( Vowels.contains(s.substring(i, i+1)) ) {
            firstVowel = i;
            break;
         }
      }
      // System.out.println(firstVowel);
      if(s.indexOf("qu") + 1 == firstVowel && s.indexOf("qu") != -1) { // First vowel is the U in QU
         // replace this u with z temporarily 
         String new_s = s;
         new_s = new_s.replaceAll("u","z");
         // System.out.print("asdff");
         // System.out.println(new_s);
         
         firstVowel = -1;
         for(int i = 0; i < new_s.length(); i++) {
            if( Vowels.contains(new_s.substring(i, i+1)) ) {
               firstVowel = i;
               break;
            }
         }
      

      }

      // No vowel
      if(firstVowel == -1) {
         return "**** NO VOWEL ****";
      }


      newWord = s.substring(firstVowel, s.length()) + s.substring(0, firstVowel); 
      // System.out.println(newWord);

      // Part 2
      if(Vowels.contains(s.substring(0,1))) { // case 1
         newWord += "way";
      }
      else if(!Vowels.contains(s.substring(0,1))) {
         newWord += "ay";
      }
      else {System.out.println("ERROR");}

      if(firstUppercase) {
         newWord = Character.toUpperCase(newWord.charAt(0)) + newWord.substring(1);
      }
      
      // System.out.println();

      newWord = beginningPunct + newWord;
      // System.out.println(newWord);
      // System.out.println(endPunct);
      newWord = newWord + endPunct;

      return newWord;

      
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
      // Shell code was missing this
      sc.close(); // Good to close in order to avoid resource leak
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      while(infile.hasNextLine()) {
         String line = infile.nextLine();
         String[] arr = line.split(" ");
         String new_arr[] = new String[arr.length];
         int i = 0;
         for(String s : arr) {
            new_arr[i] = pig(s);
            i++;
         }
         String out = String.join(" ", new_arr) + "\n";
         outfile.write(out);
      }
      
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";

      final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
      String beginningPunct = "";
      String endPunct = "";
      // beginning punct
      int a = 0;
      while(punct.contains(s.substring(a,a+1))) {
         beginningPunct += s.substring(a,a+1);
         a++;
      }
      s = s.substring(a);

      String reverse = new StringBuilder(s).reverse().toString();

      // ending punct
      int b = 0;
      while(punct.contains(reverse.substring(b,b+1))) {
         endPunct += reverse.substring(b,b+1);
         b++;
      }
      // System.out.println(b);
      reverse = reverse.substring(b);
      // .out.println(reverse);
      s = new StringBuilder(reverse).reverse().toString(); // reverse again
      // System.out.println(s);

      Boolean firstUppercase = false;
      if(Character.isUpperCase(s.charAt(0))) {
         s = Character.toLowerCase(s.charAt(0)) + s.substring(1);
         firstUppercase = true;
      }

      String new_s = new StringBuilder(s).reverse().toString();

      if(firstUppercase) {
         new_s = Character.toUpperCase(new_s.charAt(0)) + new_s.substring(1);
      }

      beginningPunct = new StringBuilder(beginningPunct).reverse().toString();
      endPunct = new StringBuilder(endPunct).reverse().toString();


      new_s = new_s + endPunct;
      new_s = beginningPunct + new_s;
         
      return new_s; 
   }
}
