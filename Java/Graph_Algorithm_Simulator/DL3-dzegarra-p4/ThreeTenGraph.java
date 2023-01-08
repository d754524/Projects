//TODO:
//  (1) Implement the graph!
//  (2) Update this code to meet the style and JavaDoc requirements.

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.DirectedGraph;

import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.EdgeType;

import org.apache.commons.collections15.Factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//You may use your hash map and set if you'd like
//or you may import the java.util versions.
//The interface is the same for both, except for
//your constructor, the code you write here (in
//ThreeTenGraph) should be the same for either one!

//Uncomment the following lines if you want to use the java.util version
//import java.util.HashMap; //or use ThreeTenMap!
//import java.util.HashSet; //or use ThreeTenSet!
	
import java.util.NoSuchElementException;

class ThreeTenGraph	implements Graph<ThreeTenNode, ThreeTenEdge>, DirectedGraph<ThreeTenNode, ThreeTenEdge> {
	
	//********************************************************************************
	//   YOU MAY instantiate this storage with a ThreeTenMap or HashMap
	//   IF YOU CHOOSE TO USE java.util.HashMap, but you must use
	//   this variable (with this name) for the map storage
	//   (we will be checking that your map has the right values!).
	//********************************************************************************
	/**
	 * Hash table that'll store the graphs 
	 */
	ThreeTenMap<ThreeTenNode,ThreeTenMap<ThreeTenNode,ThreeTenEdge>> storage;
	
	//********************************************************************************
	//   HINTS:
	//   - You'll save yourself a lot of headache if you deep copy all collections
	//     rather than returning the original collections (what if you return a HashSet
	//     and someone modifies it?!).
	//   - You'll probably want some additional storage for quickly finding other
	//     things...
	//   - ArrayList, HashSet, HashMap, ThreeTenSet, and ThreeTenMap all implement
	//     the collection interface. So if you're supposed to return a collection,
	//     then return one of those.
	//********************************************************************************
	
	/**
	 * Creates a new graph. Initializing all appropriate instance variables.
	 */
	
	/**
	 * list of nodes that make up the graph
	 */
	static ThreeTenSet<ThreeTenNode> vertexSet = new ThreeTenSet<>();
	/**
	 * list of edges that make up the graph
	 */
	static ThreeTenSet<ThreeTenEdge> edgesSet = new ThreeTenSet<>();
	/**
	 * Hash Table with an specific edge and its predecessor 
	 */
	static ThreeTenMap<ThreeTenEdge, ThreeTenNode> v1s = new ThreeTenMap<>(10);
	/**
	 * Hash Table with an specific edge and its successor 
	 */
	static ThreeTenMap<ThreeTenEdge, ThreeTenNode> v2s = new ThreeTenMap<>(10);
	
	/**
	 * constructor
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenGraph() {
		
	}
	
	/**
	 * Returns a view of all edges in this graph. In general, this
	 * obeys the Collection contract, and therefore makes no guarantees 
	 * about the ordering of the vertices within the set.
	 * @return a Collection view of all edges in this graph
	 */
	public ThreeTenSet<ThreeTenEdge> getEdges() {
		return edgesSet;
	}
	
	/**
	 * Returns a view of all vertices in this graph. In general, this
	 * obeys the Collection contract, and therefore makes no guarantees 
	 * about the ordering of the vertices within the set.
	 * @return a Collection view of all vertices in this graph
	 */
	public ThreeTenSet<ThreeTenNode> getVertices() {
		return vertexSet;
	}
	
	/**
	 * Returns the number of edges in this graph.
	 * @return the number of edges in this graph
	 */
	public int getEdgeCount() {
		return edgesSet.size();
	}
	
	/**
	 * Returns the number of vertices in this graph.
	 * @return the number of vertices in this graph
	 */
	public int getVertexCount() {
		return vertexSet.size();
	}
	
	/**
	 * If directed_edge is a directed edge in this graph, returns the source; 
	 * otherwise returns null. 
	 * The source of a directed edge d is defined to be the vertex for which  
	 * d is an outgoing edge.
	 * directed_edge is guaranteed to be a directed edge if 
	 * its EdgeType is DIRECTED. 
	 * @param directed_edge
	 * @return  the source of directed_edge if it is a directed edge in this graph, or null otherwise
	 */
	public ThreeTenNode getSource(ThreeTenEdge directed_edge) {
		return v1s.get(directed_edge);
	}

	/**
	 * If directed_edge is a directed edge in this graph, returns the destination; 
	 * otherwise returns null. 
	 * The destination of a directed edge d is defined to be the vertex 
	 * incident to d for which  
	 * d is an incoming edge.
	 * directed_edge is guaranteed to be a directed edge if 
	 * its EdgeType is DIRECTED. 
	 * @param directed_edge
	 * @return  the destination of directed_edge if it is a directed edge in this graph, or null otherwise
	 */
	public ThreeTenNode getDest(ThreeTenEdge directed_edge) {
		return v2s.get(directed_edge);
	}

