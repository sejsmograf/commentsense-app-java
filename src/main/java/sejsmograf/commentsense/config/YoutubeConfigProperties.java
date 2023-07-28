package sejsmograf.commentsense.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("youtube")
public record YoutubeConfigProperties(String apiKey) {
}
