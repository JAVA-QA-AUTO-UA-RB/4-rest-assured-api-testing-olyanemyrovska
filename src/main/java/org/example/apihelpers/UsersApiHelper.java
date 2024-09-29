package org.example.apihelpers;

import org.example.models.User;

import java.util.List;


public class UsersApiHelper extends BaseApiHelper {

    public static User getUserById(long userId) {
        return createRequest()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getObject("", User.class);
    }

    public static List<User> getUsersByUserName(String userName) {
        return createRequest()
                .queryParam("username", userName)
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getList("", User.class);
    }
}
