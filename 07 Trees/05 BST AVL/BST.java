import java.util.ArrayList;

// Name: Manav Gagvani
// Date: 2-15-22

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

public class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      root = add(root, s);
      size++;
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t == null) return new TreeNode(s, null, null);
      if(s.compareTo((String)t.getValue()) <= 0) t.setLeft(add(t.getLeft(), s));
      else t.setRight(add(t.getRight(), s));
      return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains( String obj)
   {
      return contains(getRoot(), obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null) return false;
      int c = x.compareTo((String)t.getValue());
      if(c == 0) return true;
      if(c < 0) return contains(t.getLeft(), x);
      else return contains(t.getRight(), x);
   }
   
   public String min()
   {
      return min(root, "");
   }
   private TreeNode min(TreeNode t)  //use iteration
   {
      if(t == null) return null;
      while(t.getLeft() != null) t = t.getLeft();
      return t;
   }

   private String min(TreeNode t, String s)  //use iteration
   {
      if(t == null) return null;
      while(t.getLeft() != null) t = t.getLeft();
      return (String)t.getValue();
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null) return null;
      while(t.getRight() != null) t = t.getRight();
      return (String)t.getValue();
   }

   private TreeNode max(TreeNode t, String s)  //recursive helper method
   {
      if(t == null) return null;
      while(t.getRight() != null) t = t.getRight();
      return t;
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";              //process root
      toReturn += toString(t.getRight());  //recurse right
      return toReturn;  
   } 

   private ArrayList<TreeNode> inorderTraverse(TreeNode t) {
      ArrayList<TreeNode> arr = new ArrayList<TreeNode>();
      if(t == null) return arr;
      arr.add(t.getLeft());   //recurse left
      arr.add(t);            //process root
      arr.add(t);           //recurse right
      return arr;  
   }

   public TreeNode getParent(TreeNode root, Comparable childValue) {
      TreeNode parent = null;
      while(root != null && root.getValue() != childValue) {
         parent = root;
         if(((Comparable)root.getValue()).compareTo(childValue) > 0)
           root = root.getLeft();
         else
            root=root.getRight();
      }
      return parent;
   } 

   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {  
      if (current == null)
            return null;
      if (((String)current.getValue()).compareTo(target) > 0) {
         current.setLeft(remove(current.getLeft(), target));
      } 
      else if (((String)current.getValue()).compareTo(target) < 0) {
            current.setRight(remove(current.getRight(), target));
      } 
      else {
         // if nodeToBeDeleted have both children
         if (current.getLeft() != null && current.getRight()!= null) {
            TreeNode temp = current;
            // Finding minimum element from right
            TreeNode minNodeForRight = min(temp.getRight());
            // Replacing current node with minimum node from right subtree
            current.setValue(minNodeForRight.getValue());
            // Deleting minimum node from right now
            current.setRight(remove(current.getRight(), (String)minNodeForRight.getValue()));
         }
         // if nodeToBeDeleted has only left child
         else if (current.getLeft() != null) {
            current = current.getLeft();
         }
         // if nodeToBeDeleted has only right child
         else if (current.getRight() != null) {
            current = current.getRight();
         }
         // if nodeToBeDeleted do not have child (Leaf node)
         else
            current = null;
         }

      return current;

   }



   /*  start the addBalanced methods */
   private int calcBalance(TreeNode t) //height to right minus 
   {                                    //height to left
      return height(t.getRight()) - height(t.getLeft());
   }

   private int height(TreeNode t)   //from TreeLab
   {
      if(t == null) return 0;
      int left = 1 + height(t.getLeft());
      int right = 1 + height(t.getRight());
      return Math.max(left, right);
   }

   public void addBalanced(String value)  
   {
      add(value);
      root = balanceTree(root);   // for an AVL tree. Put in the arguments you want.
   }
   private TreeNode balanceTree(TreeNode t) { //recursive.  Whatever makes sense.
   if(t == null) return null;
   t.setLeft(balanceTree(t.getLeft()));
   t.setRight(balanceTree(t.getRight()));
   if(calcBalance(t) > 1) {
      if(!(calcBalance(t.getRight()) >= 1)) { // equals signs were added
         t = rightLeftRotation(t);
      }
      else {
         t = leftRotation(t);
      }
   }
   else if(calcBalance(t) < -1) {
      if(!(calcBalance(t.getLeft()) <= -1)) { // equals signs were added
         t = leftRightRotation(t);
      }
      else {
         t = rightRotation(t);
      }
   }

   root = t;
   return t;
   }
   // 4 rotation methods
   private TreeNode leftRotation(TreeNode a) {
      TreeNode b = a.getRight();
      a.setRight(b.getLeft());
      b.setLeft(a);
      return b;
   }
   private TreeNode rightRotation(TreeNode c) {
      TreeNode b = c.getLeft();
      c.setLeft(b.getRight());
      b.setRight(c);
      return b;
   }
   private TreeNode leftRightRotation(TreeNode c) {
      c.setLeft(leftRotation(c.getLeft())); // used to be rightRotation
      return rightRotation(c); // used to be left rotation
   }
   private TreeNode rightLeftRotation(TreeNode c) {
      c.setRight(rightRotation(c.getRight()));
      return leftRotation(c);
   }
}