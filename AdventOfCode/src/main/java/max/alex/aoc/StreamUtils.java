package max.alex.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static max.alex.aoc.Utils.readLine;

public class StreamUtils {

    public Stream<List<String>> batchedLines(BufferedReader reader, int groupSize) {
        Iterator<List<String>> iter = new Iterator<>() {
            List<String> nextGroup = null;
            @Override
            public boolean hasNext() {
                if (nextGroup != null && !nextGroup.isEmpty()) {
                    return true;
                } else {
                    nextGroup = getNextGroup(reader, groupSize);
                    return !nextGroup.isEmpty();
                }
            }

            @Override
            public List<String> next() {
                if (nextGroup != null && !nextGroup.isEmpty()) {
                    var group = nextGroup;
                    nextGroup = null;
                    return group;
                } else {
                    List<String> group = getNextGroup(reader, groupSize);
                    if (group.isEmpty()) {
                        throw new NoSuchElementException();
                    }
                    return group;
                }
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    private List<String> getNextGroup(BufferedReader reader, int groupSize) {
        List<String> nextGroup = new ArrayList<>(3);
        String line = null;
        int cnt = 0;
        while ( cnt < groupSize && (line = readLine(reader)) != null) {
            nextGroup.add(line);
            cnt++;
        }
        return nextGroup;
    }
}
