import java.io.*;
import java.util.*;

public class Main {
    static int v, e, start;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static ArrayList<ArrayList<Edge>> g = new ArrayList<>();
    static int distence[];
    static boolean visited[];

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        distence = new int[v + 1];
        visited = new boolean[v + 1];

        for (int i = 0; i <= v; i++) {
            g.add(new ArrayList<>());
            distence[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            g.get(u).add(new Edge(v, w));
        }

        distence[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if(visited[e.v]) continue;
            visited[e.v] = true;

            for (Edge next : g.get(e.v)) {
                if (distence[e.v] + next.w < distence[next.v]) {
                    distence[next.v] = distence[e.v] + next.w;
                    pq.add(new Edge(next.v, distence[next.v]));
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            if (distence[i] == Integer.MAX_VALUE) sb.append("INF" + "\n");
            else sb.append(distence[i] + "\n");
        }

        System.out.println(sb.toString());
    }

}
