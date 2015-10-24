package twitterAnalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import ca.ubc.ece.cpen221.mp3.staff.*;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import ca.ubc.ece.cpen221.mp3.graph.*;

public class TwitterAnalysis {

	public static void main(String[] args) throws IOException {
		// store all the queries into the map.
		// if the users have been queries already,
		// we can check if it's a duplicate.
		Map<Vertex,Boolean> vertexScanned = new HashMap<Vertex,Boolean>();
		Map<String, Boolean> allQueries = new HashMap<String, Boolean>();
		Graph myGraph = new AdjacencyMatrixGraph();

		twitterScan("datasets/test1.txt", myGraph,vertexScanned);

		List<Vertex> dude = myGraph.getVertices();
		File file = new File(args[1]);

		// if the file for writing doesnt exist, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		//create a scanner to read our queries, and a buffered
		//writer to write to our output.
		Scanner queryScan = null;
		BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

		try {	
			queryScan = new Scanner(new BufferedReader(new FileReader(args[0])));
			
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
						commonInfluencers(user1, user2, bw, myGraph);

					}

					if (queryType.equals("numRetweets")) {
						numRetweets(user1, user2, bw, myGraph);
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

	}
	/**
	 * Writes to our output file with the correct format for the corresponding query.
	 * @param user1 the first user scanned in the file
	 * @param user2 the second user scanned in the file.
	 * @param bw our buffer writer
	 * @param graph : our Graph, must contain vertices with user1 and user2.
	 * @throws IOException if bw is invalid.
	 */

	public static void commonInfluencers(String user1, String user2, BufferedWriter bw, Graph graph)
			throws IOException {

		bw.write("query: commonInfluencers " + user1 + " " + user2 + "\n");
		bw.write("<result>\n");
		for (Vertex vertex : Algorithms.commonUpstreamVertices(graph, new Vertex(user1), new Vertex(user2))) {
			bw.write("\t" + vertex.toString() + "\n");
		}
		bw.write("</result>\n\n");
		
	}

	/**
	 * 
	 * @param user1
	 * @param user2
	 * @param bw
	 * @param graph
	 * @throws IOException
	 * @throws NoPathException
	 */
	public static void numRetweets(String user1, String user2, BufferedWriter bw, Graph graph) 
			throws IOException {

		bw.write("query: numRetweets " + user1 + " " + user2 + "\n");
		bw.write("<result>\n");
		try{
		bw.write(Integer.toString((Algorithms.shortestDistance
				(graph, new Vertex(user1), new Vertex(user2))))+ "\n");
		}
		
		catch (NoPathException e){
			bw.write("No path found\n");
		}
		bw.write("</result>\n\n");
	}

	/**
	 * 
	 * @param filename
	 * @param graph
	 * @throws FileNotFoundException
	 */
	
	public static void twitterScan(String filename, Graph graph,
			Map<Vertex,Boolean> allVertices) throws FileNotFoundException {
		Scanner twitterscan = null;
		
		try {
			twitterscan = new Scanner(new BufferedReader(new FileReader(filename)));

			while (twitterscan.hasNext()) {
				String followerName = twitterscan.next();
				twitterscan.next();
				String followedName = twitterscan.next();

				Vertex follower = new Vertex(followerName);
				Vertex followed = new Vertex(followedName);
				if(!allVertices.containsKey(follower)){
					graph.addVertex(follower);
					allVertices.put(follower, true);
				}
				if(!allVertices.containsKey(followed)){
					graph.addVertex(followed);
					allVertices.put(followed, true);
				}
				graph.addEdge(followed, follower);
				System.out.print(followed);
				System.out.println(follower);
			}
		} finally {
			if (twitterscan != null) {
				twitterscan.close();
			}
		}

	}
}
