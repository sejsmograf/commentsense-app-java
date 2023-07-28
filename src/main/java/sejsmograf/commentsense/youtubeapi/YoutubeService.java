package sejsmograf.commentsense.youtubeapi;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;

import sejsmograf.commentsense.config.YoutubeConfigProperties;

@Service
public class YoutubeService {

    private final YoutubeConfigProperties youtubeConfig;

    public YoutubeService(YoutubeConfigProperties youtubeConfig) {
        this.youtubeConfig = youtubeConfig;
    }

    public List<CommentThread> fetchAllCommentsThreads(String videoId) {
        try {
            YouTube youtube = YoutubeApiConnector.getService();
            YouTube.CommentThreads.List request = youtube.commentThreads().list(List.of("snippet"));

            request.setVideoId(videoId);
            request.setMaxResults(100L);
            request.setKey(youtubeConfig.apiKey());

            List<CommentThread> allCommentThreads = new ArrayList<>();
            String nextPageToken = null;

            allCommentThreads.addAll(request.execute().getItems());

            // do {
            // request.setPageToken(nextPageToken);
            // CommentThreadListResponse response = request.execute();
            // if (response.getItems() != null) {
            // allCommentThreads.addAll(response.getItems());
            // }
            // nextPageToken = response.getNextPageToken();
            // } while (nextPageToken != null);

            return allCommentThreads.subList(0, 2);

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            throw new YoutubeApiException("Error accessing the youtube API.", e);
        }
    }

}
