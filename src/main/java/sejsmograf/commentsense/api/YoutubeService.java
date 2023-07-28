package sejsmograf.commentsense.api;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;

import sejsmograf.commentsense.config.YoutubeConfigProperties;

@Service
public class YoutubeService {

    private final YoutubeConfigProperties youtubeConfig;

    public YoutubeService(YoutubeConfigProperties youtubeConfig) {
        this.youtubeConfig = youtubeConfig;
    }

    public List<CommentThread> getCommentThreadsForVideo(String videoId) {
        try {
            YouTube youtube = YoutubeApiConnector.getService();
            YouTube.CommentThreads.List request = youtube.commentThreads().list(List.of("snippet", "replies"));

            request.setVideoId(videoId);
            request.setMaxResults(100L);
            request.setKey(youtubeConfig.apiKey());

            List<CommentThread> allCommentThreads = new ArrayList<>();
            String nextPageToken = null;

            do {
                request.setPageToken(nextPageToken);
                CommentThreadListResponse response = request.execute();
                if (response.getItems() != null) {
                    allCommentThreads.addAll(response.getItems());
                }
                nextPageToken = response.getNextPageToken();
            } while (nextPageToken != null);

            return allCommentThreads;
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
