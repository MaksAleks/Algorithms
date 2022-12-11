package max.alex.aoc.fourth;

import max.alex.aoc.Input;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static max.alex.aoc.fourth.SectionsOverlapping.Range.range;

public class SectionsOverlapping {

    public static void main(String[] args) {
        String input = "fourth/input.txt";
        String test = "fourth/test.txt";
        Input in = new Input();

        var overlapping = new SectionsOverlapping();
        System.out.println("Test result: " + overlapping.numberOfFullyOverlappedSections(in.getInput(test).lines()));
        System.out.println("Result: " + overlapping.numberOfFullyOverlappedSections(in.getInput(input).lines()));

        System.out.println("---- SECOND PART ------");

        System.out.println("Test result: " + overlapping.numberOfOverlappedSections(in.getInput(test).lines()));
        System.out.println("Result: " + overlapping.numberOfOverlappedSections(in.getInput(input).lines()));
    }

    public long numberOfFullyOverlappedSections(Stream<String> lines) {
        return lines.map(this::fullyOverlapped)
                .filter(i -> i)
                .count();
    }

    public long numberOfOverlappedSections(Stream<String> lines) {
        return lines.map(this::overlapped)
                .filter(i -> i)
                .count();
    }

    public boolean overlapped(String sections) {
        String[] secs = sections.split(",");
        return range(secs[0]).overlapped(range(secs[1]));
    }

    public boolean fullyOverlapped(String sections) {
        String[] secs = sections.split(",");
        return range(secs[0]).fullyOverlapped(range(secs[1]));
    }

    public static class Range {
        private int from;
        private int to;

        public static Range range(String rangeStr) {
            return new Range(rangeStr);
        }

        public Range(String rangeStr) {
            String[] interval = rangeStr.split("-");
            this.from = Integer.parseInt(interval[0]);
            this.to = Integer.parseInt(interval[1]);
        }

        public boolean fullyOverlapped(Range range) {
            return this.from <= range.from && this.to >= range.to ||
                    range.from <= this.from && range.to >= this.to;
        }

        public boolean overlapped(Range range) {
            return fullyOverlapped(range) ||
                    this.from >= range.from && this.from <= range.to ||
                    this.to <= range.to && this.to >= range.from;
        }
    }
}
