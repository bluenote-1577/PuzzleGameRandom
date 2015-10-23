package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import org.junit.Before;

import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.*;
import twitterAnalysis.*;

import org.junit.Test;

public class TwitterAnalysisTest {

	Graph adjmatrix;
	Graph adjlist;
	
	@Before
	public void initialize(){
		adjmatrix = new AdjacencyMatrixGraph();
		adjlist = new AdjacencyListGraph();
		
		TwitterAnalysis.twitterScan("datasets/test1.txt", adjmatrix);	
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
