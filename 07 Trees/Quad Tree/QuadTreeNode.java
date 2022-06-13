import java.util.ArrayList;

public class QuadTreeNode {
    public boolean isStart;
    public boolean isEnd;
    public int[] coordinates; // i, j
    public boolean isLeaf;

    public QuadTreeNode up;
    public QuadTreeNode down;
    public QuadTreeNode left;
    public QuadTreeNode right;
    public ArrayList<QuadTreeNode> edges;



    public QuadTreeNode() {
        edges = new ArrayList<QuadTreeNode>();
    }


    public QuadTreeNode(boolean isStart, boolean isEnd, int[] coordinates, boolean isLeaf, QuadTreeNode up, QuadTreeNode down, QuadTreeNode left, QuadTreeNode right) {
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.coordinates = coordinates;
        this.isLeaf = isLeaf;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        if(up != null) this.edges.add(up);
        if(left != null) this.edges.add(left);
        if(right != null) this.edges.add(down);
        if(down != null) this.edges.add(right);
    }    

}
