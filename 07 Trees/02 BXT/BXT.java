// Name: Manav Gagvani
// Date: 2-3-22
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Codepost
   {
      return root;
   }
    
   public void buildTree(String str)
   {
     	// Stack<TreeNode> stack = new Stack<TreeNode>();
// 
      // root = new TreeNode(null);
      // 
      // String[] chars = str.split(" ");
// 
      // for(String s: chars) {
      //    if(isOperator(s) == false) {
      //       root = new TreeNode(s);
      //       stack.push(root);
// 
      //    }
      //    if(isOperator(s)) {
      //      root.setLeft(new TreeNode(stack.pop()));
      //      root.setRight(new TreeNode(stack.pop()));
      //      stack.push(root);
      //    }
      // }
      // root = stack.peek();
      Stack<TreeNode> stack = new Stack<TreeNode>();
      String[] strArray = str.split(" ");
      root = new TreeNode(null);

      for(String s: strArray) {
         if(isOperator(s)) {
            root = new TreeNode(s);
            root.setRight(stack.pop());
            root.setLeft(stack.pop());
            stack.push(root);
         }
         else {
            stack.push(new TreeNode(s));
         }
      }
      root = stack.peek();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      // if(t.getLeft().getValue()) { // we are at a node whose children are numbers

      // }
      String temp = (String) (t.getValue());
      if(isOperator(temp)) {
         return computeTerm(temp, evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      }
      return Double.parseDouble(temp);
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+")) return a + b;

      else if(s.equals("-")) return a - b;

      else if(s.equals("*")) return a * b;

      else if(s.equals("/")) return a / b;

      else if(s.equals("^")) return Math.pow(a, b);

      else if(s.equals("%")) return a % b;

      else System.out.println("Unsupported operator: " + s); return Double.MIN_VALUE;
     
   }
   
   private boolean isOperator(String s)
   {
      return operators.contains(s);
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";              //process root
      toReturn += inorderTraverse(t.getRight());  //recurse right
      return toReturn; 
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //process root
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension */
  //  public String inorderTraverseWithParentheses()
  //  {
  //     return inorderTraverseWithParentheses(root);
  //  }
  //  
  //  private String inorderTraverseWithParentheses(TreeNode t)
  //  {
  //     return "";
  //  }
}