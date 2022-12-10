package max.alex.aoc.first;

import java.io.*;

public class MaxCaloriesCounter {

    public static void main(String[] args) {
        String input = "first_in.txt";
        String testInput = "test_in.txt";

        var counter = new MaxCaloriesCounter();
        System.out.println("----- Max Calories -----");
        System.out.println("Test result: " + counter.maxCalories(testInput));
        System.out.println("Result: " + counter.maxCalories(input));
        System.out.println();
        System.out.println("----- Top 3 -------");
        System.out.println("Test result: " + counter.topThreeCalories(testInput));
        System.out.println("Result: " + counter.topThreeCalories(input));
    }


    int topThreeCalories(String in) {
        int[] topThree = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        getInput(in).lines().forEach(l -> {
            if (l.isEmpty()) {
                int i = 0;
                while (i < 3 && topThree[i] > topThree[i + 1]) {
                    int t = topThree[i + 1];
                    topThree[i + 1] = topThree[i];
                    topThree[i] = t;
                    i++;
                }
                topThree[0] = 0;
            } else {
                topThree[0] += Integer.parseInt(l);
            }
        });
        return topThree[3] + topThree[2] + topThree[1];
    }
    int maxCalories(String in) {
        int max = Integer.MIN_VALUE;
        try (var reader = getInput(in)) {
            String line;
            int cur = 0;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    max = Math.max(max, cur);
                    cur = 0;
                } else {
                    cur += Integer.parseInt(line);
                }
            }
            return max;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedReader getInput(String in) {
        return new BufferedReader(new InputStreamReader(getInputStream(in)));
    }

    private InputStream getInputStream(String in) {
        return MaxCaloriesCounter.class.getClassLoader().getResourceAsStream(in);
    }
}
