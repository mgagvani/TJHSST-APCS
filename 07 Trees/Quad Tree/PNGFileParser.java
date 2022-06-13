import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.*;
import java.awt.Color;

public class PNGFileParser {
    int[][] maze_arr;
    BufferedImage img;
    int start_i;
    int start_j;
    int end_i;
    int end_j;

    public static void main(String args[]) {
        PNGFileParser p = new PNGFileParser("maze1.png");
        p.generate_maze_arr();
        for(int[] row : p.maze_arr) {
            List<Integer> l = new ArrayList<Integer>();
            for(int i : row) {
                l.add(i);
            }
            System.out.println(l);
        }
    }

    public PNGFileParser() {
    }

    public PNGFileParser(String path) {
        try {
            this.img = javax.imageio.ImageIO.read(new File(path));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        maze_arr = new int[img.getHeight() - 1][img.getWidth() - 1];

    }

    public void generate_maze_arr() {
        for(int i = 0; i < img.getHeight() - 1; i++) {
            for(int j = 0; j < img.getWidth() - 1; j++) {
                Color color = new Color(img.getRGB(j, i));
                if(color.equals(new Color(255, 0, 0))) {
                    System.out.println("Start index: " + i + ", " + j);
                    maze_arr[i][j] = 1;
                    start_i = i;
                    start_j = j;
                }
                else if(color.equals(new Color(0, 255, 0))) {
                    System.out.println("End index: " + i + ", " + j);
                    maze_arr[i][j] = 2;
                    end_i = i;
                    end_j = j;
                }

                else if(color.equals(new Color(255, 255, 255))) {
                    maze_arr[i][j] = 0;
                }
                else if(color.equals(new Color(0, 0, 255))) {
                    maze_arr[i][j] = 3;
                }
                else {
                    System.out.println("Unsupported color.");
                }
            }
        }
    }
}