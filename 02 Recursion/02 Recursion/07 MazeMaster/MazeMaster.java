// Name: Manav Gagvani

import java.util.*;
import java.io.*;

public class MazeMaster
{
   public static void main(String[] args) 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next()+".txt");
      // Maze m = new Maze();    //extension
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Mark all dots.");
      System.out.println("2: Mark all dots and display the number of recursive calls.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, say so.");
      System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
      sc.close(); // This is not in the shell code but it is good practice to do this!!!!!
   } 
}

class Maze
{
   //constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char TEMP = 'o';
   private final char PATH = '*';
   //instance fields
   private char[][] maze;
   private int startRow, startCol;
   private int endRow, endCol;
   private int rows, cols;
   public static int pathCount = 0;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This is a no-arg constructor that generates a random maze
	 */
   public Maze()
   {  
      rows = 10;
      cols = 10;
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
      endRow = i;
      endCol = j;
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
      System.out.println(startRow + " " + startCol + "   " + endRow + " " + endCol);
      

   }
	
	/* 
	 * Copy Constructor  
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start location
            {
               startRow = r;
               startCol = c;
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
         rows = infile.nextInt();
         cols = infile.nextInt();
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
                  endRow = r;
                  endCol = c;
               }
            }
         }
         
         
            // System.out.println("_________");
            // System.out.println(maze[i].length);
            // System.out.println("---------");
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
      maze[startRow][startCol] = START;
      maze[endRow][endCol] = EXIT;

      for(int a = 0; a<rows; a++)
         System.out.println(String.valueOf(maze[a]));
      
      System.out.println();
   }
   
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
         {
            markAll(startRow, startCol);
            break;
         }
         case 2:
         {
            int count = markAllAndCountRecursions(startRow, startCol);
            System.out.println("Number of recursions = " + count);
            break;
         }
         case 3:
         {
            markTheCorrectPath(startRow, startCol);
            break;
         }
         case 4:         //use mazeNoPath.txt 
         {
            if( !markTheCorrectPath(startRow, startCol) )
               System.out.println("No path exists."); 
            break;
         }
         case 5:
         {
            if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
               System.out.println("No path exists."); 
            else
               System.out.println("Number of steps: " + pathCount);
            break;
         }
         default:
            System.out.println("File not found");   
      }
   }
   
	/* 
	 * From handout, #1.
	 * Fill the maze, mark every step.
	 * This is a lot like AreaFill.
	 */ 
   public void markAll(int r, int c)
   {  
      // if(r < 0 || r > maze.length - 1 || c < 0 || c > maze[0].length - 1) {
      //    System.out.println("CONDITIONS NOT MET");
      //    System.out.println(r + " " + c);
      //    System.out.println(maze[0].length);
      // }
      // else if(maze[r][c] == DOT) {
      //    System.out.println("Found a dot to replace!");
      //    maze[r][c] = PATH;
      //    System.out.println(maze[r][c]);
      //    markAll(r+1,c);
      //    markAll(r-1,c);
      //    markAll(r,c+1);
      //    markAll(r,c-1);
      //    markAll(r-1,c-1);
      //    markAll(r+1,c+1);
      // }

      if(r < 0 || r > rows - 1 || c < 0 || c > cols - 1 || maze[r][c] == WALL) {
         // System.out.println(r + " " + c);
      } // do nothing since this is base case
      else {
        //  System.out.println("else" + r + " " + c);
         if(maze[r][c] == DOT) {
            maze[r][c] = PATH;
            markAll(r+1, c);
            markAll(r-1, c);
            markAll(r, c+1);
            markAll(r, c-1);
         }
         else if(maze[r][c] == START || maze[r][c] == EXIT) { // if it is going to recurse
            markAll(r+1, c);
            markAll(r-1, c);
            markAll(r, c+1);
            markAll(r, c-1);
         }
      }
      
   }
   

	/* 
	 * From handout, #2.
	 * Fill the maze, mark and count every recursive call as you go.
	 * Like AreaFill's counting without a static variable.
	 */ 
   public int markAllAndCountRecursions(int r, int c)
   {
      if(r < 0 || r > rows - 1 || c < 0 || c > cols - 1 || maze[r][c] == WALL) {
         // System.out.println(r + " " + c);
         return 1;
      } // do nothing since this is base case
      else {
         // System.out.println("else" + r + " " + c);
         if(maze[r][c] == DOT) {
            maze[r][c] = PATH;
            int xd = markAllAndCountRecursions(r+1, c);
            int xu = markAllAndCountRecursions(r-1, c);
            int xr = markAllAndCountRecursions(r, c+1);
            int xl = markAllAndCountRecursions(r, c-1);
            
            return 1 + xd + xu + xr + xl;
            
         }
         else if(maze[r][c] == START || maze[r][c] == EXIT) { // if it is going to recurse
            maze[r][c] = PATH;
            int xd = markAllAndCountRecursions(r+1, c);
            int xu = markAllAndCountRecursions(r-1, c);
            int xr = markAllAndCountRecursions(r, c+1);
            int xl = markAllAndCountRecursions(r, c-1);

            return 1 + xd + xu + xr + xl;
         }
         else {
            System.out.print(maze[r][c]);
            System.out.println(" -- " + r + " " + c);
            return 1;
         }
      }
   }

