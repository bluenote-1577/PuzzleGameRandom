package ca.ubc.ece.cpen221.mp3.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
	 
	 
	 
	 * returns the shortest distance between two vertices, a and b in the
	 * Graph graph
	 * Precondition : a and b are in the graph.
	 * @param graph the graph we wish to investigate
	 * @param a the vertex we start from
	 * @param b the vertex we end at
	 * @return the smallest number of nodes traversed to get from vertex a to b.
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
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
		
		return distanceMap.get(b);
	}
	
	

}
