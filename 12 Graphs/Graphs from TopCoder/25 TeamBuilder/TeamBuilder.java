// Name:   
// Date:

import java.util.*;
import java.io.*;

public class TeamBuilder {
   public static void main(String[] arrgs) {
      String[] path = { "010", "000", "110" };
      // String[] path = {"0010", "1000", "1100", "1000"};
      // String[] path = {"01000", "00100", "00010", "00001", "10000"};
      // String[] path = {"0110000", "1000100", "0000001", "0010000", "0110000",
      // "1000010", "0001000"};

      int[] ret = specialLocations(path);
      System.out.println("{" + ret[0] + ", " + ret[1] + "}");
   }

   public static int[] specialLocations(String[] paths) {
      int size = paths.length;
      int[][] arr = new int[size][size];
      int z = 0;

      while (z < size) {
         for (int j = 0; j < size; j++) {
            arr[z][j] = paths[z].charAt(j) - '0';
         }
         z++;
      }

      int x = 0; int y = 0;

      while (x < size) {
         for (int i = 0; i < size; i++) {
            while (y < size) {
               if (arr[i][y] == 0 && arr[i][x] == 1 && arr[x][y] == 1)
                  arr[i][y] = 1;
               y++;
            }
            y = 0;
         }

         y = 0;
         x++;
      }

      int toRet1 = 0;
      int toRet2 = 0;
      int v = 0;

      while (v < size) {
         boolean bool1 = true;
         boolean bool2 = true;

         for (int j = 0; j < size; j++) {
            if (v == j) continue;
            else System.out.print(""); // do nothing

            if (arr[v][j] != 0) System.out.print(""); // do nothing
            else bool1 = false;

            if (arr[j][v] != 0) System.out.print(""); // do nothing
            else bool2 = false;
         }

         if (bool1 != false) toRet1++;
         else System.out.print(""); // do nothing  

         if (bool2 != false) toRet2++;
         else System.out.print(""); // do nothing

         v++;
      }

      int[] toRet = { toRet1, toRet2 };

      return toRet;
   }

}