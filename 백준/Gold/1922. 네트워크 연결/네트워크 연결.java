import java.io.*;
import java.util.*;

public class Main {
    static int n, m, edgeCnt = 0, result = 0;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int parent[];

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
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
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (a == b) continue;
            pq.add(new Edge(a, b, w));
        }

        parent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) parent[i] = i;


        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            if (find(e.u) == find(e.v)) continue;

            union(e.u, e.v);
            result += e.w;
            edgeCnt++;

            if (edgeCnt == n - 1) break;
        }

        System.out.println(result);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        parent[pa] = pb;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
