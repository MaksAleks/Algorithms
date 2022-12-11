package max.alex.aoc.third;

import max.alex.aoc.Input;
import max.alex.aoc.StreamUtils;

import java.util.List;

public class TotalItemsPriority {

    public static void main(String[] args) {
        String testIn = "third/test.txt";
        String input = "third/input.txt";
        var priorityCounter = new TotalItemsPriority();
        System.out.println("Test result: " + priorityCounter.totalPriority(testIn));
        System.out.println("Result: " + priorityCounter.totalPriority(input));

        System.out.println("--- SECOND PART ----");
        System.out.println("Test result: " + priorityCounter.totalGroupPriority(testIn));
        System.out.println("Result: " + priorityCounter.totalGroupPriority(input));
    }

    public int totalPriority(String input) {
        Input in = new Input();
        return in.getInput(input).lines()
                .mapToInt(this::priority)
                .sum();
    }

    public int totalGroupPriority(String input) {
        int groupSize = 3;
        var utils = new StreamUtils();
        Input in = new Input();
        return utils.batchedLines(in.getInput(input), groupSize)
                .mapToInt(this::groupPriority)
                .sum();
    }

    private int groupPriority(List<String> group) {
        int[] cnt = new int[53];
        for (int i = 0; i < group.size(); i++) {
            int I = i;
            group.get(i).chars()
                    .map(c -> letterToPriority(c) - 1)
                    .distinct()
                    .forEach(p -> cnt[p] += I + 1);
        }
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == 6) {
                return i + 1;
            }
        }
        return 0;
    }


    /**
     * Calculates the priority of the line
     *
     * ascii codes of small letters are [97, 122]
     * ascii codes of capital letters are [65, 90]
     *
     * the priorities of small letters are [1, 26]
     * the priorities of capital letters are [27, 52]
     *
     * So to convert ascii codes of small letters we need to subtract 96 number from a code:
     * p = c - 96
     *
     * For capital letters: P = C - 64 + 26 = C - 38
     *
     * @param line
     * @return
     */
    private int priority(String line) {
        int len = 53;
        int[] firstCnt = new int[len];
        int[] secondCnt = new int[len];

        for (int i = 0; i < line.length(); i++) {
            if (i < line.length() / 2) {
                firstCnt[letterToPriority(line.charAt(i)) - 1]++;
            } else {
                secondCnt[letterToPriority(line.charAt(i)) - 1]++;
            }
        }
        for (int i = 0; i < len; i++) {
            if (firstCnt[i] > 0 && secondCnt[i] > 0) {
                return i + 1;
            }
        }

        return 0;
    }


    private int letterToPriority(int letter) {
        if (letter <= 90) { // capital
            return letter - 38;
        } else {
            return letter - 96;
        }
    }
}
