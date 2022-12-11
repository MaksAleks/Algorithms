package max.alex.aoc.fifth;

import max.alex.aoc.StreamUtils;
import max.alex.aoc.Utils;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static max.alex.aoc.Utils.readLine;

public class StackParser {

    public List<StringStack> parse(BufferedReader reader) {
        List<String> lines = new ArrayList<>();
        String line = readLine(reader);
        while (line.contains("[")) {
            lines.add(line);
            line = readLine(reader);
        }
        return parse(lines);
    }

    public List<StringStack> parse(List<String> lines) {
        int stacksCnt = lines.get(lines.size() - 1).split(" ").length;
        List<StringStack> stacks = new ArrayList<>(stacksCnt);
        for (int i = 0; i < stacksCnt; i++) {
            stacks.add(new StringStack());
        }
        for (String line : lines) {
            var pos = new Utils.Counter();
            var idx = new Utils.Counter();
            for (char c : line.toCharArray()) {
                pos.counter++;
                if (c == ' ' || c == '[' || c == ']') {
                    if (pos.counter % 4 == 0) {
                        idx.counter++;
                    }
                } else {
                    stacks.get(idx.counter).pushLast(String.valueOf(c));
                }
            }
        }
        return stacks;
    }

    private Optional<String> getNext(String line, Utils.Counter pos) {
        if (pos.counter >= line.length()) return Optional.empty();

        String next = line.substring(pos.counter + 1, pos.counter + 2).trim();
        pos.counter += 4;
        if (next.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(next);
    }
}
