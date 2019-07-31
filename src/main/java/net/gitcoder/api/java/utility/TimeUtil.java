package net.gitcoder.api.java.utility;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.Instant;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
@UtilityClass
public class TimeUtil {

    /**
     * Получение времени от начального времени до конечного.
     * @param start - начальное время.
     * @param end - конечное время.
     * @return - время.
     */
    public long getTimeBetween(Instant start, Instant end) {
        return Duration.between(start, end).toMillis();
    }
}
