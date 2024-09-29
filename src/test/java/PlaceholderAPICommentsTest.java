import org.example.models.Comment;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.example.apihelpers.CommentsApiHelper.getCommentById;
import static org.example.apihelpers.CommentsApiHelper.getCommentsByPostId;


public class PlaceholderAPICommentsTest extends BaseTest {
    @Test(groups = {"smoke", "regression"})
    public void testGetCommentById() {
        int commentId = 1;
        Comment expectedComment = Comment.builder()
                .id(commentId)
                .postId(1)
                .email("Eliseo@gardner.biz")
                .name("id labore ex et quam laborum")
                .body("laudantium enim quasi est quidem magnam voluptate ipsam eos\n" +
                        "tempora quo necessitatibus\n" +
                        "dolor quam autem quasi\n" +
                        "reiciendis et nam sapiente accusantium")
                .build();
        Comment actualComment = getCommentById(commentId);

        Assert.assertEquals(actualComment.getId(), expectedComment.getId(), "Comment id does not match");
        Assert.assertEquals(actualComment.getPostId(), expectedComment.getPostId(), "Comment post does not match");
        Assert.assertEquals(actualComment.getName(), expectedComment.getName(), "Comment name does not match");
        Assert.assertEquals(actualComment.getEmail(), expectedComment.getEmail(), "Comment email does not match");
        Assert.assertEquals(actualComment.getBody(), expectedComment.getBody(), "Comment body does not match");
    }

    @Test(groups = "regression")
    public void testGetCommentsByPostId() {
        int currentPostId = 5;
        List<Comment> comments = getCommentsByPostId(currentPostId);

        Assert.assertEquals(comments.size(), 5);
        comments.forEach(comment -> {
                    Assert.assertEquals(comment.getPostId(), currentPostId);
                    Assert.assertTrue(comment.getId() > 0, "CommentId should be greater that 0");
                    Assert.assertFalse(comment.getEmail().isEmpty(), "Comment email should not be empty");
                    Assert.assertFalse(comment.getName().isEmpty(), "Comment name should not be empty");
                    Assert.assertFalse(comment.getBody().isEmpty(), "Comment body should not be empty");
                }
        );
    }
}


