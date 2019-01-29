package org.ron.userservice.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("example")
public class ExampleProperties {
    private String property;
}
