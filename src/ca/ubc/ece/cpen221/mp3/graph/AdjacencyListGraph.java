package ca.ubc.ece.cpen221.mp3.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
	// TODO: Implement this class jimi hong kong

	// each key in map has an array of vertices
	private Map<Vertex, ArrayList<Vertex>> vert_map; // = new HashMap<Vertex,
														// ArrayList<Vertex>>();

	// method to return vert_arr
	public Map<Vertex, ArrayList<Vertex>> getMap() {
		return vert_map;
	}

	// method to create a vert_list
	public AdjacencyListGraph() {
		vert_map = new HashMap<Vertex, ArrayList<Vertex>>();
	}

	/**
	 * Adds a vertex to the graph.
	 *
	 * Precondition: v is not already a vertex in the graph
	 */
	public void addVertex(Vertex v) {

		vert_map.put(v, new ArrayList<Vertex>());

	}

	/**
	 * Adds an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph
	 */
	public void addEdge(Vertex v1, Vertex v2) {

		ArrayList<Vertex> add_edge;
		add_edge = vert_map.get(v1);
		add_edge.add(v2);

	}

	/**
	 * Check if there is an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph Postcondition: return
	 * true iff an edge from v1 connects to v2
	 */
	public boolean edgeExists(Vertex v1, Vertex v2) {
		boolean vertex_exists = false;

		// obtain the arrayList from the vertex v1
		ArrayList<Vertex> check_edge;
		check_edge = vert_map.get(v1);
		Vertex check_vertex;

		// check all vertices inside v1's arrayList
		for (int index = 0; index < check_edge.size(); index++) {
			check_vertex = check_edge.get(index);

			if (check_vertex.equals(v2))
				vertex_exists = true;
		}

		return vertex_exists;

	}

	/**
	 * Get an array containing all downstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex w such that there is
	 * an edge from v to w. The size of the list must be as small as possible
	 * (No trailing null elements). This method should return a list of size 0
	 * iff v has no downstream neighbors.
	 */
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		List<Vertex> down_stream = new ArrayList<Vertex>(); //List to be returned 
		ArrayList<Vertex> check_edges; //ArrayList for checking the Vertices 
		
		check_edges = vert_map.get(v);
		for(int index = 0; index < check_edges.size(); index++){
			down_stream.add(index, check_edges.get(index)); //transfer each ArrayList element 
		}                                                   //into the new List 
		
		return down_stream;
	}

	/**
	 * Get an array containing all upstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex u such that there is
	 * an edge from u to v. The size of the list must be as small as possible
	 * (No trailing null elements). This method should return a list of size 0
	 * iff v has no upstream neighbors.
	 */
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		
		List<Vertex> up_stream = new ArrayList<Vertex>(); //List to be returned 
		ArrayList<Vertex> temporary_arr = new ArrayList<Vertex>();                   
		boolean found_vertex = false; 
		
		//check each key in the vert_map for v downstream
		for(Vertex key : vert_map.keySet()){
			temporary_arr.addAll(vert_map.get(key)); 
			for(int index = 0; index < temporary_arr.size(); index++){
				if(v.equals(temporary_arr.get(index)))
					found_vertex = true; 
			}
			if(found_vertex){
				up_stream.add(key); //add the upStream vertex to the list 
				found_vertex = false; 
			}
			//clear the arrayList for the next key
			temporary_arr.clear();
		}
		return up_stream; 
	}

	/**
	 * Get all vertices in the graph.
	 *
	 * Postcondition: returns a list containing all vertices in the graph. This
	 * method should return a list of size 0 iff the graph has no vertices.
	 */
	public List<Vertex> getVertices() {

		return null;
	}

}
