import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int g[][];
    static boolean visited[][];
    static int rx[] = {0, 0, -1, 1};
    static int ry[] = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        g = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int pictureCnt = 0;
        int pictureMaxSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(g[i][j] == 1 && !visited[i][j]) {
                    pictureCnt++;
                    pictureMaxSize = Math.max(bfs(i, j), pictureMaxSize);
                }
            }
        }

        System.out.println(pictureCnt + "\n" + pictureMaxSize);
    }

    static int bfs(int x, int y) {
        Deque<int[]> q = new ArrayDeque<>();
        q.addFirst(new int[]{x, y});

        visited[x][y] = true;
        int pictureSize = 1;

        while(!q.isEmpty()) {
            int cur[] = q.pollFirst();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + rx[i];
                int ny = cur[1] + ry[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(visited[nx][ny]) continue;
                if(g[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.addLast(new int[]{nx, ny});
                pictureSize++;
            }
        }

        return pictureSize;
    }
}
