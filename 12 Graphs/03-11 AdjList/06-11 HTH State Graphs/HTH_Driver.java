// Name:  Manav Gagvani 
// Date:  5-31-22
 
import java.util.*;
import java.io.*;

/* For use with Graphs11: State Graphs,
   Heads-Tails-Heads
 */

class HTH_Driver
{
   public static void main(String[] args) throws FileNotFoundException
   {
      System.out.print("Enter the initial state, three H and/or T:  ");
      Scanner in = new Scanner(System.in);
      String initial = in.next().toUpperCase();
      Vertex v = makeGraph(initial);
      System.out.println("The state graph has been made.");
      
      while(true)
      {
         System.out.print("Enter the final state, three H and/or T:  ");
         String finalState = in.next().toUpperCase();;
         if( finalState.equals("-1") )
            break;
         v = findBreadth(v, finalState);
         System.out.println("The shortest path from "+initial+" to "+ finalState+ " is: ");
         System.out.println(initial);
         String s = "";
         while(v.previous != null)
         {
            s = v+"\n"+s;
            v = v.previous;
         }
         System.out.println(s);
      }
   }
   
   public static Vertex makeGraph(String s)
   {// 
      return new Vertex(s, null);
      // if(s.equals("THT")) {
      //    return new Vertex(s);
      // }
      // else {
      //    Vertex v = new Vertex(s, null);
      //    List<Vertex> adjacents = v.getEdges();
      //    for(Vertex a: adjacents) {
      //       return makeGraph(a.repr);
      //    }
      //    return v;
      // }
      // else {
         // boolean[] arr = new boolean[3];
         // char[] chars = s.toCharArray();
         // int i = 0;
         // for(char c: chars) {
         //    if(c == 'H') arr[i] = true;
         //    else arr[i] = false;
         //    i++;
         // }
// 
         // Vertex v = new Vertex(arr, null); 
         // List<Vertex> adjList = v.getEdges();
         // 
         // return v;
      // }


   }
   
   public static Vertex findBreadth(Vertex vert, String goal)
   {  /*
      List<Vertex> reachable_vertices = new ArrayList<Vertex>();
      Queue<Vertex> queue = new LinkedList<Vertex>();

      List<Vertex> adjacents = vert.getEdges();
      System.out.println(adjacents);
      for(Vertex v: adjacents) {
         queue.add(v);
      }
      while(!queue.isEmpty()) {
         Vertex popped = queue.remove();
         if(!reachable_vertices.contains(popped)) {
            reachable_vertices.add(popped);
            for(Vertex v: popped.getEdges()) {
               if(!reachable_vertices.contains(v)) queue.add(v);
            }
         }
      }
      System.out.println(reachable_vertices);
      for(Vertex v: reachable_vertices) {
         System.out.println(v);
         if(v.repr.equals(goal)) return v;
      }
      
      return null;
      */
      
      if(vert.repr.equals(goal)) {
         System.out.println(vert.repr + " " + goal);
         return vert;
      }
      else {
         System.out.println("Calling getEdges with: " + vert.repr);
         List<Vertex> adjacents = vert.getEdges();
         for(Vertex a: adjacents) {
            return findBreadth(a , goal);
         }
         return vert;
      }

   }
}

class Vertex
{
   private final boolean[] state;
   public String repr;
   private List<Vertex> edges = new ArrayList<Vertex>();
   public Vertex previous;
   public static List<Vertex> visitedVertices = new ArrayList<Vertex>();

   public static String playTurn(char[] arr) {
      if(Math.random() > 0.5) {
         if(arr[1] == 'H') arr[1] = 'T';
         else arr[1] = 'H';
      }
      else {
         if(Math.random() > 0.5) { // flip first one?
            if(arr[1] == arr[2]) {
               if(arr[0] == 'H') arr[0] = 'T';
               else arr[0] = 'H';
            }
            else {
               if(arr[2] == 'H') arr[2] = 'T';
               else arr[2] = 'H';
            }
         }
      }
      String s = "";
      for(char c: arr) s += c;
      return s;
   }
   
   public Vertex(String s) {
      state = new boolean[3];
      char[] _arr = s.toCharArray();
      int i = 0;
      for(char c: _arr) {
         if(c == 'H') state[i] = true;
         else state[i] = false;
         i++;
      }
      this.repr = s;
   }

   public Vertex(boolean[] a, Vertex b) {
      state = a;
      this.previous = b;
      String s = "";
      for(boolean c: a) {
         if(c) s += "H";
         else s+= "T";
      }
      this.repr = s;
   }

   public Vertex(String s, Vertex b) {
      state = new boolean[3];
      this.previous = b;
      int i = 0;
      for (char c: s.toCharArray()) {
         if(c == 'H') state[i] = true;
         else state[i] = false;
         i++;
      }
      this.repr = s;
   }

   public String state2string(boolean[] a) {
      String s = "";
      for(boolean c: a) {
         if(c) s += "H";
         else s+= "T";
      }
      return s;
   }

   public void addVertex(Vertex v) {
      edges.add(v);
      v.previous = this;
   }

   public Vertex getPrevious() {
      return this.previous;
   }

   public boolean[] getState() {
      return state;
   }

   public List<Vertex> getEdges() {
      // return edges;
      boolean[] b1 = {state[0], !state[1], state[2]};
      if(!state2string(b1).equals(state2string(this.previous.getState())));
         edges.add(new Vertex(b1, this));
      if(state[0] == state[1]) {
         System.out.println("first and second are equal");
         boolean[] b2 = {state[0], state[1], !state[2]};
         edges.add(new Vertex(b2, this));
      }
      if(state[1] == state[2]) {
         System.out.println("2nd and 3rd are equal");
         boolean[] b3 =  {!state[0], state[1], state[2]};
         edges.add(new Vertex(b3, this));
      }
      System.out.print("Edges: " + edges + ", States: ");
      for(boolean b: state) System.out.print(b + ", ");
      System.out.println();

      // remove dups
      List<Vertex> list = new ArrayList<Vertex>();
      list.addAll(new HashSet<Vertex>(edges));
      return list;
   }

   public void addAdjacent(Vertex v) {
      addVertex(v);
   }

   public void addEdge(Vertex v) {
      addVertex(v);
   }

   public Set<Vertex> getAdjacencies() {
      HashSet<Vertex> a = new HashSet<Vertex>();
      a.addAll(edges);
      return a;
   }

   public boolean check(String s) {
      if(toString().equals(s)) return true;
      else return false;
   }

   public String toString() {
      // generate repr
      String s = "";
      for(boolean b: state) {
         if(b) s += "H";
         else s+= "T";
      }
      return s;
   }
}

/************************
 Enter the initial state, three H and/or T:  HTH
 The state graph has been made.
 Enter the final state, three H and/or T:  THT
 The shortest path from HTH to THT is: 
 HTH
 HHH
 HHT
 HTT
 TTT
 THT
 
 Enter the final state, three H and/or T:  HHH
 The shortest path from HTH to HHH is: 
 HTH
 HHH
 
 Enter the final state, three H and/or T:  -1
 

 *************************************/