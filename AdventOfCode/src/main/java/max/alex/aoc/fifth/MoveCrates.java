package max.alex.aoc.fifth;

import max.alex.aoc.Input;
import max.alex.aoc.Utils;

import java.io.BufferedReader;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class MoveCrates {

    public static void main(String[] args) {
        String input = "fifth/input.txt";
        String test = "fifth/test.txt";

        var in = new Input();
        var move = new MoveCrates();
        System.out.println("Test result:" + move.topStackCratesMove(in.getInput(test)));
        System.out.println("Result:" + move.topStackCratesMove(in.getInput(input)));

        System.out.println(" ------ SECOND PART ------");

        System.out.println("Test result:" + move.topStackCratesMultMove(in.getInput(test)));
        System.out.println("Result:" + move.topStackCratesMultMove(in.getInput(input)));
    }

    public String topStackCratesMultMove(BufferedReader reader) {
        return solve(reader, OperationsParser.MoveOp::moveMultiple);
    }

    public String topStackCratesMove(BufferedReader reader) {
        return solve(reader, OperationsParser.MoveOp::move);
    }
    private String solve(BufferedReader reader, BiConsumer<OperationsParser.MoveOp, List<StringStack>> moveOp) {
        var stackParser = new StackParser();
        List<StringStack> stacks = stackParser.parse(reader);
        Utils.readLine(reader); // this line is empty according to the contract, hence skip it

        var opParser = new OperationsParser();
        reader.lines()
                .map(opParser::parse)
                .forEach(op -> moveOp.accept(op, stacks));

        return stacks.stream()
                .map(StringStack::pop)
                .collect(Collectors.joining());
    }
}
