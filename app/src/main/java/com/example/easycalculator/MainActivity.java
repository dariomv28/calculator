package com.example.easycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonAC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonMinus, buttonAdd;
    MaterialButton buttonEquals, buttonDot;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignID(buttonC, R.id.button_c);
        assignID(buttonAC, R.id.button_ac);
        assignID(buttonBrackOpen, R.id.button_open_bracket);
        assignID(buttonBrackClose, R.id.button_close_bracket);
        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonMultiply, R.id.button_multiply);
        assignID(buttonMinus, R.id.button_subtract);
        assignID(buttonAdd, R.id.button_add);
        assignID(buttonEquals, R.id.button_equal);
        assignID(buttonDot, R.id.button_dot);
        assignID(button0, R.id.button_0);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);
    }

    void assignID(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String btn_text = btn.getText().toString();
        String data = solutionTv.getText().toString();

        if (btn_text.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (btn_text.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }

        if (btn_text.equals("C")) {
            if (!data.isEmpty()) {
                data = data.substring(0, data.length() - 1);
                solutionTv.setText(data);
            }
            return;
        }

        else {
            data += btn_text;
        }
        solutionTv.setText(data);
        String final_result = calculate(data);
        if (!final_result.equals("Error")) {
            resultTv.setText(final_result);
        }
    }

    String calculate(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String rawResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            double result = Double.parseDouble(rawResult);
            String final_result = String.format("%.6f", result).replaceAll("\\.?0+$", "");
            return final_result;
        } catch (Exception e) {
            return "Error";
        }
    }

}
