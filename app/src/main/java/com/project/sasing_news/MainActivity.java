package com.project.sasing_news;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements ListFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name); // Set app name from strings.xml
        }

        if (findViewById(R.id.main_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            ListFragment listFragment = new ListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, listFragment).commit();
        }
    }

    @Override
    public void onArticleSelected(String headline, String content) {
        ContentFragment contentFragment = (ContentFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFragment);

        if (contentFragment != null) {
            contentFragment.updateContent(headline, content);
        } else {
            ContentFragment newFragment = new ContentFragment();

            Bundle args = new Bundle();
            args.putString("headline", headline);
            args.putString("content", content);
            newFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}