	/**
	 * Returns a Collection view of the predecessors of vertex 
	 * in this graph.  A predecessor of vertex is defined as a vertex v 
	 * which is connected to 
	 * vertex by an edge e, where e is an outgoing edge of 
	 * v and an incoming edge of vertex.
	 * @param vertex	the vertex whose predecessors are to be returned
	 * @return  a Collection view of the predecessors of 
	 * vertex in this graph
	 */
	public Collection<ThreeTenNode> getPredecessors(ThreeTenNode vertex) {
		ThreeTenSet<ThreeTenNode> predecessors = new ThreeTenSet<>();
		ThreeTenSet<ThreeTenEdge> edges = new ThreeTenSet<>();
		ThreeTenNode vertex2;
		if(v2s.containsValue(vertex) == false) {
			return null;
		}else {
			for(ThreeTenEdge e : v2s.keySet()) {
				vertex2 = v2s.get(e);
				if(vertex.getId() == vertex2.getId()) {
					edges.add(e);
				}
			}
			Iterator<ThreeTenEdge> valuesEdges = edges.iterator();
			while(valuesEdges.hasNext()) {
				predecessors.add(v1s.get(valuesEdges.next()));
			}
		}
		
		return predecessors;
	}
	
	/**
	 * Returns a Collection view of the successors of vertex 
	 * in this graph.  A successor of vertex is defined as a vertex v 
	 * which is connected to 
	 * vertex by an edge e, where e is an incoming edge of 
	 * v and an outgoing edge of vertex.
	 * @param vertex	the vertex whose predecessors are to be returned
	 * @return  a Collection view of the successors of 
	 * vertex in this graph
	 */
	public Collection<ThreeTenNode> getSuccessors(ThreeTenNode vertex) {
		ThreeTenSet<ThreeTenNode> successors = new ThreeTenSet<>();
		ThreeTenSet<ThreeTenEdge> edges = new ThreeTenSet<>();
		ThreeTenNode vertex2;
		if(v1s.containsValue(vertex) == false) {
			return null;
		}else {
			for(ThreeTenEdge e : v1s.keySet()) {
				vertex2 = v1s.get(e);
				if(vertex.getId() == vertex2.getId()) {
					edges.add(e);
				}
			}
			Iterator<ThreeTenEdge> valuesEdges = edges.iterator();
			while(valuesEdges.hasNext()) {
				successors.add(v2s.get(valuesEdges.next()));
			}
		}
		
		return successors;
	}
	
	/**
	 * Returns a Collection view of the incoming edges incident to vertex
	 * in this graph.
	 * @param vertex	the vertex whose incoming edges are to be returned
	 * @return  a Collection view of the incoming edges incident 
	 * to vertex in this graph
	 */
	public Collection<ThreeTenEdge> getInEdges(ThreeTenNode vertex) { // v2s
		ThreeTenSet<ThreeTenEdge> edges = new ThreeTenSet<>();
		//ArrayList<ThreeTenEdge> edges = new ArrayList<>();
		ThreeTenNode vertex2;
		if(v2s.containsValue(vertex) == false) {
			return edges;
		}else {
			for(ThreeTenEdge e : v2s.keySet()) {
				vertex2 = v2s.get(e);
				if(vertex2.getId() == vertex.getId()) {
					edges.add(e);
				}
			}
		}
		
		return edges;
	}
	
	/**
	 * Returns a Collection view of the outgoing edges incident to vertex
	 * in this graph.
	 * @param vertex	the vertex whose outgoing edges are to be returned
	 * @return  a Collection view of the outgoing edges incident 
	 * to vertex in this graph
	 */
	public Collection<ThreeTenEdge> getOutEdges(ThreeTenNode vertex) {
		ThreeTenSet<ThreeTenEdge> edges = new ThreeTenSet<>();
		//ArrayList<ThreeTenEdge> edges = new ArrayList<>();
		ThreeTenNode vertex2;
		if(v1s.containsValue(vertex) == false) {
			return edges;
		}else {
			for(ThreeTenEdge e : v1s.keySet()) {
				vertex2 = v1s.get(e);
				if(vertex2.getId() == vertex.getId()) {
					edges.add(e);
				}
			}
		}
		
		return edges;
	}
	
