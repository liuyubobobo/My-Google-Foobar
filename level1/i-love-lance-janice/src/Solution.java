public class Solution {

    public static String solution(String x) {


        StringBuilder sb = new StringBuilder();
        for(char c: x.toCharArray()) {
            if (c >= 'a' && c <= 'z')
                sb.append(Character.toChars('a' + 25 - (c - 'a')));
            else
                sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args){

        System.out.println(Solution.solution("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!"));
    }
}