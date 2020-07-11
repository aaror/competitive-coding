package graphs;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class graphs {
	static class Edge {
		int n;
		int w;

		Edge(int n, int w) {
			this.n = n;
			this.w = w;
		}

	}

	public static void addedge(ArrayList<ArrayList<Edge>> graph, int v1, int v2, int w) {
		graph.get(v1).add(new Edge(v2, w));
		graph.get(v2).add(new Edge(v1, w));
	}

	public static void addDirectedEdge(ArrayList<ArrayList<Edge>> graph, int src, int dest, int wt) {
		graph.get(src).add(new Edge(dest, wt));
		graph.get(dest).add(new Edge(src, wt));
	}

	public static void display(ArrayList<ArrayList<Edge>> graph) {
		System.out.println("----------------------------------");
		for (int v = 0; v < graph.size(); v++) {
			System.out.print(v + " -> ");
			for (int n = 0; n < graph.get(v).size(); n++) {
				Edge ne = graph.get(v).get(n);
				System.out.println("[" + ne.n + "@ " + ne.w + "]");
			}
		}
		System.out.println("----------------------------------");
	}

	public static boolean hasPath(int src, int dest, ArrayList<ArrayList<Edge>> graph, boolean[] visited) {
		if (src == dest) {
			return true;
		}

		visited[src] = true;
		for (int n = 0; n < graph.get(src).size(); n++) {
			Edge ne = graph.get(src).get(n);
			if (visited[ne.n] == false) {
				if (hasPath(ne.n, dest, graph, visited) == true) {
					return true;
				}
			}
		}
		return false;

	}

	public static void printAllPaths(int src, int dest, ArrayList<ArrayList<Edge>> graph, boolean[] visited,
			String path, int dsf) {
		if (src == dest) {
			System.out.println(path + dest + "@" + dsf);
			return;
		}
		visited[src] = true;
		for (int n = 0; n < graph.get(src).size(); n++) {
			Edge ne = graph.get(src).get(n);
			if (visited[ne.n] == false) {
				printAllPaths(ne.n, dest, graph, visited, path + src, dsf + ne.w);

			}
		}
		visited[src] = false;
	}

	static String sp = "";
	static int spd = Integer.MAX_VALUE;
	static String lp = " ";
	static int lpd = Integer.MIN_VALUE;
	static String cp = "";
	static int cpd = Integer.MAX_VALUE;
	static String fp = "";
	static int fpd = Integer.MIN_VALUE;

	public static void printpaths(int src, int dest, ArrayList<ArrayList<Edge>> graph, boolean[] visited, String path,
			int dsf, int factor) {
		if (src == dest) {
			// System.out.println(path+ "@" + dsf);
			if (dsf < spd) {
				spd = dsf;
				sp = path;

			}
			if (dsf > lpd) {
				lpd = dsf;
				lp = path;

			}
			if (dsf > factor && dsf < cpd) {
				cpd = dsf;
				cp = path;
			}
			if (dsf < factor && dsf > fpd) {
				fpd = dsf;
				fp = path;
			}

			return;
		}

		visited[src] = true;
		for (int n = 0; n < graph.get(src).size(); n++) {
			Edge ne = graph.get(src).get(n);
			if (visited[ne.n] == false) {
				printpaths(ne.n, dest, graph, visited, path + ne.n, dsf + ne.w, factor);

			}
		}
		visited[src] = false;

	}

	public static void Kthlargest(int k, int src, int dest, ArrayList<ArrayList<Edge>> graph) {
		boolean[] visited = new boolean[graph.size()];
		String Kpath = " ";
		int limit = Integer.MAX_VALUE;

		for (int i = 0; i < k; i++) {
			fp = "";
			fpd = Integer.MIN_VALUE;
			visited[src] = true;
			String pdf = "";

			printpaths(src, dest, graph, visited, pdf + src, 0, limit);
			limit = fpd;
			Kpath = fp;
		}

	}

	static class TravHelper {
		int v;
		String psf;
		int dsf;

		TravHelper(int v, String psf, int dsf) {
			this.v = v;
			this.psf = psf;
			this.dsf = dsf;
		}
	}

	public static boolean BSF(int src, int dest, ArrayList<ArrayList<Edge>> graph) {
		LinkedList<TravHelper> queue = new LinkedList<>();
		boolean[] visited = new boolean[graph.size()];
		queue.add(new TravHelper(src, "" + src, 0));
		while (queue.size() > 0) {
			TravHelper rem = queue.getFirst();
			queue.removeFirst();
			System.out.println(rem.v + "via" + rem.psf + "@" + rem.dsf);
			if (visited[rem.v] == true) {
				continue;

			} else {
				visited[rem.v] = true;
			}
			if (rem.v == dest) {
				return true;
			}
			for (int n = 0; n < graph.get(rem.v).size(); n++) {
				Edge ve = graph.get(rem.v).get(n);
				{
					TravHelper nbr = new TravHelper(ve.n, rem.psf + ve.n, rem.dsf + ve.w);
					queue.addLast(nbr);
				}
			}
		}
		return false;

	}

	static class FireHelper {
		int i;
		int j;
		int t;

		FireHelper(int i, int j, int t) {
			this.i = i;
			this.j = j;
			this.t = t;
		}
	}

	public static boolean isValid(ArrayList<ArrayList<Integer>> matrix, int i, int j) {
		if (i < 0 || i >= matrix.size() || j < 0 || j >= matrix.size()) {
			return false;
		} else if (matrix.get(i).get(j) == -1) {
			return false;
		} else if (matrix.get(i).get(j) == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void firestorm(ArrayList<ArrayList<Integer>> matrix) {
		LinkedList<FireHelper> queue = new LinkedList<>();
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(i).size(); j++) {
				if (matrix.get(i).get(j) == 0) {
					queue.addLast(new FireHelper(i, j, 0));

				}
			}
		}
		while (queue.size() > 0) {
			FireHelper rem = queue.removeFirst();

			if (matrix.get(rem.i).get(rem.j) > 0) {
				continue;
			} else {
				matrix.get(rem.i).set(rem.j, rem.t);
			}
			System.out.println(rem.i + rem.j + "burntat" + rem.t);
			if (isValid(matrix, rem.i - 1, rem.j)) {
				queue.addLast(new FireHelper(rem.i - 1, rem.j, rem.t + 1));
			}
			if (isValid(matrix, rem.i + 1, rem.j)) {
				queue.addLast(new FireHelper(rem.i + 1, rem.j, rem.t + 1));
			}
			if (isValid(matrix, rem.i, rem.j - 1)) {
				queue.addLast(new FireHelper(rem.i, rem.j - 1, rem.t + 1));
			}
			if (isValid(matrix, rem.i, rem.j + 1)) {
				queue.addLast(new FireHelper(rem.i, rem.j + 1, rem.t + 1));
			}
		}
	}

	public static String getconnectedcomponent(ArrayList<ArrayList<Edge>> graph, int src, boolean[] visited) {
		String com = "";
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(src);
		while (queue.size() > 0) {
			int rem = queue.removeFirst();

			if (visited[rem] == true) {
				continue;
			} else {
				visited[rem] = true;
			}
			com += rem;
			for (int n = 0; n < graph.get(rem).size(); n++) {
				Edge ne = graph.get(rem).get(n);
				if (visited[ne.n] == false) {
					queue.add(ne.n);
				}
			}

		}
		return com;
	}

	public static ArrayList<String> connectedcomponents(ArrayList<ArrayList<Edge>> graph) {
		ArrayList<String> list = new ArrayList<>();
		boolean[] visited = new boolean[graph.size()];
		for (int v = 0; v < graph.size(); v++) {
			if (visited[v] == false) {
				String comp = getconnectedcomponent(graph, v, visited);
				list.add(comp);
			}
		}

		return list;

	}

	public static boolean isValidforislands(ArrayList<ArrayList<Integer>> matrix, int i, int j) {
		if (i < 0 || i >= matrix.size() || j < 0 || j >= matrix.get(0).size()) {
			return false;
		} else if (matrix.get(i).get(i) == -1) {
			return false;
		} else if (matrix.get(i).get(i) > 0) {
			return false;
		} else {
			return true;
		}
	}

	public static void islandscomp(ArrayList<ArrayList<Integer>> matrix, int i, int j) {
		int tc = matrix.get(0).size();

		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(i * tc + j);

		while (queue.size() > 0) {
			int rem = queue.removeFirst();

			if (matrix.get(rem / tc).get(rem % tc) > 0) {
				continue;
			} else {
				matrix.get(rem / tc).set(rem % tc, 1);

			}

			if (isValidforislands(matrix, rem / tc - 1, rem % tc))
				queue.add(rem - tc);

			if (isValidforislands(matrix, rem / tc + 1, rem % tc))
				queue.add(rem + tc);

			if (isValidforislands(matrix, rem / tc, rem % tc - 1))
				queue.add(rem - 1);

			if (isValidforislands(matrix, rem / tc, rem % tc + 1))
				queue.add(rem + 1);

		}
	}

	public static int islands(ArrayList<ArrayList<Integer>> matrix) {
		int count = 0;

		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(i).size(); j++) {
				if (matrix.get(i).get(j) == 0) {
					islandscomp(matrix, i, j);
					count++;
				}
			}
		}

		return count;
	}

	public static boolean isCyclicComponent(ArrayList<ArrayList<Edge>> graph, boolean[] visited, int src) {
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(src);
		while (queue.size() > 0) {
			int rem = queue.removeFirst();

			if (visited[rem] == true)
				return true;
			else
				visited[rem] = true;

			for (int n = 0; n < graph.get(rem).size(); n++) {
				Edge ne = graph.get(rem).get(n);
				if (visited[ne.n] == false) {
					queue.add(ne.n);
				}
			}
		}

		return false;
	}

	public static boolean isCyclic(ArrayList<ArrayList<Edge>> graph) {
		boolean[] visited = new boolean[graph.size()];

		for (int v = 0; v < graph.size(); v++) {
			if (visited[v] == false) {
				boolean res = isCyclicComponent(graph, visited, v);
				if (res == true) {
					return true;
				}
			}
		}

		return false;
	}

	static class Dijkstrahelper implements Comparable<Dijkstrahelper> {
		int vertex;
		String path;
		int dsf;

		Dijkstrahelper(int v, String psf, int dsf) {
			this.vertex = v;
			this.path = psf;
			this.dsf = dsf;

		}

		@Override
		public int compareTo(Dijkstrahelper o) {
			return this.dsf - o.dsf;
		}

	}

	public static void Dijkstra(int src, ArrayList<ArrayList<Edge>> graph) {
		PriorityQueue<Dijkstrahelper> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[graph.size()];
		pq.add(new Dijkstrahelper(src, "" + src, 0));
		while (pq.size() > 0) {
			Dijkstrahelper rem = pq.peek();
			pq.remove();
			if (visited[rem.vertex] == true) {
				continue;

			} else {
				visited[rem.vertex] = true;
			}
			System.out.println(rem.vertex + "via" + rem.path + "@" + rem.dsf);

			for (int n = 0; n < graph.get(rem.vertex).size(); n++) {
				Edge ve = graph.get(rem.vertex).get(n);
				{
					Dijkstrahelper nbr = new Dijkstrahelper(ve.n, rem.path + ve.n, rem.dsf + ve.w);
					pq.add(nbr);
				}
			}
		}

	}

	static class Primehelper implements Comparable<Primehelper> {
		int vertex;
		int parentv;
		int cost;

		Primehelper(int v, int parentv, int cost) {
			this.vertex = v;
			this.parentv = parentv;
			this.cost = cost;

		}

		@Override
		public int compareTo(Primehelper o) {
			return this.cost - o.cost;
		}

	}

	public static ArrayList<ArrayList<Edge>> Minimumspanningtree(int src, ArrayList<ArrayList<Edge>> graph) {
		PriorityQueue<Primehelper> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[graph.size()];
		ArrayList<ArrayList<Edge>> ne = new ArrayList<>();

		for (int i = 0; i < graph.size(); i++) {
			ne.add(new ArrayList<>());
		}
		pq.add(new Primehelper(src, -1, 0));
		while (pq.size() > 0) {
			Primehelper rem = pq.remove();

			if (visited[rem.vertex] == true) {
				continue;

			} else {
				visited[rem.vertex] = true;
			}

			// System.out.println("["+rem.vertex+","+ rem.parentv+","+ rem.cost+"]");
			if (rem.parentv != -1) {
				addedge(ne, rem.vertex, rem.parentv, rem.cost);
			}

			for (int n = 0; n < graph.get(rem.vertex).size(); n++) {
				Edge ve = graph.get(rem.vertex).get(n);
				{
					Primehelper nbr = new Primehelper(ve.n, rem.vertex, ve.w);
					pq.add(nbr);
				}
			}
		}
		return ne;

	}

	static class Kedge implements Comparable<Kedge> {
		int v1;
		int v2;
		int w;

		Kedge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;

		}

		@Override
		public int compareTo(Kedge o) {
			return this.w - o.w;
		}

	}

	public static int find(int[] pa, int vtx) {
		if (pa[vtx] == vtx) {
			return vtx;
		} else {
			int sl = find(pa, pa[vtx]);
			return sl;
		}
	}

	public static void merge(int[] pa, int[] ra, int v1sl, int v2sl) {
		if (ra[v1sl] < ra[v2sl]) {
			pa[v1sl] = v2sl;
		} else if (ra[v2sl] > ra[v1sl]) {
			pa[v2sl] = v1sl;
		} else {
			pa[v1sl] = v2sl;
			ra[v2sl]++;
		}
	}

	public static ArrayList<ArrayList<Edge>> Kruskals(ArrayList<ArrayList<Edge>> graph) {
		ArrayList<ArrayList<Edge>> mst = new ArrayList<>();
		for (int i = 0; i < graph.size(); i++) {
			mst.add(new ArrayList<>());
		}
		int[] pa = new int[graph.size()];
		int[] ra = new int[graph.size()];
		Arrays.fill(ra, 1);
		for (int i = 0; i < pa.length; i++) {
			pa[i] = i;
		}
		PriorityQueue<Kedge> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int v = 0; v < graph.size(); v++) {
			for (int n = 0; n < graph.get(v).size(); n++) {
				Edge ne = graph.get(v).get(n);
				if (v < ne.n) {
					Kedge ke = new Kedge(v, ne.n, ne.w);
					pq.add(ke);
				}
			}
		}

		int counter = 0;
		while (pq.size() > 0 && counter < graph.size() - 1) {
			Kedge ke = pq.remove();
			int v1sl = find(pa, ke.v1);
			int v2sl = find(pa, ke.v2);
			if (v1sl != v2sl) {
				addedge(mst, ke.v1, ke.v2, ke.w);
				merge(pa, ra, v1sl, v2sl);
				counter++;
			}

		}
		return mst;
	}

	public static void hamiltonianhelper(int src, int osrc, ArrayList<Integer> psf, boolean[] visited,
			ArrayList<ArrayList<Edge>> graph) {

		if (psf.size() == graph.size() - 1) {
			for (int i = 0; i < psf.size(); i++) {
				System.out.print(psf.get(i) + " ");
			}
			System.out.print(src);

			boolean cycle = false;

			int last = src;
			for (int i = 0; i < graph.get(osrc).size(); i++) {
				Edge ne = graph.get(osrc).get(i);
				if (ne.n == last) {
					cycle = true;
					break;
				}
			}

			if (cycle == true) {
				System.out.println("*");
			} else {
				System.out.println(".");
			}
		}
		visited[src] = true;
		for (int n = 0; n < graph.get(src).size(); n++) {
			Edge ne = graph.get(src).get(n);
			if (visited[ne.n] == false) {
				psf.add(src);
				hamiltonianhelper(ne.n, osrc, psf, visited, graph);
				psf.remove(psf.size() - 1);
			}
		}
		visited[src] = false;
	}

	public static void hamiltonion(int src, int osrc, ArrayList<ArrayList<Edge>> graph) {
		boolean[] visited = new boolean[graph.size()];
		ArrayList<Integer> psf = new ArrayList<>();

		hamiltonianhelper(src, osrc, psf, visited, graph);
	}

	public static boolean iskValid(int[][] chess, int row, int col) {
		if (row < 0 || row >= chess.length || col < 0 || col >= chess[0].length) {
			return false;
		} else if (chess[row][col] != -1) {
			return false;
		}

		else {
			return true;
		}
	}

	static int counter = 0;

	public static void knightstour(int[][] chess, int row, int col, int move) {

		if (move == chess.length * chess.length - 1) {
			counter++;
			chess[row][col] = move;
			System.out.println("-------------"+counter+"------------");
			for (int i = 0; i < chess.length; i++) {
				for (int j = 0; j < chess.length; j++) {
					System.out.print(chess[i][j] + " \t ");
				}
				System.out.println();
			}
			System.out.println("-------------"+counter+"------------");
			chess[row][col] = -1;
			return;
		}

		chess[row][col] = move;

		if (iskValid(chess, row - 2, col + 1)) {
			knightstour(chess, row - 2, col + 1, move + 1);
		}
		if (iskValid(chess, row - 1, col + 2)) {
			knightstour(chess, row - 1, col + 2, move + 1);
		}
		if (iskValid(chess, row + 1, col + 2)) {
			knightstour(chess, row + 1, col + 2, move + 1);
		}
		if (iskValid(chess, row + 2, col + 1)) {
			knightstour(chess, row + 2, col + 1, move + 1);
		}
		if (iskValid(chess, row + 2, col - 1)) {
			knightstour(chess, row + 2, col - 1, move + 1);
		}
		if (iskValid(chess, row + 1, col - 2)) {
			knightstour(chess, row + 1, col - 2, move + 1);
		}
		if (iskValid(chess, row - 1, col - 2)) {
			knightstour(chess, row - 1, col - 2, move + 1);
		}
		if (iskValid(chess, row - 2, col - 1)) {
			knightstour(chess, row - 2, col - 1, move + 1);

		}
		chess[row][col] = -1;
	}