	/**
	 * Returns an edge that connects v1 to v2.
	 * If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting 
	 * v1 to v2), any of these edges 
	 * may be returned.  findEdgeSet(v1, v2) may be 
	 * used to return all such edges.
	 * Returns null if either of the following is true:
	 * <ul>
	 * <li/>v1 is not connected to v2
	 * <li/>either v1 or v2 are not present in this graph
	 * </ul> 
	 * <p><b>Note</b>: for purposes of this method, v1 is only considered to be connected to
	 * v2 via a given <i>directed</i> edge e if
	 * v1 == e.getSource() && v2 == e.getDest() evaluates to true.
	 * (v1 and v2 are connected by an undirected edge u if 
	 * u is incident to both v1 and v2.)
	 * 
	 * @return  an edge that connects v1 to v2, 
	 * or null if no such edge exists (or either vertex is not present)
	 * @see Hypergraph#findEdgeSet(Object, Object) 
	 */
	public ThreeTenEdge findEdge(ThreeTenNode v1, ThreeTenNode v2) {
		// either v1 or v2 are not present in this graph => return null
		if((containsVertex(v1) == false) || (containsVertex(v2) == false)) {
			System.out.println("Return 1");
			return null;
		}
		// v1 is not connected to v2 => return null
		Collection<ThreeTenNode> successorsV1 = getSuccessors(v1);
		Collection<ThreeTenNode> predecessorsV2 = getPredecessors(v2);
		if((successorsV1.contains(v2) == false) || (predecessorsV2.contains(v1) == false)) {
			return null;
		}
		ThreeTenEdge edge = null;
		for(ThreeTenEdge e : v1s.keySet()) {
			if(v1s.get(e).getId() == v1.getId()) {
				edge = e;
			}
		}
		
		return edge;
	}

	/**
	 * Adds edge e to this graph such that it connects 
	 * vertex v1 to v2. 
	 * If this graph does not contain v1, v2, 
	 * or both, implementations may choose to either silently add 
	 * the vertices to the graph or throw an IllegalArgumentException.
	 * If this graph assigns edge types to its edges, the edge type of
	 * e will be the default for this graph.
	 * See Hypergraph.addEdge() for a listing of possible reasons
	 * for failure. In addition, this should fail if the vertices or edge
	 * violates any given restrictions (such as invalid IDs).
	 * Equivalent to addEdge(e, new Pair<ThreeTenNode>(v1, v2)).
	 * @param e the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object, EdgeType)
	 */
	public boolean addEdge(ThreeTenEdge e, ThreeTenNode v1, ThreeTenNode v2) {
		return edgesSet.add(e) && (v1s.put(e, v1) == null) && (v2s.put(e,  v2) == null);
	}
	
	/**
	 * Adds vertex to this graph.
	 * Fails if vertex is null or already in the graph.
	 * Also fails if the vertex violates and constraints given in
	 * the project (such as ID restrictions).
	 * 
	 * @param vertex	the vertex to add
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if vertex is null
	 */
	public boolean addVertex(ThreeTenNode vertex) {
		return vertexSet.add(vertex);
	}

	/**
	 * Removes edge from this graph.
	 * Fails if edge is null, or is otherwise not an element of this graph.
	 * 
	 * @param edge the edge to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeEdge(ThreeTenEdge edge) {
		return edgesSet.remove(edge) && (v1s.remove(edge) != null) && (v2s.remove(edge) != null);
	}
	
	/**
	 * Removes vertex from this graph.
	 * As a side effect, removes any edges e incident to vertex if the 
	 * removal of vertex would cause e to be incident to an illegal
	 * number of vertices.  (Thus, for example, incident hyperedges are not removed, but 
	 * incident edges--which must be connected to a vertex at both endpoints--are removed.) 
	 * 
	 * <p>Fails under the following circumstances:
	 * <ul>
	 * <li/>vertex is not an element of this graph
	 * <li/>vertex is null
	 * </ul>
	 * 
	 * @param vertex the vertex to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeVertex(ThreeTenNode vertex) {
		if(vertex == null) {
			return false;
		}
		if(containsVertex(vertex) == false) {
			return false;
		}
		for(ThreeTenEdge e : getInEdges(vertex)) {
			edgesSet.remove(e);
			v1s.remove(e);
			v2s.remove(e);
		}
		for(ThreeTenEdge e : getOutEdges(vertex)) {
			edgesSet.remove(e);
			v1s.remove(e);
			v2s.remove(e);
		}
		return vertexSet.remove(vertex);
	}
	
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------
	
	/**
	 * Converts the actual graph to a string
	 * @return null - not implemented
	 **/
	public String toString() {
		return null;
	}
	
