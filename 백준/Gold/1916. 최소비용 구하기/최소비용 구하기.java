import java.io.*;
import java.util.*;

public class Main {

    static int n, m, start, end;
    static int distance[];
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static ArrayList<ArrayList<Edge>> g = new ArrayList<>();
    static boolean visited[];

    static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        distance = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            distance[i] = Integer.MAX_VALUE;
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            g.get(u).add(new Edge(v, w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        distance[start] = 0;
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int cur = e.v;

            if (visited[cur]) continue;
            visited[cur] = true;

            for (int i = 0; i < g.get(cur).size(); i++) {
                Edge edge = g.get(cur).get(i);

                if (visited[edge.v]) continue;

                if (distance[cur] + edge.w < distance[edge.v]) {
                    distance[edge.v] = distance[cur] + edge.w;
                    pq.add(new Edge(edge.v, distance[edge.v]));
                }
            }
        }

        System.out.println(distance[end]);
    }

}