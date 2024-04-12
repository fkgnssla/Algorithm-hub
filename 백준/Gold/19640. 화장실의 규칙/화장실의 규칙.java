import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int days, degree, group;
        boolean flag; //데카이면 true
        
		public Node(int days, int degree, int group, boolean flag) {
			super();
			this.days = days;
			this.degree = degree;
			this.group = group;
			this.flag = flag;
		}

		@Override
		public int compareTo(Node o) {
			if(this.days == o.days) {
				if(this.degree == o.degree) {
					return this.group - o.group;
				}
				return o.degree - this.degree;
			}
			return o.days - this.days;
		}
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //사원 수
        int m = Integer.parseInt(st.nextToken()); //새로운 줄의 수
        int k = Integer.parseInt(st.nextToken()); //앞에 서 있던 사원의 수
        
        ArrayList<Deque<Node>> al = new ArrayList(m+1);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int i=0;i<=m;i++) {
        	al.add(new ArrayDeque<>());
        }
        
        int curGroup = 1;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            if(i==k) al.get(curGroup).addLast(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), curGroup, true));
            else al.get(curGroup).addLast(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), curGroup, false));
            
            curGroup++;
            if(curGroup == m+1) curGroup = 1;
        }

        for(int i=1;i<=m;i++) {
        	if(!al.get(i).isEmpty()) pq.add(al.get(i).pollFirst());
        }
        
        int result = 0;
        while(true) {
        	Node cur = pq.poll();
        	
        	//데카를 찾은 경우
        	if(cur.flag) break;
        	
        	if(!al.get(cur.group).isEmpty()) {
        		pq.add(al.get(cur.group).pollFirst());
        	}
        	
        	result++;
        }

        System.out.println(result);
    }
}