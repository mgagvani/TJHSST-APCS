// Name: Manav Gagvani
// Date: 10-9-21

import java.util.*;
import java.io.File;

public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename: ");
      Maze m = new Maze(sc.next());
      // Maze m = new Maze();    
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Mark all paths.");
      System.out.println("1: Find the shortest path\n\tIf no path exists, say so.");
      System.out.println("2: Mark only the shortest correct path and display the count of STEPs.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      sc.close(); // very important that the scanner is closed to prevent a resource leak. this is not in the shell code.
   } 
}

class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   //Instance Fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This no a arg constructor that generates a random maze
	 */
   public Maze() {
      Random rand = new Random();
      int j = rand.nextInt(10);
      int i  = rand.nextInt(10);
      maze = new char[10][10];
      // put start and exit
      maze[i][j] = START;
      startRow = i;
      startCol = j;
      i = rand.nextInt(10);
      j = rand.nextInt(10);
      while(maze[i][j] == START) {
         i = rand.nextInt(10);
         j = rand.nextInt(10);
      }
      maze[i][j] = EXIT;
      char[] options = new char[]{WALL, DOT, DOT, DOT};
      for(int ic = 0; ic < 10; ic++) {
         for(int jc = 0; jc < 10; jc++) {
            if(maze[ic][jc] == START || maze[ic][jc] == EXIT) {}
            else {
               maze[ic][jc] = options[rand.nextInt(options.length)];
            }
         }
      }
   }
	
	/* 
	 * Copy Constructor  
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int i = 0; i < maze.length; i++)
      {
         for(int j = 0; j < maze[0].length; j++)
         { 
            if(maze[i][j] == START)      //identify start
            {
               startRow = i;
               startCol = j;
            }
         }
      }
   } 
	
	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
   public Maze(String filename)    
   {
      try {
         Scanner infile = new Scanner(new File(filename));
         int rows = infile.nextInt();
         int cols = infile.nextInt();
         maze = new char[rows][cols];

         infile.nextLine();
         for(int i=0; i<rows; i++) {
            maze[i] = infile.nextLine().toCharArray();

         for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
               if(maze[r][c] == 'S') {
                  // System.out.println(maze[r][c]);
                  startRow = r;
                  startCol = c;
                  break; 
               }
               else if(maze[r][c] == EXIT) {
                  int endRow = r;
                  int endCol = c;
               }
            }
            } 
         }
      } 
   	     
      catch(Exception FileNotFoundException) {
         System.out.println("Error reading file.");
      }
      
   }
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   public void display()
   {
      if(maze == null) {
         return;
      }

      for(int a = 0; a<maze.length; a++)
         System.out.println(String.valueOf(maze[a]));
      
      System.out.println();
   }
   
   public void solve(int n)
   {
      switch(n)
      {    
         case 1:
            {   
               int shortestPath = findShortestLengthPath(startRow, startCol);
               if( shortestPath!=-1 )
                  System.out.println("Sortest path is " + shortestPath);
               else
                  System.out.println("No path exists."); 
               break;
            }   
            
          case 2:
            {
               String strShortestPath = findShortestPath(startRow, startCol);
               System.out.println("SHORTEST PATH LENGTH: " + strShortestPath);
               if( strShortestPath.length()!=0 )
               {
                  System.out.println("Shortest length path is: " + getPathLength(strShortestPath));
                  System.out.println("  Shortest path is: " + strShortestPath);
                  markPath(strShortestPath);
                  display();  //display solved maze
               }
               else
                  System.out.println("No path exists."); 
               break;
            }
         default:
            System.out.println("File not found");   
      }
   }
   
 private int getPathLength(String strShortestPath) {
      if(strShortestPath.length() == 0) {
         return -1;
      }
      else {
         int start_index = strShortestPath.indexOf(",", strShortestPath.indexOf(",") + 1) + 1;
         int end_index = strShortestPath.indexOf(")", strShortestPath.indexOf(")") + 1);
         int len = Integer.parseInt(strShortestPath.substring(start_index, end_index));
         return len;
      }
   }

   /*  1   recur until you find E, then return the shortest path
     returns -1 if it fails
     precondition: Start can't match with Exit
 */ 
   public int findShortestLengthPath(int r, int c)
   {
      if(r < 0 || c < 0 || r >= maze.length || c > maze[0].length) {
         return -1; // out of bounds
      }   
      if(maze[r][c] == WALL || maze[r][c] == 'o') // temp not in shell code? 
      {
         return -1; // error
      }
      if(maze[r][c] == EXIT) {
         return 0;
      }

      if(maze[r][c] == DOT || maze[r][c] == START) {
         if(maze[r][c] == DOT) {
            maze[r][c] = 'o'; // temp
         }
      }

      int _u = findShortestLengthPath(r-1, c);
      int _d = findShortestLengthPath(r+1, c);
      int _l = findShortestLengthPath(r, c-1);
      int _r = findShortestLengthPath(r, c+1); // just temp vars

      if(!(maze[r][c] == START)) {
         maze[r][c] = DOT;
         if((_u + _d + _l + _r) == -4) { // all are -1
            return -1; //shortest path not found
         }
         else {
            int[] temp = new int[]{_u, _d, _l, _r};
            int min = Arrays.stream(temp).min().getAsInt();
            return 1 + min;
         }
      }
      else {
         display();
         int[] temp = new int[]{_u, _d, _l, _r};
         int min = Arrays.stream(temp).min().getAsInt();
         if(min != -1) {
            return 1 + min;
         }
         else {
            return -1;
         }
      }
   }  
   
