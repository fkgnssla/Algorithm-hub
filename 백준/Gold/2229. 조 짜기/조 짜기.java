import java.io.*;
import java.util.*;

public class Main {
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[n+1];
		
		for(int i=1;i<=n;i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int dp[] = new int[n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<i;j++) {
				dp[i] = Math.max(dp[i], dp[j - 1] + func(j, i));
			}
		}

		System.out.println(dp[n]);
	}
	
	static int func(int startIdx, int endIdx) {
		int minV = Integer.MAX_VALUE;
		int maxV = Integer.MIN_VALUE;
		
		for(int i=startIdx;i<=endIdx;i++) {
			minV = Math.min(minV, arr[i]);
			maxV = Math.max(maxV, arr[i]);
		}
		
		return maxV - minV;
	}

}
