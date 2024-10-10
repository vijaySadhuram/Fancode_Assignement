package models;
public class Todo {

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    // Getters
    public int getUserId() {
        return userId;
    }

    public boolean isCompleted() {
        return completed;
    }
}

