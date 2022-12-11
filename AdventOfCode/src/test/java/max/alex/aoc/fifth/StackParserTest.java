package max.alex.aoc.fifth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StackParserTest {

    StackParser parser = new StackParser();

    @Test
    public void parse() {
        var lines = List.of(
                "    [D]    ",
                "    [N] [C]",
                "[Z] [M] [P]"
        );
        List<StringStack> stacks = parser.parse(lines);
        Assertions.assertEquals(3, stacks.size());
        Assertions.assertEquals("Z", stacks.get(0).pop());
        Assertions.assertEquals(List.of("M", "N", "D"), stacks.get(1).pop(3));
        Assertions.assertEquals(List.of("P", "C"), stacks.get(2).pop(2));
    }

}