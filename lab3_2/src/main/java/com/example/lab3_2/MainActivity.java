package com.example.lab3_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView show;
    public void customeView() {
        TableLayout loginForm = (TableLayout)getLayoutInflater().inflate(R.layout.login, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle("ANDROID APP")
                .setView(loginForm);
//                .setNegativeButton("Cancel", ((dialog, which) -> show.setText("你取消了。")))
//                .setPositiveButton("Sign in", ((dialog, which) -> show.setText("你确定了")));
        builder.create().show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = findViewById(R.id.show);
        customeView();
    }
}