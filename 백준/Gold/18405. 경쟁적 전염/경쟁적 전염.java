import java.io.*;
import java.util.*;

public class Main {
	static int n, k, s, x, y;
	static int[][] arr;
	static PriorityQueue<Node> virus = new PriorityQueue<>();
	static int[] rx = {0, 0, -1, 1};
	static int[] ry = {-1, 1, 0, 0};

	static class Node implements Comparable<Node> {
		int x, y, cost, depth;

		public Node(int x, int y, int cost, int depth) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.depth = depth;
		}

		@Override
		public int compareTo(Node o) {
			return this.depth == o.depth ? this.cost - o.cost : this.depth - o.depth;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] <= k) {
					virus.add(new Node(i, j, arr[i][j], 1));
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken()) - 1;
		y = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < s; i++) {
			cycle();
		}

		System.out.println(arr[x][y]);
	}

	static void cycle() {
		int size = virus.size();
		for (int i = 0; i < size; i++) {
			Node node = virus.poll();

			for (int j = 0; j < 4; j++) {
				int nx = node.x + rx[j];
				int ny = node.y + ry[j];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}

				if (arr[nx][ny] == 0) {
					arr[nx][ny] = node.cost;
					virus.add(new Node(nx, ny, node.cost, node.depth + 1));
				}
			}
		}
	}
}
