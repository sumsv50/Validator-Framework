package com.notification;

public class ConsoleNotification implements Notification {
    @Override
    public void notify(String field, String msg) {
    	System.out.println(msg + field);
    }
}
