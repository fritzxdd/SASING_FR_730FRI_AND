package com.project.todolist_sasing;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<TodoItem> {

    private Context context;
    private int resource;
    private List<TodoItem> todoList;

    public TodoAdapter(@NonNull Context context, int resource, @NonNull List<TodoItem> todoList) {
        super(context, resource, todoList);
        this.context = context;
        this.resource = resource;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
        }

        // Get the views
        CheckBox cbTodoCompleted = convertView.findViewById(R.id.cb_todo_completed);
        ImageView ivTodoImage = convertView.findViewById(R.id.iv_todo_image);

        // Get the current item
        TodoItem currentItem = todoList.get(position);

        // Set the CheckBox text as the task name
        cbTodoCompleted.setText(currentItem.getText());

        // Set the checkbox state based on the task's completion status
        cbTodoCompleted.setChecked(currentItem.isCompleted());

        // Show the image if available
        if (currentItem.getImageUrl() != null && !currentItem.getImageUrl().isEmpty()) {
            ivTodoImage.setVisibility(View.VISIBLE);
            ivTodoImage.setImageURI(Uri.parse(currentItem.getImageUrl()));
        } else {
            ivTodoImage.setVisibility(View.GONE);
        }

        return convertView;
    }
}


