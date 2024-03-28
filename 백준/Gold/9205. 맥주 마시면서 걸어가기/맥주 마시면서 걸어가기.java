import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int endX, endY;
	static boolean flag = false;
	static ArrayList<int[]> stores;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0;t<tc;t++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int homeX = Integer.parseInt(st.nextToken());
			int homeY = Integer.parseInt(st.nextToken());
			stores = new ArrayList<>();
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				stores.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
				
			}
			
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			stores.add(new int[] {endX, endY});
			
			bfs(homeX, homeY);
			
			if(flag) System.out.println("happy");
			else System.out.println("sad");
		}
	}

	static void bfs(int x, int y) {
		Deque<int[]> q = new ArrayDeque();
		boolean visited[] = new boolean[n + 1];
		flag= false;
		
		q.addLast(new int[] {x, y});
		
		while(!q.isEmpty() && flag==false) {
			int cur[] = q.pollFirst();
			for(int i=0;i<=n;i++) {
				if(visited[i]) continue;
				
				int[]store = stores.get(i);
				int dis = Math.abs(cur[0] - store[0]) + Math.abs(cur[1] - store[1]);
				
				if(dis<=1000) {
					visited[i] = true;
					q.addLast(new int[] {store[0], store[1]});
					if(store[0] == endX && store[1] == endY) {
						flag= true;
						break;
					}
				}
			}
		}
	}
}
