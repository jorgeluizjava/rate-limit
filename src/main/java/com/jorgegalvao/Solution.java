package com.jorgegalvao;

import com.jorgegalvao.notification.NotificationService;
import com.jorgegalvao.notification.NotificationType;
import com.jorgegalvao.providers.MyProvider;
import com.jorgegalvao.providers.NotificationProvider;

import java.util.UUID;

public class Solution {
    public static void main(String[] args) {
        NotificationProvider provider = new MyProvider();
        NotificationService notificationService = new NotificationService(provider);

        String user1 = UUID.randomUUID().toString();
        String user2 = UUID.randomUUID().toString();

        notificationService.send(NotificationType.NEWS, user1, "news 1");
        notificationService.send(NotificationType.NEWS, user1, "news 2");
        notificationService.send(NotificationType.NEWS, user1, "news 3");

        notificationService.send(NotificationType.NEWS, user2, "news 1");

        notificationService.send(NotificationType.UPDATE, user1, "update 1");
    }
}
