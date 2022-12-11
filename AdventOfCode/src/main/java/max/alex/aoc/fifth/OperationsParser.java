package max.alex.aoc.fifth;

import java.util.List;

public class OperationsParser {

    public OperationsParser.MoveOp parse(String operation) {
        String[] ops = operation.split(" ");
        return new MoveOp(ops[1], ops[3], ops[5]);
    }

    public static class MoveOp {
        private int from;
        private int qty;
        private int to;

        private MoveOp(String qty, String from, String to) {
            this.qty = Integer.parseInt(qty);
            this.from = Integer.parseInt(from) - 1;
            this.to = Integer.parseInt(to) - 1;
        }

        public void move(List<StringStack> stacks) {
            var fromS = stacks.get(from);
            var toS = stacks.get(to);

            for (int i = 0; i < qty; i++) {
                toS.push(fromS.pop());
            }
        }

        public void moveMultiple(List<StringStack> stacks) {
            stacks.get(from).moveTo(stacks.get(to), qty);
        }
    }
}
