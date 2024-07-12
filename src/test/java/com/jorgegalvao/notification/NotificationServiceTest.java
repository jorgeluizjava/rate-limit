package com.jorgegalvao.notification;

import com.jorgegalvao.providers.NotificationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationProvider notificationProvider;

    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService(notificationProvider);
    }

    @Test
    public void testSend_when_typeIsUpdate_SendNotification() {

        var type = NotificationType.UPDATE;
        var userId = UUID.randomUUID().toString();
        var message = "test update message";

        notificationService.send(type, userId, message);
        notificationService.send(type, userId, message);
        notificationService.send(type, userId, message);

        verify(this.notificationProvider, times(2)).send(userId, message);
    }

    @Test
    public void testSend_when_typeIsUpdateToDiferentUsers_SendNotification() {

        var type = NotificationType.UPDATE;
        var userId1 = UUID.randomUUID().toString();
        var message1 = "test update message user1";

        notificationService.send(type, userId1, message1);
        notificationService.send(type, userId1, message1);
        notificationService.send(type, userId1, message1);

        verify(this.notificationProvider, times(2)).send(userId1, message1);

        var userId2 = UUID.randomUUID().toString();
        var message2 = "test update message user2";

        notificationService.send(type, userId2, message2);
        notificationService.send(type, userId2, message2);
        notificationService.send(type, userId2, message2);

        verify(this.notificationProvider, times(2)).send(userId2, message2);
    }

    @Test
    public void testSend_when_typeIsUpdateAndWaitAMinuteToSendAnother_SendNotification() throws InterruptedException {

        var type = NotificationType.UPDATE;
        var userId = UUID.randomUUID().toString();
        var message = "test update message";

        notificationService.send(type, userId, message);
        notificationService.send(type, userId, message);

        Thread.sleep(Duration.ofMinutes(1));

        notificationService.send(type, userId, message);
        notificationService.send(type, userId, message);
        notificationService.send(type, userId, message);

        verify(this.notificationProvider, times(4)).send(userId, message);
    }

    @Test
    public void testSend_when_typeIsNews_SendNotification() {

        var type = NotificationType.NEWS;
        var userId = UUID.randomUUID().toString();
        var message = "test news message";

        notificationService.send(type, userId, message);
        notificationService.send(type, userId, message);

        verify(this.notificationProvider, times(1)).send(userId, message);
    }

    @Test
    public void testSend_when_typeIsMarketing_SendNotification() {

        var type = NotificationType.MARKETING;
        var userId = UUID.randomUUID().toString();
        var message = "test marketing message";

        notificationService.send(type, userId, message);
        notificationService.send(type, userId, message);
        notificationService.send(type, userId, message);
        notificationService.send(type, userId, message);

        verify(this.notificationProvider, times(3)).send(userId, message);
    }

    @Test
    public void testSend_when_typeIsUpdateNewsAndMarketingToTheSameUser_SendNotification() {

        var userId = UUID.randomUUID().toString();

        notificationService.send(NotificationType.UPDATE, userId, "test update message");
        notificationService.send(NotificationType.UPDATE, userId, "test update message");
        notificationService.send(NotificationType.UPDATE, userId, "test update message");
        verify(this.notificationProvider, times(2)).send(userId, "test update message");

        notificationService.send(NotificationType.NEWS, userId, "test news message");
        notificationService.send(NotificationType.NEWS, userId, "test news message");
        verify(this.notificationProvider, times(1)).send(userId, "test news message");

        notificationService.send(NotificationType.MARKETING, userId, "test marketing message");
        notificationService.send(NotificationType.MARKETING, userId, "test marketing message");
        notificationService.send(NotificationType.MARKETING, userId, "test marketing message");
        notificationService.send(NotificationType.MARKETING, userId, "test marketing message");
        verify(this.notificationProvider, times(3)).send(userId, "test marketing message");
    }

}
