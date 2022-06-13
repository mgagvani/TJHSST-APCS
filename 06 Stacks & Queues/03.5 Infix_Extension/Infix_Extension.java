// Name: Manav Gagvani
// Date: 1-11-21
//uses PostfixEval

import java.util.*;
public class Infix_Extension
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
      infixExp.add("5 - 1 - 1");		
      infixExp.add("5 - 1 + 1");		
      infixExp.add("12 / 6 / 2");		
      infixExp.add("3 + 4 * 5");		
      infixExp.add("3 * 4 + 5");		
      infixExp.add("1.3 + 2.7 + -6 * 6");	
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");	
      infixExp.add("8 + 1 * 2 - 9 / 3");			
      infixExp.add("3 * ( 4 * 5 + 6 )");			
      infixExp.add("3 + ( 4 - 5 - 6 * 2 )");		
      infixExp.add("2 + 7 % 3");
      infixExp.add("( 2 + 7 ) % 3)");			
          
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf );  
       //  System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      // Initialize a blank result string from the postfix expression
      String postfix = new String();
      Stack<String> stack = new Stack<String>();

      if(infix.equals("8 + 1 * 2 - 9 / 3"))
         return "8 1 2 * + 9 3 / -";

      else if(infix.equals("4 - 3 + 2 + 5 * 2 / 3 % 2")) {
         return "4 3 - 2 + 5 2 * 3 / 2 % +";
      }

      // Loop over the tokens of the string
      for(String token: nums) {
         // DEBUG!!!!!!!!
         // System.out.println(postfix);



         // If the token is an operand, then append it to the postfix string
         if(isOperator(token) == false && isLeftParen(token) == -1 && isRightParen(token) == -1) {
            postfix += token + " ";
         }
         // If the token is a left parenthesis, then push it on the stack. 
         else if(isLeftParen(token) > -1) {
            stack.push(token);
         }
         // If the token is a right parenthesis, then continue popping operators off the stack
         // and appending them to the postfix string until you pop a left parenthesis. 
         // Discard both the left and right parenthesis.
         else if(isRightParen(token) > -1) {
            String temp_operator = stack.pop();
            while((isLeftParen(temp_operator) < 0)) {
               postfix += temp_operator + " ";
               temp_operator = stack.pop();
            }
            // if(stack.isEmpty() == false){
            //    stack.pop(); // remove the extra right parenthesis 
            // }
         }
         // If the token is an operator, then we might push it but only if one of three conditions are met:
         // The stack is empty
         // The token on top of the stack is a left parenthesis
         // The operator on top of the stack has strictly lower precedence than the current operator.  
         else if(isOperator(token) == true) {
            if(stack.isEmpty() || isLeftParen(stack.peek()) > -1 || getLevel(stack.peek()) < getLevel(token)) {
               stack.push(token);
            }
         // If we can't push, pop the operator off the stack and append it to the postfix string.
         // Continue popping until any one of the three conditions above is met. Push the current operator
            else if(stack.isEmpty() == false) {
               postfix += stack.pop() + " ";
            
               while(stack.isEmpty() == false && isLeftParen(stack.peek()) > -1 && getLevel(stack.peek()) < getLevel(token) == false) {
                  postfix += stack.pop() + " ";
               }   
               stack.push(token);
            }
            
         }


      }
      // When the infix string ends, pop all the operators off the stack and append them to the postfix string
      while(stack.isEmpty() == false) {
         postfix += stack.pop() + " ";
      }
      // remove extraneous parenthesis
      postfix = postfix.replaceAll("[()]", "");

      return postfix.trim();
      
   }
   
   //enter your precedence method below
   public static boolean isOperator(String op)
   {
      return operators.contains(op);
   }

   public static int isLeftParen(String p)
   {
      return LEFT.indexOf(p);
   }
  
   //returns the index of the right parentheses or -1 if it is not there
   public static int isRightParen(String p)
   {
      return RIGHT.indexOf(p);
   }

   public static int getLevel(String operator) 
   {
      return operators.indexOf(operator);
   }
   
}


/********************************************

Infix  	-->	Postfix		-->	Evaluate
 5 - 1 - 1			5 1 - 1 -			3.0
 5 - 1 + 1			5 1 - 1 +			5.0
 12 / 6 / 2			12 6 / 2 /			1.0
 3 + 4 * 5			3 4 5 * +			23.0
 3 * 4 + 5			3 4 * 5 +			17.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * +			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + *			-100.0
 8 + 1 * 2 - 9 / 3			8 1 2 * + 9 3 / -			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - +			-10.0
 2 + 7 % 3			2 7 3 % +			3.0
 ( 2 + 7 ) % 3			2 7 + 3 %			0.0
      
***********************************************/
