import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int arr[] = new int[w];
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int right = -1;
        int leftIdx = 0, rightIdx = 0;
        long result = 0;

        while(leftIdx < w && arr[leftIdx] == 0) {
            leftIdx++;
        }

        while (leftIdx < w && rightIdx < w - 1) {
            //right 구하기
            //right가 left보다 높은 경우
            for (int i = rightIdx + 1; i < w; i++) {
                if (arr[leftIdx] <= arr[i] && right < arr[i]) {
                    right = arr[i];
                    rightIdx = i;
                    break;
                }
            }

            //left가 right보다 높은 경우
            if(right == -1) {
                for (int i = rightIdx + 1; i < w; i++) {
                    if (arr[leftIdx] >= arr[i] && right < arr[i]) {
                        right = arr[i];
                        rightIdx = i;
                    }
                }
            }

            int avgH = Math.min(arr[leftIdx], arr[rightIdx]);
            //빗물 구하기
            for (int i = leftIdx + 1; i < rightIdx; i++) {
                if (avgH - arr[i] < 0) continue;
                result += avgH - arr[i];
            }

            leftIdx = rightIdx;
            right = -1;
        }

        System.out.println(result);
    }
}