package ca.ubc.ece.cpen221.mp3.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AlgorithmsTest {

	Graph adjmatrix = new AdjacencyMatrixGraph();
	Vertex v1 = new Vertex("a");
	Vertex v2 = new Vertex("b");
	Vertex v3 = new Vertex("c");
	Vertex v4 = new Vertex("d");
	Vertex v5 = new Vertex("e");
	Vertex v6 = new Vertex("f");
	Vertex v7 = new Vertex("g");
	@Before
	public void initialize(){
		adjmatrix.addVertex(v1);
		adjmatrix.addVertex(v2);
		adjmatrix.addVertex(v3);
		adjmatrix.addVertex(v4);
		adjmatrix.addVertex(v5);
		adjmatrix.addVertex(v6);
		adjmatrix.addVertex(v7);
	}
	
	@Test
	public void ShortestDistanceTest() {
		//first test, straight line
		adjmatrix.addEdge(v1, v2);
		adjmatrix.addEdge(v2, v3);
		adjmatrix.addEdge(v3, v4);

		
		assertEquals(Algorithms.shortestDistance(adjmatrix,v1,v4),3);
		
		//second test, 2 paths equal distance
		adjmatrix.addEdge(v1,v5);
		adjmatrix.addEdge(v5, v6);
		adjmatrix.addEdge(v6, v4);
		
		assertEquals(Algorithms.shortestDistance(adjmatrix, v1, v4),3);
		
		//three paths, unequal distances.
		adjmatrix.addEdge(v1, v7);
		adjmatrix.addEdge(v7, v4);
		
		assertEquals(Algorithms.shortestDistance(adjmatrix, v1, v4),2);
		
		//distance to self
		
		assertEquals(Algorithms.shortestDistance(adjmatrix, v1, v1),0);
		
		//distance of 1, neighbour
		
		assertEquals(Algorithms.shortestDistance(adjmatrix, v1, v2),1);
		
		
		
		
	}

}
