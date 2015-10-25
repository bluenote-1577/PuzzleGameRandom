package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.graph.NoPathException;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * 
 * Testing all of the algorithms on the AdjacencyList.
 *
 */
public class AlgorithmsListTest {
	
	Graph adj_list = new AdjacencyListGraph();
	Graph empty_adjlist = new AdjacencyListGraph();
	
	
	Vertex v1 = new Vertex("a");
	Vertex v2 = new Vertex("b");
	Vertex v3 = new Vertex("c");
	Vertex v4 = new Vertex("d");
	Vertex v5 = new Vertex("e");
	Vertex v6 = new Vertex("f");
	Vertex v7 = new Vertex("g");
	
	@Before
	public void initialize(){
		adj_list.addVertex(v1);
		adj_list.addVertex(v2);
		adj_list.addVertex(v3);
		adj_list.addVertex(v4);
		adj_list.addVertex(v5);
		adj_list.addVertex(v6);
		adj_list.addVertex(v7);
	}

	@Test
	public void shortestDistanceTest() throws NoPathException {
	
		//case 1: distance is 0 (distance between vertex and itself) 
		assertEquals(0, Algorithms.shortestDistance(adj_list, v1, v1));
		
		adj_list.addEdge(v1, v2);
		
		//case 2: distance is 1 
		assertEquals(1, Algorithms.shortestDistance(adj_list, v1, v2));
		
		adj_list.addEdge(v2, v3);
        
		//case 3: distance is 2 
		assertEquals(2, Algorithms.shortestDistance(adj_list, v1, v3));

		adj_list.addEdge(v3, v6);
		adj_list.addEdge(v4, v2);
		adj_list.addEdge(v6, v5);
		adj_list.addEdge(v7, v4);
	    //case 4: complicated route with a distance of 5
		assertEquals(5, Algorithms.shortestDistance(adj_list, v7, v5));
		
		adj_list.addEdge(v4, v6);
		
		//case 5: two routes, should pick the shorter one with distance of 3, not 5
		assertEquals(3, Algorithms.shortestDistance(adj_list, v7, v5));
		
		adj_list.addEdge(v7, v6);
		
		//case 6: three routes, all of different lengths, shortest is distance of 2 
		assertEquals(2, Algorithms.shortestDistance(adj_list, v7, v5));
		
		adj_list.addEdge(v1, v5);
		adj_list.addEdge(v5, v3); 
		
		//case 7: two routes of equal distance of 2 
		assertEquals(2, Algorithms.shortestDistance(adj_list, v1, v3));
	
	}
	
	@Test
	public void BFSTest1(){
		
		//case 1: graph is empty, so list is empty (size = 0)
		assertEquals(0, Algorithms.BFS(empty_adjlist).size());
		
		adj_list.addEdge(v1, v2);
		adj_list.addEdge(v4, v6);
		adj_list.addEdge(v6, v5);
		
		Set<List<Vertex>> my_list = new LinkedHashSet<List<Vertex>>();
		my_list.add(Arrays.asList(v1, v2));
		my_list.add(Arrays.asList(v2));
		my_list.add(Arrays.asList(v3));
		my_list.add(Arrays.asList(v4, v6, v5));
		my_list.add(Arrays.asList(v5));
		my_list.add(Arrays.asList(v6, v5));
		my_list.add(Arrays.asList(v7));
		
		//case 2: 1 path maximum for a vertex 
		assertEquals(my_list, Algorithms.BFS(adj_list));
		
	}
	
	@Test public void BFSTest2(){
		
		adj_list.addEdge(v1, v3);
		adj_list.addEdge(v1, v5);
		adj_list.addEdge(v3, v6);
		adj_list.addEdge(v5, v7);
		
		Set<List<Vertex>> my_list = new LinkedHashSet<List<Vertex>>();
		my_list.add(Arrays.asList(v1, v3, v5, v6, v7));
		my_list.add(Arrays.asList(v2));
		my_list.add(Arrays.asList(v3, v6));
		my_list.add(Arrays.asList(v4));
		my_list.add(Arrays.asList(v5, v7));
		my_list.add(Arrays.asList(v6));
		my_list.add(Arrays.asList(v7));
		
		//case 3: 2 path maximum for a vertex 
		assertEquals(my_list, Algorithms.BFS(adj_list));
	}
	
