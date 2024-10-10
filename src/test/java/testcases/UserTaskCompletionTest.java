package testcases;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import models.Todo;
import models.User;
import services.TodoPage;
import services.UserPage;
import utilities.TaskValidator;
public class UserTaskCompletionTest {

    private UserPage userPage;
    private TodoPage todoPage;
    private TaskValidator taskValidator;

    @BeforeClass
    public void setup() {
        userPage = new UserPage();
        todoPage = new TodoPage();
        taskValidator = new TaskValidator();
    }

    @Test
    public void testFanCodeUserTaskCompletion() {
        // Fetch users and todos
        Response usersResponse = userPage.getUsers();
        Response todosResponse = todoPage.getTodos();

        
        // Get FanCode users
        List<User> fanCodeUsers = userPage.getFanCodeUsers(usersResponse);

        // Validate task completion for each user in FanCode
        for (User user : fanCodeUsers) {
            List<Todo> userTodos = todoPage.getTodosForUser(user.getId(), todosResponse);
            boolean hasCompletedMoreThanHalf = taskValidator.hasMoreThanHalfCompleted(userTodos);

            // Assert that user has completed more than half of their tasks
            Assert.assertTrue(hasCompletedMoreThanHalf, "User " + user.getId() + " has not completed more than half of their tasks.");
        }
    }
}