   /* 
	 * From handout, #3.
	 * Solve the maze, OR the booleans, and mark the path through it with an asterisk
	 * Recur until you find E, then mark the True path.
	 */ 	
   public boolean markTheCorrectPath(int r, int c)
   {  
      /*
      if(r < 0 || r > rows - 1 || c < 0 || c > cols - 1 || maze[r][c] == WALL) {
         // System.out.println(r + " " + c);
      } // do nothing since this is base case
      else {
        //  System.out.println("else" + r + " " + c);
         if(maze[r][c] == DOT) {
            maze[r][c] = PATH;

            markTheCorrectPath(r+1, c);
            if(maze[r+1][c] == TEMP) 
               maze[r][c] = TEMP;

            markTheCorrectPath(r-1, c);
            if(maze[r-1][c] == TEMP)
               maze[r][c] = TEMP;

            markTheCorrectPath(r, c+1);
            if(maze[r][c+1] == TEMP) 
               maze[r][c] = TEMP;
            

            markTheCorrectPath(r, c-1);
            if(maze[r][c-1] == TEMP) 
               maze[r][c] = TEMP;
            
         }
         else if(maze[r][c] == START) { // if it is going to recurse
            markTheCorrectPath(r+1, c);
            markTheCorrectPath(r-1, c);
            markTheCorrectPath(r, c+1);
            markTheCorrectPath(r, c-1);
         }
         else if(maze[r][c] == EXIT) { // done recursing
            maze[r][c] = TEMP;
         }
      }
      return false;
      */
      if(r < 0 || r > rows - 1 || c < 0 || c > cols - 1 || maze[r][c] == WALL) {
         // System.out.println(r + " " + c);
         return false;
      } // do nothing since this is base case
      else {
        //  System.out.println("else" + r + " " + c);
         if(maze[r][c] == DOT) {
            maze[r][c] = PATH;
            boolean xd = markTheCorrectPath(r+1, c);
            boolean xu = markTheCorrectPath(r-1, c);
            boolean xr = markTheCorrectPath(r, c+1);
            boolean xl = markTheCorrectPath(r, c-1);
            if(xd || xu || xr || xl) { // if any of the recurses came from the true path return true
               // maze[r][c] = TEMP;
               return true;
            }
            else {
               maze[r][c] = DOT;
            }
         }
         else if(maze[r][c] == START) { // if it is going to recurse
            boolean xd = markTheCorrectPath(r+1, c);
            boolean xu = markTheCorrectPath(r-1, c);
            boolean xr = markTheCorrectPath(r, c+1);
            boolean xl = markTheCorrectPath(r, c-1);
            if(xd || xu || xr || xl)
               return true;
         }
         else if(maze[r][c] == EXIT) {
            return true;
         }
         return false;
      }

   }
	
	
   /*  4   Mark only the correct path. If no path exists, say so.
           Hint:  the method above returns the boolean that you need. */
      

   /* 
	 * From handout, #5.
	 * Solve the maze, mark the path, count the steps. 	 
	 * Mark only the correct path and display the number of steps.
	 * If no path exists, say so.
	 */ 	
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
      if(r < 0 || r > rows - 1 || c < 0 || c > cols - 1 || maze[r][c] == WALL) {
         // System.out.println(r + " " + c);
         return false;
      } // do nothing since this is base case
      else {
        //  System.out.println("else" + r + " " + c);
         if(maze[r][c] == DOT) {
            maze[r][c] = PATH;
            boolean xd = markCorrectPathAndCountSteps(r+1, c, count + 1);
            boolean xu = markCorrectPathAndCountSteps(r-1, c, count + 1);
            boolean xr = markCorrectPathAndCountSteps(r, c+1, count + 1);
            boolean xl = markCorrectPathAndCountSteps(r, c-1, count + 1);
            if(xd || xu || xr || xl) { // if any of the recurses came from the true path return true
               // maze[r][c] = TEMP;
               // pathCount = count;
               return true;
            }
            else {
               maze[r][c] = DOT;
            }
         }
         else if(maze[r][c] == START) { // if it is going to recurse
            boolean xd = markCorrectPathAndCountSteps(r+1, c, count + 1);
            boolean xu = markCorrectPathAndCountSteps(r-1, c, count + 1);
            boolean xr = markCorrectPathAndCountSteps(r, c+1, count + 1);
            boolean xl = markCorrectPathAndCountSteps(r, c-1, count + 1);
            if(xd || xu || xr || xl)
               return true;
         }
         else if(maze[r][c] == EXIT) {
            pathCount = count;
            return true;
         }
         return false;
   } } //
}

/*****************************************
 
 ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 1
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 2
 Number of recursions = 105
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 3
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
     
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): mazeNoPath
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 4
 No path exists.
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 5
 Number of steps = 14
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
  ********************************************/