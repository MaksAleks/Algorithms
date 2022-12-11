package max.alex.aoc;

import max.alex.aoc.first.MaxCaloriesCounter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Input {

    public BufferedReader getInput(String input) {
        return new BufferedReader(new InputStreamReader(getInputStream(input)));
    }

    private InputStream getInputStream(String in) {
        return MaxCaloriesCounter.class.getClassLoader().getResourceAsStream(in);
    }
}
