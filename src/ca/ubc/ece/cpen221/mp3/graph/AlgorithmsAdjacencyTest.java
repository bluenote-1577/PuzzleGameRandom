package ca.ubc.ece.cpen221.mp3.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AlgorithmsAdjacencyTest {
	
	Graph adj_list = new AdjacencyListGraph();
	
	
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
	public void shortestDistaneTest() throws NoPathException {
	
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
		assertEquals(0, Algorithms.shortestDistance(adj_list, v1, v3));
	
	}

}
