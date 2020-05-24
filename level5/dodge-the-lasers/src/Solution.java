import java.math.BigDecimal;
import java.math.BigInteger;


// https://math.stackexchange.com/questions/2052179/how-to-find-sum-i-1n-left-lfloor-i-sqrt2-right-rfloor-a001951-a-beatty-s/2053713#2053713
public class Solution {

    private static BigDecimal sqrt2 = new BigDecimal("1.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727");

    public static String solution(String s) {

        BigInteger res = calc(new BigInteger(s));
        return res.toString();
    }

    private static BigInteger calc(BigInteger n){

        if(n.equals(BigInteger.ZERO)) return BigInteger.ZERO;

        BigInteger n2 = sqrt2.subtract(BigDecimal.ONE).multiply(new BigDecimal(n)).toBigInteger();

        return n.multiply(n2)
                .add(n.multiply(n.add(BigInteger.ONE)).shiftRight(1))
                .subtract(n2.multiply(n2.add(BigInteger.ONE)).shiftRight(1))
                .subtract(calc(n2));
    }

    public static void main(String[] args){

        System.out.println(Solution.solution("5"));
        // 19

        System.out.println(Solution.solution("77"));
        // 4208
    }
}
