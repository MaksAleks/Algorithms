package max.learn.algorithms.stirng;

public class LeftMost {

    public static void main(String[] args) {
        String a = "abgdggda";
        System.out.println("left most of " + a + " is: " + leftMostBetter(a));
    }

    public static int leftMost(String a) {
        char[] chars = a.toCharArray();

        for (int i = 0; i < a.length(); i++) {
            for (int j = i + 1; j < a.length(); j++) {
                if (chars[i] == chars[j]) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int leftMostBetter(String a) {
        int CHARS = 256;
        int[] count = new int[CHARS];

        int left = Integer.MAX_VALUE;
        for (int i = a.length() - 1; i >= 0; i--) {
            count[a.charAt(i)]++;
            if (count[a.charAt(i)] > 1 && i < left) {
                left = i;
            }
        }
        return left == Integer.MAX_VALUE ? -1 : left;
    }
}
