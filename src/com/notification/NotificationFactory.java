package com.notification;

public class NotificationFactory {
    static NotificationFactory instance = null;

    private NotificationFactory() {}

    static public NotificationFactory getInstance() {
        if(instance == null) {
            return new NotificationFactory();
        }

        return instance;
    }

    public Notification getNotification(String type) {
      switch (type) {
          case "window":
              return new WindowNotification();
          default:
              return new ConsoleNotification();
      }
    }
}
