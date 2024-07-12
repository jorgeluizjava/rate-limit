package com.jorgegalvao.notification;

import java.time.Duration;

public enum NotificationType {
    UPDATE(2, Duration.ofMinutes(1)),
    NEWS(1, Duration.ofDays(1)),
    MARKETING(3, Duration.ofHours(1)),;

    private final int maxCount;
    private final Duration duration;

    NotificationType(int maxCount, Duration duration) {
        this.maxCount = maxCount;
        this.duration = duration;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public Duration getDuration() {
        return duration;
    }
}