/* 2 recur until you find E, then build the True path 
     use only the shortest path
     returns -1 if it fails
     precondition: Start can't match with Exit
 */
   public String findShortestPath(int r, int c)
   {
      if(r < 0 || c < 0 || r >= maze.length || c >= maze[0].length || maze[r][c] == WALL || maze[r][c] == EXIT) {
         return "";
      }
      if(maze[r][c] == EXIT) { // done!!
         String toRet = "((" + r + "," + c + "),0)"; // since we found exit immediately need the 0
         return toRet;
      }

      if(maze[r][c] == DOT || maze[r][c] == START) {
         if(maze[r][c] == DOT) maze[r][c] = 'o'; // no temp variable??

         String _u, _d, _r, _l;
         int su, sd, sr, sl; // r is already being used so changed to sr, etc...
         // recur
         _u = findShortestPath(r - 1, c);
         _d = findShortestPath(r - 1, c);
         _r = findShortestPath(r, c + 1);
         _l = findShortestPath(r, c - 1);

         // find path lengths based on coordinates
         su = getPathLength(_u);
         sd = getPathLength(_d);
         sr = getPathLength(_r);
         sl = getPathLength(_l);

         if(maze[r][c] != START) { // so it must be temp 
            maze[r][c] = DOT;
         
            if(su != -1 || sd != -1 || sr != -1 || sl != -1) { // cannot use sum != -4 because it's or, not and
               int[] temp = new int[]{su, sd, sr, sl};
               int min = Arrays.stream(temp).min().getAsInt();
               if(su == min) { // up corresponds with shortest path
                  String toRet = new String();
                  toRet = String.format("((%x,%x),%x,),%b", r, c, (min + 1), _u);
                  return toRet;
               }
               else if(sd == min) {
                  String toRet = new String();
                  toRet = String.format("((%x,%x),%x,),%b", r, c, (min + 1), _d);
                  return toRet;
               }
               else if(sr == min) {
                  String toRet = new String();
                  toRet = String.format("((%x,%x),%x,),%b", r, c, (min + 1), _r);
                  return toRet;
               }
               else if(sl == min) {
                  String toRet = new String();
                  toRet = String.format("((%x,%x),%x,),%b", r, c, (min + 1), _l);
                  return toRet;
               }
               else {
                  System.out.println("Error");
                  return "error";
               }
            }
            else {
               return ""; // nothing found
            }
         }
         else {
            // even if you cannot recurse you still need to return something.
            // which is the same coordinates as last time. 
            int[] temp = new int[]{su, sd, sr, sl};
            int min = Arrays.stream(temp).min().getAsInt(); // finds the minimum
            if(min > -1) { // -1 means no path found
               if(su == min) { // up corresponds with shortest path
                  String toRet = new String();
                  toRet = String.format("((%x,%x),%x,),%b", r, c, (min + 1), _u);
                  return toRet;
               }
               else if(sd == min) {
                  String toRet = new String();
                  toRet = String.format("((%x,%x),%x,),%b", r, c, (min + 1), _d);
                  return toRet;
               }
               else if(sr == min) {
                  String toRet = new String();
                  toRet = String.format("((%x,%x),%x,),%b", r, c, (min + 1), _r);
                  return toRet;
               }
               else if(sl == min) {
                  String toRet = new String();
                  toRet = String.format("((%x,%x),%x,),%b", r, c, (min + 1), _l);
                  return toRet;
               }
               else {
                  System.out.println("Error");
                  return "error";
               }
            }
            else {
               return ""; // nothing found
            }
         }
      }
      else {
         return "";
      }
      
   }	


   //a recursive method that takes an argument created by the method 2 in the form of 
   //((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
   //and it marks the actual path in the maze with STEP
   //precondition:   the String is either an empty String or one that has the correct format above
   //                the indexes must be correct for this method to work  
   public void markPath(String strPath)
   {
      if(strPath.length() == 0) {
         return;
      }
      int sr;
      int er;

      sr = strPath.indexOf("(") + 2; // because theres another parentheses
      er = strPath.indexOf(",");

      int r;
      r = Integer.parseInt(strPath.substring(sr, er)); // find row

      int sc;
      int ec;

      sc = strPath.indexOf(",") + 1; // will come after the first comma
      ec = strPath.indexOf(")"); // right b4 this wil come the end

      int c;
      c = Integer.parseInt(strPath.substring(sc, ec)); // find column

      if(maze[r][c] != EXIT && maze[r][c] != START) {
         maze[r][c] = STEP; // prevent stack overflow
      }


      // temp variable better for compiler?
      String temp = strPath.substring(strPath.indexOf(")", strPath.indexOf(")") + 1)); // need to add 1 because not inclusive
      strPath = temp;

      if(strPath.length() != 1) {
         markPath(strPath.substring(2)); // recur
         // need to do substring so that all of it is not being considered over and over again. 
      }



   }
}

 // Enter the maze's filename (no .txt): maze0
 // WWWWWWWW
 // W....W.W
 // WW.W...W
 // W....W.W
 // W.W.WW.E
 // S.W.WW.W
 // W......W
 // WWWWWWWW
 // 
 // Options: 
 // 1: Find the shortest path
 // 	If no path exists, say so.
 // 2: Mark only the shortest correct path and display the count of STEPs.
 // 	If no path exists, say so.
 // Please make a selection: 2
 // Sortest lenght path is: 10
 //   Sortest path is: ((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
 // WWWWWWWW
 // W....W.W
 // WW.W...W
 // W....W.W
 // W.W.WW*E
 // S*W.WW*W
 // W******W
 // WWWWWWWW