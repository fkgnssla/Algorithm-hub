import java.io.*;
import java.util.HashMap;

public class Main {
    static int t, n;

    static class Node {
        HashMap<Character, Node> child;
        boolean endOfWord;

        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        boolean insert(String str) {
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (i == str.length() - 1 && node.child.get(c) != null) {
                    return false;
                }

                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);

                if (node.endOfWord) return false;
            }

            node.endOfWord = true;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();

            boolean result = true;
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                result = trie.insert(br.readLine());
                if(!result) flag = false;
            }

            if (flag) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb.toString());
    }

}