package max.alex.aoc;

import java.io.BufferedReader;
import java.io.IOException;

public class Utils {


    public static String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Counter {
        public int counter = 0;
    }
}
