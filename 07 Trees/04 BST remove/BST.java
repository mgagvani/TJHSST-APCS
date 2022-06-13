import java.util.ArrayList;
// Name: Manav Gagvani
// Date: 2-10-22

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);  //BST_AVL
   public void remove(String obj);    
   //public void removeBalanced(String obj); //extra lab Red_Black
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
BST. Implement the remove() method.
Test it with BST_Remove_Driver.java
**********************/
public class BST implements BSTinterface
{
   /*  copy your BST code here  */
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
      // if(t == null) return new TreeNode(s, null, null);
      // TreeNode p = null;
      // TreeNode c = t;
      // while(c != null) {
      //    p = c;
      //    if(s.compareTo((String)t.getValue()) <= 0) {
      //       c = c.getLeft();
      //    }
      //    else c = c.getRight(); 
      // }
      // if(s.compareTo((String)p.getValue()) <= 0) p.setLeft((new TreeNode(s, null, null)));
      // else p.setRight(new TreeNode(s, null, null));
// 
      // return t;
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






      /* -- first try -- 
      // find and search for parent
      TreeNode parent = new TreeNode("__TEMP__");


      boolean currentIsRight = false;
      while(!current.getValue().toString().equals(target)) {
         String comparable = current.getValue().toString();
         parent = current;
         if(comparable.compareTo(target) > 0) {
            current = current.getRight();
            currentIsRight = true;
         }
         else if(comparable.compareTo(target) < 0) {
            current = current.getRight();
            currentIsRight = false;
         }
      }

      // case 1a
      if(current.getRight() == null && current.getLeft() == null) {
         parent.setLeft(null);
         parent.setRight(null);
      }
      // case 1b
      if(getRoot() == current && current.getLeft() == null && current.getRight() == null) {
         return null; // the node "current" is the root of the tree         // 
      }
      // case 2a
      if(current.getRight() != null && current.getLeft() == null) {
         if(currentIsRight) parent.setRight(current.getRight());
         else parent.setLeft(current.getRight());
      }
      // case 2b
      if(current.getLeft() != null && current.getRight() == null) {
         if(currentIsRight) parent.setRight(current.getLeft());
         else parent.setLeft(current.getLeft());
      }
      // case 2c
      if(getRoot() == current && current.getLeft() != null && current.getRight() == null) {
         return current.getLeft();
      }
      // case 2d
      if(getRoot() == current && current.getLeft() == null && current.getRight() != null) {
         return current.getRight();
      }
      // case 3
      if(current.getRight() != null && current.getLeft() != null) {
         // max of left subtree
         TreeNode mls = max(current.getLeft(), "");
         TreeNode mlsParent = getParent(mls, (Comparable)mls.getValue());
         // 3a - is a leaf node
         if(mls.getLeft() != null && mls.getRight() != null) {
            current.setValue(mls.getValue());
            mlsParent.setLeft(null);
            mlsParent.setRight(null);
         }
         // 3bcd - isn't a leaf node
         // b
         if(mls.getLeft() == null || mls.getRight() == null && current == parent.getRight() && mlsParent.getRight() == mls) {
            parent.setRight(mls);
            mlsParent.setRight(null);
         }
         if(mls.getLeft() == null || mls.getRight() == null && current == parent.getLeft() && mlsParent.getRight() == mls) {
            parent.getLeft();
            mlsParent.setRight(null);
         }
         if(mls.getLeft() == null || mls.getRight() == null && current == parent.getRight() && mlsParent.getLeft() == mls) {
            parent.setRight(mls);
            mlsParent.setLeft(null);
         }
         if(mls.getLeft() == null || mls.getRight() == null && current == parent.getLeft() && mlsParent.getLeft() == mls) {
            parent.setLeft(null);
            mlsParent.setLeft(null);
         }
      }
      */

   }
}
