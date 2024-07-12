package com.jorgegalvao.notification;

import com.jorgegalvao.providers.NotificationProvider;

import java.util.HashMap;
import java.util.Map;

public class NotificationService {
    private final NotificationProvider notificationProvider;

    private final Map<String, Map<NotificationType, NotificationLimit>> notificationLimits = new HashMap<>();

    public NotificationService(NotificationProvider notificationProvider) {
        this.notificationProvider = notificationProvider;
    }

    public void send(NotificationType type, String userId, String message) {
        NotificationLimit notificationLimit = notificationLimits
                .computeIfAbsent(userId, k -> new HashMap<>())
                .computeIfAbsent(type, k -> new NotificationLimit(type.getDuration(), type.getMaxCount()));

        if (notificationLimit.canNotify()) {
            this.notificationProvider.send(userId, message);
        }
    }
}
