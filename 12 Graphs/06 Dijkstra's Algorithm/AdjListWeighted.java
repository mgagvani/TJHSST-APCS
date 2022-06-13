// Name:   Manav Gagvani
// Date:   5-24-22
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces for 
 *              Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Neighbor implements Comparable<Neighbor>
{
   //2 Neighbors are equal if and only if they have the same name
   //implement all methods needed for a HashSet and TreeSet to work with Neighbor objects
   private final wVertex target;
   private final double edgeDistance;
   
   public Neighbor(wVertex t, double d) {
      target = t;
      edgeDistance = d;
   }
   
   //add all methods needed for a HashSet and TreeSet to function with Neighbor objects
   //use only target, not distances, since a vertex can't have 2 neighbors that have the same target
   //.........

   public wVertex getTarget() { return target; }
   public double getEdgeDistance() { return edgeDistance; }

   public int compareTo(Neighbor other) { return target.getName().compareTo(other.getTarget().getName()); }  
   public boolean equals(Object obj) { 
      Neighbor other = (Neighbor)obj;
      return target.equals(other.getTarget());
   }
   public int hashCode() { return target.hashCode(); }

   public String toString()
   {
      return target.getName() + " " + edgeDistance;  
   }
}

 /**************************************************************/
class PQelement implements Comparable<PQelement> { 
//used just for a PQ, contains a wVertex and a distance, also previous that is used for Dijksra 7
//compareTo is using the distanceToVertex to order them such that the PriorityQueue works
//will be used by the priority queue to order by distance
 
   private wVertex vertex;
   private Double distanceToVertex; 
   private wVertex previous; //for Dijkstra 7
      
   public PQelement(wVertex v, double d) {
      vertex = v;
      distanceToVertex = d;
      previous = null;
   }
   
   //getter and setter methods provided
   public wVertex getVertex() {
      return this.vertex;
   }
   
   public Double getDistanceToVertex() {
      return this.distanceToVertex;
   }
   
   public void setVertex(wVertex v) {
      this.vertex = v;
   }
   
   public void setDistanceToVertex(Double d) {
      this.distanceToVertex = d;
   }   
   
   public int compareTo(PQelement other) {
      //we assume no overflow will happen since distances will not go over the range of int
      return (int)(distanceToVertex - other.distanceToVertex);
   }
   
   public wVertex getPrevious()  //Dijkstra 7
   {
      return this.previous;
   }
   public void setPrevious(wVertex v) //Dijkstra 7
   {
      this.previous = v;
   } 
   
   //implement toString to match the sample output   
   public String toString()
   { 
      String toRet = "";
      toRet += vertex.getName();
      toRet += " " + getDistanceToVertex();
      toRet += " Previous: "; // for LAB 7
      if(previous != null) toRet += " " + previous.getName();
      else toRet += " null";

      
      return toRet;
   }
}

/********************* wVertexInterface ************************/
interface wVertexInterface 
{
   public String getName();
   
   public Set<Neighbor> getNeighbors();
   
   /*  adds to the neighbors set  
       called at the beginning of the lab*/
   public void addAdjacent(wVertex v, double d); 
     
    /*returns an arraylist of PQelements that store distanceToVertex to another wVertex  */
   public ArrayList<PQelement> getAlDistanceToVertex();
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v);
   
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v);
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double d); 
 
   public String toString();  
 
}

class wVertex implements Comparable<wVertex>, wVertexInterface 
{ 
   public static final double NODISTANCE = Double.POSITIVE_INFINITY; //constant to be used in class
   private final String name;
   private Set<Neighbor> neighbors;  
   private ArrayList<PQelement> alDistanceToVertex; //should have no duplicates, enforced by the getter/setter methods
  
   /* constructor, accessors, modifiers  */ 
   public wVertex(String s) {
      name = s;
      neighbors = new TreeSet<Neighbor>();
      alDistanceToVertex = new ArrayList<PQelement>();
   }
   
   
   /* 2 vertexes are equal if and only if they have the same name
      add all methods needed for a HashSet and TreeSet to function with Neighbor objects
      use only target, not distances, since a vertex can't have 2 neighbors that have the same target   
   */
   
   
   
   
   public String toString()
   { 
      String toReturn = name;
      toReturn += " "+ neighbors;
      toReturn += " List: " + alDistanceToVertex; 
      return toReturn;
   }

   public String getName() {
      return name;
   }

   public Set<Neighbor> getNeighbors() {
      return neighbors;
   }

   public void addAdjacent(wVertex v, double d) {

      neighbors.add(new Neighbor(v, d)); // compare name in Neighbor
      
   }

   public ArrayList<PQelement> getAlDistanceToVertex() {
      return alDistanceToVertex;
   }

   public PQelement getPQelement(wVertex v) {
      for(PQelement cur: alDistanceToVertex) if(cur.getVertex().getName().equals(v.getName())) return cur;
      return null;
   }

   public Double getDistanceToVertex(wVertex v) {
      if(getPQelement(v) == null) return null;
      else return getPQelement(v).getDistanceToVertex();
   }

   public void setDistanceToVertex(wVertex v, double d) {
      if(getPQelement(v) == null) alDistanceToVertex.add(new PQelement(v, d));
      else getPQelement(v).setDistanceToVertex(d);
      
   }

   public boolean equals(Object obj) {
      if(obj instanceof wVertex) {
         wVertex other = (wVertex)obj;
         return name.equals(other.getName());
      }
      return false;
   }

   public int hashCode() {
      return name.hashCode();
   }

   public int compareTo(wVertex other) {
      return name.compareTo(other.getName());
   }
}

