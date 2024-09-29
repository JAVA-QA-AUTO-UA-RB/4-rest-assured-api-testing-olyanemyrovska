package org.example.apihelpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.ConfigProvider;
import org.example.models.Comment;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class BaseApiHelper {
    public static RequestSpecification createRequest() {
        return RestAssured.given()
                .baseUri(ConfigProvider.getBaseUrl()) // Base URI
                .header("Content-Type", "application/json") // Common header
                .log().all(); // Log request details
    }

    static void assertStatusCode(int ExpectedStatus, Response response) {
        assertThat(response.statusCode(), equalTo(ExpectedStatus));
    }
}
