import java.io.*;
import java.util.*;

public class Main {

	static int r,c;
	static char arr[][];
	static int startX, startY, endX, endY;
	static ArrayList<int[]> al, al2;
	
	static class Node {
		int x, y, cost;

		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		al = new ArrayList<>();
		
		arr = new char[r][c];
		visited = new boolean[r][c];
		
		for(int i=0;i<r;i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(arr[i][j] == 'S') {
					startX = i;
					startY = j;
				} else if(arr[i][j] == 'D') {
					endX = i;
					endY = j;
				} else if(arr[i][j] == '*') {
					al.add(new int[] {i, j});
				}
			}
		}
		
		bfs();
		
		if(result == null) {
			System.out.println("KAKTUS");
		} else System.out.println(result.cost);
	}

	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	static boolean visited[][];
	static Node result = null;
	static void bfs() {
		Deque<Node> q = new ArrayDeque();
		q.addLast(new Node(startX, startY, 0));
		
		while(!q.isEmpty()) {
			//물 이동
			al2 = new ArrayList<>();
			for(int water[] : al) {
				waterMove(water[0], water[1]);
			}
			al = al2;
			
			//도치 이동
			int size = q.size();
			for(int i=0;i<size;i++) {
				Node poll = q.pollFirst();
				
				if(poll.x == endX && poll.y == endY) {
					result = poll;
					break;
				}
				
				//4방 탐색
				for(int j=0;j<4;j++) {
					int nx = poll.x + dx[j];
					int ny = poll.y + dy[j];
					
					if(nx<0 || ny<0 || nx>=r || ny>=c) continue;
					if(visited[nx][ny]) continue;
					if(arr[nx][ny] == '.' || arr[nx][ny] == 'D') {
						visited[nx][ny] = true;
						q.addLast(new Node(nx, ny, poll.cost + 1));
					}
				}
			}
			
			if(result != null) break;
		}
	}
	
	static void waterMove(int x, int y) {
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || ny<0 || nx>=r || ny>=c) continue;
			if(arr[nx][ny] != '.') continue;
			
			arr[nx][ny] = '*';
			al2.add(new int[] {nx, ny});
		}
	}
}
