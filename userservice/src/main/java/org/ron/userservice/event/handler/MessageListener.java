package org.ron.userservice.event.handler;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.userservice.event.CustomChannels;
import org.ron.userservice.event.model.MessageModel;
import org.ron.userservice.model.User;
import org.ron.userservice.service.UserService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@RequiredArgsConstructor
@EnableBinding(CustomChannels.class)
public class MessageListener {
    private final Gson gson;
    private final UserService userService;

    @StreamListener("inboundUserChanges")
    public void userChangeHandler(MessageModel messageModel) {
        log.info("message received: {}", messageModel.getMessage());
        switch(messageModel.getAction()) {
            case "POST":
                userService.saveUser(gson.fromJson(messageModel.getMessage(), User.class));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
