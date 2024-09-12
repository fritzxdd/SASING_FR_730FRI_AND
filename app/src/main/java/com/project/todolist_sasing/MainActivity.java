package com.project.todolist_sasing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etTodoText;
    private Button btnSelectImage, btnSaveTodo;
    private ImageView ivImagePreview;
    private ListView listView;
    private Uri imageUri; // For new items
    private Uri editImageUri; // For editing items
    private TodoAdapter todoAdapter;
    private List<TodoItem> todoList;
    private ImageView ivEditImagePreview; // Global variable to hold the edit dialog's ImageView reference
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_IMAGE_REQUEST_FOR_EDIT = 2;
    private GestureDetector gestureDetector;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etTodoText = findViewById(R.id.et_todo_text);
        btnSelectImage = findViewById(R.id.btn_select_image);
        btnSaveTodo = findViewById(R.id.btn_save_todo);
        ivImagePreview = findViewById(R.id.iv_image_preview);
        listView = findViewById(R.id.list_view);

        // Initialize ListView and adapter
        todoList = new ArrayList<>();
        todoAdapter = new TodoAdapter(this, R.layout.item_todo, todoList);
        listView.setAdapter(todoAdapter);

        // Set up gesture detector for double-click detection
        gestureDetector = new GestureDetector(this, new GestureListener());

        listView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return false; // Return false to let the ListView handle the event
        });

        // Select image from gallery
        btnSelectImage.setOnClickListener(v -> openFileChooser());

        // Save the to-do item
        btnSaveTodo.setOnClickListener(v -> saveTodoItem());
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void openFileChooserForEdit() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST_FOR_EDIT); // Different request code for edit
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();

            if (requestCode == PICK_IMAGE_REQUEST) {
                // For new items
                imageUri = selectedImageUri;
                ivImagePreview.setVisibility(View.VISIBLE);
                ivImagePreview.setImageURI(imageUri);
            } else if (requestCode == PICK_IMAGE_REQUEST_FOR_EDIT) {
                // For editing items
                editImageUri = selectedImageUri;
                if (ivEditImagePreview != null) {
                    ivEditImagePreview.setVisibility(View.VISIBLE);
                    ivEditImagePreview.setImageURI(editImageUri);
                }
            }
        }
    }



    private void saveTodoItem() {
        String todoText = etTodoText.getText().toString().trim();

        // Check if task text is empty
        if (todoText.isEmpty()) {
            etTodoText.setError("Task cannot be empty");
            etTodoText.requestFocus();
            return;
        }

        // Check if image is selected
        if (imageUri == null) {
            // Optionally, show a message to the user
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create new TodoItem and add to the list
        TodoItem todoItem = new TodoItem(todoText, imageUri != null ? imageUri.toString() : null, false);
        todoList.add(todoItem);
        todoAdapter.notifyDataSetChanged();

        // Clear the input fields
        etTodoText.setText("");
        ivImagePreview.setVisibility(View.GONE);
        imageUri = null;
    }


    private void showEditDeleteDialog(TodoItem item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Action")
                .setItems(new String[]{"Edit", "Delete"}, (dialog, which) -> {
                    switch (which) {
                        case 0: // Edit
                            showEditDialog(item, position);
                            break;
                        case 1: // Delete
                            todoList.remove(position);
                            todoAdapter.notifyDataSetChanged();
                            break;
                    }
                })
                .show();
    }

    private void showEditDialog(TodoItem item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        // Inflate the layout for the dialog
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_todo, null);
        builder.setView(dialogView);

        EditText etEditText = dialogView.findViewById(R.id.et_edit_text);
        ivEditImagePreview = dialogView.findViewById(R.id.iv_edit_image);
        Button btnSelectNewImage = dialogView.findViewById(R.id.btn_select_new_image);

        // Pre-fill data
        etEditText.setText(item.getText());
        editImageUri = Uri.parse(item.getImageUrl()); // Use a separate URI for editing
        if (editImageUri != null) {
            ivEditImagePreview.setVisibility(View.VISIBLE);
            ivEditImagePreview.setImageURI(editImageUri);
        }

        // Image selection for editing
        btnSelectNewImage.setOnClickListener(v -> openFileChooserForEdit());

        builder.setPositiveButton("Save", (dialog, which) -> {
                    String updatedText = etEditText.getText().toString().trim();
                    if (!updatedText.isEmpty()) {
                        item.setText(updatedText);

                        // If a new image was selected, update the image URL
                        if (editImageUri != null)
                        {
                            item.setImageUrl(editImageUri.toString());
                        }

                        todoList.set(position, item);
                        todoAdapter.notifyDataSetChanged();

                        // Clear the editImageUri after saving
                        editImageUri = null;
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }





    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int DOUBLE_TAP_TIMEOUT = 200; // Time between taps

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            int itemPosition = listView.pointToPosition((int) e.getX(), (int) e.getY());
            if (itemPosition != ListView.INVALID_POSITION) {
                TodoItem item = todoList.get(itemPosition);
                showEditDeleteDialog(item, itemPosition);
            }
            return true;
        }
    }
}










