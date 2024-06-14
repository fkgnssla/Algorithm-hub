import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int maxDp[][] = new int[n + 1][3];
        int minDp[][] = new int[n + 1][3];

        st = new StringTokenizer(br.readLine());
        int i1, i2, i3;
        i1 = Integer.parseInt(st.nextToken());
        i2 = Integer.parseInt(st.nextToken());
        i3 = Integer.parseInt(st.nextToken());
        maxDp[1][0] = minDp[1][0] = i1;
        maxDp[1][1] = minDp[1][1] = i2;
        maxDp[1][2] = minDp[1][2] = i3;

        for (int i = 2; i <= n; i++) {
            maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]);
            maxDp[i][1] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]);
            maxDp[i][1] = Math.max(maxDp[i][1], maxDp[i - 1][2]);
            maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]);

            minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]);
            minDp[i][1] = Math.min(minDp[i - 1][0], minDp[i - 1][1]);
            minDp[i][1] = Math.min(minDp[i][1], minDp[i - 1][2]);
            minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]);

            st = new StringTokenizer(br.readLine());
            i1 = Integer.parseInt(st.nextToken());
            i2 = Integer.parseInt(st.nextToken());
            i3 = Integer.parseInt(st.nextToken());
            
            maxDp[i][0] += i1;
            maxDp[i][1] += i2;
            maxDp[i][2] += i3;
            
            minDp[i][0] += i1;
            minDp[i][1] += i2;
            minDp[i][2] += i3;
        }

        int max = -1;
        int min = Integer.MAX_VALUE;

        max = Math.max(maxDp[n][0], maxDp[n][1]);
        max = Math.max(max, maxDp[n][2]);

        min = Math.min(minDp[n][0], minDp[n][1]);
        min = Math.min(min, minDp[n][2]);

        System.out.println(max + " " + min);
    }
}