package services;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import io.restassured.response.Response;
import models.Todo;
import utilities.ApiUtils;

public class TodoPage {

    // Fetch todos from the API
    public Response getTodos() {
        return ApiUtils.getTodos();
    }

    // Get todos for a specific user
    public List<Todo> getTodosForUser(int userId, Response todosResponse) {
        Gson gson = new Gson();
        try {
        Todo[] todos = gson.fromJson(todosResponse.asString(), Todo[].class);

        // Filter tasks by userId
        return List.of(todos).stream()
                .filter(todo -> todo.getUserId() == userId)
                .collect(Collectors.toList());
        }
        catch(Exception e) {
        	e.printStackTrace();
        	return null;
        	
        }
    }
}



