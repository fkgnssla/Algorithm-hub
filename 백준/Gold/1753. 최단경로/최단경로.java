import java.io.*;
import java.util.*;

public class Main {

	static class Edge implements Comparable<Edge> {
		int end, cost;

		public Edge(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost- o.cost;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Edge>> list = new ArrayList();
		for(int i=0;i<=v; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0;i<e;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Edge(b, cost));
		}
		
		int distance[] = new int[v+1];
		boolean visited[] = new boolean[v+1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[k] = 0;

		int min;
		int idx;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(k,0));
		
		while(!pq.isEmpty()) {
			//방문하지 않은 정점들 중 최소가중치의 정점 선택
			Edge cur = pq.poll();
			if(visited[cur.end]) continue;
			
			idx = cur.end;
			min = cur.cost;
			
			visited[idx] = true;
			
			//현재 정점을 통해 갈 수 있는 정점 갱신
			for(Edge edge : list.get(idx)) {
				int end = edge.end;
				int cost = edge.cost;
				
				if(!visited[end] && distance[end] > min + cost) {
					distance[end] = min + cost;
					pq.add(new Edge(end, distance[end]));
				}
			}
		}
		
		for(int i=1;i<=v;i++) {
			if(distance[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else System.out.println(distance[i]);
		}
	}
}
