package max.learn.algo.yandex;

import java.util.ArrayList;
import java.util.List;

/**
 * Example for n = 3
 * e - empty
 *
 *                          e:  n = 3 (l = 3, r = 3)
 *                          |
 *                          (  l = 2, r = 3
 *                       /     \
 *                     /        \
 *         l = 1, r = 3 ((     () l = 2, r = 2
*                    /   |        \
 *                 /    |          \
 *            (((      (()           ()(
 *            |        / |           |  \
 *           |       /   |           |   \
 *       ((
 *
 *
 */
public class RightParenthesisSeq {

    public static List<String> seqs = new ArrayList<>();

    public static void main(String[] args) {
        int n = 3;
        gen(n, n, "");
    }
    public static void gen(int left, int right, String s) {
        if (left == 0 && right == 0) {
            System.out.println(s);
        }
        if (left > 0) {
            gen(left - 1, right, s + "(");
        }
        if (right > 0 && right > left) {
            gen(left, right - 1, s + ")");
        }
    }
}