	@Test
	public void DFSTest1(){
		
		//case 1: graph is empty, so list is empty (size = 0)
		assertEquals(0, Algorithms.DFS(empty_adjlist).size());
		
		adj_list.addEdge(v1, v2);
		adj_list.addEdge(v4, v6);
		adj_list.addEdge(v6, v5);
		
		Set<List<Vertex>> my_list = new LinkedHashSet<List<Vertex>>();
		my_list.add(Arrays.asList(v1, v2));
		my_list.add(Arrays.asList(v2));
		my_list.add(Arrays.asList(v3));
		my_list.add(Arrays.asList(v4, v6, v5));
		my_list.add(Arrays.asList(v5));
		my_list.add(Arrays.asList(v6, v5));
		my_list.add(Arrays.asList(v7));
		
		//case 2: maximum of 1 path for each vertex 
		assertEquals(my_list, Algorithms.DFS(adj_list));
		
	}
	
	@Test 
	public void DFSTest2(){
		
		adj_list.addEdge(v1, v3);
		adj_list.addEdge(v1, v5);
		adj_list.addEdge(v3, v6);
		adj_list.addEdge(v5, v7);
		
		Set<List<Vertex>> my_list = new LinkedHashSet<List<Vertex>>();
		my_list.add(Arrays.asList(v1, v5, v7, v3, v6));
		my_list.add(Arrays.asList(v2));
		my_list.add(Arrays.asList(v3, v6));
		my_list.add(Arrays.asList(v4));
		my_list.add(Arrays.asList(v5, v7));
		my_list.add(Arrays.asList(v6));
		my_list.add(Arrays.asList(v7));
		
		//case 3: 2 path maximum for a vertex 
		assertEquals(my_list, Algorithms.DFS(adj_list));
	}
	
	@Test 
	public void CommonUpstreamVertices(){
		
		//case 1: list is empty, no common vertices up stream
		assertEquals(0, Algorithms.commonUpstreamVertices(adj_list, v1, v2).size());
		
		adj_list.addEdge(v1, v2);
		adj_list.addEdge(v1, v3);
		
		List<Vertex> list1 = new ArrayList<Vertex>();
		list1.add(v1);
		
		//case 2: one common upStream vertex
		assertEquals(list1, Algorithms.commonUpstreamVertices(adj_list, v2, v3));
		
		adj_list.addEdge(v4, v2);
		adj_list.addEdge(v4, v3);
		adj_list.addEdge(v7, v2);
		adj_list.addEdge(v7, v3);
		adj_list.addEdge(v6, v2);
		
		list1.add(v4);
		list1.add(v7);
		
		//case 3: multiple common upStream vertices 
		assertEquals(list1, Algorithms.commonUpstreamVertices(adj_list, v2, v3));
	}
	
	@Test 
	public void CommonDownstreamVerticesTest(){
		
		//case 1: list is empty, no common vertices down stream 
		assertEquals(0, Algorithms.commonDownstreamVertices(adj_list, v1, v2).size());
		
		adj_list.addEdge(v1, v5);
		adj_list.addEdge(v2, v5);
		
		List<Vertex> list1 = new ArrayList<Vertex>();
		list1.add(v5);
		
		//case 2: one common down stream vertex 
		assertEquals(list1, Algorithms.commonDownstreamVertices(adj_list, v1, v2));
		
		adj_list.addEdge(v1, v6);
		adj_list.addEdge(v2, v6);
		adj_list.addEdge(v1, v7);
		adj_list.addEdge(v2, v7);
		
		list1.add(v6);
		list1.add(v7);
		
		//case 3: multiple common down stream vertices 
		assertEquals(list1, Algorithms.commonDownstreamVertices(adj_list, v1, v2));
		
	}
	
	@Test 
	public void CommonUpstreamVerticesTest2(){
		adj_list.addEdge(v2, v1);
		adj_list.addEdge(v3, v1);
		adj_list.addEdge(v3, v5);
		adj_list.addEdge(v2, v5);
		adj_list.addEdge(v4, v5);
		adj_list.addEdge(v7, v1);
		adj_list.addEdge(v7, v2);
		
		List<Vertex> list1 = new ArrayList<Vertex>();
		list1.add(v2);
		list1.add(v3);
		
		assertEquals(list1,Algorithms.commonUpstreamVertices(adj_list,
				new Vertex("a"),new Vertex("e")));
		
	}

}
