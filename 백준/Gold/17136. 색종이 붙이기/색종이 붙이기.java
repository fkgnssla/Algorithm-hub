import java.io.*;
import java.util.*;

public class Main {
	static int arr[][];
	static ArrayList<int[]> al = new ArrayList();
	static boolean visited[][];
	static int[] paperCnt = {5,5,5,5,5};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
	
        arr = new int[10][10];
        visited = new boolean[10][10];
        
        for(int i=0;i<10;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<10;j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		if(arr[i][j] == 1) al.add(new int[] {i, j});
        	}
        }
        
        backtrack(0, 0);
        
        if(al.size() == 0) System.out.println(0);
        else if(result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
	}
	
	static int result = Integer.MAX_VALUE;
	static void backtrack(int idx, int cnt) {
        //현재 cnt값으론 result를 갱신시킬 수 없는 경우
        if(cnt >= result) return;
		
		if(idx == al.size()) {
			result = Math.min(result, cnt);
			return;
		}
		
		int cur[] = al.get(idx);
		boolean flag;
		
		if(visited[cur[0]][cur[1]]) {
			backtrack(idx + 1, cnt);
			return;
		}
		
		//1x1
		if(paperCnt[0] > 0) {
			visited[cur[0]][cur[1]] = true;
			paperCnt[0]--;
			backtrack(idx + 1, cnt + 1);
			paperCnt[0]++;
			visited[cur[0]][cur[1]] = false;
		}
		
		int nx = 0;
		int ny = 0;
		
		//2x2
		flag = false;
		if(paperCnt[1] > 0) {
			for(int i=0;i<2;i++) {
				for(int j=0;j<2;j++) {
					nx = cur[0] + i;
					ny = cur[1] + j;
					if(nx<0||ny<0||nx>=10||ny>=10) {
						flag = true; break;
					}
					if(arr[nx][ny] == 0 || visited[nx][ny]) {
						flag = true; break;
					}
				}
				if(flag) break;
			}
			if(!flag) {
				for(int i=0;i<2;i++) {
					for(int j=0;j<2;j++) {
						nx = cur[0] + i;
						ny = cur[1] + j;
						visited[nx][ny] = true;
					}
				}
				paperCnt[1]--;
				backtrack(idx + 1, cnt + 1);
				paperCnt[1]++;
				for(int i=0;i<2;i++) {
					for(int j=0;j<2;j++) {
						nx = cur[0] + i;
						ny = cur[1] + j;
						visited[nx][ny] = false;
					}
				}
			}
		}
		
		//3x3
		flag = false;
		if(paperCnt[2] > 0) {
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					nx = cur[0] + i;
					ny = cur[1] + j;
					if(nx<0||ny<0||nx>=10||ny>=10) {
						flag = true; break;
					}
					if(arr[nx][ny] == 0 || visited[nx][ny]) {
						flag = true; break;
					}
				}
				if(flag) break;
			}
			if(!flag) {
				for(int i=0;i<3;i++) {
					for(int j=0;j<3;j++) {
						nx = cur[0] + i;
						ny = cur[1] + j;
						visited[nx][ny] = true;
					}
				}
				paperCnt[2]--;
				backtrack(idx + 1, cnt + 1);
				paperCnt[2]++;
				for(int i=0;i<3;i++) {
					for(int j=0;j<3;j++) {
						nx = cur[0] + i;
						ny = cur[1] + j;
						visited[nx][ny] = false;
					}
				}
			}
		}
		
		
		//4x4
		flag = false;
		if(paperCnt[3] > 0) {
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					nx = cur[0] + i;
					ny = cur[1] + j;
					if(nx<0||ny<0||nx>=10||ny>=10) {
						flag = true; break;
					}
					if(arr[nx][ny] == 0 || visited[nx][ny]) {
						flag = true; break;
					}
				}
				if(flag) break;
			}
			if(!flag) {
				for(int i=0;i<4;i++) {
					for(int j=0;j<4;j++) {
						nx = cur[0] + i;
						ny = cur[1] + j;
						visited[nx][ny] = true;
					}
				}
				paperCnt[3]--;
				backtrack(idx + 1, cnt + 1);
				paperCnt[3]++;
				for(int i=0;i<4;i++) {
					for(int j=0;j<4;j++) {
						nx = cur[0] + i;
						ny = cur[1] + j;
						visited[nx][ny] = false;
					}
				}
			}
		}
		
		//5x5
		flag = false;
		if(paperCnt[4] > 0) {
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					nx = cur[0] + i;
					ny = cur[1] + j;
					if(nx<0||ny<0||nx>=10||ny>=10) {
						flag = true; break;
					}
					if(arr[nx][ny] == 0 || visited[nx][ny]) {
						flag = true; break;
					}
				}
				if(flag) break;
			}
			if(!flag) {
				for(int i=0;i<5;i++) {
					for(int j=0;j<5;j++) {
						nx = cur[0] + i;
						ny = cur[1] + j;
						visited[nx][ny] = true;
					}
				}
				paperCnt[4]--;
				backtrack(idx + 1, cnt + 1);
				paperCnt[4]++;
				for(int i=0;i<5;i++) {
					for(int j=0;j<5;j++) {
						nx = cur[0] + i;
						ny = cur[1] + j;
						visited[nx][ny] = false;
					}
				}
			}
		}
	}

}