	public static void main(String[] args) {
		//constructs a graph
		
		ThreeTenGraph graph1 = new ThreeTenGraph();
		for(int i = 0; i < 3; i++) {
			graph1.addVertex(new ThreeTenNode(""+i));
		}
		for(ThreeTenNode n : graph1.getVertices()) {
			graph1.addEdge(new ThreeTenEdge((int)(Math.random()*100)), n, n);
		}
		
		if(graph1.getVertexCount() == 3 && graph1.getEdgeCount() == 3) {
			System.out.println("Yay 1");
		}
		
		//create a set of nodes and edges to test with
		ThreeTenNode[] nodes = {
			new ThreeTenNode("A"), 
			new ThreeTenNode("B"), 
			new ThreeTenNode("X"), 
			new ThreeTenNode("7"), 
			new ThreeTenNode("M"), 
			new ThreeTenNode("D"), 
			new ThreeTenNode("33"), 
			new ThreeTenNode("Cat"), 
			new ThreeTenNode("2"), 
			new ThreeTenNode("-1")
		};
		
		ThreeTenEdge[] edges = {
			new ThreeTenEdge(10000000), 
			new ThreeTenEdge(4), 
			new ThreeTenEdge(Integer.MAX_VALUE),
			new ThreeTenEdge(Integer.MIN_VALUE), 
			new ThreeTenEdge(500), 
			new ThreeTenEdge(11),
			new ThreeTenEdge(12),
			new ThreeTenEdge(13),
			new ThreeTenEdge(14),
			new ThreeTenEdge(15),
			new ThreeTenEdge(16),
			new ThreeTenEdge(17),
			new ThreeTenEdge(18),
			new ThreeTenEdge(19),
			new ThreeTenEdge(20)
		};
		
		//constructs a graph
		ThreeTenGraph graph2 = new ThreeTenGraph();
		for(ThreeTenNode n : nodes) {
			graph2.addVertex(n);
		}
		graph2.addEdge(edges[0],nodes[0],nodes[1]);
		System.out.println("Creando conexión: <"+edges[0].getId()+"> "+nodes[0].getId()+" -> "+nodes[1].getId());
		graph2.addEdge(edges[1],nodes[1],nodes[2]);
		System.out.println("Creando conexión: <"+edges[1].getId()+"> "+nodes[1].getId()+" -> "+nodes[2].getId());
		graph2.addEdge(edges[2],nodes[3],nodes[2]);
		System.out.println("Creando conexión: <"+edges[2].getId()+"> "+nodes[3].getId()+" -> "+nodes[2].getId());
		graph2.addEdge(edges[3],nodes[6],nodes[7]);
		System.out.println("Creando conexión: <"+edges[3].getId()+"> "+nodes[6].getId()+" -> "+nodes[7].getId());
		graph2.addEdge(edges[4],nodes[8],nodes[9]);
		System.out.println("Creando conexión: <"+edges[4].getId()+"> "+nodes[8].getId()+" -> "+nodes[9].getId());
		graph2.addEdge(edges[5],nodes[9],nodes[0]);
		System.out.println("Creando conexión: <"+edges[5].getId()+"> "+nodes[9].getId()+" -> "+nodes[0].getId());
		graph2.addEdge(edges[6],nodes[0],nodes[3]);
		System.out.println("Creando conexión: <"+edges[6].getId()+"> "+nodes[0].getId()+" -> "+nodes[3].getId());
		graph2.addEdge(edges[7],nodes[0],nodes[4]);
		System.out.println("Creando conexión: <"+edges[7].getId()+"> "+nodes[0].getId()+" -> "+nodes[4].getId());
		graph2.addEdge(edges[8],nodes[0],nodes[5]);
		System.out.println("Creando conexión: <"+edges[8].getId()+"> "+nodes[0].getId()+" -> "+nodes[5].getId());
		graph2.addEdge(edges[9],nodes[6],nodes[0]);
		System.out.println("Creando conexión: <"+edges[9].getId()+"> "+nodes[6].getId()+" -> "+nodes[0].getId());
		graph2.addEdge(edges[10],nodes[7],nodes[0]);
		System.out.println("Creando conexión: <"+edges[10].getId()+"> "+nodes[7].getId()+" -> "+nodes[0].getId());
		graph2.addEdge(edges[11],nodes[8],nodes[0]);
		System.out.println("Creando conexión: <"+edges[11].getId()+"> "+nodes[8].getId()+" -> "+nodes[0].getId());
		graph2.addEdge(edges[12],nodes[2],nodes[7]);
		System.out.println("Creando conexión: <"+edges[12].getId()+"> "+nodes[2].getId()+" -> "+nodes[7].getId());
		graph2.addEdge(edges[13],nodes[9],nodes[2]);
		System.out.println("Creando conexión: <"+edges[13].getId()+"> "+nodes[9].getId()+" -> "+nodes[2].getId());
		graph2.addEdge(edges[14],nodes[8],nodes[7]);
		System.out.println("Creando conexión: <"+edges[14].getId()+"> "+nodes[8].getId()+" -> "+nodes[7].getId());
		
		System.out.println("############################");
		System.out.println("Pred "+nodes[0].getId());
		for(ThreeTenNode n : graph2.getPredecessors(nodes[0])) {
			System.out.println("\t"+n.getId());
		}
		
		System.out.println("############################");
		System.out.println("Pred "+nodes[2].getId());
		for(ThreeTenNode n : graph2.getPredecessors(nodes[2])) {
			System.out.println("\t"+n.getId());
		}
		
		System.out.println("############################");
		System.out.println("Pred "+nodes[7].getId());
		for(ThreeTenNode n : graph2.getPredecessors(nodes[7])) {
			System.out.println("\t"+n.getId());
		}
		
		System.out.println("############################");
		System.out.println("Succ "+nodes[0].getId());
		for(ThreeTenNode n : graph2.getSuccessors(nodes[0])) {
			System.out.println("\t"+n.getId());
		}
		
		System.out.println("############################");
		System.out.println("Succ "+nodes[6].getId());
		for(ThreeTenNode n : graph2.getSuccessors(nodes[6])) {
			System.out.println("\t"+n.getId());
		}
		
		System.out.println("############################");
		System.out.println("Get In Edges "+nodes[0].getId());
		for(ThreeTenEdge e : graph2.getInEdges(nodes[0])) {
			System.out.println("\t<"+e.getId()+"> "+v1s.get(e).getId()+" -> "+v2s.get(e).getId());
		}
		
		System.out.println("############################");
		System.out.println("Get Out Edges "+nodes[0].getId());
		for(ThreeTenEdge e : graph2.getOutEdges(nodes[0])) {
			System.out.println("\t<"+e.getId()+"> "+v1s.get(e).getId()+" -> "+v2s.get(e).getId());
		}
		
		System.out.println("############################");
		System.out.println("Edge into node "+nodes[0].getId()+" and node "+nodes[4].getId());
		System.out.println("Edge: <"+graph2.findEdge(nodes[0], nodes[4]).getId()+">");
		
		System.out.println("############################");
		System.out.println("Edge into node "+nodes[3].getId()+" and node "+nodes[2].getId());
		System.out.println("Edge: <"+graph2.findEdge(nodes[3], nodes[2]).getId()+">");
		
		System.out.println("############################");
		System.out.println("Is node "+nodes[0].getId()+" in graph2 ? "+graph2.containsVertex(nodes[0]));
		
		System.out.println("############################");
		System.out.println("Remove node "+nodes[0].getId());
		graph2.removeVertex(nodes[0]);
		System.out.println("Ok! Removed");
		
		System.out.println("############################");
		System.out.println("Is node "+nodes[0].getId()+" in graph2 ? "+graph2.containsVertex(nodes[0]));
		
		if(graph2.containsVertex(new ThreeTenNode("A")) && graph2.containsEdge(new ThreeTenEdge(10000000))) {
			System.out.println("Yay 2");
		}
		/*
		System.out.println("############################");
		System.out.println("All nodes of graph2");
		for(ThreeTenNode n : nodes) {
			System.out.println(n.getId()+" - "+n.getText());
		}
		System.out.println("############################");
		System.out.println("All edges of graph2");
		for(ThreeTenEdge e : edgesSet) {
			System.out.println(e.getId()+" - "+e.getWeight());
		}
		System.out.println("############################");
		System.out.println("All conections of graph2");
		System.out.println("Edge\t\tPredecessor\t\tSuccessor");
		for(ThreeTenEdge e : edgesSet) {
			System.out.println(e.getId()+"\t\t"+v1s.get(e).getId()+"\t\t"+v2s.get(e).getId());
		}
		*/
		//lot more testing here...
		
		//If your graph "looks funny" you probably want to check:
		//getVertexCount(), getVertices(), getInEdges(vertex),
		//and getIncidentVertices(incomingEdge) first. These are
		//used by the layout class.
	}
	
