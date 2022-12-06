package max.learn.algorithms.stirng;

public class Subsequence {

    public static void main(String[] args) {
        String a = "dadbdcd";
        String b = "adbc";
        System.out.println(b + " is a subseq of " + a + ": " + isSubsequence(a, b));
    }

    public static boolean isSubsequence(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        // for first c of b
        // starting from beginning of a:
        //  find it in a,
        //  remember idx,
        //  move to next in b
        //  starting from idx + 1 find it a

        int aIdx = 0;
        int bIdx = 0;

        while (bIdx < b.length() && aIdx < a.length()) {
            if (bChars[bIdx] == aChars[aIdx]) {
                bIdx++;
            }
            aIdx++;
        }
        return bIdx == b.length();
    }
}
