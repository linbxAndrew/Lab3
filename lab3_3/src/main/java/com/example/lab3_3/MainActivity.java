package com.example.lab3_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
    }
    // 按下menu键
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // 单击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        if (mi.isCheckable()) {
            mi.setChecked(true);
        }
        switch (mi.getItemId()) {
            case R.id.font_10: txt.setTextSize(10 * 2); break;
            case R.id.font_16: txt.setTextSize(16 * 2); break;
            case R.id.font_20: txt.setTextSize(20 * 2); break;

            case R.id.plain_item:
                Toast.makeText(this, "you clicked the plain text", Toast.LENGTH_SHORT).show();
                break;

            case R.id.font_red: txt.setTextColor(Color.RED);
            case R.id.font_black: txt.setTextColor(Color.BLACK);
        }
        return true;
    }
}