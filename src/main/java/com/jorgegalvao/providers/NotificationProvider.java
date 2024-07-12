package com.jorgegalvao.providers;

public interface NotificationProvider {

    void send(String userId, String message);
}
