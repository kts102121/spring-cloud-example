package org.ron.userservice.event.handler;

import org.ron.userservice.event.UserCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class UserEventListener implements ApplicationListener<UserCreatedEvent> {

    @Autowired
    private MessageService messageService;

    @Override
    public void onApplicationEvent(UserCreatedEvent userCreatedEvent) {
        messageService.sendMessage("POST", userCreatedEvent.getUser());
    }
}
