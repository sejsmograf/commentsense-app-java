package sejsmograf.commentsense.youtubeapi;

public class YoutubeApiException extends RuntimeException {
    public YoutubeApiException(String message) {
        super(message);
    }

    public YoutubeApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
