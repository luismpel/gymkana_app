package com.example.gymkana;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecretCodeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        final String secret = getIntent().getStringExtra("secretCode");
        final EditText input = findViewById(R.id.editTextNumber);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length() == 4){
                    if(editable.toString().equals(secret)){
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                    else {
                        editable.clear();
                        Snackbar.make(findViewById(R.id.editTextNumber), "Código incorrecto", Snackbar.LENGTH_SHORT).show();
                    }
                }
                else if(editable.length()>4)
                    editable.clear();
            }
        });
        input.requestFocus();
        input.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(input, 0);
                input.getEditableText().clear();
            }
        }, 50);
    }
}
