import java.util.*;

public class QuadTree {
    public QuadTreeNode head;
    public int[][] maze;
    public boolean[][] visited;

    public QuadTree() {
        this.head = new QuadTreeNode();
    }


    // Maze definition
    /*
    0 -- Free Space
    1 -- Start
    2 -- End
    3 -- Block
    */
    public QuadTree(int[][] maze) {
        this.maze = maze;
        // First, find head node
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                if(maze[i][j] == 1) {
                    head = new QuadTreeNode(true, false, new int[]{i, j}, false, null, null, null, null);
                }
            }
        }
        this.visited = new boolean[maze.length][maze[0].length];

    }

    public void populateTree() {
        populateTree(head);
    }


    public void populateTree(QuadTreeNode node) {
        // node that is passed in is head

        // first check if node is leaf, if it is don't bother
        if(node.isLeaf) {
            return;
        }

        // attempt to branch in north south east west
        int[] coords = node.coordinates;
        int i = coords[0];
        int j = coords[1];
        // update the array of visited locations
        visited[i][j] = true;

        if(checkCoords(new int[]{i-1, j})) {
            node.up = new QuadTreeNode(false, false, new int[]{i-1, j}, false, null, null, null, null);
            populateTree(node.up); // recursively populate starting from this node
        }
        else if(checkCoords(new int[]{i+1, j})) {
            node.down = new QuadTreeNode(false, false, new int[]{i+1, j}, false, null, null, null, null);
            populateTree(node.down); // recursively populate starting from this node
        }
        else if(checkCoords(new int[]{i, j+1})) {
            node.right = new QuadTreeNode(false, false, new int[]{i, j+1}, false, null, null, null, null);
            populateTree(node.right); // recursively populate starting from this node
        }
        else if(checkCoords(new int[]{i, j-1})) {
            node.left = new QuadTreeNode(false, false, new int[]{i, j-1}, false, null, null, null, null);
            populateTree(node.left); // recursively populate starting from this node
        }
        else {
            node.isLeaf = true;
        }
    }

    public boolean checkCoords(int[] coordinates) {
        // i, j indexed just link a java array

        // out of bounds
        if(coordinates[0] < 0 || coordinates[1] < 0 || coordinates[0] > (maze.length - 1) || coordinates[0] > (maze[0].length - 1)) {
            return false;
        }
        // blocked
        else if(maze[coordinates[0]][coordinates[1]] == 3) {
            return false; 
        }
        else if(visited[coordinates[0]][coordinates[1]] == true) {
            return false;
        }
        else {
            return true; 
        }
    }

    public List<QuadTreeNode> breadthFirstSearch(int[] coordinates) {
        // check to see if valid index
        if(coordinates[0] < 0 || coordinates[1] < 0 || coordinates[0] > (maze.length - 1) || coordinates[0] > (maze[0].length - 1)) {
            return new ArrayList<QuadTreeNode>();
        }
        // blocked
        else if(maze[coordinates[0]][coordinates[1]] == 3) {
            return new ArrayList<QuadTreeNode>(); 
        }
        // find associated quad tree node
        QuadTreeNode target = new QuadTreeNode();
        boolean found = false;
        List<QuadTreeNode> nodes = inorderTraverse();
        for(QuadTreeNode node: nodes) {
            if(node.coordinates == coordinates) {
                target = node;
                found = true;
            }
        }
        if(!found) {
            System.out.println("Target node not found");
            return new ArrayList<QuadTreeNode>();
        }
        else {
            return breadthFirstSearch(target);
        }

    }

    public List<QuadTreeNode> breadthFirstSearch(QuadTreeNode node) {   
        List<QuadTreeNode> reachable_vertices = new ArrayList<QuadTreeNode>();
        Queue<QuadTreeNode> queue = new LinkedList<QuadTreeNode>();


        for(QuadTreeNode v: node.edges) {
            queue.add(v);
        }
        while(!queue.isEmpty()) {
            QuadTreeNode popped = queue.remove();
            if(!reachable_vertices.contains(popped)) {
                reachable_vertices.add(popped);
                for(QuadTreeNode v: popped.edges) {
                   queue.add(v);
                }
        }

        return reachable_vertices;
    }

      return reachable_vertices;   
    }

    public List<QuadTreeNode> inorderTraverse() {
        // head is assumed
        return inorderTraverse(head);
    }

    public List<QuadTreeNode> inorderTraverse(QuadTreeNode root) {
        List<QuadTreeNode> l = new ArrayList<QuadTreeNode>();
        if(root == null) {
            return l;
        }
        l.addAll(inorderTraverse(root.up)); // clockwise direction
        l.addAll(inorderTraverse(root.right));
        l.add(root);
        l.addAll(inorderTraverse(root.down));
        l.addAll(inorderTraverse(root.left));
        return l; 
    }


}