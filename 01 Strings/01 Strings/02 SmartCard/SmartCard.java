// name: Manav Gagvani
// date: 8-26-21

import java.text.DecimalFormat;
public class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;

   private double balance;
   private Station boardingStation;
   private boolean onboard;
   
   /* the one-arg constructor  */
   public SmartCard(double initBalance)
   {
      balance = initBalance;
      boardingStation = null;
      onboard = false;
   }

   //these three getter methods only return your private data
   //they do not make any changes to your data
   public boolean getIsBoarded() 
   { 
     return onboard;
   }
   
   public double getBalance()
   {
      return balance;
   }
         
   public Station getBoardedAt()
   {
      return boardingStation;
   }

   public String getFormattedBalance() {
       return df.format(balance);
   }

   public void board(Station s) {
      // already boarded
         if(onboard) {
            System.out.println("Error: already boarded?!");
            return;
         }

      if(balance < MIN_FARE) {
         // Do not have enough money
         System.out.println("Insufficient funds to board. Please add more money.");
         return;
      }

      // all good, set new boarding station
      boardingStation = s;
      onboard = true;
      
   }

   public void exit(Station s) {
      if(!(onboard)) { // if not onboard
         System.out.println("Error: Did not board?!");
         return;
      }

      if(cost(s) > balance) { // not enough money
         System.out.println("Insufficient funds to exit. Please add more money.");
         return;
      }

      onboard = false; // since we have exited train
      balance -= cost(s);
      
      String output = "From " + boardingStation.getName() + " to " + s.getName() + " costs " + df.format(cost(s)) + ". SmartCard has " + getFormattedBalance();
      System.out.println(output);

      boardingStation = null;
   }

   private double cost(Station s) { // does not need to be accessible to anything else
      int exit_zone = s.getZone();
      int enter_zone = boardingStation.getZone();

      double cost = Math.abs(exit_zone - enter_zone) * 0.75 + 0.50;

      return cost;
   }

   public void addMoney(double d) {
      balance += d;
   }
    
     
    

}
   
// ***********  start a new class.  The new class does NOT have public or private.  ***/
class Station
{
   private String name;
   private int zone;
   
   // Default constructor
   public Station() {
      name = "Station";
      zone = 0;
   }

   // 2-arg constructor
   public Station(String Name, int Zone) {
      name = Name;
      zone = Zone;
   }

   // accessor functions
   public int getZone() {
      return zone;
   }
   public String getName() {
      return name;
   }
   
}

