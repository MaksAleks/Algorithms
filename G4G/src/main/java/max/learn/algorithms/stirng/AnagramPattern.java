package max.learn.algorithms.stirng;

import java.util.Arrays;

public class AnagramPattern {

    public static void main(String[] args) {
        String s = "geeksforgeeks";
        String p = "frog";
        System.out.println(p + " is present in " + s + ": " + present(s,p));
    }

    public static boolean present(String s, String pattern) {
        for (int i = 0; i < s.length() - pattern.length(); i++) {
            if(isAnagram(s, pattern, i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean presentBetter(String s, String p) {
        int[] cts = new int[256];
        int[] ctp = new int[256];

        for (int i = 0; i < p.length(); i++) {
             ctp[p.charAt(i)]++;
             cts[s.charAt(i)]++;
        }

        for (int i = p.length(); i < s.length(); i++) {

            if (Arrays.equals(ctp, cts)) {
                return true;
            }
            cts[s.charAt(i)]++;
            cts[s.charAt(i - p.length())]--;
        }

        return false;
    }

    public static boolean isAnagram(String s, String p, int from) {
        int[] cnts = new int[256];

        for (char c : p.toCharArray()) {
            cnts[c]++;
        }

        for (int i = from; i < from + p.length(); i++) {
            cnts[s.charAt(i)]--;
        }
        return Arrays.stream(cnts).sum() == 0;
    }
}
