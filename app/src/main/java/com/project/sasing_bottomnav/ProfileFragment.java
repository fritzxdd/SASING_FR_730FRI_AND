package com.project.sasing_bottomnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private EditText etFullName, etEmail, etAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioMale, radioFemale;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        etFullName = view.findViewById(R.id.et_full_name);
        etEmail = view.findViewById(R.id.et_email);
        etAge = view.findViewById(R.id.et_age);
        radioGroupGender = view.findViewById(R.id.radio_group_gender);
        radioMale = view.findViewById(R.id.radio_male);
        radioFemale = view.findViewById(R.id.radio_female);
        Button btnSaveProfile = view.findViewById(R.id.btn_save_profile);

        // Set default values
        etFullName.setText("Guts Berserkman"); // Replace with actual data
        etEmail.setText("gutsberserkman@example.com"); // Replace with actual data
        etAge.setText("69"); // Replace with actual data
        radioMale.setChecked(true); // Set default gender to Male, change if needed

        // Save button functionality
        btnSaveProfile.setOnClickListener(v -> {
            // Save profile logic here
        });

        return view;
    }
}
