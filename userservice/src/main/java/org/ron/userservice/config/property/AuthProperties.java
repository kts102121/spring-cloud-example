package org.ron.userservice.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("signing")
public class AuthProperties {
    private String key;
}
