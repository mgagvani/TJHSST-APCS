// Name: Manav Gagvani
// Date: 1 - 11 - 21

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *")	;
      postfixExp.add("3 4 5 + * 2 - 5 /")	;
      postfixExp.add("8 1 2 * + 9 3 / -")	;
      postfixExp.add("2 3 ^")	;	
      postfixExp.add("20 3 %");		
      postfixExp.add("21 3 %");		
      postfixExp.add("22 3 %");		
      postfixExp.add("23 3 %");	
      postfixExp.add("5 !")	;
      postfixExp.add("1 1 1 1 1 + + + + !");		
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Double> stack = new Stack<Double>();
      for(String part: postfixParts) {
         if(isOperator(part) == false) {
            stack.push(Double.parseDouble(part));
         }
         else if(isOperator(part) == true) {
            if(part.equals("!")){
               Double right = stack.pop();
               stack.push(eval(right, 0, part));
            }
            else {
               Double right = stack.pop();
               Double left = stack.pop();
               stack.push(eval(left, right, part));
            }
         }
         else {
            System.out.println("Error");
         }
      }
      // Pop final result
      return stack.pop();
   
   }
   
   public static double eval(double a, double b, String ch)
   {
      if(ch.equals("+")) {
         return (a+b);
      }
      else if(ch.equals("-")) {
         return (a-b);
      }
      else if(ch.equals("*")) {
         return (a*b);
      }
      else if(ch.equals("/")) {
         return (a/b);
      }
      else if(ch.equals("%")) {
         return (a%b);
      }
      else if(ch.equals("^")) {
         return Math.pow(a, b);
      }
      else if(ch.equals("!")){
         return factorial(a);
      }
      else {
         System.out.println("ERROR! NOT ALLOWED TO HAVE THIS OPERATOR!!!");
         return 0;
      }
   }
   
   public static boolean isOperator(String op)
   {
      return operators.contains(op);
   }

   public static double factorial(double n) {
      if (n == 0) return 1;
      else return(n * factorial(n-1));
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/