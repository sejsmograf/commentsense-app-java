package sejsmograf.commentsense;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.SearchResult;

import sejsmograf.commentsense.youtubeapi.YoutubeService;

@Controller
public class MainController {
    private final YoutubeService youtubeService;

    public MainController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @GetMapping("/search")
    public String getIndex(Model model) {
        return "search.html";
    }

    @GetMapping("/comments/{videoId}")
    @ResponseBody
    public List<CommentThread> fetchAllCommentThreads(@PathVariable(value = "videoId") String videoId) {

        return youtubeService.fetchAllCommentsThreads(videoId);
    }

    @GetMapping("/search/{query}")
    @ResponseBody
    public List<SearchResult> searchVideos(@PathVariable(value = "query") String query) {
        return youtubeService.searchVideos(query);
    }

}
