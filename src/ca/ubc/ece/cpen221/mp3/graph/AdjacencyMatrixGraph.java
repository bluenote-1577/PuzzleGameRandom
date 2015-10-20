package ca.ubc.ece.cpen221.mp3.graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
	
	private final ArrayList<ArrayList<Boolean>> matrix = new ArrayList<ArrayList<Boolean>>();
	private final Map<Vertex,Integer> verticesMap = new LinkedHashMap<Vertex,Integer>();

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
		verticesMap.put(v,size);
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
		int index1 = verticesMap.get(v1);
		int index2 = verticesMap.get(v2);
		
		matrix.get(index1).set(index2, true);
		
		
	}
	
	@Override
	public boolean edgeExists(Vertex v1, Vertex v2){
		int index1 = verticesMap.get(v1);
		int index2 = verticesMap.get(v2);
		
		return matrix.get(index1).get(index2);
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
	/**
	 * returns all of the vertices in our graph.
	 */
	public List<Vertex> getVertices(){
		List<Vertex> dude = new ArrayList<Vertex>();
		for (Vertex vertices : verticesMap.keySet()){
			dude.add(vertices);
		}
	
		return dude;
	}
}
