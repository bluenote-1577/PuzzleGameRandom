package ca.ubc.ece.cpen221.mp3.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraphTest {

	AdjacencyMatrixGraph thisGraph = new AdjacencyMatrixGraph();
	@Test
	public void AddVertexTest() {
		thisGraph.addVertex(new Vertex("lolol"));
		ArrayList<ArrayList<Boolean>> matrixTest = new ArrayList<ArrayList<Boolean>>();
		ArrayList<Boolean> toAdd = new ArrayList<Boolean>();
		toAdd.add(false);
		matrixTest.add(toAdd);
		
		assertEquals(thisGraph.test(),matrixTest);
		
		thisGraph.addVertex(new Vertex("haha"));
		ArrayList<Boolean> toAdd1 = new ArrayList<Boolean>();
		toAdd.add(false);
		toAdd1.add(false);
		toAdd1.add(false);
		
		ArrayList<ArrayList<Boolean>> matrixTest1 = new ArrayList<ArrayList<Boolean>>();
		matrixTest1.add(toAdd);
		matrixTest1.add(toAdd1);
		
		assertEquals(thisGraph.test(),matrixTest1);
	}
	//test the getvertices function.
	@Test
	public void getVerticesTest() {
		thisGraph.addVertex(new Vertex("lolol"));
		thisGraph.addVertex(new Vertex("rofl"));
		List<Vertex> allVertices = thisGraph.getVertices();
		assertEquals(new Vertex("lolol"),allVertices.get(0));
		assertEquals("rofl",allVertices.get(1).toString());
	}
	@Test
	//tests the addEdge function and edgeExists
	public void addEdgeTest_edgeExistsTest(){
		thisGraph.addVertex(new Vertex("lolol"));
		thisGraph.addVertex(new Vertex("Rofl"));
		thisGraph.addEdge(thisGraph.getVertices().get(0),thisGraph.getVertices().get(1));
		ArrayList<Boolean> toAdd1 = new ArrayList<Boolean>();
		ArrayList<Boolean> toAdd = new ArrayList<Boolean>();
		toAdd1.add(false);
		toAdd1.add(true);
		toAdd.add(false);
		toAdd.add(false);
		ArrayList<ArrayList<Boolean>> matrixTest1 = new ArrayList<ArrayList<Boolean>>();
		matrixTest1.add(toAdd1);
		matrixTest1.add(toAdd);
		
		assertEquals(thisGraph.test(),matrixTest1 );
		
		matrixTest1.get(1).set(0, true);
		thisGraph.addEdge(new Vertex("Rofl"), new Vertex("lolol"));
		
		assertEquals(thisGraph.test(),matrixTest1);
		
		assertEquals(thisGraph.edgeExists(thisGraph.getVertices().get(1), thisGraph.getVertices().get(0)),true);
		assertEquals(thisGraph.edgeExists(thisGraph.getVertices().get(0), thisGraph.getVertices().get(1)), true);
		assertEquals(thisGraph.edgeExists(new Vertex("Rofl"), new Vertex("lolol")), true);
		assertEquals(thisGraph.edgeExists(thisGraph.getVertices().get(0), thisGraph.getVertices().get(0)),false);
	}
}
