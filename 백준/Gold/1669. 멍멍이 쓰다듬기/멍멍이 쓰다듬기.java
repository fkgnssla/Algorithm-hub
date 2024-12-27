import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long x, y, cur = 1, result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		while (x != y) {
			if (x + cur == y || x + cur > y) {
				result += 1;
				break;
			} else if (x + cur * 2 > y) {
				result += 2;
				break;
			}
			x += (cur * 2);
			result += 2;
			cur++;
		}

		System.out.println(result);
	}
}
