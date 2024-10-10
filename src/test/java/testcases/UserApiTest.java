package testcases;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class UserApiTest {

    @Test
    public void validateUsersAPI() {
        // Step 1: Get users
        Response usersResponse = RestAssured.get("https://jsonplaceholder.typicode.com/users");
        Assert.assertEquals(usersResponse.getStatusCode(), 200, "User API response not successful");

        List<Map<String, Object>> users = usersResponse.jsonPath().getList("$");
        Assert.assertNotNull(users, "No users found in the /users API response.");

        // Print user count for verification
        System.out.println("Number of users fetched: " + users.size());
    }
}
