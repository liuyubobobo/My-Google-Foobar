import java.math.BigInteger;

public class Solution {

    public static int solution(String x) {

        BigInteger n = new BigInteger(x);
        int res = 0;
        while(!n.equals(BigInteger.ONE)){
            int p = n.getLowestSetBit();
            if(p != 0){
                res += p;
                n = n.shiftRight(p);
            }
            else{
                if(n.equals(BigInteger.valueOf(3)) ||
                   n.remainder(BigInteger.valueOf(4)).equals(BigInteger.ONE)){
                    n = n.subtract(BigInteger.ONE);
                    res ++;
                }
                else{
                    n = n.add(BigInteger.ONE);
                    res ++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args){

        System.out.println(Solution.solution("15"));
        // 5

        System.out.println(Solution.solution("4"));
        // 2

        System.out.println(Solution.solution("3"));
        // 2
    }
}
