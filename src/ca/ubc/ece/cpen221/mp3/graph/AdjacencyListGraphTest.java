package ca.ubc.ece.cpen221.mp3.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraphTest {

	String A = "A";
	String B = "B";
	String C = "C";
	String D = "D";

	Vertex v1 = new Vertex(A);
	Vertex v2 = new Vertex(B);
	Vertex v3 = new Vertex(C);
	Vertex v4 = new Vertex(D);

	// the graph
	AdjacencyListGraph vert_list;

	@Test
	public void addVertextTest() {

		vert_list = new AdjacencyListGraph();

		// add vertices to the Adjacency List Graph
		vert_list.addVertex(v1);
		vert_list.addVertex(v2);

		// create map to get the ArrayLists from each vertex
		Map<Vertex, ArrayList<Vertex>> my_map;
		my_map = vert_list.getMap();

		// get the arrays from each vertex in the map
		ArrayList<Vertex> arr1 = my_map.get(v1);
		ArrayList<Vertex> arr2 = my_map.get(v2);

		// nothing in the arrays
		assertEquals(arr1, arr2);

		arr1.add(0, v3);
		arr2.add(0, v3);

		// one vertex in each each array (same vertex for each)
		assertEquals(arr1, arr2);

		arr1.add(1, v4);
		arr2.add(1, v4);

		// two of the same vertices in each array
		assertEquals(arr1, arr2);

	}

	@Test
	public void addEdgeTest() {

		vert_list = new AdjacencyListGraph();

		// add vertices to the graph
		vert_list.addVertex(v1);
		vert_list.addVertex(v2);
		vert_list.addVertex(v3);
		vert_list.addVertex(v4);

		// create map to get the ArrayLists from each vertex
		Map<Vertex, ArrayList<Vertex>> my_map;
		my_map = vert_list.getMap();

		// get the arrays from each vertex in the graph
		ArrayList<Vertex> arr1 = my_map.get(v1);
		ArrayList<Vertex> arr2 = my_map.get(v2);
		ArrayList<Vertex> arr3 = my_map.get(v3);
		ArrayList<Vertex> arr4 = new ArrayList<Vertex>(); // created a new array
															// to change things
															// up

		vert_list.addEdge(v1, v2);
		arr3.add(0, v2);

		// check to see if there is an edge from v1 to v2
		assertEquals(arr1, arr3);

		vert_list.addEdge(v1, v4);
		arr3.add(v4);

		// check to see if there is an edge from v1 to v2 and from v1 to v4
		assertEquals(arr1, arr3);

		vert_list.addEdge(v2, v4);
		arr4.add(v4);

		// check to see if there is an edge from v2 to v4
		assertEquals(arr2, arr4);

		vert_list.addEdge(v2, v1);
		arr4.add(v1);

		// check to see if there is an edge from v2 to v4 and from v2 to v1
		assertEquals(arr2, arr4);

	}

	@Test
	public void edgeExistsTest() {
		vert_list = new AdjacencyListGraph();
		
		// add vertices to the graph
		vert_list.addVertex(v1);
		vert_list.addVertex(v2);
		vert_list.addVertex(v3);
		vert_list.addVertex(v4);

		// create map to get the ArrayLists from each vertex
		Map<Vertex, ArrayList<Vertex>> my_map;
		my_map = vert_list.getMap();
		
		// get the arrays from each vertex in the graph
		ArrayList<Vertex> arr1 = my_map.get(v1);
		ArrayList<Vertex> arr2 = my_map.get(v2);
		ArrayList<Vertex> arr3 = my_map.get(v3);
		
		arr1.add(v2);
		
		//first case: only one value in the arrayList
		assertEquals(true, vert_list.edgeExists(v1, v2));
		
		arr1.add(v3);
		
		//second case: two values in arrayList (check each value) 
		assertEquals(true, vert_list.edgeExists(v1, v3));
		assertEquals(true, vert_list.edgeExists(v1, v2));
		
		//third case: vertex is not in the arrayList
		assertEquals(false, vert_list.edgeExists(v1, v4));
		
		//fourth case: empty arrayList 
		assertEquals(false, vert_list.edgeExists(v2, v1));
	}
	
	@Test
	public void getDownstreamNeighboursTest(){
vert_list = new AdjacencyListGraph();
		
		// add vertices to the graph
		vert_list.addVertex(v1);
		vert_list.addVertex(v2);
		vert_list.addVertex(v3);
		vert_list.addVertex(v4);

		// create map to get the ArrayLists from each vertex
		Map<Vertex, ArrayList<Vertex>> my_map;
		my_map = vert_list.getMap();
		
		// get the arrays from each vertex in the graph
		ArrayList<Vertex> arr1 = my_map.get(v1);
		ArrayList<Vertex> arr2 = my_map.get(v2);
		ArrayList<Vertex> arr3 = new ArrayList<Vertex>();
		
		arr1.add(v2);
		arr1.add(v3);
		
		arr3 = my_map.getDownstreamNeighbours(v1);
		
		
		
		
	}

}
