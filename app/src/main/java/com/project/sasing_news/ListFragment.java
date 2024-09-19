package com.project.sasing_news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {

    private OnHeadlineSelectedListener callback;

    public interface OnHeadlineSelectedListener {
        void onArticleSelected(String headline, String content);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ListView listView = view.findViewById(R.id.headlineListView);
        String[] headlines = {"News 1", "News 2", "News 3"};
        String[] contents = {"Content for News 1", "Content for News 2", "Content for News 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, headlines);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedHeadline = headlines[position];
            String selectedContent = contents[position];
            callback.onArticleSelected(selectedHeadline, selectedContent);
        });

        return view;
    }
}

