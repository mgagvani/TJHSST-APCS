import java.util.*;

public class QuadMazeSolver {
    public static void main(String[] args) {
        PNGFileParser p = new PNGFileParser("maze1.png");
        p.generate_maze_arr();

        for(int[] row : p.maze_arr) {
            List<Integer> l = new ArrayList<Integer>();
            for(int i : row) {
                l.add(i);
            }
            System.out.println(l);
        }

        QuadTree qt = new QuadTree(p.maze_arr);
        qt.populateTree();
        int[] end_coords = {p.end_i, p.end_j};
        List<QuadTreeNode> bfs_out = qt.breadthFirstSearch(end_coords);
        for(QuadTreeNode node: bfs_out) {
            System.out.println(node.coordinates);
        }
    }
}