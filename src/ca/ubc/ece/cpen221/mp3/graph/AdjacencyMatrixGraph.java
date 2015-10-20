package ca.ubc.ece.cpen221.mp3.graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
	
	private final ArrayList<ArrayList<Boolean>> matrix = new ArrayList<ArrayList<Boolean>>();
	private final HashMap<Integer,Vertex> vertices = new HashMap<Integer,Vertex>();

	/**
	 * Changes our matrix to accommodate for the new vertex that is not
	 * being pointed to. The vertex is then placed inside the hashmap. 
	 * @param v the vertex we wish to put inside of our map and be represented
	 * by our matrix.
	 */
	public void addVertex(Vertex v){
		int size = matrix.size();
		
		for (ArrayList<Boolean> row : matrix){
			row.add(false);
		}
		ArrayList<Boolean> toAdd = new ArrayList<Boolean>(size);
		
		for(int i = 0; i <= size; i++){
			toAdd.add(false);
		}
		
		matrix.add(toAdd);
		vertices.put(size, v);
	}
	
	public ArrayList<ArrayList<Boolean>> test(){
		return matrix;
	}
	
	@Override
	public void addEdge(Vertex v1, Vertex v2){
		
	}
	
	@Override
	public boolean edgeExists(Vertex v1, Vertex v2){
		return false;
	}
	
	@Override
	public List<Vertex> getDownstreamNeighbors(Vertex v){
		return null;
	}

	@Override
	public List<Vertex> getUpstreamNeighbors(Vertex v){
		return null;
	}
	
	@Override
	public List<Vertex> getVertices(){
		return null;
	}
}
