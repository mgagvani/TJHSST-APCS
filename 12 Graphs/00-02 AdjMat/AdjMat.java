// Name:   Manav Gagvani
// Date:   5-6-22
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   public int[][] getGrid();
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public String toString();  //returns the grid as a String
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   public void allPairsReachability();   // Warshall's Algorithm
   public List<String> getReachables(String from);  //Warshall extension
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall ,Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  write constructor, accessor method, and instance methods here  */ 
   public AdjMat(int size) {
      grid = new int[size][size];
      vertices = new TreeMap<String, Integer>();
      nameList = new ArrayList<String>();
   } 

   public void addEdge(int source, int target) {
      grid[source][target] = 1;
   }

   public void removeEdge(int source, int target) {
      grid[source][target] = 0;
   }

   public boolean isEdge(int from, int to) {
      return grid[from][to] > 0 && grid[from][to] < 9999;
   }

   public String toString() {
      String toRet = "";
      for(int[] row: grid) {
         for(int num: row) {
            toRet += num + " ";
         }
         toRet += "\n";
      }
      return toRet;
   }

   public int[][] getGrid() {
      return grid;
   }

   public int edgeCount() {
      int count = 0;
      for(int[] row: grid) {
         for(int num: row) {
            if(num > 0 && num < 9999) count++; // arbitrary "inf"
         }
      }
      return count;
   }

   public List<Integer> getNeighbors(int source) {
      int counter = 0;
      List<Integer> list = new LinkedList<Integer>();
      for(int num: grid[source]) {
         if(num == 1) list.add(counter);
         counter++;
      }
      return list;
   }

   public void displayVertices() {
      for(String s: vertices.keySet()) {
         System.out.println(vertices.get(s)+"-"+s);
      }
   }

   public Map<String, Integer> getVertices() {
      return vertices;
   }

   public void readNames(String fileName) throws FileNotFoundException {
      Scanner sc = new Scanner(new File(fileName));
      int numNames = sc.nextInt();
      for(int i = 0; i < numNames; i++) {
         nameList.add(sc.next());
         vertices.put(nameList.get(i), i);
      }
      
   }

   public void readGrid(String fileName) throws FileNotFoundException {
      /**
      Scanner sc = new Scanner(new File(fileName));
      int numNames = sc.nextInt();
      for(int i = 0; i < numNames; i++) {
         String[] stuff = sc.nextLine().split(" ");
         for(int j = 0; j < stuff.length; j++) {
            grid[i][j] = Integer.parseInt(stuff[j]);
         }
      }
      */
      Scanner sc = new Scanner(new File(fileName));
      int size = sc.nextInt();
      for(int i = 0; i < size; i++) {
         for(int j = 0; j < size; j++) {
            grid[i][j] = sc.nextInt();
         }
      }
      
   }

   public void allPairsReachability() {
      for(int ii = 0; ii < grid.length; ii++ ) {
         for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
               if(isEdge(ii, j) && isEdge(i, ii)) {
                  addEdge(i, j);
               }
            }
         }
      }
   }

   public List<String> getReachables(String from) {
      ArrayList<String> toRet = new ArrayList<String>();
      for(String str: nameList) {
         if(isEdge(from, str)) toRet.add(str);
      }
      return toRet;
   }

   public boolean isEdge(String from, String to) {
      return isEdge(vertices.get(from), vertices.get(to));
   }

   public int getCost(int from, int to) {
      return grid[from][to];
   }

   public int getCost(String from, String to) {
      return getCost(vertices.get(from), vertices.get(to));
      
   }

   public void allPairsWeighted() {
      int size = grid.length;
      for(int k = 0; k < size; k++) {
         for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
               if(isEdge(k, j) && isEdge(i, k) && (getCost(i, k) + getCost(k, j)) < getCost(i, j)) {
                  grid[i][j] = getCost(i, k) + getCost(k, j);
               }
            }
         }
      }
      
   }
   
   
}
