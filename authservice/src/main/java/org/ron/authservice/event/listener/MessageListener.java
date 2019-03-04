package org.ron.authservice.event.listener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.authservice.event.CustomChannels;
import org.ron.authservice.event.model.MessageModel;
import org.ron.authservice.model.User;
import org.ron.authservice.service.UserCredentialService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@RequiredArgsConstructor
@EnableBinding(CustomChannels.class)
public class MessageListener {
    private final Gson gson;
    private final UserCredentialService userCredentialService;

    @StreamListener("inboundUserChanges")
    public void userChangeListener(MessageModel messageModel) {
        log.info("message received: {}", messageModel.getBody());
        switch (messageModel.getAction()) {
            case "POST":
                User user = gson.fromJson(messageModel.getBody(), new TypeToken<User>(){}.getType());
                userCredentialService.saveUserCredential(user);
                break;
            default:
                throw new IllegalArgumentException("Unknown message");
        }
    }
}
