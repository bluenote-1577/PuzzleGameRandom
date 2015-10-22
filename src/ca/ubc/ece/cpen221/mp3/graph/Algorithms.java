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
		distanceMap.put(a, 0);
		
		while(!verticesToSearch.isEmpty()){
			Vertex vertexToSearch = verticesToSearch.remove();
			int vertexDistance = distanceMap.get(vertexToSearch);
			
			for(Vertex eachVertex : graph.getDownstreamNeighbors(vertexToSearch)){
				if(distanceMap.containsKey(eachVertex)==false ||
						distanceMap.get(eachVertex) > vertexDistance+1){
				distanceMap.put(eachVertex, vertexDistance+1);
				}
				verticesToSearch.add(eachVertex);
			}
		}
		if(distanceMap.containsKey(b))
			return distanceMap.get(b);
		else throw new NoPathException("No path found between vertices");
	}
	
	public static Set<List<Vertex>> BFS(Graph graph){
		Set<List<Vertex>> myLists = new LinkedHashSet<List<Vertex>>();
		List<Vertex> allVertices = graph.getVertices();
		Queue<Vertex> verticesToSearch = new LinkedList<Vertex>();
		
	
		
		for(Vertex vertex : allVertices){
			verticesToSearch.add(vertex);
			List<Vertex> BFSVertices = new LinkedList<Vertex>();
			
			while(!verticesToSearch.isEmpty()){
				Vertex vertexToSearch = verticesToSearch.remove();
				if(!BFSVertices.contains(vertexToSearch))
					BFSVertices.add(vertexToSearch);
				for(Vertex eachVertex : graph.getDownstreamNeighbors(vertexToSearch)){
					verticesToSearch.add(eachVertex);
				}
			}
			myLists.add(BFSVertices);
		}
		
		return myLists;
	}
	
	public static Set<List<Vertex>> DFS(Graph graph){
		Stack<Vertex> verticesToSearch = new Stack<Vertex>();
		Set<List<Vertex>> myLists = new LinkedHashSet<List<Vertex>>();
		List<Vertex> allVertices = graph.getVertices();

		
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
	
	

}
