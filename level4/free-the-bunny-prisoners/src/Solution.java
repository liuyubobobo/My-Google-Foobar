import java.util.ArrayList;

public class Solution {

    public static int[][] solution(int num_buns, int num_required) {

        ArrayList<ArrayList<Integer>> combs = new ArrayList<>();
        ArrayList<Integer> cur = new ArrayList<>();
        dfs(num_buns, num_buns - num_required + 1, 0, cur, combs);

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for(int i = 0; i < num_buns; i ++)
            res.add(new ArrayList<>());
        for(int i = 0; i < combs.size(); i ++)
            for(int j: combs.get(i))
                res.get(j).add(i);

        int sz = res.get(0).size();
        int[][] ret = new int[num_buns][sz];
        for(int i = 0; i < num_buns; i ++){
            ret[i] = new int[sz];
            if(res.get(i).size() != sz) throw new RuntimeException();
            for(int j = 0; j < sz; j ++) ret[i][j] = res.get(i).get(j);
        }
        return ret;
    }

    private static void dfs(int n, int k, int index,
                            ArrayList<Integer> cur, ArrayList<ArrayList<Integer>> comb){

        if(cur.size() == k){
            comb.add(new ArrayList<>(cur));
            return;
        }

        for(int i = index; i < n; i ++){
            cur.add(i);
            dfs(n, k, i + 1, cur, comb);
            cur.remove(cur.size() - 1);
        }
    }

    private static void printResult(int[][] res){

        for(int[] row: res){
            for(int e: row) System.out.print(e + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args){

        printResult(Solution.solution(2, 1));
        // 0
        // 0

        printResult(Solution.solution(4, 4));
        // 0
        // 1
        // 2
        // 3

        printResult(Solution.solution(5, 3));
        // 0
        // 1
        // 2
        // 3
    }
}
