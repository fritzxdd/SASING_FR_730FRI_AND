package com.project.todolist_sasing;

public class TodoItem {
    private String text;
    private String imageUrl; // URL for the image (can be null)
    private boolean isCompleted;

    // Constructor
    public TodoItem(String text, String imageUrl, boolean isCompleted) {
        this.text = text;
        this.imageUrl = imageUrl;
        this.isCompleted = isCompleted;
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}





