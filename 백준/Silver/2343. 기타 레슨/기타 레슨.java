import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //강의의 수
        int m = Integer.parseInt(st.nextToken()); //블루레이 수
        int arr[] = new int[n];
        int left = arr[0], right = 0, result = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, arr[i]);
            right += arr[i];
        }

        while(left <= right) {
            int mid = (left + right) / 2;

            int blueray = 0, bluerayCnt = 0;
            for (int i = 0; i < n; i++) {
                if(blueray + arr[i] > mid) {
                    bluerayCnt++;
                    blueray = 0;
                }
                blueray += arr[i];
            }

            if(blueray > 0) bluerayCnt++;

            if(bluerayCnt <= m) {
                right = mid - 1;
                result = Math.min(result, mid);
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

}