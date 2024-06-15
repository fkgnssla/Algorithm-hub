import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, result;
    static int arr[];
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];
        result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        permutation(0, new int[n]);

        System.out.println(result);
    }

    static void permutation(int idx, int[] perm) {
        if(idx == n) {
            countLines(perm);
            return;
        }

        for (int i = 0; i < n; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            perm[idx] = arr[i];
            permutation(idx + 1, perm);
            visited[i] = false;
        }
    }

    static void countLines(int perm[]) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            int sum = 0;
            while (sum < 50) {
                sum += perm[j];
                j = (j + 1) % n;

                if(sum == 50) cnt++;
            }
        }

        // 2로 나누는 이유: 합계가 50일 때 cnt가 1 증가하니까, 그 반대편에서도 1이 증가할 것이기 때문이다.
        result = Math.max(result, cnt / 2);
    }
}