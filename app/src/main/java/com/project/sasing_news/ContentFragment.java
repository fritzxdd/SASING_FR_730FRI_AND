package com.project.sasing_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {

    private TextView headlineTextView;
    private TextView contentTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if arguments were passed to the fragment
        if (getArguments() != null) {
            String headline = getArguments().getString("headline");
            String content = getArguments().getString("content");
            // Update the content when arguments are available
            updateContent(headline, content);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        headlineTextView = view.findViewById(R.id.headlineTextView);
        contentTextView = view.findViewById(R.id.contentTextView);
        // If arguments were provided, update the content after the view is created
        if (getArguments() != null) {
            String headline = getArguments().getString("headline");
            String content = getArguments().getString("content");
            updateContent(headline, content);
        }
        return view;
    }

    public void updateContent(String headline, String content) {
        if (headlineTextView != null && contentTextView != null) {
            headlineTextView.setText(headline);
            contentTextView.setText(content);
        }
    }
}





