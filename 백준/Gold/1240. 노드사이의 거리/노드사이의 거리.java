import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n, m, a, b, cost;
	static ArrayList<ArrayList<Node>> al = new ArrayList<>();
	static boolean visited[];

	static class Node {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken()); // 노드의 개수
		m = Integer.parseInt(st.nextToken()); // 거리를 알고 싶은 노드 쌍의 개수
		visited = new boolean[n + 1];

		for (int i = 0; i <= n; i++) {
			al.add(new ArrayList<>());
		}

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			al.get(a).add(new Node(b, cost));
			al.get(b).add(new Node(a, cost));
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			for (int j = 0; j <= n; j++) visited[j] = false;

            visited[a] = true;
			sb.append(dfs(a, b, 0) + "\n");
		}
		System.out.println(sb.toString());
	}

	public static int dfs(int a, int b, int sum) {
		if (a == b) {
			return sum;
		}

		for (int i = 0; i < al.get(a).size(); i++) {
			Node node = al.get(a).get(i);
			if (visited[node.v]) continue;
			visited[node.v] = true;

			int result = dfs(node.v, b, sum + node.cost);
			if (result != -1) {
				return result;
			}
		}

		return -1;
	}
}
