// Name: Manav Gagvani
// Date: 11/16/21

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size = 0;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   //no constructor needed
   
   /* two accessor methods  */
   public int size()
   {
      return size;
   }
   public DLNode getHead()
   {
      return head;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException  // this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode head = getHead();
      for(int i = 0; i < index; i++) {
         head = head.getNext();
      }
      DLNode temp = new DLNode(obj, head, head.getNext());
      head.setNext(temp);    
      size++;            
   }
   
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      if(index == 0) {
         return getFirst();
      }
      if(index == size - 1) {
         return head.getPrev().getValue();
      }
      DLNode temp = getHead().getNext();
      for(int i = 0; i < index; i++) {
         temp = temp.getNext();
      }
      return temp.getValue(); 
      
   }
   
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode head = getHead().getNext();
      for(int i = 0; i < index; i++) {
         head = head.getNext();
      }
      Object oldvalue = head.getValue();
      head.setValue(obj);
      return oldvalue;
      
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = getHead();
      for(int i =0; i <= index; i++) {
         temp = temp.getNext();
      }
      temp.getPrev().setNext(temp.getNext());
      temp.getNext().setPrev(temp);
      size--;
      return temp.getValue();

   }
   
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj)
   {
      DLNode head = getHead();
      DLNode newFirst = new DLNode(obj, null, null);
      head.getNext().setPrev(newFirst); // after
      newFirst.setNext(head.getNext());

      head.setNext(newFirst); // before
      newFirst.setPrev(head);

      size++;
   }
   
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj)
   {
      DLNode last = getHead();
      for(int i = 0; i < size(); i++) {
         last = last.getNext();
      }
      DLNode newLast = new DLNode();
      last.setNext(newLast);
      newLast.setPrev(last);
      newLast.setValue(obj);
      newLast.setNext(getHead());
      size++;
   }
   
   /* returns the first element in this list  
      */
   public Object getFirst()
   {
      return getHead().getNext().getValue(); 
   }
   
   /* returns the last element in this list  
     */
   public Object getLast()
   {  
      DLNode start = head.getNext();
      for(int i = 1; i < size(); i++) {
         start = start.getNext();
      }
      return start.getValue();

      // return head.getPrev().getValue(); 
      // return get(size - 1);
   }

   public DLNode getLastnode(int _temp)
   {  
      DLNode start = head.getNext();
      for(int i = 1; i < size(); i++) {
         start = start.getNext();
      }
      return start;
   }
   
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst()
   { 
      DLNode first = getHead().getNext().getNext(); 
      first.setPrev(head);

      DLNode unlink = head.getNext();
      unlink.setPrev(null); // the unlinking
      unlink.setNext(null);
      head.setNext(first);

      this.size--; // decrement size by 1 since we removed 1
      return unlink.getValue();
   }
   
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast()
   {
      DLNode last = getLastnode(0).getPrev(); // 2nd to last node
      // last.setNext(head); // just making sure

      // DLNode unlink = head.getPrev();
      DLNode oldLast = last.getNext();
      
      oldLast.setPrev(null);
      oldLast.setNext(null);

      head.setPrev(last);
      last.setNext(head);


      this.size--;
      return oldLast.getValue(); 
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {  
      String s = "[";
      DLNode temp = getHead();
      for(int i = 0; i < size(); i++) {
         temp = temp.getNext();
         s += "" + temp.getValue() + ", ";
      }
      s = s.substring(0, s.length() - 2);
      return s + "]";
   }
}