package sejsmograf.commentsense;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import sejsmograf.commentsense.api.YoutubeService;

@Controller
public class MainController {
    private final YoutubeService youtubeService;

    public MainController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @GetMapping("/sejsmo")
    public String getIndex(Model model) {
        return "index.html";
    }

    @GetMapping("/comments/{videoId}")
    @ResponseBody
    public String getCommentsText(@PathVariable(value = "videoId") String videoId) {

        List<String> commentsText = youtubeService.getCommentThreadsForVideo(videoId).stream()
                .map(thread -> thread.getSnippet().getTopLevelComment().getSnippet().getTextDisplay())
                .collect(Collectors.toList());

        return String.join("\n", commentsText);
    }

}