	//********************************************************************************
	//   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// This is a good place to look for "optimizing" your code to be faster.
	//********************************************************************************
	
	/**
	 * Returns true if this graph's vertex collection contains vertex.
	 * Equivalent to getVertices().contains(vertex).
	 * @param vertex the vertex whose presence is being queried
	 * @return true iff this graph contains a vertex vertex
	 */
	public boolean containsVertex(ThreeTenNode vertex) {
		return getVertices().contains(vertex);
	}
	
	/**
	 * Returns true if this graph's edge collection contains edge.
	 * Equivalent to getEdges().contains(edge).
	 * @param edge the edge whose presence is being queried
	 * @return true iff this graph contains an edge edge
	 */
	public boolean containsEdge(ThreeTenEdge edge) {
		return getEdges().contains(edge);
	}

	/**
	 * Returns true if vertex and edge 
	 * are incident to each other.
	 * Equivalent to getIncidentEdges(vertex).contains(edge) and to
	 * getIncidentVertices(edge).contains(vertex).
	 * @param vertex
	 * @param edge
	 * @return true if vertex and edge 
	 * are incident to each other
	 */
	public boolean isIncident(ThreeTenNode vertex, ThreeTenEdge edge) {
		return getIncidentEdges(vertex).contains(edge);
	}

