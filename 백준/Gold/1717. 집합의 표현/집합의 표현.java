import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, v, a, b;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];

		for (int i = 0; i < n + 1; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if (v == 0) {
				union(a, b);
			} else if (find(a) == find(b)) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}

		System.out.println(sb.toString());
	}

	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		parent[pa] = pb;
	}

	static int find(int v) {
		if (parent[v] == v) {
			return v;
		}
		return parent[v] = find(parent[v]);
	}

}
