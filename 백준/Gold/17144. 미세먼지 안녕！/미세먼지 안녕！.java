import java.io.*;
import java.util.*;

public class Main {
    static int r, c, t;
    static int airTop, airBottom;
    static int arr[][];
    static ArrayList<int[]> al;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()); //행의 크기
        c = Integer.parseInt(st.nextToken()); //열의크기
        t = Integer.parseInt(st.nextToken()); //시간
        
        arr = new int[r][c];
        al = new ArrayList<>(); //미세먼지 위치 리스트
        
        for(int i=0;i<r;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<c;j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        		if(arr[i][j] != 0 && arr[i][j] != -1) al.add(new int[] {i, j});
        		else if(arr[i][j] == -1) {
        			airBottom = i;
        		}
        	}
        }

        airTop = airBottom - 1;
        
        int dx[] = {0,0,-1,1};
        int dy[] = {-1,1,0,0};
        for(int i=0;i<t;i++) {
        	int newArr1[][] = new int[r][c];
        	
        	for(int a=0;a<r;a++) {
            	for(int b=0;b<c;b++) {
            		newArr1[a][b] = arr[a][b];
            	}
            }
        	
        	//확산 
        	int size = al.size();
        	for(int j=0;j<size;j++) {
        		int[] cur = al.get(j);
        		int num = 0;
        		
        		for(int k=0;k<4;k++) {
        			int nx = cur[0] + dx[k];
        			int ny = cur[1] + dy[k];
        			
        			if(nx<0 || ny<0 || nx>= r || ny>= c) continue;
        			if(arr[nx][ny]== -1) continue;
        			
        			num++;
        			newArr1[nx][ny] += arr[cur[0]][cur[1]]/5;
        		}
        		
        		newArr1[cur[0]][cur[1]] -= (arr[cur[0]][cur[1]]/5 * num);
        	}
        
        	
        	arr = newArr1;
        	
        	//공기청정기 작동
        	//반 시계 방향
        	int newArr[][] = new int[r][c];
        	newArr[airTop][1] = 0;
        	for(int j=2;j<c;j++) newArr[airTop][j] = arr[airTop][j-1];
        	for(int j=airTop-1;j>=0;j--) newArr[j][c-1] = arr[j+1][c-1];
        	for(int j=c-2;j>=0;j--) newArr[0][j] = arr[0][j+1];
        	for(int j=1;j<airTop;j++) newArr[j][0] = arr[j-1][0];
        	
        	//시계방향
        	newArr[airBottom][1] = 0;
        	for(int j=2;j<c;j++) newArr[airBottom][j] = arr[airBottom][j-1];
        	for(int j=airBottom+1;j<r;j++) newArr[j][c-1] = arr[j-1][c-1];
        	for(int j=c-2;j>=0;j--) newArr[r-1][j] = arr[r-1][j+1];
        	for(int j=r-2;j>airBottom;j--) newArr[j][0] = arr[j+1][0];
        	
        	for(int j=1;j<airTop;j++) {
        		for(int k=1;k<c-1;k++) {
        			newArr[j][k] = arr[j][k];
        		}
        	}
        	
        	for(int j=airBottom + 1;j<r - 1;j++) {
        		for(int k=1;k<c-1;k++) {
        			newArr[j][k] = arr[j][k];
        		}
        	}
        	
        	arr = newArr;
        	
        	
        	al.clear();
        	for(int a=0;a<r;a++) {
            	for(int b=0;b<c;b++) {
            		if(arr[a][b] > 0) {
            			al.add(new int[] {a,b});
            		}
            	}
            }
        }
        
        int result = 0;
        for(int a=0;a<r;a++) {
        	for(int b=0;b<c;b++) {
        		if(arr[a][b] > 0) {
        			result += arr[a][b];
        		}
        	}
        }
        
        System.out.println(result);
    }
}
