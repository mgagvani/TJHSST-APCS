public class Recursion_Contest
{
   /**
    * Run me and see the output at the bottom of the jGrasp screen. It will say the operation and expected result, and print the recursively generated result beneath it. 
    */
   public static void main(String[] args)
   { 
      System.out.println("Factorial of 7 = 5040");
      System.out.println(factorial(7));    
      System.out.println();

      System.out.println("Sum of Digits of 1302 = 6");
      System.out.println(sumOfDigits(1302)); 
      System.out.println();

      System.out.println("Decimal to Binary of 17 = 10001");
      System.out.println(decimalToBinary(17));
      System.out.println();

      System.out.println("2 ^ 16 = 65536");
      System.out.println(powerOfNumber(2, 16));
      System.out.println();

      System.out.println("GCD of 18 and 24 = 6");
      System.out.println(gcd(18, 24));
      System.out.println();

      System.out.println("Permutations of 'abc': ");
      permutation("abc");
      System.out.println();

      System.out.println("10th Fibonacci Number = 55");
      System.out.println(fiboSeries(10));
      System.out.println();

      System.out.println("APCS is super duper epic --reversed--> cipe repud repus si SCPA");
      reverseSentence("APCS is super duper epic");
      System.out.println('\n');
      
      System.out.println("APCS is super duper epic --25 recursions--> cipe repud repus si SCPA");
      int a = reverseAndCount("APCS is super duper epic");
      System.out.println();
      System.out.println(a + " recursions");
      System.out.println('\n');

      
      
      char[][] map = {{'.', '.', '.', 'T'},
                     {'.', '.', '.', '.'},
                     {'.', '.', '.', '.'}};
      System.out.println("Map: ");
      for(char[] c : map) {
         System.out.println(String.valueOf(c));
      }
      findT(map, 0, 0);
      System.out.println();

      
   }
   
   /****************************************
    * #1. Find factorial of a number 
    *     using recursion
    *    Ex) factorial(4) returns 24 (4*3*2*1)
    *  precondition: n > 0
    ****************************************/
   public static int factorial(int n)
   {
      if(n  > 0) {
         return n * factorial(n - 1);
      }
      else {
         return 1;
      }
   }
   
  /****************************************  
   * #2. Find the sum of digits of a number
   *     using recursion
   *    Ex) 1302 returns 6 (1 + 3 + 0 + 2).
   * precondition: n > 0
   ****************************************/
   public static int sumOfDigits(int num)
   {
      if(num > 0) {
         return num % 10 + sumOfDigits(num / 10);
      }
      else {
         return 0;
      }
   }
    
  /***************************************
   * #3. Convert a number from Decimal 
   *     to Binary using recursion
   *    Ex) 14 is converted to 1110 
   * precondition: n >= 0
   ***************************************/
   public static int decimalToBinary(int dec)
   {
      if(dec == 0) {
         return 0;
      }
      else {
         return ((dec % 2) + (10 * decimalToBinary(dec / 2)));
         // Say we start with 17
         /*
         17 % 2 = 1
         8  % 2 = 0
         4 %  2 = 0
         2 %  2 = 0
         Add 1. 10001. Answer. The 10x in the code is so the digitsare in order
         */
      }
   }     
    
  /*******************************************
   * #4. Calculate Power of a number
   *     using recursion
   *    Ex) powerOfNumber(3, 2) returns 9 (3^2)
   * precondition: base > 0
   ********************************************/
   public static int powerOfNumber(int base, int exp)
   {
      if(exp == 1) {
         return base;
      }
      else {
         return base * powerOfNumber(base, exp - 1);
      }
   }
     
  /********************************************
   * #5. Find GCD(Greatest Common Divisor) of
   *     two numbers usirng recursion
   *   Ex) gcd(4, 6) returns 2
   * precondition: a > 0 and b > 0
   *******************************************/
   public static int gcd(int a, int b)
   {
      if(a == 0) {
         return b;
      }
      else if(b == 0) {
         return a;
      }
      else {
         if(a == b) {
            return b;
         }
         else if(a > b) {
            return gcd(a - b, b);
         }
         else { // b > a
            return gcd(a, b - a);
         }
      }

      // Uses Euclidean Algorithm
   }
      
   /*******************************************
    * #6. Find all permutations of characters
    *     in a String using recursion
    *    Ex) permutation("abc") prints
    *        abc
    *        acb
    *        bac
    *        bca
    *        cab
    *        cba
    * precondition: str has no same character
    *               str.length() > 0
    *******************************************/
   public static void permutation(String str)
   {
      if(str.isEmpty() || str == null) {
         return; // all done
      }
      else {
         permutation(str.toCharArray(), 0); // start recursing through the helper function
      }
   }
   // helper function to recurse which accepts more arguments
   public static void permutation(char[] chars, int currIdx) {
      if(currIdx == (chars.length - 1)) { // length 1 to n, but idx 0 to n-1
         System.out.println(String.valueOf(chars));
      }
      else {
         for(int n = currIdx; n < chars.length; n++) {
            swap(chars, currIdx, n);
            permutation(chars, currIdx + 1); // go to next index
            swap(chars, currIdx, n);
         }
      }
   }
   // another helper function for swapping characters
   private static void swap(char[] chars, int a, int b) {
       char t = chars[a];
       chars[a] = chars[b];
       chars[b] = t;
   }
       
   /*******************************************
    * #7. Find the Fibonacci series on nth term 
    *     using recursion
    *   Ex) fiboSeries(5) returns 12 (1+1+2+3+5)
    * precondition: n > 0
    ********************************************/
   public static int fiboSeries(int n)
   {
      if(n <= 1) { 
         return n;
      }    
      else {
          return fiboSeries(n-1) + fiboSeries(n-2);
      }
      
   } 
        
   /********************************************
    * #8. Print the reversed sentence
    *     using recursion
    *   Ex) "How are you?" -> "you? are How"     
    * precondition: sentence.length() > 0
    ********************************************/
   public static void reverseSentence(String sentence)
   {
      if(sentence == null || sentence.length() == 0) {
         System.out.print("");
      }
      // recursive case
      else {
         reverseSentence(sentence.substring(1));
         System.out.print(sentence.substring(0,1));
      }

   }
        
   /********************************************
    * #9. Print the reversed sentence and return
    *     the number of words using recursion
    *    Ex) "How are you?" -> "you? are How"
    *        and then, the method returns 3
    * precondition: sentence.length() > 0
    *********************************************/
   public static int reverseAndCount(String sentence)
   {
      if(sentence == null || sentence.length() == 0) {
         System.out.print("");
         return 1; // it is still a recursion, even though it did not recurse further.
      }
      // recursive case
      else {
         int a = reverseAndCount(sentence.substring(1));
         System.out.print(sentence.substring(0,1));
         return a + 1;
      }
   }
   
   /************************************************
    * #10. Find 'T' (Treasure) in the given char 
    *      array and print the position of 'T'
    *      using recursion
    *    Ex) char[][] map = {{'.', '.', '.', 'T'},
    *                        {'.', '.', '.', '.'},
    *                        {'.', '.', '.', '.'}}
    *    findT(map, 1, 3) will print "(0, 3)"
    * precondition: map != null
    ************************************************/
   public static void findT(char[][] map, int startRow, int startCol)
   {  
      if(startRow <= map.length - 1 && startRow >= 0 && startCol <= map[0].length - 1 && startCol >= 0 && map[startRow][startCol] != '%') {
         // System.out.println("" + startRow + " " + startCol + " = " + map[startRow][startCol]);
         if(map[startRow][startCol] == 'T') {
            System.out.println("(" + startRow + "," + startCol + ")");
            return;
         }
         else {
            map[startRow][startCol] = '%'; // a DO NOT GO symbol to prevent stack overflow
            findT(map, startRow + 1, startCol);
            findT(map, startRow - 1, startCol);
            findT(map, startRow, startCol + 1);
            findT(map, startRow, startCol - 1);
         }
      }
      else {
         // System.out.println("Not in bounds." + startRow + " " + startCol);
      }
      
      /*
      if(startRow > -1 && startCol > -1 && startRow < map.length - 1 && startCol < map[0].length - 1){ // in bounds
         if(map[startRow][startCol] == 'T') {
            System.out.println("(" + startRow + ", " + startCol);
            return;
         }
         else {
            findT(map, startRow + 1, startCol);
            findT(map, startRow - 1, startCol);
            findT(map, startRow, startCol + 1);
            findT(map, startRow, startCol - 1);
         }
      } 
      */
      /*
      if(map[startRow][startCol]=='T'){
         System.out.print("Location: " + startRow + " " + startCol );
      }
      else {
         if(startCol+1<map[0].length){
             findT(map, startRow,startCol);
         } else if(startRow + 1<map[1].length){
             findT(map, startRow+1,0);
         } else {
             System.out.print("T not found");
         }
     }
     */
   } 
}