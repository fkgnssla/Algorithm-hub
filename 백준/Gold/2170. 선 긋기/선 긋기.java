import java.io.*;
import java.util.*;

public class Main {
    static int n, x, y, curX, curY, result = 0;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static Node node;

    static class Node implements Comparable<Node> {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Node o) {
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            pq.add(new Node(x, y));
        }

        node = pq.poll();
        x = node.x;
        y = node.y;

        while (!pq.isEmpty()) {
            node = pq.poll();
            curX = node.x;
            curY = node.y;

            if (curX < y && y <= curY) {
                y = curY;
            } else if (x <= curX && curY <= y) {
                continue;
            } else {
                result += (y - x);
                x = curX;
                y = curY;
            }
        }

        result += (y - x);
        System.out.println(result);
    }
}