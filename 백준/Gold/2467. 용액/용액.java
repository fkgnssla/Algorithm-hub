import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = n-1;
		int result = Integer.MAX_VALUE, resultX = -1, resultY = -1;
		while(left<right) {
			int value = arr[left] + arr[right];
			
			if(result >= Math.abs(value)) {
				result = Math.abs(value);
				resultX = arr[left];
				resultY = arr[right];
			}

			if(value > 0) right--;
			else left++;
		}
		
		System.out.println(resultX + " " + resultY);
	}

}
