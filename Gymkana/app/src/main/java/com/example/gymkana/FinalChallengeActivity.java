package com.example.gymkana;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FinalChallengeActivity extends AppCompatActivity {

    private String resultString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_challenge);
        FloatingActionButton scanButton = findViewById(R.id.fab);
        final Activity activity = this;
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent qr = new Intent(activity, QRReaderActivity.class);
                startActivityForResult(qr, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_FIRST_USER){
            Snackbar.make(findViewById(android.R.id.content), "Necesitas conceder permisos de acceso a la cámara para poder superar" +
                    " la prueba", Snackbar.LENGTH_LONG).setAction("Ignorar", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            }).show();
        }
        else if(resultCode == Activity.RESULT_OK){
            assert data != null;
            resultString = data.getStringExtra("QRText");
            loadSuccessText(resultString);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("ReadResult", resultString);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        resultString = savedInstanceState.getString("ReadResult");
        if(resultString != null && !resultString.equals("")){
            loadSuccessText(resultString);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    @SuppressLint({"RestrictedApi", "SetTextI18n"})
    private void loadSuccessText(String resultText){
        FloatingActionButton button = findViewById(R.id.fab);
        button.setVisibility(View.INVISIBLE);
        TextView title = findViewById(R.id.title_text);
        TextView desc = findViewById(R.id.desc_text);
        title.setText("¡Habéis ganado!");
        title.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_logro, 0,0, 0);
        desc.setText(resultText);
    }
}