	/**
	 * Returns true if v1 and v2 share an incident edge.
	 * Equivalent to getNeighbors(v1).contains(v2).
	 * 
	 * @param v1 the first vertex to test
	 * @param v2 the second vertex to test
	 * @return true if v1 and v2 share an incident edge
	 */
	public boolean isNeighbor(ThreeTenNode v1, ThreeTenNode v2) {
		return getNeighbors(v1).contains(v2);
	}
	
	/**
	 * Returns the collection of vertices in this graph which are connected to edge.
	 * Note that for some graph types there are guarantees about the size of this collection
	 * (i.e., some graphs contain edges that have exactly two endpoints, which may or may 
	 * not be distinct).  Implementations for those graph types may provide alternate methods 
	 * that provide more convenient access to the vertices.
	 * 
	 * @param edge the edge whose incident vertices are to be returned
	 * @return  the collection of vertices which are connected to edge, 
	 * or null if edge is not present
	 */
	public Collection<ThreeTenNode> getIncidentVertices(ThreeTenEdge edge) {
		if(!containsEdge(edge)) return null;
		ArrayList<ThreeTenNode> nodes = new ArrayList<>();
		nodes.add(getSource(edge));
		nodes.add(getDest(edge));
		return nodes;
	}

	/**
	 * Returns the collection of vertices which are connected to vertex
	 * via any edges in this graph.
	 * If vertex is connected to itself with a self-loop, then 
	 * it will be included in the collection returned.
	 * 
	 * @param vertex the vertex whose neighbors are to be returned
	 * @return  the collection of vertices which are connected to vertex, 
	 * or null if vertex is not present
	 */
	public Collection<ThreeTenNode> getNeighbors(ThreeTenNode vertex) {
		if(!containsVertex(vertex)) return null;
		ArrayList<ThreeTenNode> nodes = new ArrayList<>();
		nodes.addAll(getPredecessors(vertex));
		for(ThreeTenNode n : getSuccessors(vertex)) {
			if(!nodes.contains(n)) {
				nodes.add(n);
			}
		}
		return nodes;
	}
	
	/**
	 * Returns the collection of edges in this graph which are connected to vertex.
	 * 
	 * @param vertex the vertex whose incident edges are to be returned
	 * @return  the collection of edges which are connected to vertex, 
	 * or null if vertex is not present
	 */
	public Collection<ThreeTenEdge> getIncidentEdges(ThreeTenNode vertex) {
		if(!containsVertex(vertex)) return null;
		ArrayList<ThreeTenEdge> edges = new ArrayList<>();
		edges.addAll(getInEdges(vertex));
		edges.addAll(getOutEdges(vertex));
		return edges;
	}
	
	/**
	 * Returns true if v1 is a predecessor of v2 in this graph.
	 * Equivalent to v1.getPredecessors().contains(v2).
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a predecessor of v2, and false otherwise.
	 */
	public boolean isPredecessor(ThreeTenNode v1, ThreeTenNode v2) {
		return getPredecessors(v1).contains(v2);
	}
	
	/**
	 * Returns true if v1 is a successor of v2 in this graph.
	 * Equivalent to v1.getSuccessors().contains(v2).
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a successor of v2, and false otherwise.
	 */
	public boolean isSuccessor(ThreeTenNode v1, ThreeTenNode v2) {
		return getSuccessors(v1).contains(v2);
	}
	
	/**
	 * Returns the number of edges incident to vertex.  
	 * Special cases of interest:
	 * <ul>
	 * <li/> Incident self-loops are counted once.
	 * <li> If there is only one edge that connects this vertex to
	 * each of its neighbors (and vice versa), then the value returned 
	 * will also be equal to the number of neighbors that this vertex has
	 * (that is, the output of getNeighborCount).
	 * <li> If the graph is directed, then the value returned will be 
	 * the sum of this vertex's indegree (the number of edges whose 
	 * destination is this vertex) and its outdegree (the number
	 * of edges whose source is this vertex), minus the number of
	 * incident self-loops (to avoid double-counting).
	 * </ul>
	 * <p>Equivalent to getIncidentEdges(vertex).size().
	 * 
	 * @param vertex the vertex whose degree is to be returned
	 * @return the degree of this node
	 * @see Hypergraph#getNeighborCount(Object)
	 */
	public int degree(ThreeTenNode vertex) {
		return getIncidentEdges(vertex).size();
	}

	/**
	 * Returns the number of vertices that are adjacent to vertex
	 * (that is, the number of vertices that are incident to edges in vertex's
	 * incident edge set).
	 * 
	 * <p>Equivalent to getNeighbors(vertex).size().
	 * @param vertex the vertex whose neighbor count is to be returned
	 * @return the number of neighboring vertices
	 */
	public int getNeighborCount(ThreeTenNode vertex) {
		return getNeighbors(vertex).size();
	}
	
