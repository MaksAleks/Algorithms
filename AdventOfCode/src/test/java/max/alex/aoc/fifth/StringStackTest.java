package max.alex.aoc.fifth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StringStackTest {


    @Test
    public void stack() {
        var stack1 = new StringStack();
        var stack2 = new StringStack();

        stack1.push("A");
        stack1.push("B");
        stack1.push("C");

        stack1.push(stack1.pop(2));

        Assertions.assertEquals("C", stack1.pop());
        Assertions.assertEquals("B", stack1.pop());
        Assertions.assertEquals("A", stack1.pop());

        stack1.push("A");
        stack1.push("B");
        stack1.push("C");

        stack1.moveTo(stack1, 2);

        Assertions.assertEquals("C", stack1.pop());
        Assertions.assertEquals("B", stack1.pop());
        Assertions.assertEquals("A", stack1.pop());

        stack1.push("A");
        stack1.push("B");
        stack1.push("C");
        stack1.push("D");

        Assertions.assertEquals(List.of("B", "C", "D"), stack1.pop(3));
    }
}