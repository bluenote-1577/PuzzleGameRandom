package ca.ubc.ece.cpen221.mp3.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		
		thisGraph.addVertex(new Vertex("dicks"));
		ArrayList<Boolean> toAdd1 = new ArrayList<Boolean>();
		toAdd.add(false);
		toAdd1.add(false);
		toAdd1.add(false);
		
		ArrayList<ArrayList<Boolean>> matrixTest1 = new ArrayList<ArrayList<Boolean>>();
		matrixTest1.add(toAdd);
		matrixTest1.add(toAdd1);
		
		assertEquals(thisGraph.test(),matrixTest1);
	}

}
