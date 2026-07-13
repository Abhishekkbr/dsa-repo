import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BfsDfs {

	int V;
	int E;
	LinkedList<Integer> adj[];

//	BfsDfs(int size){
//		this.V = size;
//		this.E = 0;
//		this.adj = new LinkedList[size];
//		for(int i=0 ; i<size ; i++) {
//			adj[i] = new LinkedList();
//		}
//	}
//
//	void addEdge(int v, int w) {
//		adj[v].add(w);
//		adj[w].add(v);
//		E++;
//	}
	public BfsDfs(int size) {
		// TODO Auto-generated constructor stub
		this.V = size;
		this.E = 0;
		this.adj = new LinkedList[size];
		for(int i = 0; i < size ; i++) {
			adj[i] = new LinkedList();
		}
	}

	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
//	public void bfs(int s) {
//		Queue<Integer> queue = new LinkedList<>();
//		boolean[] visited = new boolean[V];
//		visited[s] = true;
//		queue.offer(s);
//
//		while(!queue.isEmpty()) {
//			int u = queue.poll();
//			System.out.print(u + " ");
//
//			for(int i : adj[u]) {
//				if(!visited[i]) {
//					visited[i] = true;
//					queue.offer(i);
//				}
//			}
//		}
//		System.out.println();
//	}
	
	public void bfs(int s) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[V];
		visited[s] = true;
		queue.offer(s);
		while(!queue.isEmpty()) {
			int u = queue.poll();
			System.out.println(u);
			for(int i : adj[u]) {
				if(!visited[i]) {
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		System.out.println();
	}
	
	public void dfs(int s) {
		boolean[] visited1 = new boolean[V];
		Stack<Integer> stack = new Stack<>();
		stack.push(s);
		
		while(!stack.isEmpty()) {
			int u = stack.pop();
			if(!visited1[u]) {
				visited1[u] = true;
				System.out.print(u + " ");
				
				for(int v : adj[u]) {
					if(!visited1[v]) {
						stack.push(v);
					}
				}
			}
		}
	}


	public static void main(String[] args) {
		BfsDfs g = new BfsDfs(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 0);
		g.addEdge(2, 4);

		System.out.println("Following is Breadth First Traversal "+
				"(starting from vertex 2)");

		g.bfs(0);
		
		System.out.println("Following is Depth First Traversal "+
				"(starting from vertex 2)");
		g.dfs(0);
	}
}
