import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphImpl {
	public static void main(String[] args) {
		int v = 5;
		ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>(v);
		for(int i = 0 ; i < v ; i++) {
			arr.add(new ArrayList<Integer>());
		}
		
		addEdge(arr, 0, 1);
		addEdge(arr, 0, 4);
		addEdge(arr, 1, 2);
		addEdge(arr, 1, 3);
		addEdge(arr, 1, 4);
		addEdge(arr, 2, 3);
		addEdge(arr, 3, 4);
		
		printGraph(arr);
	}

	private static void printGraph(ArrayList<ArrayList<Integer>> arr) {
		// TODO Auto-generated method stub
		for(int i=0 ; i<arr.size() ; i++) {
			System.out.println("\nAdjacency list of vertex : " + i);
            System.out.print("head");
            for(int j=0 ; j<arr.get(i).size() ; j++) {
            	System.out.print("---> "+arr.get(i).get(j));
            }
            System.out.println();
		}
	}

	private static void addEdge(ArrayList<ArrayList<Integer>> arr, int i, int j) {
		// TODO Auto-generated method stub
		arr.get(i).add(j);
		arr.get(j).add(i);
	}
}
