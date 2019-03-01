package org.ron.authservice.event.source;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ron.authservice.event.model.MessageModel;
import org.ron.authservice.model.UserDTO;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageService {
    private final Gson gson;
    private final Source source;

    public void sendMessage(String action, UserDTO body) {
        MessageModel messageBody = new MessageModel(action, gson.toJson(body));
        source.output().send(MessageBuilder.withPayload(messageBody).build());
    }
}
