package com.notification;
import javax.swing.*;

public class WindowNotification implements Notification {
    @Override
    public void notify(String field, String msg) {
    	JOptionPane.showMessageDialog(null, msg, field, JOptionPane.ERROR_MESSAGE);
    }
}
