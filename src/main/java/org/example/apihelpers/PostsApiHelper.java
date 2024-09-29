package org.example.apihelpers;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.Comment;
import org.example.models.PostRequest;
import org.example.models.PostResponse;

import static org.example.apihelpers.BaseApiHelper.createRequest;


public class PostsApiHelper extends BaseApiHelper {

    public static Response getPostsByIdRaw(long postId) {
        return createRequest()
                .get("/posts/" + postId)
                .then()
                .extract()
                .response();
    }

    public static PostResponse getPostsById(long postId) {
        Response response = getPostsByIdRaw(postId);
        assertStatusCode(HttpStatus.SC_OK, response);
        return response.jsonPath().getObject("", PostResponse.class);
    }

    public static Response createPostRaw(PostRequest post) {
        return createRequest()
                .body(post)
                .when()
                .post("/posts")
                .then()
                .extract()
                .response();
    }

    public static PostResponse createPost(PostRequest post) {
        Response response = createPostRaw(post);
        assertStatusCode(HttpStatus.SC_CREATED, response);
        return response.jsonPath().getObject("", PostResponse.class);
    }

    public static PostResponse updatePost(long postId, PostRequest post) {
        return createRequest()
                .body(post)
                .when()
                .put("/posts/" + postId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath().getObject("", PostResponse.class);
    }

    public static Response deletePost(long postId) {
        return createRequest()
                .when()
                .delete("/posts/" + postId)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
