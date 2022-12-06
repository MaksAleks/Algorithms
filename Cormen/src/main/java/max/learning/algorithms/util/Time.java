package max.learning.algorithms.util;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class Time {

    public static <R> R logTime(Supplier<? extends R> func) {
        var start = Instant.now();
        R r = func.get();
        var end = Instant.now();
        System.out.println("Time: " + Duration.between(start, end));
        return r;
    }
}
