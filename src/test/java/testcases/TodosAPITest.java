package testcases;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class TodosAPITest {

    @Test
    public void validateTodosForFanCodeUsers() {
        // Step 1: Get users from the city FanCode
        Response usersResponse = RestAssured.get("https://jsonplaceholder.typicode.com/users");
        List<Map<String, Object>> users = usersResponse.jsonPath().getList("$");

        for (Map<String, Object> user : users) {
            Map<String, String> geo = (Map<String, String>) ((Map<String, Object>) user.get("address")).get("geo");
            double lat = Double.parseDouble(geo.get("lat"));
            double lng = Double.parseDouble(geo.get("lng"));

            if (isInFanCodeCity(lat, lng)) {
                int userId = (int) user.get("id");

                // Step 2: Get todos for this user
                Response todosResponse = RestAssured.get("https://jsonplaceholder.typicode.com/todos?userId=" + userId);
                Assert.assertEquals(todosResponse.getStatusCode(), 200, "Todos API response not successful");

                List<Map<String, Object>> todos = todosResponse.jsonPath().getList("$");
                Assert.assertNotNull(todos, "No todos found for user: " + userId);

                System.out.println("User " + userId + " has " + todos.size() + " todos.");
            }
        }
    }

    // Utility method to check if user is in FanCode city
    private boolean isInFanCodeCity(double lat, double lng) {
        return lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100;
    }
}


