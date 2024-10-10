package utilities;



import java.util.List;

import models.Todo;

public class TaskValidator {

    // Check if more than half of the tasks are completed
    public boolean hasMoreThanHalfCompleted(List<Todo> todos) {
        long completedCount = todos.stream().filter(Todo::isCompleted).count();
        return ((double) completedCount / todos.size()) > 0.5;
    }
}
