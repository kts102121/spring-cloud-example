package org.ron.userservice.event;

import org.ron.userservice.model.User;
import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {

    private final User user;

    public UserCreatedEvent(Object source) {
        super(source);
        this.user = (User) source;
    }

    public User getUser() {
        return user;
    }
}
