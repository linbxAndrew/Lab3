package com.example.lab3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView = null;
    SimpleAdapter simpleAdapter = null;

    List<Map<String, Object>> listItems = new ArrayList<>();
    Map<String, Object> listItem = null;

    private final String[] names = new String[]{"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private final int[] imageIds = new int[]{R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog, R.drawable.cat, R.drawable.elephant};

    String[] keyString = new String[] {"name", "image"};
    int[] componentInt = new int[] {R.id.name, R.id.image};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for (int i = 0; i < names.length; i++) {
            listItem = new HashMap<>();
            listItem.put("name", names[i]);
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }
        simpleAdapter = new SimpleAdapter(this, listItems, R.layout.simple_item, keyString, componentInt);
        listView = findViewById(R.id.myList);
        listView.setAdapter(simpleAdapter);

//      为ListView的列表项的单击事件绑定事件监听器
//      reference resources：https://blog.csdn.net/openswc/article/details/87898681
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                设置文本。也不知道为什么，点了后都成了大写字母……
                Toast toast = Toast.makeText(MainActivity.this, names[position], Toast.LENGTH_SHORT);
                toast.show();
//                设置颜色。红的不太好看。
                for (int i = 0; i < parent.getCount(); i++) {
                    View v = parent.getChildAt(i);
                    if (position == i) {
                        v.setBackgroundColor(Color.RED);
                    } else {
                        v.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
    }

}