import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> al = new ArrayList<>();
    static boolean visited[]; //방문 여부
    static boolean possible[][]; //경로 방문 여부

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); //도시의 수
        m = Integer.parseInt(br.readLine()); //여행 계획에 속한 도시의 수
        possible = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];
        String result = "YES";

        for (int i = 0; i <= n; i++) {
            al.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    al.get(i).add(j);
                }
            }
        }

        int idx = 0;
        int arr[] = new int[m];
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            arr[idx++] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m - 1; i++) {
            int a = arr[i];
            int b = arr[i + 1];

            if (possible[a][b]) continue;

            for (int j = 1; j <= n; j++) visited[j] = false;

            if (!bfs(a, b)) {
                result = "NO";
                break;
            }
        }

        System.out.println(result);
    }

    static boolean bfs(int x, int y) {
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(x);
        visited[x] = true;

        int cur = -1;
        while (!q.isEmpty()) {
            cur = q.pollFirst();

            if(possible[cur][y]) {
                cur = y;
                break;
            }
            if (cur == y) break;

            for (int i : al.get(cur)) {
                if (visited[i]) continue;

                q.addLast(i);
                visited[i] = true;
                possible[cur][i] = true;
                possible[i][cur] = true;
            }
        }

        return cur == y;
    }

}