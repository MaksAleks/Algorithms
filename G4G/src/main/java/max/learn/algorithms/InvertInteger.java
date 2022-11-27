package max.learn.algorithms;

import java.nio.charset.StandardCharsets;

public class InvertInteger {

    public static void main(String[] args) {
        var alg = new InvertInteger();
        for (int i = 0; i < 100_000; i++) {
            final int actual = alg.invert(i);
            final int expected = alg.trivialInvert(i);
            if (actual != expected) {
                System.err.println("Error: invert(" + i + ") = " + actual + "; expected = " + expected);
                return;
            }
        }
    }

    public int trivialInvert(int n) {
        byte[] bytes = String.valueOf(n).getBytes(StandardCharsets.UTF_8);
        int m = bytes.length / 2;
        for (int i = 0; i < m; i++) {
            byte tmp = bytes[i];
            bytes[i] = bytes[bytes.length - i - 1];
            bytes[bytes.length - i - 1] = tmp;
        }
        return Integer.parseInt(new String(bytes, StandardCharsets.UTF_8));
    }

    public int invert(int num) {
        int rev = 0;
        while (num > 0) {
            rev = rev * 10 + num % 10;
            num = num / 10;
        }
        return rev;
    }


    public static void printBytes(String prefix, int i) {
        System.out.printf("%s: %s\n",prefix, Integer.toBinaryString(i));
    }
}