	/**
	 * Returns the number of incoming edges incident to vertex.
	 * Equivalent to getInEdges(vertex).size().
	 * @param vertex	the vertex whose indegree is to be calculated
	 * @return  the number of incoming edges incident to vertex
	 */
	public int inDegree(ThreeTenNode vertex) {
		return getInEdges(vertex).size();
	}
	
	/**
	 * Returns the number of outgoing edges incident to vertex.
	 * Equivalent to getOutEdges(vertex).size().
	 * @param vertex	the vertex whose outdegree is to be calculated
	 * @return  the number of outgoing edges incident to vertex
	 */
	public int outDegree(ThreeTenNode vertex) {
		return getOutEdges(vertex).size();
	}

	/**
	 * Returns the number of predecessors that vertex has in this graph.
	 * Equivalent to vertex.getPredecessors().size().
	 * @param vertex the vertex whose predecessor count is to be returned
	 * @return  the number of predecessors that vertex has in this graph
	 */
	public int getPredecessorCount(ThreeTenNode vertex) {
		return getPredecessors(vertex).size();
	}
	
	/**
	 * Returns the number of successors that vertex has in this graph.
	 * Equivalent to vertex.getSuccessors().size().
	 * @param vertex the vertex whose successor count is to be returned
	 * @return  the number of successors that vertex has in this graph
	 */
	public int getSuccessorCount(ThreeTenNode vertex) {
		return getSuccessors(vertex).size();
	}
	
	/**
	 * Returns the vertex at the other end of edge from vertex.
	 * (That is, returns the vertex incident to edge which is not vertex.)
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return the vertex at the other end of edge from vertex
	 */
	public ThreeTenNode getOpposite(ThreeTenNode vertex, ThreeTenEdge edge) {
		Pair<ThreeTenNode> p = getEndpoints(edge);
		if(p.getFirst().equals(vertex)) {
			return p.getSecond();
		}
		else {
			return p.getFirst();
		}
	}
	
	/**
	 * Returns all edges that connects v1 to v2.
	 * If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting 
	 * v1 to v2), any of these edges 
	 * may be returned.  findEdgeSet(v1, v2) may be 
	 * used to return all such edges.
	 * Returns null if v1 is not connected to v2.
	 * <br/>Returns an empty collection if either v1 or v2 are not present in this graph.
	 *  
	 * <p><b>Note</b>: for purposes of this method, v1 is only considered to be connected to
	 * v2 via a given <i>directed</i> edge d if
	 * v1 == d.getSource() && v2 == d.getDest() evaluates to true.
	 * (v1 and v2 are connected by an undirected edge u if 
	 * u is incident to both v1 and v2.)
	 * 
	 * @return  a collection containing all edges that connect v1 to v2, 
	 * or null if either vertex is not present
	 * @see Hypergraph#findEdge(Object, Object) 
	 */
	public Collection<ThreeTenEdge> findEdgeSet(ThreeTenNode v1, ThreeTenNode v2) {
		ThreeTenEdge edge = findEdge(v1, v2);
		if(edge == null) {
			return null;
		}
		
		ArrayList<ThreeTenEdge> ret = new ArrayList<>();
		ret.add(edge);
		return ret;
		
	}
	
	/**
	 * Returns true if vertex is the source of edge.
	 * Equivalent to getSource(edge).equals(vertex).
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true iff vertex is the source of edge
	 */
	public boolean isSource(ThreeTenNode vertex, ThreeTenEdge edge) {
		return getSource(edge).equals(vertex);
	}
	
	/**
	 * Returns true if vertex is the destination of edge.
	 * Equivalent to getDest(edge).equals(vertex).
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true iff vertex is the destination of edge
	 */
	public boolean isDest(ThreeTenNode vertex, ThreeTenEdge edge) {
		return getDest(edge).equals(vertex);
	}
	
	/**
	 * Adds edge e to this graph such that it connects 
	 * vertex v1 to v2.
	 * Equivalent to addEdge(e, new Pair<ThreeTenNode>(v1, v2)).
	 * If this graph does not contain v1, v2, 
	 * or both, implementations may choose to either silently add 
	 * the vertices to the graph or throw an IllegalArgumentException.
	 * If edgeType is not legal for this graph, this method will
	 * throw IllegalArgumentException.
	 * See Hypergraph.addEdge() for a listing of possible reasons
	 * for failure.
	 * @param e the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @param edgeType the type to be assigned to the edge
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object)
	 */
	public boolean addEdge(ThreeTenEdge e, ThreeTenNode v1, ThreeTenNode v2, EdgeType edgeType) {
		//NOTE: Only undirected edges allowed
		
		if(edgeType != EdgeType.DIRECTED) {
			throw new IllegalArgumentException();
		}
		
		return addEdge(e, v1, v2);
	}
	
