// Name:   Manav Gagvani
// Date:   5/12/22
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface
{
   public String getName();
   public HashSet<Vertex> getAdjacencies();
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    this method should be O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private final String name;
   private HashSet<Vertex> adjacencies;
  /* enter your code here  */
  public Vertex(String n) {
      name = n;
      adjacencies = new HashSet<Vertex>();
  }

  public int hashCode() {
     return name.hashCode();
  }

  public String getName() {
   return name;
  }

  public HashSet<Vertex> getAdjacencies() {
     return adjacencies;
  }

  public String toString() {
     String s = name + " [";
     if(adjacencies.isEmpty()) return s + "]";
     for(Vertex v: new HashSet<Vertex>(adjacencies)) s += v.getName() + " ";
     return s.substring(0, s.length() - 1) + "]";
  }

  public void addAdjacent(Vertex v) {
     if(!adjacencies.contains(v)){
      adjacencies.add(v);
     } 
  }

  public int compareTo(Vertex o) {
     return name.compareTo(o.getName());
  }
  
  
}   

/*************************************************************/
interface AdjListInterface 
{
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 

}

  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS
{
   public List<Vertex> depthFirstSearch(String name);
   public List<Vertex> breadthFirstSearch(String name);
   /*   extra credit methods */
   public List<Vertex> depthFirstRecur(String name);
   public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities
{
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}


/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();

   public Set<Vertex> getVertices() {
      Set<Vertex> toRet = new HashSet<Vertex>();
      toRet.addAll(vertexMap.values());
      return toRet;
   }

   public Vertex getVertex(String vName) {
      return vertexMap.get(vName);
   }

   public Map<String, Vertex> getVertexMap() {
      return vertexMap;
   }

   public void addVertex(String vName) {
      vertexMap.putIfAbsent(vName, new Vertex(vName));
      
   }

   public void addEdge(String source, String target) {
      vertexMap.get(source).addAdjacent(vertexMap.get(target));
      
   }

   public String toString() {
      // String toRet = "";
      // for(Vertex v: vertexMap.values()) {
      //    toRet+= v.toString();
      //    toRet+="\n";
      // }
      // return toRet;
      String s = "";
      for(String k: vertexMap.keySet()) {
         s += vertexMap.get(k).toString() + "\n";
      }
      return s;
   }

   public List<Vertex> depthFirstSearch(Vertex vert) {
      Stack<Vertex> stk = new Stack<Vertex>();
      ArrayList<Vertex> reachable_vertices = new ArrayList<Vertex>();

      for(Vertex v: vert.getAdjacencies()) {
         stk.push(v);
      }
      while(!stk.isEmpty()) {
         Vertex popped = stk.pop();
         if(!reachable_vertices.contains(popped)) {
            reachable_vertices.add(popped);
            for(Vertex v: popped.getAdjacencies()) {
               stk.push(v);
            }
         }
      }

      return reachable_vertices;
   }

   public List<Vertex> depthFirstSearch(String name) {
      return depthFirstSearch(vertexMap.get(name));
   }

   public List<Vertex> breadthFirstSearch(Vertex vert) {
      List<Vertex> reachable_vertices = new ArrayList<Vertex>();
      Queue<Vertex> queue = new LinkedList<Vertex>();


      for(Vertex v: vert.getAdjacencies()) {
         queue.add(v);
      }
      while(!queue.isEmpty()) {
         Vertex popped = queue.remove();
         if(!reachable_vertices.contains(popped)) {
            reachable_vertices.add(popped);
            for(Vertex v: popped.getAdjacencies()) {
               queue.add(v);
            }
         }
      }

      return reachable_vertices;
   }

   public List<Vertex> breadthFirstSearch(String name) {
      return breadthFirstSearch(vertexMap.get(name));
   }

   public List<Vertex> depthFirstRecur(String name) {
      return depthFirstSearch(name);
   }

   public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable) {
      return depthFirstRecur(v.getName());
   }

   public void readData(String cities, String edges) throws FileNotFoundException {
      Scanner sc = new Scanner(new File(cities));
      Scanner sc1 = new Scanner(new File(edges));
      while(sc.hasNext()) addVertex(sc.next());
      while(sc1.hasNextLine()) {
         String temp = sc1.nextLine();
         addEdge(temp.split(" ")[0], temp.split(" ")[1]);
      }
   }

   public int edgeCount() {
      int count = 0;
      for(Vertex v: getVertices()) count += v.getAdjacencies().size();
      return count;
   }

   public int vertexCount() {
      return getVertices().size();
   }

   public boolean isReachable(String source, String target) {
      return breadthFirstSearch(source).contains(getVertex(target));
   }

   public boolean isStronglyConnected() {
      for(Vertex v: getVertices()) {
         for(Vertex v_ : getVertices()) {
            if(!(isReachable(v.getName(), v_.getName())) && v != v_) return false;
         }
      }
      return true;
   }
   
 
 
 
 
 
 
 
}