//	public static boolean isKvalid(int[][] arr, int row, int col) {
//		if (row < 0 || row >= arr.length || col < 0 || col >= arr[0].length) {
//			return false;
//		} else if (arr[row][col] != -1) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//
//	static int count = 0;
//
//	public static void KnightsTour(int[][] chess, int row, int col, int move) {
//
//		if (move == chess.length * chess.length - 1) {
//			count++;
//			chess[row][col] = move;
//			System.out.println("----------------------" + count + "----------------------");
//			for (int i = 0; i < chess.length; ++i) {
//				for (int j = 0; j < chess.length; ++j) {
//					System.out.print(chess[i][j] + "\t");
//				}
//				System.out.println();
//			}
//			System.out.println("----------------------" + count + "----------------------");
//			chess[row][col] = -1;
//
//			return;
//		}
//
//		chess[row][col] = move;
//		if (isKvalid(chess, row - 2, col + 1)) {
//			KnightsTour(chess, row - 2, col + 1, move + 1);
//		}
//		if (isKvalid(chess, row - 2, col - 1)) {
//			KnightsTour(chess, row - 2, col - 1, move + 1);
//		}
//		if (isKvalid(chess, row + 2, col + 1)) {
//			KnightsTour(chess, row + 2, col + 1, move + 1);
//		}
//		if (isKvalid(chess, row + 2, col - 1)) {
//			KnightsTour(chess, row + 2, col - 1, move + 1);
//		}
//		if (isKvalid(chess, row + 1, col - 2)) {
//			KnightsTour(chess, row + 1, col - 2, move + 1);
//		}
//		if (isKvalid(chess, row + 1, col + 2)) {
//			KnightsTour(chess, row + 1, col + 2, move + 1);
//		}
//		if (isKvalid(chess, row - 1, col - 2)) {
//			KnightsTour(chess, row - 1, col - 2, move + 1);
//		}
//		if (isKvalid(chess, row - 1, col + 2)) {
//			KnightsTour(chess, row - 1, col + 2, move + 1);
//		}
//		chess[row][col] = -1;
//	}
	public static void Floydwarshall(ArrayList<ArrayList<Edge>> graph) {
		int[][] res = new int[graph.size()][graph.size()];
		for (int i = 0; i < res.length; i++)
			Arrays.fill(res[i], Integer.MAX_VALUE);
		for (int i = 0; i < graph.size(); i++) {
			res[i][i] = 0;
			for (int n = 0; n < graph.get(i).size(); n++) {
				Edge ne = graph.get(i).get(n);
				res[i][ne.n] = ne.w;
			}
		}
		for (int i = 0; i < graph.size(); i++) {
			for (int s = 0; s < graph.size(); s++) {
				for (int d = 0; d < graph.size(); d++) {
					if (i == s || i == d || s == d) {
						continue;
					}

					else if (res[i][d] == Integer.MAX_VALUE || res[s][i] == Integer.MAX_VALUE) {
						continue;
					} else {
						res[s][d] = Math.min(res[s][d], res[s][i] + res[i][d]);
					}

				}
			}
		}
		for (int s = 0; s < graph.size(); s++) {
			for (int d = 0; d < graph.size(); d++) {
				System.out.print(res[s][d] + "\t\t");
			}
			System.out.println();
		}

	}

	public static void bellmanFord(ArrayList<ArrayList<Edge>> graph, int src) {
		int[] res = new int[graph.size()];
		Arrays.fill(res, Integer.MAX_VALUE);
		res[src] = 0;

		ArrayList<Kedge> alledges = new ArrayList<>();

		for (int v = 0; v < graph.size(); ++v) {
			for (int n = 0; n < graph.get(v).size(); ++n) {
				Edge ne = graph.get(v).get(n);
				Kedge ke = new Kedge(v, ne.n, ne.w);
				alledges.add(ke);
			}
		}

		for (int i = 0; i < graph.size() - 1; ++i) {
			for (int j = 0; j < alledges.size(); ++j) {
				Kedge ke = alledges.get(j);
				if (res[ke.v1] != Integer.MAX_VALUE) {
					if (res[ke.v1] + ke.w < res[ke.v2]) {
						res[ke.v2] = res[ke.v1] + ke.w;
					}
				}
			}
		}
		for (int j = 0; j < alledges.size(); ++j) {

			Kedge ke = alledges.get(j);

			if (res[ke.v1] != Integer.MAX_VALUE) {
				if (res[ke.v1] + ke.w < res[ke.v2]) {
					System.out.println("Negative cycle");
					return;
				}
			}

		}
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + "   ");
		}
		System.out.println();

	}

	public static void topologicalsorthelper(ArrayList<ArrayList<Edge>> graph, int src, boolean[] visited,
			LinkedList<Integer> stack) {

		visited[src] = true;
		for (int n = 0; n < graph.get(src).size(); n++) {
			Edge ne = graph.get(src).get(n);
			if (visited[ne.n] == false) {
				topologicalsorthelper(graph, ne.n, visited, stack);
			}

		}
		stack.add(src);

	}

	public static void topologicalsort(ArrayList<ArrayList<Edge>> graph) {
		boolean[] visited = new boolean[graph.size()];
		// Arrays.fill(visited, false);

		LinkedList<Integer> stack = new LinkedList<>();
		for (int v = 0; v < graph.size(); v++) {
			if (visited[v] == false) {
				topologicalsorthelper(graph, v, visited, stack);
			}
		}
		while (stack.size() > 0) {
			int val = stack.remove();
			System.out.print(val + " ");
		}
		System.out.println();
	}
	static int timer=0;
