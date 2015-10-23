package ca.ubc.ece.cpen221.mp3.graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
	
	private final ArrayList<ArrayList<Boolean>> matrix = new ArrayList<ArrayList<Boolean>>();
	private final ArrayList<Vertex> allVertices = new ArrayList<Vertex>();
	/**
	 * Changes our matrix to accommodate for the new vertex that is not
	 * being pointed to. The vertex is then placed inside the hashmap. 
	 * Does nothing if the vertex is already inside.
	 * @param v the vertex we wish to put inside of our map and be represented
	 * by our matrix.
	 */
	public void addVertex(Vertex v){
		if(allVertices.contains(v))
			return;
		int size = matrix.size();
		
		for (ArrayList<Boolean> row : matrix){
			row.add(false);
		}
		ArrayList<Boolean> toAdd = new ArrayList<Boolean>(size);
		
		for(int i = 0; i <= size; i++){
			toAdd.add(false);
		}
		
		matrix.add(toAdd);

		allVertices.add(v);
		
	}
	
	/**
	 * this is a method for junit testing that returns my matrix
	 * so that I can test it easily.
	 * @return matrix
	 */
	public ArrayList<ArrayList<Boolean>> test(){
		return matrix;
	}
	
	@Override
	public void addEdge(Vertex v1, Vertex v2){

		
		int index1 = allVertices.indexOf(v1);
		int index2 = allVertices.indexOf(v2);
		
		matrix.get(index1).set(index2, true);
		
		
	}
	
	@Override
	public boolean edgeExists(Vertex v1, Vertex v2){
	
		
		int index1 = allVertices.indexOf(v1);
		int index2 = allVertices.indexOf(v2);
		
		return matrix.get(index1).get(index2);
	}
	
	@Override
	public List<Vertex> getDownstreamNeighbors(Vertex v){
		
		int indexV = allVertices.indexOf(v);
		ArrayList<Boolean> returnedRow = matrix.get(indexV);
		List<Vertex> toReturn = new ArrayList<Vertex>();
		int indexofEdge = 0;
		for ( Boolean directedEdge : returnedRow){
			if (directedEdge == true){
				toReturn.add(allVertices.get(indexofEdge));
			}
			indexofEdge++;
		}
		
		return toReturn;
		
	}

	@Override
	public List<Vertex> getUpstreamNeighbors(Vertex v){
		int indexV = allVertices.indexOf(v);
		List<Vertex> toReturn = new ArrayList<Vertex>();
		int indexofEdge = 0;
		for (ArrayList<Boolean> matrixRow : matrix){
			if(matrixRow.get(indexV) == true){
				toReturn.add(allVertices.get(indexofEdge));
			}
			indexofEdge++;
		}
		
		return toReturn;
	}
	
	@Override
	/**
	 * returns all of the vertices in our graph.
	 */
	public List<Vertex> getVertices(){
		List<Vertex> dude = new ArrayList<Vertex>();
		for (Vertex vertices : allVertices){
			dude.add(vertices);
		}
	
		return dude;
	}
}
