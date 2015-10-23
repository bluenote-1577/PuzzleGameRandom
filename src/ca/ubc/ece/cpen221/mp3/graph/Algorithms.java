package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 * 
	 * Please see the README for the machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */

	/**
	 * This is provided as an example to indicate that this method and other
	 * methods should be implemented here.
	 * 
	 * You should write the specs for this and all other methods.
	 * @throws NoPathException 
	 
	 **/
	
	/*
	 
	 * returns the shortest distance between two vertices, a and b in the
	 * Graph graph
	 * Precondition : a and b are in the graph.
	 * @param graph the graph we wish to investigate
	 * @param a the vertex we start from
	 * @param b the vertex we end at
	 * @return the smallest number of nodes traversed to get from vertex a to b.
	 * if no edge is from a to b, then we throw a checked exception NoPathException
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) throws NoPathException {
		Map<Vertex,Integer> distanceMap = new HashMap<Vertex,Integer>();
		Queue<Vertex> verticesToSearch = new LinkedList<Vertex>();
		
		verticesToSearch.add(a);
		//first vertex has distance 0
		distanceMap.put(a, 0);
		
		while(!verticesToSearch.isEmpty()){
			Vertex vertexToSearch = verticesToSearch.remove();
			int vertexDistance = distanceMap.get(vertexToSearch);
			
			for(Vertex eachVertex : graph.getDownstreamNeighbors(vertexToSearch)){
				//if the vertex hasn't been discovered,
				//we give it a value of distance of the previous node + 1
				if(distanceMap.containsKey(eachVertex)==false ||
						distanceMap.get(eachVertex) > vertexDistance+1){
				distanceMap.put(eachVertex, vertexDistance+1);
				verticesToSearch.add(eachVertex);
				}
			
			}
		}
		if(distanceMap.containsKey(b))
			return distanceMap.get(b);
		else throw new NoPathException("No path found between vertices");
	}
	
	/**
	 * The breadth-first search. Calls the BFS on every vertex in our graph.
	 * Important to note that the order of calling the BFS on each of our vertices is determined by
	 * the order in which the vertices are put in the graph. The order of the lists
	 * returned correspond to how the BFS is supposed to traverse a graph, but the
	 * order of the sets (we used a LinkedHashSet) depends on the order of the vertices
	 * inserted into the graph
	 * @param graph : The graph to be traversed.
	 * @return a set (which we made ordered for testing purposes) of Lists of the BFS results
	 * called on every vertex. Returns an empty list if the graph is empty.
	 */
	public static Set<List<Vertex>> BFS(Graph graph){
		Set<List<Vertex>> myLists = new LinkedHashSet<List<Vertex>>();
		List<Vertex> allVertices = graph.getVertices();
		Queue<Vertex> verticesToSearch = new LinkedList<Vertex>();
		
	
		//calls BFS on all vertices
		for(Vertex vertex : allVertices){
			verticesToSearch.add(vertex);
			List<Vertex> BFSVertices = new LinkedList<Vertex>();
			Set<Vertex> visited = new LinkedHashSet<Vertex>();
			
			while(!verticesToSearch.isEmpty()){
				
				Vertex vertexToSearch = verticesToSearch.remove();
				if(!BFSVertices.contains(vertexToSearch))
					BFSVertices.add(vertexToSearch);
				for(Vertex eachVertex : graph.getDownstreamNeighbors(vertexToSearch)){
					if(!visited.contains(eachVertex)){
						visited.add(eachVertex);
						verticesToSearch.add(eachVertex);
					}
				}
			}
			myLists.add(BFSVertices);
		}
		
		return myLists;
	}
	
	/**
	 * Calls the Depth-First search on every single vertex in our graph. 
	 * The order of the Set of Lists returned is dependent on the order in which our 
	 * vertices are added. That is, the first traversal is called
	 * on the first vertex put in our graph.
	 * @param graph : The graph that is searched.
	 * @return Set<List<Vertex>> : an ordered set of lists. The first set
	 * is the list of the DFS traversal of our first vertex put into the graph and
	 * so forth. The order of the List is the order of DFS traversal. Returns
	 * an empty set of lists if graph is empty.
	 */
	public static Set<List<Vertex>> DFS(Graph graph){
		Stack<Vertex> verticesToSearch = new Stack<Vertex>();
		Set<List<Vertex>> myLists = new LinkedHashSet<List<Vertex>>();
		List<Vertex> allVertices = graph.getVertices();

		//calls DFS on all vertices, not just one vertex
		for(Vertex vertex : allVertices){
		
			verticesToSearch.add(vertex);
			List<Vertex> BFSVertices = new LinkedList<Vertex>();
			
			while(!verticesToSearch.isEmpty()){
				
				Vertex vertexToSearch = verticesToSearch.pop();
				
				if(!BFSVertices.contains(vertexToSearch)){
					BFSVertices.add(vertexToSearch);
					for(Vertex eachVertex : graph.getDownstreamNeighbors(vertexToSearch)){
						verticesToSearch.add(eachVertex);
					}
				}
				
			}
			myLists.add(BFSVertices);
		}
		return myLists;
		
	}
	
	/**
	 * A method for finding common upstream vertices of two vertices a and b.
	 * 
	 * @param graph : The graph in which vertices a and b are located.
	 * @param a has to be inside graph.
	 * @param b has to be inside graph.
	 * @return a List<Vertex> containing all the vertices that have an edge to both
	 * a and b. Returns an empty list of vertices if there are no vertices with
	 * edges to both a and b.
	 */
	public static List<Vertex> commonUpstreamVertices(Graph graph, Vertex a, Vertex b){
		List<Vertex> toReturn = new LinkedList<Vertex>();
		
		List<Vertex> upstreamVerticesA = graph.getUpstreamNeighbors(a);
		List<Vertex> upstreamVerticesB = graph.getUpstreamNeighbors(b);
		
		for (Vertex vertex: upstreamVerticesA){
			if (upstreamVerticesB.contains(vertex)){
				toReturn.add(vertex);
			}
		}
		
		return toReturn;
	}
	
	/**
	 * 
	 * A method for finding common upstream vertices of two vertices a and b.
	 * 
	 * @param graph : The graph in which vertices a and b are located
	 * @param a has to be inside the graph.
	 * @param b has to be inside the graph.
	 * @return a List<Vertex> containing all the vertices that have an edge to both
	 * a and b. Returns an empty list of vertices if there are no vertices with
	 * edges to both a and b.
	 */

	
	public static List<Vertex> commonDownstreamVertices(Graph graph, Vertex a, Vertex b){
		
		List<Vertex> toReturn = new LinkedList<Vertex>();
		
		List<Vertex> downstreamVerticesA = graph.getDownstreamNeighbors(a);
		List<Vertex> downstreamVerticesB = graph.getDownstreamNeighbors(b);
		
		for (Vertex vertex: downstreamVerticesA){
			if (downstreamVerticesB.contains(vertex)){
				toReturn.add(vertex);
			}
		}
		
		return toReturn;
	}
}
