package sejsmograf.commentsense.youtubeapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class YoutubeApiExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(YoutubeApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String youtubeApiExceptionHandler(YoutubeApiException ex) {
        return ex.getMessage();
    }
}
