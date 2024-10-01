package com.project.sasing_bottomnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

public class CalculatorFragment extends Fragment {
    private EditText firstNumberEditText, secondNumberEditText;
    private TextView resultTextView;
    private Button addButton, subtractButton, multiplyButton, divideButton;
    private DecimalFormat decimalFormat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        // Initialize views
        firstNumberEditText = view.findViewById(R.id.firstNumberEditText);
        secondNumberEditText = view.findViewById(R.id.secondNumberEditText);
        resultTextView = view.findViewById(R.id.resultTextView);
        addButton = view.findViewById(R.id.addButton);
        subtractButton = view.findViewById(R.id.subtractButton);
        multiplyButton = view.findViewById(R.id.multiplyButton);
        divideButton = view.findViewById(R.id.divideButton);

        // Initialize DecimalFormat to show two decimal places
        decimalFormat = new DecimalFormat("0.00");

        // Set button click listeners
        addButton.setOnClickListener(v -> performOperation("+"));
        subtractButton.setOnClickListener(v -> performOperation("-"));
        multiplyButton.setOnClickListener(v -> performOperation("*"));
        divideButton.setOnClickListener(v -> performOperation("/"));

        return view;
    }

    private void performOperation(String operator) {
        // Get the input numbers
        String firstNumberString = firstNumberEditText.getText().toString();
        String secondNumberString = secondNumberEditText.getText().toString();

        // Validate the inputs
        if (!firstNumberString.isEmpty() && !secondNumberString.isEmpty()) {
            double num1 = Double.parseDouble(firstNumberString);
            double num2 = Double.parseDouble(secondNumberString);
            double result = 0;

            // Perform the calculation based on the operator
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        resultTextView.setText("Cannot divide by zero");
                        return;
                    }
                    break;
            }

            // Display the result formatted to two decimal places
            resultTextView.setText(decimalFormat.format(result));
        } else {
            resultTextView.setText("Please enter both numbers");
        }
    }
}
