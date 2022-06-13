//Updated on 12.14.2020 v2

//Name:   Date:
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = 0.2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   //public static final int numberOfServiceWindows = 3;  //for McRonald 3
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   // public static void displayTimeAndQueue(Queue<Customer> q, int min)
   public static void displayTimeAndQueue(Queue<Integer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      Queue<Integer> queue = new LinkedList<Integer>();
      int servingTime = timeToOrderAndBeServed();
      int min = 0;
      while(min < TIME || queue.isEmpty() == false) {
         // Is time valid for a new customer to come?
         if(min < TIME) {
            // Test if a new customer has come.
            if(Math.random() < CHANCE_OF_CUSTOMER) {
               queue.add(min); // Add a new customer with the arrival time 
               customers++;
               servingTime = timeToOrderAndBeServed();
            }
         }
         if(servingTime == 0 && queue.isEmpty() == false) {
            int time = queue.peek();
            queue.remove();
            thisCustomersTime = min - time;
            if(thisCustomersTime > longestWaitTime) longestWaitTime = thisCustomersTime;
            totalMinutes += thisCustomersTime;
         }
         else {
            servingTime--;
         }
         // Display the queue
         displayTimeAndQueue(queue, min);
         // Calculate stats
         if(queue.size() > longestQueue) longestQueue = queue.size();
         // Increment minute counter
         min++;
      }
        
        
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
   
    
    
   }
}