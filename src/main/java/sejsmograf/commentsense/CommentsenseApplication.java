package sejsmograf.commentsense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import sejsmograf.commentsense.config.YoutubeConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(YoutubeConfigProperties.class)
public class CommentsenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentsenseApplication.class, args);
	}

}
