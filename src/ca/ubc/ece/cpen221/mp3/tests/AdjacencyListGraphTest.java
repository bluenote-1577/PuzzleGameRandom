package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * 
 * Testing the implementation of AdjacencyListGraph.
 *
 */
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

		arr1.add(v2);

		// first case: only one value in the arrayList
		assertEquals(true, vert_list.edgeExists(v1, v2));

		arr1.add(v3);

		// second case: two values in arrayList (check each value)
		assertEquals(true, vert_list.edgeExists(v1, v3));
		assertEquals(true, vert_list.edgeExists(v1, v2));

		// third case: vertex is not in the arrayList
		assertEquals(false, vert_list.edgeExists(v1, v4));

		// fourth case: empty arrayList
		assertEquals(false, vert_list.edgeExists(v2, v1));
	}

	@Test
	public void getDownstreamNeighborsTest() {
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
		List<Vertex> list1 = new ArrayList<Vertex>();
		List<Vertex> list2 = new ArrayList<Vertex>();

		// case 1: size equals 0
		list1 = vert_list.getDownstreamNeighbors(v1);
		assertEquals(0, list1.size());

		arr1.add(v2);
		list1 = vert_list.getDownstreamNeighbors(v1);

		// case 2: one vertex down stream, and size = 1
		assertEquals(arr1, list1);
		assertEquals(1, list1.size());

		arr1.add(v3);
		list2 = vert_list.getDownstreamNeighbors(v1);

		// case3: 2 vertices down stream, and size = 2
		assertEquals(arr1, list2);
		assertEquals(2, list2.size());
	}
	
	@Test
	public void getUpstreamNeighbors(){
		
		vert_list = new AdjacencyListGraph();

		// add vertices to the graph
		vert_list.addVertex(v1);
		vert_list.addVertex(v2);
		vert_list.addVertex(v3);
		vert_list.addVertex(v4);

		// create map to get the ArrayLists from each vertex
		Map<Vertex, ArrayList<Vertex>> my_map;
		my_map = vert_list.getMap();
		
		ArrayList<Vertex> arr2 = my_map.get(v2);
		ArrayList<Vertex> arr3 = my_map.get(v3);
		ArrayList<Vertex> arr4 = my_map.get(v4);
		
		List<Vertex> list1 = new ArrayList<Vertex>();
		List<Vertex> list2 = new ArrayList<Vertex>();

		
		//add v2, v3 and v4 as downstream edges to v1
		//arr1.add(v2);
	    //arr1.add(v3);
	    vert_list.addEdge(v1, v2);
	    vert_list.addEdge(v1, v3);
		
	    //add v1 as a downstream edge to v2
	    arr2.add(v1);
	    
	    //add v3 as a downstream edge to v3
	    arr3.add(v3);
	    
	    //add v3 as a downstream edge to v4
	    arr4.add(v3);

	    //case 1: no upstreamEdges (size = 0)
	    assertEquals(0, vert_list.getUpstreamNeighbors(v4).size());
		
	    //case 2: 1 upStream Edge (size = 1) 
	    list1.add(v2);
	    assertEquals(1, vert_list.getUpstreamNeighbors(v1).size());
	    assertEquals(list1, vert_list.getUpstreamNeighbors(v1));
	    
	    //case 3: more than one upStream edges (size = 3)
	    list2.add(v1);
	    list2.add(v3);
	    list2.add(v4);
	    
	    assertEquals(3, vert_list.getUpstreamNeighbors(v3).size());
	    assertEquals(list2, vert_list.getUpstreamNeighbors(v3));
	}
	
	@Test 
	public void getVerticesTest(){
		
		vert_list = new AdjacencyListGraph();
		
		List<Vertex> list1 = new ArrayList<Vertex>();
		
		//case 1: no vertices in the graph (size = 0)
		assertEquals(0, vert_list.getVertices().size());
		
		// add v1 to the graph
		vert_list.addVertex(v1);
		
		list1.add(v1);
		
		//case 2: one vertex in the graph (size = 1)
		assertEquals(1, vert_list.getVertices().size());
		assertEquals(list1, vert_list.getVertices());
		
		//add a few more vertices to the graph 
		vert_list.addVertex(v2);
		vert_list.addVertex(v4);
		
		list1.add(v2);
		list1.add(v4);
		
		//case 3: multiple vertices in the graph (size =3)
		assertEquals(3, vert_list.getVertices().size());
		assertEquals(list1, vert_list.getVertices());
		
		
	}

}
