package twitterAnalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ca.ubc.ece.cpen221.mp3.staff.*;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import ca.ubc.ece.cpen221.mp3.graph.*;

public class TwitterAnalysis {

	public static void main(String[] args) throws IOException{
		//store all the queries into the map. 
		//if the users have been queries already,
		//we can check if it's a duplicate.
		
		Map allQueries = new HashMap<String,Boolean>();
		Graph myGraph = new AdjacencyMatrixGraph();
		
		twitterScan("datasets/test1.txt", myGraph);
		
		File file = new File("datasets/outTest.txt");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		Scanner queryScan = new Scanner(new BufferedReader(new FileReader("datasets/test1.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
		
		
		try{
			queryScan = new Scanner(new BufferedReader(new FileReader("datasets/query.txt")));
			
			while(queryScan.hasNext()){
				String queryType = queryScan.next();
				String user1 = queryScan.next();
				String user2 = queryScan.next();
				String question = queryScan.next();
				
				if (!question.equals("?") || allQueries.containsKey(user1+user2+queryType));
				
				else{
					if (queryType.equals("commonInfluencers")){
						commonInfluencers(user1,user2,bw);
					}
					
					if (queryType.equals("numRetweets")){
						numRetweets(user1,user2,bw);
					}
					
				}
				
				
			}
		}
		finally{
			if (queryScan !=null){
				queryScan.close();
			}
		}
		
	}
		
		
	
	private static void commonInfluencers(String user1, String user2, BufferedWriter bw){
		
	}
	
	private static void numRetweets(String user1, String user2, BufferedWriter bw){
		
	}
	
	private static void twitterScan (String filename, Graph graph) throws FileNotFoundException{
		Scanner twitterscan = null;
		try{
			twitterscan = new Scanner(new BufferedReader(new FileReader(filename)));
			
			while(twitterscan.hasNext()){
				String followerName = twitterscan.next();
				System.out.print(followerName);
				twitterscan.next();
				String followedName = twitterscan.next();
				System.out.print(followedName);
				System.out.println("");
				
				Vertex follower = new Vertex(followerName);
				Vertex followed = new Vertex(followedName);
				
				graph.addVertex(follower);
				graph.addVertex(followed);
				graph.addEdge(follower, followed);
				
			}
		}
		finally{
			if (twitterscan !=null){
				twitterscan.close();
			}
		}
		
	}
}
