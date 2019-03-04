package org.ron.userservice.event.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.userservice.event.model.MessageModel;
import org.ron.userservice.model.User;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageService {
    private final Gson gson;
    private final Source source;

    public void sendMessage(String action, User body) {
        log.info("sending user: {}", gson.toJson(body, new TypeToken<User>() {}.getType()));
        MessageModel messageBody = new MessageModel(action, gson.toJson(body));
        source.output().send(MessageBuilder.withPayload(messageBody).build(), 3000);
    }
}
