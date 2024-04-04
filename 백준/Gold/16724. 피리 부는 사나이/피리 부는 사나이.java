import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char arr[][];
    static int parent[];
    static int[] dx;
    static int[] dy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        visited = new boolean[n][m];
        parent = new int[1010000];
        dx = new int[150];
        dy = new int[150];

        dx['L'] = 0; dy['L'] = -1;
        dx['R'] = 0; dy['R'] = 1;
        dx['U'] = -1; dy['U'] = 0;
        dx['D'] = 1; dy['D'] = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                parent[i * 1000 + j] = i * 1000 + j;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(visited[i][j]) continue;
                move(i, j);
            }
        }

        System.out.println(result);
    }

    static boolean visited[][];
    static int result = 0;
    static void move(int x, int y) {
        visited[x][y] = true;

        char di = arr[x][y];
        int nx = x + dx[di];
        int ny = y + dy[di];

        if(nx < 0 || ny<0 || nx>=n || ny>=m) {
            result++;
            return;
        }
        if(find(x*1000 + y) == find(nx*1000 + ny)) {
            result++;
            return;
        }
        if(visited[nx][ny]) {
            union(x * 1000 + y, nx * 1000 + ny);
            return; //이미 있던 사이클
        }

        union(x * 1000 + y, nx * 1000 + ny);
        move(nx, ny);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        parent[pa] = parent[pb];
    }

    static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}

