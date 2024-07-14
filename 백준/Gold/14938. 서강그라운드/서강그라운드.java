import java.io.*;
import java.util.*;

public class Main {
    static int n, m, r, cost, result = 0;
    static int itemCnt[], distence[];
    static ArrayList<ArrayList<Edge>> g = new ArrayList<>();
    static boolean visited[];
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        itemCnt = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) itemCnt[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());


        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            g.get(u).add(new Edge(v, w));
            g.get(v).add(new Edge(u, w));
        }

        distence = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(distence, Integer.MAX_VALUE);
            Arrays.fill(visited, false);
            pq.clear();

            dijkstra(i);
            cost = 0;

            for (int j = 1; j <= n; j++) {
                if (distence[j] <= m) cost += itemCnt[j];
            }

            result = Math.max(result, cost);
        }

        System.out.println(result);
    }

    static void dijkstra(int idx) {
        distence[idx] = 0;
        pq.add(new Edge(idx, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            visited[cur.v] = true;

            for (Edge next : g.get(cur.v)) {
                if (visited[next.v]) continue;

                if (distence[cur.v] + next.w < distence[next.v]) {
                    distence[next.v] = distence[cur.v] + next.w;
                    pq.add(new Edge(next.v, distence[next.v]));
                }
            }
        }
    }
}