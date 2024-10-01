package com.project.sasing_bottomnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private EditText etFullName, etEmail;
    private RadioGroup radioGroupGender;
    private CheckBox checkboxNewsletter, checkboxPromotions;
    private Button btnSaveProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        etFullName = view.findViewById(R.id.et_full_name);
        etEmail = view.findViewById(R.id.et_email);
        radioGroupGender = view.findViewById(R.id.radio_group_gender);
        checkboxNewsletter = view.findViewById(R.id.checkbox_newsletter);
        checkboxPromotions = view.findViewById(R.id.checkbox_promotions);
        btnSaveProfile = view.findViewById(R.id.btn_save_profile);

        // Set a click listener for the save button
        btnSaveProfile.setOnClickListener(v -> saveProfile());

        return view;
    }

    private void saveProfile() {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        String gender = selectedGenderId == R.id.radio_male ? "Male" :
                selectedGenderId == R.id.radio_female ? "Female" : "Not Specified";
        boolean isNewsletterSubscribed = checkboxNewsletter.isChecked();
        boolean isPromotionsSubscribed = checkboxPromotions.isChecked();

        // Add your saving logic here (e.g., save to database or SharedPreferences)

        // For now, show a toast as a placeholder
        Toast.makeText(getContext(), "Profile Saved: " + fullName + ", " + email + ", " + gender +
                "\nNewsletter: " + isNewsletterSubscribed + ", Promotions: " + isPromotionsSubscribed, Toast.LENGTH_LONG).show();
    }
}
