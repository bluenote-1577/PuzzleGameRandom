package ca.ubc.ece.cpen221.mp3.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;

import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.*;
import twitterAnalysis.*;

import org.junit.Test;

public class TwitterAnalysisMatrixTest {

	Graph adjmatrix;
	
	
	@Before
	public void initialize () throws FileNotFoundException{
		adjmatrix = new AdjacencyMatrixGraph();
		TwitterAnalysis.twitterScan("datasets/test1.txt", adjmatrix);
	}
	
	@Test
	public void queryTestCommonInfluencers() throws IOException {
		
		Map<String, Boolean> allQueries = new HashMap<String, Boolean>();
		File file = new File("datasets/outputTest.txt");

		// if the file for writing doesnt exist, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		//create a scanner to read our queries, and a buffered
		//writer to write to our output.
		Scanner queryScan = new Scanner(new BufferedReader(new FileReader("datasets/test1.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

		try {
			queryScan = new Scanner(new BufferedReader(new FileReader("datasets/queryTest1.txt")));
			
			while (queryScan.hasNext()) {
				//scans 4 elements in the line
				String queryType = queryScan.next();
				String user1 = queryScan.next();
				String user2 = queryScan.next();
				String question = queryScan.next();

				//make sure that the query isn't a duplicate 
				if (!question.equals("?") || allQueries.containsKey(user1 + user2 + queryType))
					;

				else {
					if (queryType.equals("commonInfluencers")) {
						TwitterAnalysis.commonInfluencers(user1, user2, bw, adjmatrix);

					}

					if (queryType.equals("numRetweets")) {
						TwitterAnalysis.numRetweets(user1, user2, bw, adjmatrix);
					}
					//puts possible combinations of user1 and user2 in the map
					//to check for duplicates
					allQueries.put((user1 + user2 + queryType), true);
					allQueries.put((user2 + user1 + queryType), true);

				}
			}
		} finally {
			if (queryScan != null) {
				queryScan.close();
			}
			if (bw != null){
				bw.close();
			}
		}
		
		try {
		    file.delete();
		}
		 catch (Exception e) {
		    // File permission problems are caught here.
		    System.err.println("Something went wrong with delete");
		}
		
	}

}
