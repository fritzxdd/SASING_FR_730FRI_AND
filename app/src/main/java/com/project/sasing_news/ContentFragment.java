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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        headlineTextView = view.findViewById(R.id.headlineTextView);
        contentTextView = view.findViewById(R.id.contentTextView);
        return view;
    }

    public void updateContent(String headline, String content) {
        headlineTextView.setText(headline);
        contentTextView.setText(content);
    }
}


