package max.alex.aoc.fifth;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class StringStack {

    private Deque<String> list = new LinkedList<>();

    public void push(String s) {
        list.offerFirst(s);
    }

    public void pushLast(String s) {
        list.offerLast(s);
    }

    public void push(List<String> s) {
        s.forEach(this::push);
    }

    public String pop() {
        return list.pollFirst();
    }

    public List<String> pop(int quantity) {
        LinkedList<String> rs = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            rs.addFirst(pop());
        }
        return rs;
    }

    public void moveTo(StringStack to, int quantity) {
        to.push(pop(quantity));
    }
}
