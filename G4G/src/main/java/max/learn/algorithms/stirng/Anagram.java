package max.learn.algorithms.stirng;

import java.util.Arrays;

public class Anagram {

    public static void main(String[] args) {
        String a = "aaacba";
        String b = "abaca";
        System.out.println(a + " is anagram of " + b + ": " + isAnagramLinear(a, b));

    }

    public static boolean isAnagram(String a, String b) {

        int[] aArr = a.chars()
                .sorted()
                .toArray();
        int[] bArr = b.chars()
                .sorted()
                .toArray();

        return Arrays.equals(aArr, bArr);
    }

    public static boolean isAnagramLinear(String a, String b) {
        int A_CODE = 'a';

        int[] count = new int[26]; // 26 is the number of chars in latin alphabet

        for (int c : a.toCharArray()) {
            count[c - A_CODE]++;
        }

        for (int c : b.toCharArray()) {
            count[c - A_CODE]--;
        }

        return Arrays.stream(count)
                .sum() == 0;
    }
}
