package com.sasing.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the initial fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MyFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.next_fragment:
                // Load another fragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new MyFragment())
                        .commit();
                return true;
            case R.id.call_dialogue:
                // Show dialog fragment
                DialogueFragment dialogFragment = new DialogueFragment();
                dialogFragment.show(getSupportFragmentManager(), "dialogue");
                return true;
            case R.id.action_home:
                // Handle Home option
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}