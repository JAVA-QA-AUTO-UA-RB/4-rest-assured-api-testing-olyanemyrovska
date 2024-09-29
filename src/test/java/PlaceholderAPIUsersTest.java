import org.example.models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.example.apihelpers.UsersApiHelper.getUserById;
import static org.example.apihelpers.UsersApiHelper.getUsersByUserName;


public class PlaceholderAPIUsersTest extends BaseTest {
    @Test(groups = {"smoke", "regression"})
    public void testGetUserById() {
        User expectedUserData = getDefaultUserDataForVerification();
        User actualUser = getUserById(1);

        Assert.assertEquals(actualUser, expectedUserData);
    }

    @Test(groups = "regression")
    public void testGetUserByUsername() {
        User expectedUserData = getDefaultUserDataForVerification();
        List<User> actualUsers = getUsersByUserName("Bret");

        Assert.assertEquals(actualUsers.size(), 1);
        User actualUser = actualUsers.get(0);
        Assert.assertEquals(actualUser, expectedUserData);
    }

    public User getDefaultUserDataForVerification() {
        return User.builder()
                .id(1)
                .name("Leanne Graham")
                .username("Bret")
                .email("Sincere@april.biz")
                .address(User.Address.builder()
                        .street("Kulas Light")
                        .suite("Apt. 556")
                        .city("Gwenborough")
                        .zipcode("92998-3874")
                        .geo(User.Address.Geo.builder()
                                .lat("-37.3159")
                                .lng("81.1496")
                                .build())
                        .build())
                .phone("1-770-736-8031 x56442")
                .website("hildegard.org")
                .company(User.Company.builder()
                        .name("Romaguera-Crona")
                        .catchPhrase("Multi-layered client-server neural-net")
                        .bs("harness real-time e-markets")
                        .build())
                .build();
    }
}


