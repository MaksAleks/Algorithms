package max.learning.algorithms;

import java.util.Arrays;
import java.util.stream.Collectors;

class ReverseWords {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println("actual: " + new ReverseWords().reverseWords(s));
        System.out.println("expected: " + "s'teL ekat edoCteeL tsetnoc");
    }

    public String reverseWordsAnotherSolution(String s) {
        StringBuilder sb = new StringBuilder();
        return Arrays.stream(s.split(" "))
                .map(str -> {
                    sb.setLength(0);
                    return sb.append(s).reverse().toString();
                })
                .collect(Collectors.joining(" "));
    }

    public String reverseWords(String s) {
        int l = 0, r;
        char[] chars = Arrays.copyOf(s.toCharArray(), s.length() + 1);
        chars[s.length()] = ' ';
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                r = i;
                reverseString(chars, l, r);
                l = r + 1;
            }
        }
        return String.valueOf(chars, 0, chars.length - 1);
    }
    
    private void reverseString(char[] s, int l, int r) {
        int size = r - l;
        for (int i = 0; i < size / 2; i++) {
            char tmp = s[l + i];
            s[l + i] = s[r - i - 1];
            s[r - i - 1] = tmp;
        }
    }
}