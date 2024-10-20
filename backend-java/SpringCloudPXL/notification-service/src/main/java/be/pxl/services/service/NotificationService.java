package be.pxl.services.service;

import be.pxl.services.domain.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendMessage(Notification notification) {
        System.out.println("Sender: " + notification.getFrom());
        System.out.println("Receiver: " + notification.getTo());
        System.out.println("Subject: " + notification.getSubject());
        System.out.println("Message: " + notification.getMessage());
    }
}
