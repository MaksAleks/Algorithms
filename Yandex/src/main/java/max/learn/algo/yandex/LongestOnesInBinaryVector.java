package max.learn.algo.yandex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestOnesInBinaryVector {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        long cur = 0;
        long max = 0;
        int[] arr = reader.lines()
                .limit(n)
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                if (cur > max) {
                    max = cur;
                }
                cur = 0;
            } else {
                cur++;
            }
        }
        if (cur > max) {
            max = cur;
        }
        System.out.println(max);
    }
}
