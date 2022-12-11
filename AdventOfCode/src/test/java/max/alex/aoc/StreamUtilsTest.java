package max.alex.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

class StreamUtilsTest {

    StreamUtils utils = new StreamUtils();

    @Test
    public void groupedLinesTest() {
        BufferedReader reader = new BufferedReader(new StringReader("AB\nCD\nEE\nUU"));
        var res = utils.batchedLines(reader, 3).toList();
        Assertions.assertEquals(2, res.size());
        Assertions.assertEquals(List.of("AB", "CD", "EE"), res.get(0));
        Assertions.assertEquals(List.of("UU"), res.get(1));
    }

}