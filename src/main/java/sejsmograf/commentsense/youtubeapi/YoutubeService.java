package sejsmograf.commentsense.youtubeapi;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;

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

            do {
                request.setPageToken(nextPageToken);
                CommentThreadListResponse response = request.execute();
                if (response.getItems() != null) {
                    allCommentThreads.addAll(response.getItems());
                }
                nextPageToken = response.getNextPageToken();
            } while (nextPageToken != null);

            return allCommentThreads.subList(0, 2);

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            throw new YoutubeApiException("Error accessing the youtube API.", e);
        }
    }

    public List<SearchResult> searchVideos(String query) {
        try {
            YouTube youtube = YoutubeApiConnector.getService();
            YouTube.Search.List request = youtube.search().list(List.of("snippet"));

            request.setQ(query);
            request.setKey(youtubeConfig.apiKey());

            return request.execute().getItems();

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            throw new YoutubeApiException("Error accessing the youtube API.", e);
        }

    }

}
