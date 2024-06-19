import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int arr[] = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0, right = n - 1, min = Integer.MAX_VALUE;
        int result1 = -1, result2 = -1;

        while(left < right) {
            if(Math.abs(arr[left] + arr[right]) < min) {
                min = Math.abs(arr[left] + arr[right]);
                result1 = arr[left];
                result2 = arr[right];
            }

            if(arr[left] + arr[right] > 0) right--;
            else if(arr[left] + arr[right] < 0) left++;
            else break;
        }

        System.out.println(result1 + " "  + result2);
    }
}