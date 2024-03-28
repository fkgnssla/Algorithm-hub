import java.io.*;
import java.util.*;

class Solution {
    int userIdlen = 0, bannedLen = 0,result = 0;
	boolean visited[];
	String str;
	HashSet<String> set = new HashSet<>();
	public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        userIdlen = user_id.length;
        bannedLen = banned_id.length;
        visited = new boolean[userIdlen];
        
        subset(0, user_id, banned_id);
        
        return set.size();
    }
	
	public void subset(int cnt, String[] user_id, String[] banned_id) {
		if(cnt == bannedLen) {
			String str = "";
			for(int i=0;i<userIdlen;i++) {
				if(visited[i]) {
					str += user_id[i] + " ";
				}
			}
			
			set.add(str);
//			result++;
			return;
		}
		
		String cur = banned_id[cnt];
		for(int i=0;i<userIdlen;i++) {
			if(visited[i]) continue;
			
			String findId = user_id[i];
			//가능한 문자열인지 확인
			if(cur.length() != findId.length()) continue;
			
			boolean flag = false;
			for(int j=0;j<findId.length();j++) {
				if(cur.charAt(j) != findId.charAt(j)) {
					if(cur.charAt(j) != '*') {
						flag = true;
						break;
					}
				}
			}
			
			if(flag) continue;
			visited[i] = true;
			subset(cnt + 1, user_id, banned_id);
			visited[i] = false;
		}
	}
}