/*********************   Interface for Graphs 6:  Dijkstra ****************/
interface AdjListWeightedInterface 
{
   public Set<wVertex> getVertices();  
   public Map<String, wVertex> getVertexMap();  //this is just for codepost testing
   public wVertex getVertex(String vName);
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName);
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d);
   public void minimumWeightPath(String vertexName); // Dijstra's algorithm
   public String toString();  
}  

 /***********************  Interface for Graphs 7:  Dijkstra with Cities   */
interface AdjListWeightedInterfaceWithCities 
{       
   public List<String> getShortestPathTo(wVertex vSource, wVertex target);
   public void readData(String vertexNames, String edgeListData) ;
}
 
/****************************************************************/ 
/**************** this is the graph  ****************************/
public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, wVertex> vertexMap = new TreeMap<String, wVertex>();
   private wVertex lastVertex;
   
   /* default constructor -- not needed!  */
  
   /* similar to AdjList, but handles distances (weights) and wVertex*/ 
   
   
   public String toString()
   {
      String strResult = "";
      for(String vName: vertexMap.keySet())
      {
         strResult += vertexMap.get(vName) + "\n"; 
      }
      return strResult;
   }
     
   public void readData(String vertexNames, String edgeListData) 
   {  
      Scanner sc = null, sc1 = null;
      try {
         sc = new Scanner(new File(vertexNames));
         sc1 = new Scanner(new File(edgeListData));
         
      }
      catch (Exception e) {
         System.out.println("error file not found: "+ e);
         System.exit(1);
      }
      sc.next();
      while(sc.hasNext()) addVertex(sc.next());
      while(sc1.hasNextLine()) {
         String temp = sc1.nextLine();
         addEdge(temp.split(" ")[0], temp.split(" ")[1], Double.parseDouble(temp.split(" ")[2]));
      }   
   }
      
   

   public Set<wVertex> getVertices() {
      Set<wVertex> toRet = new HashSet<wVertex>();
      toRet.addAll(vertexMap.values());
      return toRet;
   }

   public Map<String, wVertex> getVertexMap() {
      return vertexMap;
   }

   public wVertex getVertex(String vName) {
      return vertexMap.get(vName);
   }

   public void addVertex(String vName) {
      vertexMap.putIfAbsent(vName, new wVertex(vName));
      
   }

   public void addEdge(String source, String target, double d) {
      vertexMap.get(source).addAdjacent(vertexMap.get(target), d);
   }
      
   

   public void minimumWeightPath(String vertexName) {
      PriorityQueue<PQelement> pq = new PriorityQueue<PQelement>();
      PQelement source = new PQelement(getVertex(vertexName), 0.0);
      
      for(wVertex n: getVertices()) {
         if(n.compareTo(vertexMap.get(vertexName)) != 0) { // It's not the source
            source.getVertex().getAlDistanceToVertex().add(new PQelement(n, wVertex.NODISTANCE));
         }
         else { // it is the source
            getVertex(vertexName).getAlDistanceToVertex().add(source);
         }
      }
      pq.add(source);
      
      while(!pq.isEmpty()) {
         PQelement dq = pq.remove();
         for (Neighbor n: dq.getVertex().getNeighbors()) {
            double newCost = dq.getDistanceToVertex() + n.getEdgeDistance();
            double oldCost = source.getVertex().getDistanceToVertex(n.getTarget());
            if(newCost < oldCost) {
               pq.add(new PQelement(n.getTarget(), newCost));
               source.getVertex().setDistanceToVertex(n.getTarget(), newCost);
               source.getVertex().getPQelement(n.getTarget()).setPrevious(dq.getVertex());
            }
         }
      }
      /// wVertex start = vertexMap.get(vertexName);
      /// this.lastVertex = start;
      /// for(wVertex v: getVertices()) {
      ///    start.setDistanceToVertex(v, Double.POSITIVE_INFINITY);
      /// }
      /// start.setDistanceToVertex(start, 0);
      /// PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      /// pq.add(start);
      /// while(!pq.isEmpty()) {
      ///    wVertex u = pq.poll();  
      ///    pq.remove(u);
      ///    for(Neighbor v: u.getNeighbors()) {
      ///       wVertex vV = v.getTarget();
      ///       double alt = start.getDistanceToVertex(u) + v.getEdgeDistance();
      ///       if(alt < start.getDistanceToVertex(vV)) {
      ///          pq.remove(vV);
      ///          start.setDistanceToVertex(vV, alt);
      ///          start.getPQelement(vV).setPrevious(u);
      ///          pq.add(vV);
      ///          start.getPQelement(v.getTarget()).setPrevious(vV);
      ///       }
      ///    }
      /// }
   }

   public List<String> getShortestPathTo(wVertex vSource, wVertex targe) {
      // List<String> reachable_vertices = new ArrayList<String>();
      // Stack<wVertex> queue = new Stack<wVertex>();
// 
// 
      // for(Neighbor v: vSource.getNeighbors()) {
      //    queue.push(v.getTarget());
      // }
      // while(!queue.isEmpty()) {
      //    wVertex popped = queue.pop();
      //    if(!reachable_vertices.contains(popped.getName())) {
      //       reachable_vertices.add(popped.getName());
      //       for(Neighbor v: popped.getNeighbors()) {
      //          queue.push(v.getTarget());
      //       }
      //    }
      // }
// 
      // return reachable_vertices;
      LinkedList<String> list = new LinkedList<String>();
      wVertex target = targe;
      while (target != null) {
         list.addFirst(target.getName());
         target = vSource.getPQelement(target).getPrevious();
      }
      return list;
   }
}