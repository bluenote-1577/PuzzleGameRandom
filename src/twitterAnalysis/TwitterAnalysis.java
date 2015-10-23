package twitterAnalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import ca.ubc.ece.cpen221.mp3.staff.*;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import ca.ubc.ece.cpen221.mp3.graph.*;

public class TwitterAnalysis {

	public static void main(String[] args) throws IOException{
		Scanner s = null;
		Graph myGraph = new AdjacencyMatrixGraph();
		
		try{
			s = new Scanner(new BufferedReader(new FileReader("datasets/test1.txt")));
			
			while(s.hasNext()){
				String followerName = s.next();
				System.out.print(followerName);
				s.next();
				String followedName = s.next();
				System.out.print(followedName);
				System.out.println("");
				
				Vertex follower = new Vertex(followerName);
				Vertex followed = new Vertex(followedName);
				
				myGraph.addVertex(follower);
				myGraph.addVertex(followed);
				myGraph.addEdge(follower, followed);
				
			}
		}
		finally{
			if (s !=null){
				s.close();
			}
		}
		
		
	}
}
