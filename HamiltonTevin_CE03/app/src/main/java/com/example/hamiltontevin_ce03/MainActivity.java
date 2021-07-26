package com.example.hamiltontevin_ce03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  TextView mainTextView;
    private  FrameLayout mainFrameLayout;
    private int textColorSelection = 0;
    private int backgroundColorSelection = 0;
    private boolean userEnteredText = false;
    private  EditText changeText = null;
    private  Switch mainSwitch = null;
    private RadioGroup radioGroup_FL_ColorChange;
    private RadioGroup radioGroup_tv_ColorChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup_FL_ColorChange = findViewById(R.id.bgColorChange_rg);
        radioGroup_tv_ColorChange = findViewById(R.id.textColorChange_rg);

        radioGroup_FL_ColorChange.check(R.id.radioButton_fl1);
        radioGroup_tv_ColorChange.check(R.id.radioButton_tv2);

        textColorSelection = R.id.radioButton_tv2;
        backgroundColorSelection = R.id.radioButton_fl1;

        mainFrameLayout = findViewById(R.id.fl_Main);
        mainTextView = findViewById(R.id.tv_textAppears);

        changeText = findViewById(R.id.changeText_editText);
        mainSwitch = findViewById(R.id.bold_Swtich);

        mainSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    mainTextView.setTextAppearance(R.style.textStyleBold);

                }
                else {
                    mainTextView.setTextAppearance(R.style.textStyleNormal);
                }
            }
        });

        changeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                mainTextView.setText(text.toString());
                userEnteredText = true;
            }
        });

        radioGroup_tv_ColorChange.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_tv1:
                        mainTextView.setTextColor(getColor(R.color.purple_color));
                        break;

                    case  R.id.radioButton_tv2:
                        mainTextView.setTextColor(getColor(R.color.green_color));
                        break;

                    case  R.id.radioButton_tv3:
                        mainTextView.setTextColor(getColor(R.color.black_color));
                        break;
                }
                textColorSelection = checkedId;
            }
        });
        radioGroup_FL_ColorChange.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_fl1:
                        mainFrameLayout.setBackgroundColor(getColor(R.color.purple_color));
                        break;

                    case  R.id.radioButton_fl2:
                        mainFrameLayout.setBackgroundColor(getColor(R.color.green_color));
                        break;

                    case  R.id.radioButton_fl3:
                        mainFrameLayout.setBackgroundColor(getColor(R.color.black_color));
                        break;
                }
                backgroundColorSelection = checkedId;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputText",mainTextView.getText().toString());
        outState.putInt("textColor",textColorSelection);
        outState.putInt("backgroundColor",backgroundColorSelection);
        outState.putBoolean("userEnteredState",userEnteredText);
        outState.putBoolean("boldState",mainSwitch.isChecked());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String enteredText = (savedInstanceState.getString("inputText"));
        mainTextView.setText(enteredText);

        mainSwitch.setChecked(savedInstanceState.getBoolean("boldState"));

        radioGroup_FL_ColorChange.check(savedInstanceState.getInt("backgroundColor"));
        radioGroup_tv_ColorChange.check(savedInstanceState.getInt("textColor"));

        if (!savedInstanceState.getBoolean("userEnteredState")) {
            changeText.setText(enteredText);
        }



    }
}
