import java.util.LinkedList;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Queue;

public class Solution {

    private static class Pair implements Comparable<Pair>{

        public int a, b;

        public Pair(int a, int b){
            this.a = a;
            this.b = b;
        }

        public Pair(){
            this(0, 0);
        }

        @Override
        public int compareTo(Pair another){

            if(a == another.a) return b - another.b;
            return a - another.a;
        }

        @Override
        public boolean equals(Object o){

            if(this == o)
                return true;

            if(o == null || o.getClass() != this.getClass())
                return false;

            Pair another = (Pair)o;
            return a == another.a && b == another.b;
        }
    }

    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) {

        int w = dimensions[0], h = dimensions[1];

        TreeSet<Pair> res = new TreeSet<>();
        TreeMap<Pair, int[]> suiside = new TreeMap<>();
        TreeMap<Pair, int[]> yourVisited = new TreeMap<>();
        TreeMap<Pair, int[]> guardVisited = new TreeMap<>();

        Queue<Pair> queue = new LinkedList<>();
        if(disSquare(your_position, guard_position) > distance * distance)
            return 0;

        res.add(directions(your_position, guard_position));
        queue.add(new Pair());
        yourVisited.put(new Pair(), your_position);
        guardVisited.put(new Pair(), guard_position);

        while(!queue.isEmpty()){
            Pair cur = queue.poll();

            for(int[] d: dirs){

                Pair next = new Pair(cur.a + d[0], cur.b + d[1]);
                if(guardVisited.containsKey(next)){
                    if(!yourVisited.containsKey(next)) throw new RuntimeException();
                    continue;
                }

                int[] newGuardPosition = {
                        next.a == cur.a ? guardVisited.get(cur)[0] :
                        next.a >= 0 ?
                        (next.a % 2 == 1 ? next.a * w + w - guard_position[0] : next.a * w + guard_position[0]) :
                        ((-next.a) % 2 == 1 ? -(-next.a - 1) * w - guard_position[0] : -(-next.a - 1) * w - ( w - guard_position[0]))
                        ,
                        next.b == cur.b ? guardVisited.get(cur)[1] :
                        next.b >= 0 ?
                        (next.b % 2 == 1 ? next.b * h + h - guard_position[1] : next.b * h + guard_position[1]) :
                        ((-next.b) % 2 == 1 ? -(-next.b - 1) * h - guard_position[1] : -(-next.b - 1) * h - ( h - guard_position[1]))
                };

                int[] newYourPosition = {
                        next.a == cur.a ? yourVisited.get(cur)[0] :
                        next.a >= 0 ?
                        (next.a % 2 == 1 ? next.a * w + w - your_position[0] : next.a * w + your_position[0]) :
                        ((-next.a) % 2 == 1 ? -(-next.a - 1) * w - your_position[0] : -(-next.a - 1) * w - (w - your_position[0]))
                        ,
                        next.b == cur.b ? yourVisited.get(cur)[1] :
                        next.b >= 0 ?
                        (next.b % 2 == 1 ? next.b * h + h - your_position[1] : next.b * h + your_position[1]) :
                        ((-next.b) % 2 == 1 ? -(-next.b - 1) * h - your_position[1] : -(-next.b - 1) * h - (h - your_position[1]))
                };

                guardVisited.put(next, newGuardPosition);
                yourVisited.put(next, newYourPosition);

                Pair tres2 = directions(your_position, newYourPosition);
                if(!suiside.containsKey(tres2))
                    suiside.put(tres2, newYourPosition);

                if(disSquare(your_position, newGuardPosition) <= distance * distance){

                    Pair tres1 = directions(your_position, newGuardPosition);

                    if(suiside.containsKey(tres1) && disSquare(your_position, suiside.get(tres1)) <= disSquare(your_position, newGuardPosition))
                        continue;

                    if(tres1.equals(tres2) && disSquare(your_position, newYourPosition) <= disSquare(your_position, newGuardPosition))
                        continue;

                    res.add(tres1);
                    queue.add(next);
                }
            }
        }
        return res.size();
    }

    private static Pair directions(int[] a, int[] b){

        int[] res = {b[0] - a[0], b[1] - a[1]};

        if(res[0] == 0)
            res[1] = sign(res[1]);
        else if(res[1] == 0)
            res[0] = sign(res[0]);
        else{
            int g = gcd(Math.abs(res[0]), Math.abs(res[1]));
            res[0] /= g; res[1] /= g;
        }
        return new Pair(res[0], res[1]);
    }

    private static int sign(int x){
        return Integer.compare(x, 0);
    }

    private static int gcd(int x, int y){
        if(x < y) return gcd(y, x);
        return y == 0 ? x : gcd(y, x % y);
    }

    private static int disSquare(int[] a, int[] b){
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }

    public static void main(String[] args){

        int[] dimensions1 = {3, 2};
        int[] your_position1 = {1, 1};
        int[] guard_position1 = {2, 1};
        System.out.println(Solution.solution(dimensions1, your_position1, guard_position1, 4));
        // 7

        int[] dimensions2 = {300, 275};
        int[] your_position2 = {150, 150};
        int[] guard_position2 = {185, 100};
        System.out.println(Solution.solution(dimensions2, your_position2, guard_position2, 500));
        // 9

        int[] dimensions3 = {2, 5};
        int[] your_position3 = {1, 2};
        int[] guard_position3 = {1, 4};
        System.out.println(Solution.solution(dimensions3, your_position3, guard_position3, 11));
        // 25? 27?

        int[] dimensions4 = {10, 10};
        int[] your_position4 = {4, 4};
        int[] guard_position4 = {3, 3};
        System.out.println(Solution.solution(dimensions4, your_position4, guard_position4, 5000));
        // 739323

        int[] dimensions5 = {23, 10};
        int[] your_position5 = {6, 4};
        int[] guard_position5 = {3, 2};
        System.out.println(Solution.solution(dimensions5, your_position5, guard_position5, 23));
        // 8
    }
}
