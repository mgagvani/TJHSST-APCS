// Name: Manav Gagvani      
// Date: 3-29-22
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
      keyboard.close(); // Note it is very very important to close ALL scanner objects to avoid leaks!

   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {  /*
      TreeNode root = new TreeNode("");

      while(huffLines.hasNextLine()) {
         String s = huffLines.nextLine();
         String token = "" + s.toCharArray()[0];
         String binary = s.substring(1);

         TreeNode temp = root;

         for(Character c: binary.toCharArray()) {
            if(c == '0') { // LEFT CHILD
               if(temp.getLeft() == null) temp.setLeft(new TreeNode(""));
               temp = temp.getLeft();
            }
            else if(c == '1') { // RIGHT CHILD
               if(temp.getRight() == null) temp.setRight(new TreeNode(""));
               temp = temp.getRight();
            }
            else System.out.println("ERROR");
         }
         temp.setValue((String)token);
      }

      return root;
      */
      TreeNode root = new TreeNode("", null, null);
      while(huffLines.hasNext()) {
         String line = huffLines.nextLine();
         String val = line.substring(0, 1);
         String code = line.substring(1).trim();

         TreeNode temp = root;
         
         for(char c: code.toCharArray()) {
            if(c == '0') {
               if(temp.getLeft() == null) temp.setLeft(new TreeNode(""));
               temp = temp.getLeft();
            }
            else {
               if(temp.getRight() == null) temp.setRight(new TreeNode(""));
               temp = temp.getRight();
            }
         }
         temp.setValue(val);

      }
      return root; 
   }
   public static String dehuff(String text, TreeNode root)
   {  /*
      TreeNode temp = root;
      String toRet = "";
      for(Character c: text.toCharArray()) { // each 0 and 1
         if(c == '0') {
            temp = temp.getLeft();
         }
         else if(c == '1') {
            temp = temp.getRight();
         }
         else {
            System.out.println("BIG BIG ERROR!");
         }
         toRet += temp.getValue();
         temp = root;
      }
      return toRet;
      */
      String result = "";
      TreeNode temp = root;

      for(char c: text.toCharArray()) {
         if(c == '0') temp = temp.getLeft();
         else temp = temp.getRight();

         if(temp.getLeft() == null && temp.getRight() == null) {
            result += temp.getValue();
            temp = root;
         }
      }
      return result; 
   }
   
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
