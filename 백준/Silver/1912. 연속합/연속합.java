import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, result = -1001, cur = -1000;
	static int arr[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			if(result < cur) result = cur;
			if(result < arr[i]) result = arr[i];
			if(cur < 0) cur = 0;

			cur += arr[i];
		}

		if(result < cur) result = cur;
		System.out.println(result);
	}
}
