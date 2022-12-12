package max.learn.algo.yandex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StonesAndJewelry {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String J = reader.readLine();
        final String S = reader.readLine();

        int res = 0;
        for (char c : S.toCharArray()) {
            if (J.indexOf(c) >= 0) {
                res++;
            }
        }

        System.out.println(res);
    }
}