	/**
	 * Adds edge to this graph.
	 * Fails under the following circumstances:
	 * <ul>
	 * <li/>edge is already an element of the graph 
	 * <li/>either edge or vertices is null
	 * <li/>vertices has the wrong number of vertices for the graph type
	 * <li/>vertices are already connected by another edge in this graph,
	 * and this graph does not accept parallel edges
	 * </ul>
	 * 
	 * @param edge
	 * @param vertices
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null, 
	 * or if a different vertex set in this graph is already connected by edge, 
	 * or if vertices are not a legal vertex set for edge 
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(ThreeTenEdge edge, Collection<? extends ThreeTenNode> vertices) {
		if(edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}
		
		ThreeTenNode[] vs = (ThreeTenNode[])vertices.toArray();
		return addEdge(edge, vs[0], vs[1]);
	}

	/**
	 * Adds edge to this graph with type edge_type.
	 * Fails under the following circumstances:
	 * <ul>
	 * <li/>edge is already an element of the graph 
	 * <li/>either edge or vertices is null
	 * <li/>vertices has the wrong number of vertices for the graph type
	 * <li/>vertices are already connected by another edge in this graph,
	 * and this graph does not accept parallel edges
	 * <li/>edge_type is not legal for this graph
	 * </ul>
	 * 
	 * @param edge
	 * @param vertices
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null, 
	 * or if a different vertex set in this graph is already connected by edge, 
	 * or if vertices are not a legal vertex set for edge 
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(ThreeTenEdge edge, Collection<? extends ThreeTenNode> vertices, EdgeType edge_type) {
		if(edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}
		
		ThreeTenNode[] vs = (ThreeTenNode[])vertices.toArray();
		return addEdge(edge, vs[0], vs[1], edge_type);
	}
	
	/**
	 * Returns the number of edges of type edge_type in this graph.
	 * @param edge_type the type of edge for which the count is to be returned
	 * @return the number of edges of type edge_type in this graph
	 */
	public int getEdgeCount(EdgeType edge_type) {
		if(edge_type != EdgeType.UNDIRECTED) {
			return getEdgeCount();
		}
		return 0;
	}
	
	/**
	 * Returns the collection of edges in this graph which are of type edge_type.
	 * @param edge_type the type of edges to be returned
	 * @return the collection of edges which are of type edge_type, or
	 * null if the graph does not accept edges of this type
	 * @see EdgeType
	 */
	public Collection<ThreeTenEdge> getEdges(EdgeType edge_type) {
		if(edge_type != EdgeType.UNDIRECTED) {
			return getEdges();
		}
		return null;
	}
	
	/**
	 * Returns the number of vertices that are incident to edge.
	 * For hyperedges, this can be any nonnegative integer; for edges this
	 * must be 2 (or 1 if self-loops are permitted). 
	 * 
	 * <p>Equivalent to getIncidentVertices(edge).size().
	 * @param edge the edge whose incident vertex count is to be returned
	 * @return the number of vertices that are incident to edge.
	 */
	public int getIncidentCount(ThreeTenEdge edge) {
		return getIncidentVertices(edge).size();
	}

	/**
	 * Returns the endpoints of edge as a Pair<ThreeTenNode>.
	 * @param edge the edge whose endpoints are to be returned
	 * @return the endpoints (incident vertices) of edge
	 */
	@SuppressWarnings("unchecked")
	public Pair<ThreeTenNode> getEndpoints(ThreeTenEdge edge) {
		Object[] ends = getIncidentVertices(edge).toArray();
		return new Pair<>((ThreeTenNode)ends[0],(ThreeTenNode)ends[1]);
	}
	
	//********************************************************************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE (except to edit/fix the JavaDocs)
	//********************************************************************************
	
	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 */
	public static Factory<Graph<ThreeTenNode,ThreeTenEdge>> getFactory() { 
		return new Factory<Graph<ThreeTenNode,ThreeTenEdge>> () {
			public Graph<ThreeTenNode,ThreeTenEdge> create() {
				return new ThreeTenGraph();
			}
		};
	}
	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 */
	public static Factory<DirectedGraph<ThreeTenNode,ThreeTenEdge>> getDirectedFactory() { 
		return new Factory<DirectedGraph<ThreeTenNode,ThreeTenEdge>> () {
			public DirectedGraph<ThreeTenNode,ThreeTenEdge> create() {
				return new ThreeTenGraph();
			}
		};
	}
	
	/**
	 * Returns the edge type of edge in this graph.
	 * @param edge
	 * @return the EdgeType of edge, or null if edge has no defined type
	 */
	public EdgeType getEdgeType(ThreeTenEdge edge) {
		return EdgeType.DIRECTED;
	}
	
	/**
	 * Returns the default edge type for this graph.
	 * 
	 * @return the default edge type for this graph
	 */
	public EdgeType getDefaultEdgeType() {
		return EdgeType.DIRECTED;
	}
}
