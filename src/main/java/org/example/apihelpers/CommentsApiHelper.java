package org.example.apihelpers;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.Comment;

import java.util.List;


public class CommentsApiHelper extends BaseApiHelper {

    public static Response getCommentsByPostIdRaw(int postId) {
        return createRequest()
                .queryParam("postId", postId)
                .get("/comments")
                .then()
                .extract()
                .response();
    }


    public static List<Comment> getCommentsByPostId(int postId) {
        Response response = getCommentsByPostIdRaw(postId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return response.jsonPath().getList("", Comment.class);
    }

    public static Response getCommentByIdRaw(int commentId) {
        return createRequest()
                .get("/comments/" + commentId)
                .then()
                .extract()
                .response();
    }

    public static Comment getCommentById(int commentId) {
        Response response = getCommentByIdRaw(commentId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return response.jsonPath().getObject("", Comment.class);
    }
}
