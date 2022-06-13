 //Name:   Manav Gagvani
 //Date:   3-24-22
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
      return myHeap.size() - 1;
   }
   
   public boolean isEmpty()
   {
      if(myHeap.size() == 1) return true;
      return false; 
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }
   
   public E remove()
   {
      swap(1, lastIndex());
      E temp = myHeap.remove(lastIndex());
      heapDown(1, lastIndex());
      return temp;
   }
   
   public E peek()
   {
      if(lastIndex() > 0) return myHeap.get(1);
      else return null; 
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
      int parent = k / 2;
      if(parent == 0) return;
      if(myHeap.get(parent).compareTo(myHeap.get(k)) > 0) {
         swap(k, parent);
         heapUp(parent);
      }      
   }
   
   private void swap(int a, int b)
   {
     E temp = myHeap.get(a);
     myHeap.set(a, myHeap.get(b));
     myHeap.set(b, temp);
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {
      int left = 2 * k;
      int right = left + 1;
      if(k > lastIndex || left > lastIndex) {
         return;
      }
      if(right > lastIndex) {
         if(myHeap.get(k) .compareTo(myHeap.get(left)) > 0) {
            swap(k, left);
         }
      }
      else {
         // int min = (myHeap.get(left).compareTo(myHeap.get(right)) < 0) ? left : right;
         int min;
         if(myHeap.get(right).compareTo(myHeap.get(left)) > 0) min = left;
         else min = right;
         if(myHeap.get(k).compareTo(myHeap.get(min)) > 0) {
            swap(k, min);
            heapDown(min, lastIndex);
         }
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
