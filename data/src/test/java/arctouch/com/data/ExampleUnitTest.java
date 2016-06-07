package arctouch.com.data;

import com.arctouch.arcnews.domain.rss.Item;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import arctouch.com.data.infra.ObservableUtil;
import arctouch.com.data.rss.PostsRepository;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    private List<Item> posts;

    @Test
    public void retrievingPosts_isCorrect() throws Exception {
        posts = new ArrayList<>();
        PostsRepository repo = new PostsRepository(new ObservableUtil());
        repo.getPostList().forEach(items-> {
                    for (Item item: items
                    ){
                        System.out.println(item.getTitle());
                        System.out.println(item.getDescription());
                        System.out.println(item.getLink());
                    }
                }
        );
    }
}