/// Source : Google FooBar
/// Author : liuyubobobo
/// Time   : 2020-05-22

/// Dynamic Programming
/// Time Complexity: O(n^2)
/// Space Complexity: O(n)
public class Solution {

    public static int solution(int[] l) {

        int n = l.length;
        int[] ok = new int[n];
        for(int i = 1; i < n; i ++)
            for(int j = i - 1; j >= 0; j --)
                if(l[i] % l[j] == 0) ok[i] ++;

        int res = 0;
        for(int i = 2; i < n; i ++)
            for(int j = i - 1; j >= 0; j --)
                if(l[i] % l[j] == 0) res += ok[j];
        return res;
    }

    public static void main(String[] args){

        int[] l1 = {1, 1, 1};
        System.out.println(Solution.solution(l1));
        // 1

        int[] l2 = {1, 2, 3, 4, 5, 6};
        System.out.println(Solution.solution(l2));
        // 3
    }
}