package Search;
import java.util.HashSet;
public class Solution {
    public static void main(String[] args){
        Solution s = new Solution();
//        char[][] b = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        char[][] b = {{'a','b'}};
//        String ss = "ABCESSS";
//        System.out.println(ss.substring(1,ss.length()));
        System.out.println(s.exist(b,"aba"));
//        HashSet<coord> set = new HashSet<>();
//        set.add(new coord(1,1));
//        System.out.println(set.contains(new coord(1,1)));
    }
    /************************79*******************************/
    // This code is LTE, but I think it handles the border condition well.
    // The reason of LTE is that I rebuild a two dimen char array and it costs much time
    // The basic idea is use dfs to search
     public boolean exist(char[][] board, String word) {
         int h = board.length, w = board[0].length;
         if(w == 0)
             return false;
         if(word.length() == 0)
             return true;
         char[][] wboard = new char[h + 2][w + 2];
         for(int i = 0; i < h; i++){
             for(int j = 0; j < w;j++){
                 wboard[i + 1][j + 1] = board[i][j];
             }
         }
         char head = word.charAt(0);
         boolean ans = false;
         boolean[][] isVisit = new boolean[h + 2][w + 2];
         for(int i = 1;i < h + 1;i++){
             for(int j = 1; j < w + 1;j++){
                 if(dfs(wboard,i, j,isVisit,word, 0))
                     return true;
             }
         }

         return false;
     }
     public boolean dfs(char[][] board, int i, int j, boolean[][] isVisit, String word, int step){
         int len = word.length();
         if(len == step) {
             return true;
         }
         isVisit[i][j] = true;
         char target = word.charAt(step);
         boolean ans = false;
         if(board[i - 1][j] == target && !isVisit[i - 1][j])
             ans = ans | dfs(board,i - 1, j, isVisit, word,step + 1);
         if(board[i + 1][j] == target && !isVisit[i + 1][j])
             ans = ans | dfs(board, i + 1, j, isVisit, word, step+ 1);
         if(board[i][j - 1] == target && !isVisit[i][j - 1])
             ans = ans | dfs(board, i, j - 1, isVisit, word, step + 1);
         if(board[i][j + 1] == target && !isVisit[i][j + 1])
             ans = ans | dfs(board,i , j + 1, isVisit, word,step+1);
         isVisit[i][j] = false;
         return ans;
     }
}
class coord implements Comparable<coord>{
    int x;
    int y;
    public coord(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(coord c){
        if(c.x == this.x && c.y == this.y)
            return 0;
        else
            return -1;
    }

    @Override
    public boolean equals(Object obj) {
        coord c = (coord) obj;
        if(this.x == c.x && this.y == c.y)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return this.x + this.y;
    }
}
