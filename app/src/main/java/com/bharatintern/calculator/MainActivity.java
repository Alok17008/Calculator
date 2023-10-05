package com.bharatintern.calculator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView txtInput, txtSolution;
    private StringBuilder input = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput = findViewById(R.id.txtInput);
        txtSolution = findViewById(R.id.txtSolution);

        // Set click listeners for numeric and operator buttons
        setNumericClickListener(R.id.btnZero, "0");
        setNumericClickListener(R.id.btnOne, "1");
        setNumericClickListener(R.id.btnTwo, "2");
        setNumericClickListener(R.id.btnThree, "3");
        setNumericClickListener(R.id.btnFour, "4");
        setNumericClickListener(R.id.btnFive, "5");
        setNumericClickListener(R.id.btnSix, "6");
        setNumericClickListener(R.id.btnSeven, "7");
        setNumericClickListener(R.id.btnEight, "8");
        setNumericClickListener(R.id.btnNine, "9");

        setOperatorClickListener(R.id.btnAdd, "+");
        setOperatorClickListener(R.id.btnSubtract, "-");
        setOperatorClickListener(R.id.btnMultiply, "x");
        setOperatorClickListener(R.id.btnDivide, "/");

        // Clear and Backspace buttons
        Button btnClear = findViewById(R.id.btnClear);
        Button btnBack = findViewById(R.id.btnBack);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backspaceInput();
            }
        });

        // Equals button
        Button btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        // Decimal point button
        Button btnDecimal = findViewById(R.id.btnDecimal);
        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput(".");
            }
        });
    }

    private void setNumericClickListener(int buttonId, final String value) {
        // Set click listeners for numeric buttons (0-9)
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput(value);
            }
        });
    }

    private void setOperatorClickListener(int buttonId, final String operator) {
        // Set click listeners for operator buttons (+, -, x, /)
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendInput(" " + operator + " ");
            }
        });
    }

    private void appendInput(String value) {
        input.append(value);
        updateInputText();
    }

    private void clearInput() {
        input.setLength(0);
        updateInputText();
        txtSolution.setText("");
    }

    private void backspaceInput() {
        if (input.length() > 0) {
            input.deleteCharAt(input.length() - 1);
            updateInputText();
        }
    }

    private void updateInputText() {
        txtInput.setText(input.toString());
    }

    private void calculate() {
        try {
            // Perform manual calculation based on the input string
            String expression = input.toString();
            double result = evaluateExpression(expression);
            txtSolution.setText(Double.toString(result));
        } catch (Exception e) {
            txtSolution.setText("Error");
        }
    }

    private double evaluateExpression(String expression) {
        // Simple expression evaluation for +, -, x, /
        String[] tokens = expression.split(" ");
        double result = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);

            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "x":
                    result *= operand;
                    break;
                case "/":
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        // Division by zero error
                        throw new ArithmeticException("Division by zero");
                    }
                    break;
            }
        }

        return result;
    }
}