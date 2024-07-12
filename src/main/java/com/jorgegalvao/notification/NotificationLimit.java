package com.jorgegalvao.notification;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class NotificationLimit {
    private final Duration duration;
    private final int maxCount;
    private final Map<Instant, Integer> notifications = new HashMap<>();

    public NotificationLimit(Duration duration, int maxCount) {
        this.duration = duration;
        this.maxCount = maxCount;
    }

    public synchronized boolean canNotify() {
        Instant now = Instant.now();
        notifications.keySet().removeIf(time -> time.plus(duration).isBefore(now));

        int count = notifications.values().stream().mapToInt(Integer::intValue).sum();
        if (count >= maxCount) {
            return false;
        }

        notifications.merge(now, 1, Integer::sum);
        return true;
    }
}
