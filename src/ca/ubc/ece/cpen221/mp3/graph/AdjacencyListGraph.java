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
		ArrayList<Vertex> down_stream = new ArrayList<Vertex>(); //ArrayList to be returned 
		ArrayList<Vertex> check_edges; //ArrayList for checking the Vertices 
		
		check_edges = vert_map.get(v);
		for(int index = 0; index < check_edges.size(); index++){
			down_stream.add(index, check_edges.get(index));
		}
		
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
		return null;
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
