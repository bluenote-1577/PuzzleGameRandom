package ca.ubc.ece.cpen221.mp3.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AlgorithmsTest {

	Graph adjmatrix = new AdjacencyMatrixGraph();
	Graph emptymatrix = new AdjacencyMatrixGraph();
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
	public void ShortestDistanceTest() throws NoPathException {
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
	
	@Test(expected = NoPathException.class)
	public void ExceptionTest() throws NoPathException {
		Algorithms.shortestDistance(adjmatrix, v1, v5);
		adjmatrix.addEdge(v1, v5);;
		Algorithms.shortestDistance(adjmatrix, v1, v5);
	}
	
	@Test
	public void BFSTest1(){
		//everything downstream, not cyclical
		//3 members, sparse matrix
		
		adjmatrix.addEdge(v1,v2);
		adjmatrix.addEdge(v2, v3);
		Set<List<Vertex>> compare = new LinkedHashSet<List<Vertex>>();
		compare.add(Arrays.asList(v1,v2,v3));
		compare.add(Arrays.asList(v2,v3));
		compare.add(Arrays.asList(v3));
		compare.add(Arrays.asList(v4));
		compare.add(Arrays.asList(v5));
		compare.add(Arrays.asList(v6));
		compare.add(Arrays.asList(v7));
		
		
		assertEquals(Algorithms.BFS(adjmatrix),compare);
		
		//tests the empty matrix
		
		Set<List<Vertex>> empty = new LinkedHashSet<List<Vertex>>();
		
		assertEquals(Algorithms.BFS(emptymatrix),empty);
	}
	
	
	@Test
	public void BFSTest2(){
		
		//2 paths
		
		adjmatrix.addEdge(v1, v2);
		adjmatrix.addEdge(v2, v3);
		adjmatrix.addEdge(v1, v4);
		adjmatrix.addEdge(v4,v5);
		Set<List<Vertex>> compare = new LinkedHashSet<List<Vertex>>();
		compare.add(Arrays.asList(v1,v2,v4,v3,v5));
		compare.add(Arrays.asList(v2,v3));
		compare.add(Arrays.asList(v3));
		compare.add(Arrays.asList(v4,v5));
		compare.add(Arrays.asList(v5));
		compare.add(Arrays.asList(v6));
		compare.add(Arrays.asList(v7));
		
		
		assertEquals(Algorithms.BFS(adjmatrix),compare);
	}
	
	@Test
	public void InfiniteLoopTest() throws NoPathException{
		
		//circular. causes infinite looping if care is not taken.
		
		adjmatrix.addEdge(v1, v2);
		adjmatrix.addEdge(v2, v3);
		adjmatrix.addEdge(v3, v1);
		adjmatrix.addEdge(v1, v3);
		
		LinkedHashSet<List<Vertex>> compare = new LinkedHashSet<List<Vertex>>();
		LinkedHashSet<List<Vertex>> compare2 = new LinkedHashSet<List<Vertex>>();
		compare.add(Arrays.asList(v1,v2,v3));
		compare.add(Arrays.asList(v2,v3,v1));
		compare.add(Arrays.asList(v3,v1,v2));
		compare.add(Arrays.asList(v4));
		compare.add(Arrays.asList(v5));
		compare.add(Arrays.asList(v6));
		compare.add(Arrays.asList(v7));
		
		assertEquals(Algorithms.BFS(adjmatrix),compare);
		assertEquals(Algorithms.shortestDistance(adjmatrix, v1, v3),1);
		
		compare2.add(Arrays.asList(v1,v3,v2));
		compare2.add(Arrays.asList(v2,v3,v1));
		compare2.add(Arrays.asList(v3,v1,v2));
		compare2.add(Arrays.asList(v4));
		compare2.add(Arrays.asList(v5));
		compare2.add(Arrays.asList(v6));
		compare2.add(Arrays.asList(v7));
		
		assertEquals(Algorithms.DFS(adjmatrix),compare2);
	}
	
	
	@Test
	public void DFSTest1(){
		
		//everything downstream, not cyclical
		//3 members, sparse matrix
		
		adjmatrix.addEdge(v1, v2);
		adjmatrix.addEdge(v2, v3);
		Set<List<Vertex>> compare = new LinkedHashSet<List<Vertex>>();
		compare.add(Arrays.asList(v1,v2,v3));
		compare.add(Arrays.asList(v2,v3));
		compare.add(Arrays.asList(v3));
		compare.add(Arrays.asList(v4));
		compare.add(Arrays.asList(v5));
		compare.add(Arrays.asList(v6));
		compare.add(Arrays.asList(v7));
		
		
		assertEquals(Algorithms.BFS(adjmatrix),compare);
		
		//Test empty matrix
		
		Set<List<Vertex>> empty = new LinkedHashSet<List<Vertex>>();
		
		assertEquals(Algorithms.DFS(emptymatrix),empty);
	}
	
	
	@Test
	public void DFSTest2(){
		
		//2 paths
		
		
		adjmatrix.addEdge(v1, v2);
		adjmatrix.addEdge(v2, v3);
		adjmatrix.addEdge(v1, v4);
		adjmatrix.addEdge(v4, v5);
		Set<List<Vertex>> compare = new LinkedHashSet<List<Vertex>>();
		compare.add(Arrays.asList(v1,v4,v5,v2,v3));
		compare.add(Arrays.asList(v2,v3));
		compare.add(Arrays.asList(v3));
		compare.add(Arrays.asList(v4,v5));
		compare.add(Arrays.asList(v5));
		compare.add(Arrays.asList(v6));
		compare.add(Arrays.asList(v7));
		
		
		assertEquals(Algorithms.DFS(adjmatrix),compare);
		
	}
	
	@Test
	public void DFSTest3(){
		//2 paths, one vertex connecting the two paths.
		adjmatrix.addEdge(v1, v2);
		adjmatrix.addEdge(v2, v3);
		adjmatrix.addEdge(v1, v3);
		Set<List<Vertex>> compare = new LinkedHashSet<List<Vertex>>();
		compare.add(Arrays.asList(v1,v3,v2));
		compare.add(Arrays.asList(v3));
		compare.add(Arrays.asList(v2,v3));
	}
	
	@Test
	public void commonUpVerticesTest1(){
		//simple test, 2 upstream vertices.
		adjmatrix.addEdge(v1, v5);
		adjmatrix.addEdge(v1, v6);
		adjmatrix.addEdge(v2, v5);
		adjmatrix.addEdge(v2, v6);
		emptymatrix.addVertex(v1);
		emptymatrix.addVertex(v2);
		
		List<Vertex> compare = new LinkedList<Vertex>();
		compare.add(v1);
		compare.add(v2);
		//empty test
		List<Vertex> empty= new LinkedList<Vertex>();
		
		assertEquals(Algorithms.commonUpstreamVertices(adjmatrix, v5, v6),compare);
		assertEquals(Algorithms.commonUpstreamVertices(emptymatrix, v1, v2),empty);
	}
	
	@Test
	public void commonDownVerticesTest1(){
		
		//simple test, 2 common downstream vertices
		//also an empty test, should return empty list
		adjmatrix.addEdge(v1, v2);
		adjmatrix.addEdge(v1, v3);
		adjmatrix.addEdge(v4, v2);
		adjmatrix.addEdge(v4, v3);
		
		emptymatrix.addVertex(v1);
		emptymatrix.addVertex(v2);
		
		List<Vertex> compare = new LinkedList<Vertex>();
		compare.add(v2);
		compare.add(v3);
		
		List<Vertex> empty = new LinkedList<Vertex>();
		
		assertEquals(Algorithms.commonDownstreamVertices(adjmatrix, v1, v4),compare);
		assertEquals(Algorithms.commonDownstreamVertices(emptymatrix, v1, v2),empty);
	}
	
	

}
