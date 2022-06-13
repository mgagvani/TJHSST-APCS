//String Contest
// Name:
// Period: 

import java.util.Scanner;

public class StringContest
{
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      int testCase = -1;
      do{
         System.out.println("\n"+
            "1. Print Duplicate Characters from a given String\n" +
            "2. Check if two given strings are anagrams of each other\n" +
            "3. Reverse and print a given String\n" +
            "4. Return the number of vowels of a given String\n" +
            "5. Count occurence of a word in a given String and return it\n" +
            "6. Print all permutations of a given String\n" +
            "7. Given 3 strings, check if the third is the valid shuffle of first and second\n" +
            "8. Accept a String and a character to be removed and return the changed string.");  
      
         System.out.print("\nWhich case do you want to test? (1-8): ");
         testCase = keyboard.nextInt();
         keyboard.nextLine();
         switch(testCase)
         {
            case 1: case 3: case 4: case 5: case 6: case 8: 
               System.out.print("Type an input string: ");
               String phrase = keyboard.nextLine();
               switch(testCase)
               {
                  case 1: printDuplicateCharacters(phrase); 
                     break;
                  case 3: printReverse(phrase); 
                     break;
                  case 4: System.out.println(countNumberOfVowels(phrase)); 
                     break;
                  case 5: System.out.print("Type a word to count: ");
                     String word = keyboard.next();
                     System.out.println(countOccurenceOfAWord(phrase, word)); 
                     break;
                  case 6: printAllPermutations(phrase); 
                     break;
                  case 8: System.out.print("Type a character to be removed: ");
                     char ch = keyboard.next().charAt(0);
                     System.out.println(removeCharacters(phrase, ch)); 
                     break;
               }
               break;
            case 2: 
               System.out.print("Type the first String: ");
               String one = keyboard.nextLine();
               System.out.print("Type the second String: ");
               String two = keyboard.nextLine();
               if(checkAnagrams(one, two))
                  System.out.println("Anagrams!");
               else
                  System.out.println("Not anagrams!");
               break;
            case 7: 
               System.out.print("Type the first word: ");
               String first = keyboard.next();
               System.out.print("Type the second word: ");
               String second = keyboard.next();
               System.out.print("Type the third word: ");
               String third = keyboard.next();
               if(checkValidShuffle(first, second, third))
                  System.out.println("Valid Shuffle!");
               else
                  System.out.println("Invalid Shuffle!");
               break;
         }
      }while(testCase > 0 && testCase < 9);
   }
  
   /***************************************************************
   * #1.
   * pre-condition: phrase.length() > 0
   * post-condition: print duplicate characters from a given String, 
   *  for example if String is "Java" then program should print "a".
   *  if String has no duplicate, print an empty String.
   *  if String has multiple duplicate characters, print all.
   *  For example, if String is "Programming" then program should
   *  print "g, r, m".
   ****************************************************************/
   public static void printDuplicateCharacters(String phrase)
   {
   
   }
   
   /***********************************************************************
   * #2.
   * pre-condition: one.length() > 0 and two.length() > 0
   * post-condition: check if two given strings are anagrams of Each
   *  other. Two strings are anagrams if they are written using the same 
   *  exact letters, ignoring space, punctuation and capitalization. 
   *  Each letter should have the same count in both strings. 
   *  For example, Army and Mary are anagram of each other.
   ***********************************************************************/
   public static boolean checkAnagrams(String one, String two)
   {
      // remove spaces
      one = one.trim();
      two = two.trim(); // TODO TEST ME OUT
      // remove punctuation
      one = one.replaceAll("\\p{Punct}", ""); // replace punct using regular expression
      two = two.replaceAll("\\p{Punct}", "");
      // make everything lowercase
      one = one.toLowerCase();
      two = two.toLowerCase();
      


   }
   
   /********************************************************
   * #3
   * pre-condition: phrase.length() > 0
   * post-condition: reverse and print the given String.
   *********************************************************/
   public static void printReverse(String phrase)
   {
      return new StringBuilder(phrase).reverse().toString(); // TODO TEST ME

   }
   
   /*******************************************************************
   * #4
   * pre-condition: phrase.length() > 0
   * post-condition: return the number of vowels of the input String.
   ********************************************************************/
   public static int countNumberOfVowels(String phrase)
   {
      return -9999;
   }
   
   /******************************************************************
   * #5
   * pre-condition: phrase.length() > 0 and word.length() > 0
   * post-condition: return the number of occurence of a word in phrase
   *  ignoring capitalization. For example, if phrase is "Java in VA" 
   *  and word is "va", it returns 2.
   *******************************************************************/   
   public static int countOccurenceOfAWord(String phrase, String word)
   {
      return -9999;
   }
   
   /*******************************************************************
   * #6
   * pre-condition: phrase.length() > 1
   * post-condition: print all permutations of a String, for example, 
   *  the if input is "xyz" then it should print 
   *  "xyz, yzx, zxy, xzy, yxz, zyx".
   ********************************************************************/
   public static void printAllPermutations(String phrase)
   {
      
   }
   
   /**********************************************************************************
   * #7
   * pre-condition: first.length() == second.length() and third.length() > 0
   * post-condition: given 3 strings: first,  second, and  third.  
   *  third String is said to be a shuffle of first and second if it can be 
   *  formed by interleaving the characters of first and second String in 
   *  a way that maintains the left to right ordering of the characters from 
   *  each string. For example, given first = "abc" and second = "def",  
   *  third = "dabecf"  is a valid shuffle since it preserves the character 
   *  ordering of the two strings. So, given these 3 strings write a function 
   *  that detects whether third String is a valid shuffle of first and second String.
   **********************************************************************************/
   public static boolean checkValidShuffle(String first, String second, String third)
   {
      return false;
   }
   
   /*******************************************************************
   * #8
   * pre-condition: phrase.length() > 0
   * post-condition: accept a String and a character to be removed and 
   *  return a String, which doesn't has that character. 
   ********************************************************************/
   public static String removeCharacters(String phrase, char ch)
   {
      String newPhrase = phrase.replace(ch, "");
      return newPhrase; // TODO TEST ME

   }
  
}