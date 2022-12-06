package max.learn.algorithms.stirng;

public class Palindrome {

    public static void main(String[] args) {
        String a = "AABBAA";
        String b = "ABBAAB";
        System.out.println(a + ": " + isPalindrome(a));
        System.out.println(b + ": " + isPalindrome(b));
    }

    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();
        int half = s.length() / 2;
        for (int i = 0; i < half; i++) {
            if (chars[i] != chars[len - i - 1]) {
                return false;
            }
        }
        return true;
    }
}
