package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.mariuszgromada.math.mxparser.*;
import org.mariuszgromada.*;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id. display);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (getString(R.string.display).equals(display.getText().toString()))
                    display.setText("");
            }
        });

    }

    private void updateText(String strToAdd)
    {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }


    public void ZeroBTN(View view)
    {
        updateText("0");
    }

    public void OneBTN(View view)
    {
        updateText("1");
    }

    public void TwoBTN(View view)
    {
        updateText("2");
    }

    public void ThreeBTN(View view)
    {
        updateText("3");
    }

    public void FourBTN(View view)
    {
        updateText("4");
    }

    public void FiveBTN(View view)
    {
        updateText("5");
    }

    public void SixBTN(View view)
    {
        updateText("6");
    }

    public void SevenBTN(View view)
    {
        updateText("7");
    }

    public void EightBTN(View view)
    {
        updateText("8");
    }

    public void NineBTN(View view)
    {
        updateText("9");
    }

    public void DotBTN(View view)
    {
        updateText(".");
    }

    public void MulBTN(View view)
    {
        updateText("×");
    }

    public void DivideBTN(View view)
    {
        updateText("÷");
    }

    public void AddBTN(View view)
    {
        updateText("+");
    }

    public void SubBTN(View view)
    {
        updateText("-");
    }

    public void ParenthesisBTN(View view)
    {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                openPar +=1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")){
                closedPar += 1;
            }
        }
        if(openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
        }
        else if (closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }

    public void EqualsBTN(View view)
    {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void ModBTN(View view)
    {
        updateText("%");
    }

    public void BackBTN(View view)
    {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos -1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void AllClearBTN(View view)
    {
        display.setText("");
    }


}