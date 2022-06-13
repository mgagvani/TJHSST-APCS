// Name:
// Date: 
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{
  private TreeNode root;
  private int size;
  public BST_Generic()
  {
     root = null;
     size = 0;
  }
  public int size()
  {
     return size;
  }
  public TreeNode<E> getRoot()   //for Grade-It
  {
     return root;
  }
  /***************************************
  @param s -- one string to be inserted
   * @return 
  ****************************************/
  public E add(E s) 
  {
     root = add(root, s);
     size++;
     return s;
  }
  private TreeNode<E> add(TreeNode<E> t, E s) //recursive helper method
  {      
     if(t == null) return new TreeNode(s, null, null);
     if(s.compareTo(t.getValue()) <= 0) t.setLeft(add(t.getLeft(), s));
     else t.setRight(add(t.getRight(), s));
     return t;
  }
  
  public String display()
  {
     return display(root, 0);
  }
  private String display(TreeNode<E> t, int level) //recursive helper method
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
  
  public boolean contains( E obj)
  {
     return contains(getRoot(), obj);
  }
  private boolean contains(TreeNode<E> t, E x) //recursive helper method
  {
     if(t == null) return false;
     int c = x.compareTo(t.getValue());
     if(c == 0) return true;
     if(c < 0) return contains(t.getLeft(), x);
     else return contains(t.getRight(), x);
  }
  
  public E min()
  {
     return (E)min(root, "");
  }
  private TreeNode<E> min(TreeNode<E> t)  //use iteration
  {
     if(t == null) return null;
     while(t.getLeft() != null) t = t.getLeft();
     return t;
  }

  private E min(TreeNode<E> t, String s)  //use iteration
  {
     if(t == null) return null;
     while(t.getLeft() != null) t = t.getLeft();
     return t.getValue();
  }
  
  public E max()
  {
     return (E)max(root);
  }
  private E max(TreeNode<E> t)  //recursive helper method
  {
     if(t == null) return null;
     while(t.getRight() != null) t = t.getRight();
     return t.getValue();
  }

  public String toString()
  {
     return toString(root);
  }
  private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
  {
     String toReturn = "";
     if(t == null)
        return "";
     toReturn += toString(t.getLeft());   //recurse left
     toReturn += t.getValue() + " ";              //process root
     toReturn += toString(t.getRight());  //recurse right
     return toReturn;  
  } 

  public List<E> toList() {
     TreeNode<E> t = root;
     List<E> arr = new ArrayList<E>();
     toList(t, arr);
     return arr;
     
  }

  public void toList(TreeNode<E> n, List<E> list) {
   if(n == null){
      return;
   }
   toList(n.getLeft(), list);
   list.add(n.getValue());
   toList(n.getRight(), list);
  }

  public TreeNode<E> getParent(TreeNode<E> root, Comparable<E> childValue) {
     TreeNode<E> parent = null;
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
  public E remove(E target)
  {
     root = remove(root, target);
     size--;
     return target; 
  }
  private TreeNode<E> remove(TreeNode<E> current, E target)
  {  
     if (current == null)
           return null;
     if (current.getValue().compareTo(target) > 0) {
        current.setLeft(remove(current.getLeft(), target));
     } 
     else if (current.getValue().compareTo(target) < 0) {
           current.setRight(remove(current.getRight(), target));
     } 
     else {
        // if nodeToBeDeleted have both children
        if (current.getLeft() != null && current.getRight()!= null) {
           TreeNode<E> temp = current;
           // Finding minimum element from right
           TreeNode<E> minNodeForRight = min(temp.getRight());
           // Replacing current node with minimum node from right subtree
           current.setValue(minNodeForRight.getValue());
           // Deleting minimum node from right now
           current.setRight(remove(current.getRight(), minNodeForRight.getValue()));
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
  private int calcBalance(TreeNode<E> t) //height to right minus 
  {                                    //height to left
     return height(t.getRight()) - height(t.getLeft());
  }

  private int height(TreeNode<E> t)   //from TreeLab
  {
     if(t == null) return 0;
     int left = 1 + height(t.getLeft());
     int right = 1 + height(t.getRight());
     return Math.max(left, right);
  }

  public void addBalanced(E value)  
  {
     add(value);
     root = balanceTree(root);   // for an AVL tree. Put in the arguments you want.
  }
  private TreeNode<E> balanceTree(TreeNode<E> t) { //recursive.  Whatever makes sense.
  if(t == null) return null;
  t.setLeft((t.getLeft()));
  t.setRight((t.getRight()));
  if(calcBalance(t) > 1) {
     if(calcBalance(t.getRight()) < -1) { // equals signs were added
        t = leftRightRotation(t);
     }
     else {
        t = leftRotation(t);
     }
  }
  else if(calcBalance(t) < -1) {
     if(calcBalance(t.getLeft()) > 1) { // equals signs were added
        t = rightLeftRotation(t);
     }
     else {
        t = rightRotation(t);
     }
  }
  // false base case -- no rotate is needed 
  // balanceTree(root.getLeft());
  // balanceTree(root.getRight());
  /**
  if...else if....
  if(.....)
     parent.setRight( rotation method )
  */
  return t;
  }
  // 4 rotation methods
  private TreeNode<E> leftRotation(TreeNode<E> a) {
     TreeNode<E> b = a.getRight();
     a.setRight(b.getLeft());
     b.setLeft(a);
     return b;
  }
  private TreeNode<E> rightRotation(TreeNode<E> c) {
     TreeNode<E> b = c.getLeft();
     c.setLeft(b.getRight());
     b.setRight(c);
     return b;
  }
  private TreeNode<E> leftRightRotation(TreeNode<E> c) {
     c.setRight(rightRotation(c.getRight())); // used to be rightRotation
     return leftRotation(c); // used to be left rotation
  }
  private TreeNode<E> rightLeftRotation(TreeNode<E> c) {
     c.setLeft(leftRotation(c.getLeft()));
     return rightRotation(c);
  }
}

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E>
{
  private E value; 
  private TreeNode<E> left, right;

   public TreeNode(E initValue)
  { 
     value = initValue; 
     left = null; 
     right = null; 
  }

   public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
  { 
     value = initValue; 
     left = initLeft; 
     right = initRight; 
  }

   public E getValue()
  { 
     return value; 
  }

   public TreeNode<E> getLeft() 
  { 
     return left; 
  }

   public TreeNode<E> getRight() 
  { 
     return right; 
  }

   public void setValue(E theNewValue) 
  { 
     value = theNewValue; 
  }

   public void setLeft(TreeNode<E> theNewLeft) 
  { 
     left = theNewLeft;
  }

   public void setRight(TreeNode<E> theNewRight)
  { 
     right = theNewRight;
  }
}