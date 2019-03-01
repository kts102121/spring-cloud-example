package org.ron.userservice.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {
    @Input("inboundUserChanges")
    SubscribableChannel users();
}
