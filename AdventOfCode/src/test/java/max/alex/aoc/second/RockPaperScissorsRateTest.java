package max.alex.aoc.second;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RockPaperScissorsRateTest {

    RockPaperScissorsRate sol = new RockPaperScissorsRate();

    @Test
    public void test() {

        var in1 = Stream.of("AX", "BY", "CZ");
        assertEquals( (3 + 1) + (3 + 2) + (3 + 3), sol.computeScore(in1));

        var in2 = Stream.of("AY");
        assertEquals(6 + 2, sol.computeScore(in2));

        var in3 = Stream.of("AZ");
        assertEquals(0 + 3, sol.computeScore(in3));

        var in4 = Stream.of("CX");
        assertEquals(6 + 1, sol.computeScore(in4));

        var in5 = Stream.of("BZ");
        assertEquals(6 + 3, sol.computeScore(in5));

        var in6 = Stream.of("CY");
        assertEquals(0 + 2, sol.computeScore(in6));

        var in7 = Stream.of("BX");
        assertEquals(0 + 1, sol.computeScore(in7));
    }

    @Test
    public void testCase() {
        var in = Stream.of("A Y", "B X", "C Z");
        assertEquals(15, sol.computeScore(in));
    }

}