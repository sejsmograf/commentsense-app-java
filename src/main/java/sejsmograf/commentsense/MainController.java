package sejsmograf.commentsense;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.services.youtube.model.CommentThread;

import sejsmograf.commentsense.youtubeapi.YoutubeService;

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
    public List<CommentThread> getCommentsText(@PathVariable(value = "videoId") String videoId) {

        return youtubeService.fetchAllCommentsThreads(videoId);
    }

}
