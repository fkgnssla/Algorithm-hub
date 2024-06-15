import java.io.*;
import java.util.*;

public class Main {
    static int parent[];

    static class Edge implements Comparable<Edge> {
        int u, v, cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //건물의 개수
        int m = Integer.parseInt(st.nextToken()); //도로의 개수
        long totalCost = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Edge newEdge = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            totalCost += newEdge.cost;
            pq.add(newEdge);
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int lineCnt = 0;
        long result = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.u;
            int v = e.v;

            if(find(u) == find(v)) continue;

            union(u, v);
            result += e.cost;
            lineCnt++;

            if (lineCnt == (n - 1)) break;
        }

        if (lineCnt < (n - 1)) System.out.println(-1);
        else System.out.println(totalCost - result);
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        parent[px] = py;
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

}