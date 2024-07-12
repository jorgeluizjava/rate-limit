package com.jorgegalvao.providers;

public class MyProvider implements NotificationProvider {
    @Override
    public void send(String userId, String message) {
        System.out.println("Sending message " + message + " to userId " + userId);
    }
}
