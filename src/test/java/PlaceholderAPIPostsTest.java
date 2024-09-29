import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.PostRequest;
import org.example.models.PostResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.apihelpers.PostsApiHelper.*;
import static org.example.models.ModelsGenerator.generatePostModelWithValidData;


public class PlaceholderAPIPostsTest extends BaseTest {
    @Test(groups = {"smoke", "regression"})
    public void testGetPost() {
        long postId = 5;
        PostResponse post = getPostsById(postId);

        Assert.assertEquals(post.getId(), postId);
        Assert.assertTrue(post.getUserId() > 0);
        Assert.assertFalse(post.getTitle().isEmpty());
        Assert.assertFalse(post.getBody().isEmpty());
    }

    @Test(groups = "regression")
    public void testCreatePost() {
        PostRequest newPost = generatePostModelWithValidData();
        PostResponse response = createPost(newPost);

        Assert.assertTrue(response.getId() > 0);
        Assert.assertEquals(response.getBody(), newPost.getBody());
        Assert.assertEquals(response.getTitle(), newPost.getTitle());
        Assert.assertEquals(response.getUserId(), newPost.getUserId());

    }

    @Test(groups = "regression")
    public void testUpdatePost() {
        long postId = 10; //let it be 10
        PostRequest updatePostRequest = generatePostModelWithValidData();
        PostResponse updatedPostResponse = updatePost(postId, updatePostRequest);

        Assert.assertEquals(updatedPostResponse.getId(), postId);
        Assert.assertEquals(updatedPostResponse.getTitle(), updatePostRequest.getTitle());
        Assert.assertEquals(updatedPostResponse.getUserId(), updatePostRequest.getUserId());
        Assert.assertEquals(updatedPostResponse.getBody(), updatePostRequest.getBody());
    }

    @Test(groups = "regression")
    public void testDeletePost() {
        PostResponse postToDelete = createPost(generatePostModelWithValidData());
        deletePost(postToDelete.getId());

        Response notFoundResponse = getPostsByIdRaw(postToDelete.getId());
        Assert.assertEquals(notFoundResponse.statusCode(), HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals(notFoundResponse.getBody().asString(), "{}", "Response body should be an empty JSON object");
    }
}
