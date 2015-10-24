package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * Testing the implementation of AdjacencyMatrixGraph
 * 
 *
 */
public class AdjacencyMatrixGraphTest {

	AdjacencyMatrixGraph thisGraph = new AdjacencyMatrixGraph();
	@Test
	/**
	 * Testing the addVertex function. Should create a new row on the matrix
	 * and make a new column of falses.
	 */
	public void AddVertexTest() {
		
		thisGraph.addVertex(new Vertex("lolol"));
		ArrayList<ArrayList<Boolean>> matrixTest = new ArrayList<ArrayList<Boolean>>();
		ArrayList<Boolean> toAdd = new ArrayList<Boolean>();
		toAdd.add(false);
		matrixTest.add(toAdd);
		//added a vertex, our matrix should be one arraylist with 1 false element
		assertEquals(thisGraph.test(),matrixTest);
		
		thisGraph.addVertex(new Vertex("haha"));
		ArrayList<Boolean> toAdd1 = new ArrayList<Boolean>();
		toAdd.add(false);
		toAdd1.add(false);
		toAdd1.add(false);
		
		ArrayList<ArrayList<Boolean>> matrixTest1 = new ArrayList<ArrayList<Boolean>>();
		matrixTest1.add(toAdd);
		matrixTest1.add(toAdd1);
		//our new matrix, matrixTest1 should be a 2x2 matrix with all falses now since
		//we added another vertex.
		assertEquals(thisGraph.test(),matrixTest1);
	}
	//test the getvertices function.
	@Test
	public void getVerticesTest() {
		thisGraph.addVertex(new Vertex("lolol"));
		thisGraph.addVertex(new Vertex("rofl"));
		List<Vertex> allVertices = thisGraph.getVertices();
		//our new vertex tested is the same as our previous vertex.
		//allVertices' first element 
		assertEquals(new Vertex("lolol"),allVertices.get(0));
		
		//make sure that our second element from the returned
		//getVertices function has the same label as before
		assertEquals("rofl",allVertices.get(1).toString());
	}
	@Test
	//tests the addEdge function and edgeExists
	public void addEdgeTest_edgeExistsTest(){
		
		
		thisGraph.addVertex(new Vertex("lolol"));
		thisGraph.addVertex(new Vertex("Rofl"));
		
		//add edge between "lolol" and "rofl"
		thisGraph.addEdge(thisGraph.getVertices().get(0),thisGraph.getVertices().get(1));
		ArrayList<Boolean> toAdd1 = new ArrayList<Boolean>();
		ArrayList<Boolean> toAdd = new ArrayList<Boolean>();
		/**
		 * our matrix now looks like this
		 * 		   lolol rofl
		 * 
		 * lolol   false true
		 * Rofl    false false
		 * 
		 * row one describes the edges that lolol connects to.
		 * the true indicates that it has an edge directed to rofl.
		 * 
		 * 
		 */
		toAdd1.add(false);
		toAdd1.add(true);
		toAdd.add(false);
		toAdd.add(false);
		
		
		ArrayList<ArrayList<Boolean>> matrixTest1 = new ArrayList<ArrayList<Boolean>>();
		matrixTest1.add(toAdd1);
		matrixTest1.add(toAdd);
		//tests the above matrix
		assertEquals(thisGraph.test(),matrixTest1 );
		/**
		 * changes the matrices to 
		 * false true
		 * true false
		 * 
		 * which corresponds to adding an edge between "Rofl" and "lolol"
		 */
		matrixTest1.get(1).set(0, true);
		thisGraph.addEdge(new Vertex("Rofl"), new Vertex("lolol"));
		//tests the above matrix
		assertEquals(thisGraph.test(),matrixTest1);
		
		//tests the edgeexists function for the matrix above for true values
		assertEquals(thisGraph.edgeExists(thisGraph.getVertices().get(1), thisGraph.getVertices().get(0)),true);
		assertEquals(thisGraph.edgeExists(thisGraph.getVertices().get(0), thisGraph.getVertices().get(1)), true);
		//tests labels
		assertEquals(thisGraph.edgeExists(new Vertex("Rofl"), new Vertex("lolol")), true);
		//tests for false values
		assertEquals(thisGraph.edgeExists(thisGraph.getVertices().get(0), thisGraph.getVertices().get(0)),false);
	}
	
	@Test
	public void downstreamNeighboursTest(){
		//initialize 3 vertices
		Vertex vertex1 = new Vertex("roflcopter");
		Vertex vertex2 = new Vertex("roflmao");
		Vertex vertex3 = new Vertex("lmao");
		//add the vertices into the graph, should be a 3x3 matrix now
		thisGraph.addVertex(vertex1);
		thisGraph.addVertex(vertex2);
		thisGraph.addVertex(vertex3);
		
		//2 edges from vertex1 to vertex 2 and 3, downstream neighbours should be
		//vertices 2 and 3.
		thisGraph.addEdge(vertex1, vertex2);
		thisGraph.addEdge(vertex1, vertex3);
		
		ArrayList<Vertex> compare = new ArrayList<Vertex>();
		compare.add(vertex2);
		compare.add(vertex3);
		assertEquals(compare,thisGraph.getDownstreamNeighbors(vertex1));
		
		compare.remove(vertex2);
		thisGraph.addEdge(vertex2, vertex3);
		assertEquals(thisGraph.getDownstreamNeighbors(vertex2),compare);
		//test return an empty list of size 0
		
		assertEquals(thisGraph.getDownstreamNeighbors(vertex3).size(),0);
	}
	
	@Test
	public void upstreamNeighboursTest(){
		Vertex vertex1 = new Vertex("roflcopter");
		Vertex vertex2 = new Vertex("roflmao");
		Vertex vertex3 = new Vertex("lmao");
		thisGraph.addVertex(vertex1);
		thisGraph.addVertex(vertex2);
		thisGraph.addVertex(vertex3);
		
		thisGraph.addEdge(vertex1, vertex2);
		thisGraph.addEdge(vertex3, vertex2);
		
		ArrayList<Vertex> compare = new ArrayList<Vertex>();
		compare.add(vertex1);
		compare.add(vertex3);
		
		//tests the matrix to make sure that vertex1 and vertex3, the upstream
		//neighbours, are returned
		assertEquals(compare,thisGraph.getUpstreamNeighbors(vertex2));
		
		//try returning one neighbour
		compare.remove(vertex3);
		thisGraph.addEdge(vertex1, vertex3);
		
		assertEquals(thisGraph.getUpstreamNeighbors(vertex3),compare);
		
		//try returning nothing
		assertEquals(thisGraph.getUpstreamNeighbors(vertex1).size(),0);
		
	}
}
