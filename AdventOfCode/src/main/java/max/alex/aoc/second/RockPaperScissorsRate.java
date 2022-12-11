package max.alex.aoc.second;

import max.alex.aoc.Input;

import java.util.stream.Stream;

public class RockPaperScissorsRate {

    public static void main(String[] args) {
        var sol = new RockPaperScissorsRate();
        var in = new Input();
        String test = "second/test.txt";
        String input = "second/input.txt";
        System.out.println("Test result: " + sol.computeScore(in.getInput(test).lines()));
        System.out.println("Result: " + sol.computeScore(in.getInput(input).lines()));
        System.out.println("---- SECOND ------");

        System.out.println("Test result: " + sol.choseAnswerAndComputeScore(in.getInput(test).lines()));
        System.out.println("Result: " + sol.choseAnswerAndComputeScore(in.getInput(input).lines()));
    }

    public int choseAnswerAndComputeScore(Stream<String> lines) {
        return lines.map(s -> s.replaceAll("\s", ""))
                .mapToInt(s -> s.chars().reduce(this::choseAnswer).getAsInt())
                .sum();
    }

    public int choseAnswer(int a, int b) {
        if (b == 88) { // X
            b = a == 65 ? 67 : a - 1;
        } else if (b == 89) { // Y
            b = a;
        } else {
            b = a == 67 ? 65 : a + 1;
        }
        return getScore(a, b);
    }

    public int computeScore(Stream<String> lines) {
        return lines.map(s -> s.replaceAll("\s", ""))
                .mapToInt(s -> s.chars().reduce(this::getScore).getAsInt())
                .sum();
    }


    private int getScore(int a, int b) {// b = A or B or C
        b -= 23; // convert X -> A, Y -> B, Z -> C
        int shapeRate = (b - 64); // A = 1, B = 2, C = 3
        int d = (b - a);
        if (Math.abs(d) == 2) {
            d = d < 0 ? 1 : -1;
        }
        return (d + 1) * 3 + shapeRate;
    }
}
