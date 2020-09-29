package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    public double data1, data2;
    public enum Operator { ADD, SUBTRACT, MULTIPLY, DIVIDE, NONE }
    public Operator operator;

    private static class InputOnClickListener implements View.OnClickListener {
        // InputOnClickListener
        // This is for the numbers 0-9 and .
        private TextView display;
        private String input;

        public InputOnClickListener(TextView display, String input) {
            this.display = display;
            this.input = input;
        }

        @Override
        public void onClick(View v) {
            String text = this.display.getText() + this.input;
            this.display.setText(text);
        }
    }

    private static class OperatorOnClickListener implements View.OnClickListener {
        // OperatorOnClickListener
        // This is for the +-/*
        private MainActivity activity;
        private Operator operator;

        public OperatorOnClickListener(MainActivity activity, Operator operator) {
            this.activity = activity;
            this.operator = operator;
        }

        @Override
        public void onClick(View v) {
            String text = activity.display.getText().toString();
            activity.operator = this.operator;
            activity.data1 = Double.parseDouble(text);
            activity.display.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.display = findViewById(R.id.display);
        this.operator = Operator.NONE;
        this.addListeners();
    }

    private void addListeners() {
        // Input listeners
        findViewById(R.id.btn0).setOnClickListener(new InputOnClickListener(this.display, "0"));
        findViewById(R.id.btn1).setOnClickListener(new InputOnClickListener(this.display, "1"));
        findViewById(R.id.btn2).setOnClickListener(new InputOnClickListener(this.display, "2"));
        findViewById(R.id.btn3).setOnClickListener(new InputOnClickListener(this.display, "3"));
        findViewById(R.id.btn4).setOnClickListener(new InputOnClickListener(this.display, "4"));
        findViewById(R.id.btn5).setOnClickListener(new InputOnClickListener(this.display, "5"));
        findViewById(R.id.btn6).setOnClickListener(new InputOnClickListener(this.display, "6"));
        findViewById(R.id.btn7).setOnClickListener(new InputOnClickListener(this.display, "7"));
        findViewById(R.id.btn8).setOnClickListener(new InputOnClickListener(this.display, "8"));
        findViewById(R.id.btn9).setOnClickListener(new InputOnClickListener(this.display, "9"));
        findViewById(R.id.btnDot).setOnClickListener(new InputOnClickListener(this.display, "."));

        // Operator listeners
        findViewById(R.id.btnAdd).setOnClickListener(new OperatorOnClickListener(this, Operator.ADD));
        findViewById(R.id.btnMinus).setOnClickListener(new OperatorOnClickListener(this, Operator.SUBTRACT));
        findViewById(R.id.btnMultiply).setOnClickListener(new OperatorOnClickListener(this, Operator.MULTIPLY));
        findViewById(R.id.btnDivide).setOnClickListener(new OperatorOnClickListener(this, Operator.DIVIDE));
    }

    public void btnResultOnClick(View v) {
        // Set in XML
        if (this.operator != Operator.NONE) {
            this.data2 = Double.parseDouble(this.display.getText().toString());
            double result = 0;
            if (this.operator == Operator.ADD) {
                result = data1 + data2;
            } else if (this.operator == Operator.SUBTRACT) {
                result = data1 - data2;
            } else if (this.operator == Operator.MULTIPLY) {
                result = data1 * data2;
            } else if (this.operator == Operator.DIVIDE) {
                result = data1 / data2;
            }

            this.operator = Operator.NONE;
            if ((result-(int)result) != 0) {
                this.display.setText(String.valueOf(result));
            } else {
                this.display.setText(String.valueOf((int) result));
            }
        }
    }

    public void btnClearOnClick(View v) {
        // Set in XML
        this.operator = Operator.NONE;
        this.display.setText("");
    }

}