public static void bridgesandAP(ArrayList<ArrayList<Edge>> graph, boolean[] visited, boolean[] aps,int[] discovery, int[]low,int src,int par) {
	
	visited[src]=true;
	discovery[src]=low[src]=++timer;
	int counter=0;
	for(int n=0;n<graph.get(src).size();n++) {
		Edge ne=graph.get(src).get(n);
		int nbr=ne.n;
		if(visited[nbr]==true && nbr==par) {
			//bridgesandAP(graph, visited, aps, discovery, low, src, par);
			
		}
		else if(visited[nbr]==true && nbr!=par) {
			low[src]=Math.min(low[src], discovery[nbr]);
		}
		else {
		counter++;
		
			low[src]=Math.min(low[src],low[nbr]);
		
			bridgesandAP(graph, visited, aps, discovery, low, src, nbr);
			if(discovery[src]==1 ) {
				
			
				if(counter>=2) {
					aps[src]=true;
				}
			
			else {
		if(low[nbr]>=discovery[src]) {
			aps[src]=true;
			
		}
				}
				
		if(low[nbr]>discovery[src]){
			System.out.println("Bridge"+src+"to"+ nbr);
		}
	}
		}

}
}
	public static void main(String[] args) {
		ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
		for (int v = 0; v < 15; v++) {
			graph.add(new ArrayList<>());
		}
		addedge(graph, 0, 1, 10);
		addedge(graph, 1, 2, 10);
		addedge(graph, 2, 3, 10);
		addedge(graph, 0, 3, 40);
		addedge(graph, 3, 4, 2);
		addedge(graph, 4, 5, 3);
		addedge(graph, 4, 6, 8);
		addedge(graph, 5, 6, 3);
		addedge(graph, 4, 6, 8);
		addedge(graph, 0, 7, 1);
		addedge(graph, 0, 8, 1);
		display(graph);
		int[] discovery=new int[graph.size()];
		Arrays.fill(discovery, 0);

//		int[] low=new int[graph.size()];
//		Arrays.fill(low, 0);
//		boolean[] aps=new boolean[graph.size()];
//		boolean[] visited=new boolean[graph.size()];
//		bridgesandAP(graph, visited, aps, discovery, low,-1, 0);
//		for(int i=0;i<aps.length;i++) {
//			if(aps[i]==true) {
//				System.out.println(i+" ");
//			}
//			System.out.println();
//		}
		// display(graph);
		// hamiltonion(2, 2, graph);
		// hamiltonianhelper(2, 2, new ArrayList<Integer>(), new boolean[graph.size()],
		// graph);

		// ArrayList<ArrayList<Integer>> chess=new ArrayList<>(new
		// ArrayList<>(Arrays.asList(4));

		

		// ArrayList<ArrayList<Integer>> abc=new ArrayList<>(5);
		// abc.add(new ArrayList<>(Arrays.asList(-1,-1,-1,-1,-1,-1)));
		// abc.add(new ArrayList<>(Arrays.asList(-1,-1,-1,-1,-1,-1)));
		// abc.add(new ArrayList<>(Arrays.asList(-1,-1,-1,-2,-2,-2)));
		// abc.add(new ArrayList<>(Arrays.asList(-2,-2,-2,-2,-1,-1)));
		// abc.add(new ArrayList<>(Arrays.asList(-2,-1,-1,0,-2,-2)));
		// System.out.println(hasPath(0,6 , graph, new boolean[graph.size()]));
		// String path = "";
		//
		// Kthlargest(3,0,6,graph,new boolean[graph.size()],path,0);
		// System.out.println(" largest is " + fp +"@ "+ fpd);
		// printAllPaths(0, 6, graph, new boolean[graph.size()], path,0);
		// printpaths(0, 6, graph,new boolean[graph.size()], path+"0", 0,40);
		// System.out.println("Smallest" + sp + "@" + spd);
		// System.out.println("larget" + lp + "@" + lpd);
		// System.out.println("Ceil "+ cp +"@"+ cpd);
		// System.out.println("Floor "+ fp +"@" + fpd);
		// System.out.println(BSF(0, 6, graph));
		// int k=3;
		// Kthlargest(k, 0, 6, graph);
		// System.out.println(k+"largest"+ fp+"@"+ fpd);
		//
		// System.out.println(" largest is " + fp +"@ "+ fpd);
		// ArrayList<ArrayList<Integer>> abc=new ArrayList<>();
		// abc.add(new ArrayList<>(Arrays.asList(-2,-2,0,-2,-2,-2)));
		// abc.add(new ArrayList<>(Arrays.asList(-2,-1,-1,-2,-1,-1)));
		// abc.add(new ArrayList<>(Arrays.asList(-2,-2,-1,-2,-2,-2)));
		// abc.add(new ArrayList<>(Arrays.asList(-2,-2,-2,-2,-1,-1)));
		// abc.add(new ArrayList<>(Arrays.asList(-2,-1,-1,0,-2,-2)));
		// firestorm(abc);
		// System.out.println(connectedcomponents(graph));
		// ArrayList<ArrayList<Integer>> abc=new ArrayList<>();
		// abc.add(new ArrayList<>(Arrays.asList(0,0,-1,0,0)));
		// abc.add(new ArrayList<>(Arrays.asList(0,-1,-1,-1,-1)));
		// abc.add(new ArrayList<>(Arrays.asList(-1,0,0,0,0)));
		// abc.add(new ArrayList<>(Arrays.asList(0,-1,0,-1,0)));
		// abc.add(new ArrayList<>(Arrays.asList(0,-1,-1,-1,0)));
		//
		// System.out.println(islands(abc));
		// System.out.println(isCyclic(graph));
		// //Dijkstra(0, graph);
		// ArrayList<ArrayList<Edge>> mst=Minimumspanningtree(0, graph);
		// display(mst);
		// ArrayList<ArrayList<Edge>> mst = Kruskals(graph);
		// display(mst);
		// ArrayList<ArrayList<Edge>> graph1 = new ArrayList<>();
		//
		// for(int i=0;i<7;++i){
		// graph1.add(new ArrayList<>());
		// }

		// addDirectedEdge(graph1, 0, 1, 10);
		// addDirectedEdge(graph1, 1, 2, 10);
		// addDirectedEdge(graph1, 2, 3, 10);
		// addDirectedEdge(graph1, 0, 3, 40);
		// addDirectedEdge(graph1, 3, 4, 2);
		// addDirectedEdge(graph1, 4, 5, 3);
		// addDirectedEdge(graph1, 5, 6, 3);
		// addDirectedEdge(graph1, 4,6,8);
		// Floydwarshall(graph1);
		// int[][] chess=new int[5][];
		// Arrays.fill(chess[5], -1);
		// knightstour(chess, 2,1, 0);

		// bellmanFord(graph, 0);

//		ArrayList<ArrayList<Edge>> graph1 = new ArrayList<>();
//
//		for (int i = 0; i < 7; ++i) {
//			graph1.add(new ArrayList<>());
//		}
//		addDirectedEdge(graph1, 0, 1, 1);
//		addDirectedEdge(graph1, 0, 4, 1);
//		addDirectedEdge(graph1, 1, 2, 1);
//		addDirectedEdge(graph1, 2, 3, 1);
//		addDirectedEdge(graph1, 5, 4, 1);
//		addDirectedEdge(graph1, 5, 6, 1);
//		addDirectedEdge(graph1, 6, 3, 1);
//		topologicalsort(graph1);
	
//		int[][] arr = new int[5][5];
//
//		for (int i = 0; i < arr.length; ++i)
//			Arrays.fill(arr[i], -1);
//
//		knightstour(arr, 1, 1, 0);
	}
	

}
