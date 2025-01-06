import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static String arr[][];
	static ArrayList<Node> teachers = new ArrayList<>();
	static int[] rx = {0, 0, -1, 1};
	static int[] ry = {-1, 1, 0, 0};
	static String result = "NO";

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new String[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = st.nextToken();
				if (arr[i][j].equals("T"))
					teachers.add(new Node(i, j));
			}
		}

		backtrack(0);
		System.out.println(result);
	}

	static void backtrack(int cnt) {
		if (cnt == 3) {
			if (checkTeachers())
				result = "YES";
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j].equals("X")) {
					arr[i][j] = "O";
					backtrack(cnt + 1);
					arr[i][j] = "X";
				}
			}
		}
	}

	static boolean checkTeachers() {
		for (int i = 0; i < teachers.size(); i++) {
			Node node = teachers.get(i);
			for (int j = 0; j < 4; j++) {
				int nx = node.x + rx[j];
				int ny = node.y + ry[j];

				while (true) {
					if (nx == n || ny == n || nx < 0 || ny < 0 || arr[nx][ny].equals("O")) break;
					if (arr[nx][ny].equals("S")) return false;

					nx = nx + rx[j];
					ny = ny + ry[j];
				}
			}
		}
		return true;
	}
}
