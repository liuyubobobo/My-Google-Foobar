/// Source : Google FooBar
/// Author : liuyubobobo
/// Time   : 2020-05-22

import java.util.Arrays;
import java.util.HashSet;


/// Using HashSet
/// Time Complexity: O(|res| * |s|)
/// Space Complexity: O(|res| * |s|)
public class Solution {

    public static int solution(String n, int b) {

        int k = n.length();

        HashSet<String> hashset = new HashSet<>();
        while(!hashset.contains(n)){
            hashset.add(n);
            n = next(n, k, b);
//            System.out.println(n);
        }

        return cycleLength(n, k, b);
    }

    private static int cycleLength(String n, int k, int b){

        HashSet<String> hashset = new HashSet<>();
        int res = 0;
        while(!hashset.contains(n)){
            hashset.add(n);
            n = next(n, k, b);
            res ++;
        }
        return res;
    }

    private static String next(String n, int k, int b){

        char[] charn1 = n.toCharArray();
        char[] charn2 = n.toCharArray();

        Arrays.sort(charn1);
        for(int i = 0; i < charn1.length / 2; i ++){
            char t = charn1[i];
            charn1[i] = charn1[charn1.length - 1 - i];
            charn1[charn1.length - 1 - i] = t;
        }
        String n1 = String.copyValueOf(charn1);
//        System.out.println(n1);

        Arrays.sort(charn2);
        String n2 = String.copyValueOf(charn2);
//        System.out.println(n2);

        int res = Integer.parseInt(n1, b) - Integer.parseInt(n2, b);
        String ret = Integer.toString(res, b);

        StringBuilder zero = new StringBuilder();
        for(int i = 0; i < k - ret.length(); i ++) zero.append('0');
        return zero.toString() + ret;
    }

    public static void main(String[] args){

        System.out.println(Solution.solution("210022", 3));
        // 3

        System.out.println(Solution.solution("1211", 10));
        // 1
    }

}