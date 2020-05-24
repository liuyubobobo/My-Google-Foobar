public class Solution {

    public static String solution(long x, long y) {

        long res = (x + 1) * x / 2;
        if(y == 1) return String.valueOf(res);

        res += (x * 2 + y - 2) * (y - 1) / 2;
        return String.valueOf(res);
    }

    public static void main(String[] args){

        System.out.println(Solution.solution(3, 2));
        // 9

        System.out.println(Solution.solution(5, 10));
        // 96

        System.out.println(Solution.solution(1, 1));
        // 1

        System.out.println(Solution.solution(2, 3));
        // 8

        System.out.println(Solution.solution(100000, 100000));
        // 19999800001
    }
}