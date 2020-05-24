/// Source : Google FooBar
/// Author : liuyubobobo
/// Time   : 2020-05-22

import java.util.LinkedList;
import java.util.Queue;


/// BFS
/// Time Complexity: O(m * n * 2)
/// Space Complexity: O(m * n * 2)
public class Solution {

    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int solution(int[][] map) {

        int m = map.length, n = map[0].length;

        int[][][] visited = new int[m][n][2];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[0][0][0] = visited[0][0][1] = 1;
        while(!queue.isEmpty()){

            int cur = queue.poll();
            int curx = cur / 2 / n, cury = cur / 2 % n, left = cur % 2;
            if(curx == m - 1 && cury == n - 1) return visited[curx][cury][left];

            for(int[] d: dirs){
                int nextx = curx + d[0], nexty = cury + d[1];
                if(inArea(nextx, nexty, m, n)){
                    if(map[nextx][nexty] == 0 && visited[nextx][nexty][left] == 0){
                        visited[nextx][nexty][left] = visited[curx][cury][left] + 1;
                        queue.add(nextx * 2 * n + nexty * 2 + left);
                    }
                    else if(map[nextx][nexty] == 1 && left == 1 && visited[nextx][nexty][0] == 0){
                        visited[nextx][nexty][0] = visited[curx][cury][left] + 1;
                        queue.add(nextx * 2 * n + nexty * 2);
                    }
                }
            }
        }
        return -1;
    }

    private static boolean inArea(int x, int y, int m, int n){
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args){

        int[][] map1 = {
                {0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}
        };
        System.out.println(Solution.solution(map1));
        // 7

        int[][] map2 = {
                {0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}
        };
        System.out.println(Solution.solution(map2));
        // 11
    }
}
