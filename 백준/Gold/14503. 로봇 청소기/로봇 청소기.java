import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x, y, di;

        public Node(int x, int y, int di) {
            this.x = x;
            this.y = y;
            this.di = di;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int arr[][] = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 1;
        int di[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북 동 남 서
        Deque<Node> q = new ArrayDeque<>();
        q.addLast(new Node(x, y, direction));

        if(arr[x][y] == 1) result = 0;
        arr[x][y] = 2;

        while (result == 0 || !q.isEmpty()) {
            Node cur = q.pollFirst();
            boolean flag = false;
            int nx = -1, ny = -1, nextDi = -1;

            for (int i = 0; i < 4; i++) {
                nextDi = cur.di;
                for (int j = 0; j < i + 1; j++) {
                    nextDi -= 1;
                    nextDi = nextDi < 0 ? 3 : nextDi;
                }

                nx = cur.x + di[nextDi][0];
                ny = cur.y + di[nextDi][1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (arr[nx][ny] == 1 || arr[nx][ny] == 2) continue;

                flag = true;
                break;
            }


            if (!flag) { //주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                nextDi = cur.di;
                for (int i = 0; i < 2; i++) {
                    nextDi -= 1;
                    nextDi = nextDi < 0 ? 3 : nextDi;
                }

                //후진
                nx = cur.x + di[nextDi][0];
                ny = cur.y + di[nextDi][1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) break;
                if (arr[nx][ny] == 1) break;

                q.addLast(new Node(nx, ny, cur.di));
            } else { //주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                arr[nx][ny] = 2;
                result++;
                q.addLast(new Node(nx, ny, nextDi));
            }
        }
        System.out.println(result);
    }

}