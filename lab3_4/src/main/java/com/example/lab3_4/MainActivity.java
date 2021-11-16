package com.example.lab3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView empty;
//    private ActionMode actionMode;
//    ArrayList list;
    //选中数量
    int num = 0;
    ListView listView;
    ArrayAdapter adapter;
    Boolean[] booleans = new Boolean[] {false, false, false, false, false};
    List<Boolean> isChecked;
    String[] strs = new String[] {"One", "Two", "Three", "Four", "Five"};
    ArrayList<String> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empty = findViewById(R.id.empty);

        listView = findViewById(R.id.list);
        arr = new ArrayList<String>(Arrays.asList(strs));
        isChecked = Arrays.asList(booleans);
        adapter = new ArrayAdapter(this, R.layout.simple_item, arr);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            /*
             * 参数：ActionMode是长按后出现的标题栏
             * 		positon是当前选中的item的序号
             *		id 是当前选中的item的id
             *		checked 如果是选中事件则为true，如果是取消事件则为false
             */
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
// 调整选定条目
                if (checked == true) {
                    listView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.green));
                    num++;
                } else {
                    listView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.white));
                    num--;
                }
                isChecked.set(position, checked);
                // 用TextView显示
                mode.setTitle("  " + num + " Selected");
            }


            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.actionmode_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an <code><a href="/reference/android/view/ActionMode.html#invalidate()">invalidate()</a></code> request
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.menu_delete:
                        deleteSelectedItems();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
                for (int i = 0; i < listView.getCount(); i++) {
                    if (isChecked.get(i) == true) {
                        isChecked.set(i, false);
                    }
                    listView.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.white));
                }
                adapter.notifyDataSetChanged();
                num = 0;
            }

        });

    }

    public void deleteSelectedItems() {
        int i;
        for (i = listView.getCount() - 1; i >= 0; i--) {
            if (isChecked.get(i) == true) {
                arr.remove(i);
            }
        }

        isChecked = new ArrayList<Boolean>();
        for (i = 0; i < listView.getCount() - num; i++) {
            isChecked.add(false);
        }

        if (arr.isEmpty() == true) {
            empty.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
        num = 0;
    }

}