package com.sasing.menu;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogueFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogue_fragment, container, false);

        Button btnAdd = view.findViewById(R.id.add);
        Button btnExit = view.findViewById(R.id.exit);

        btnAdd.setOnClickListener(v -> {
            // Handle adding fragment
            dismiss();
        });

        btnExit.setOnClickListener(v -> {
            // Handle exiting app
            dismiss();
            getActivity().finish();
        });

        return view;
    }
}