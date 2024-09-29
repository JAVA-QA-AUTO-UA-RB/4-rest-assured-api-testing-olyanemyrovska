import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeClass
    public void commonSetup() {
        RestAssured.filters(
                new RequestLoggingFilter(LogDetail.ALL), // Logs all details of the request
                new ResponseLoggingFilter(LogDetail.ALL) // Logs all details of the response
        );
    }
}
