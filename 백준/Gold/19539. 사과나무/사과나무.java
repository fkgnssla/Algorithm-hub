import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer.parseInt(br.readLine());
        int sum = 0;
        ArrayList<Integer> al = new ArrayList();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int i = Integer.parseInt(st.nextToken());
            sum += i;
            al.add(i);
        }

        if(sum % 3 != 0) System.out.println("NO");
        else {
            int count = 0;
            for (int i : al) {
                count += i / 2;
            }
            if(sum/3 <= count) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}