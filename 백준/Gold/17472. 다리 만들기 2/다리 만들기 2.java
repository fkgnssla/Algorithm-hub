import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int arr[][], parent[];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	static class Edge implements Comparable<Edge> {
		int u, v, cost;

		public Edge(int u, int v, int cost) {
			super();
			this.u = u;
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "Edge [u=" + u + ", v=" + v + ", cost=" + cost + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//섬 나누기
		int num = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					change(i, j, num++);
				}
			}
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		//모든 간선 만들기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int curIsland = arr[i][j];
				if (curIsland == 0)
					continue;

				for (int k = 0; k < 4; k++) {
					int nx = i;
					int ny = j;
					int cost = -1;
					while (true) {
						nx += dx[k];
						ny += dy[k];
						cost++;

						if (nx < 0 || ny < 0 || nx >= n || ny >= m)	break;
						if (arr[nx][ny] == curIsland) break;
						if (cost == 1) {
							//다리의 길이가 2미만일때
							if (arr[nx][ny] != 0) break;
							continue;
						}
						
						if (arr[nx][ny] != 0) {
							pq.add(new Edge(curIsland, arr[nx][ny], cost));
							break;
						}
					}
				}
			}
		}

		// parent 초기화
		parent = new int[num];
		for (int i = 0; i < num; i++) {
			parent[i] = i;
		}

		int lineCnt = 0; //간선 수
		int result = 0; //다리 길이
		while (!pq.isEmpty()) {
			Edge poll = pq.poll();
			int u = poll.u;
			int v = poll.v;

			if (find(u) == find(v))
				continue;

			union(u, v);
			lineCnt++;
			result += poll.cost;

			if (lineCnt == num - 2) break;
		}


		if (lineCnt == num - 2)
			System.out.println(result);
		else
			System.out.println(-1);
	}

	static boolean visited[][];
	static void change(int x, int y, int num) {
		Deque<int[]> q = new ArrayDeque<>();
		visited[x][y] = true;
		arr[x][y] = num;
		q.addFirst(new int[] { x, y });

		while (!q.isEmpty()) {
			int cur[] = q.pollLast();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m)
					continue;
				if (arr[nx][ny] != 1)
					continue;
				if (visited[nx][ny])
					continue;

				visited[nx][ny] = true;
				arr[nx][ny] = num;
				q.addLast(new int[] { nx, ny });
			}
		}
	}

	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		parent[pb] = pa;
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}
}
