package com.bradteachescode.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText itemET;
    private Button addBtn, list1Btn, list2Btn, list3Btn;
    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public String listFile = "list1.dat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemET = findViewById(R.id.item_edit_text);
        addBtn = findViewById(R.id.add_btn);
        itemsList = findViewById(R.id.items_list);
        list1Btn = findViewById(R.id.list_1_btn);
        list2Btn = findViewById(R.id.list_2_btn);
        list3Btn = findViewById(R.id.list_3_btn);

        //Loads list1 at start////////////////
        //Pass In context and listFile
        items = FileHelper.readData(this, listFile);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);

        addBtn.setOnClickListener(this);
        list1Btn.setOnClickListener(this);
        list2Btn.setOnClickListener(this);
        list3Btn.setOnClickListener(this);
        itemsList.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_btn:
                String itemEntered = itemET.getText().toString();
                adapter.add(itemEntered);
                itemET.setText("");
                FileHelper.writeData(items, this, listFile);
                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                break;

            case R.id.list_1_btn:
                listFile = "list1.dat";
                items = FileHelper.readData(this, listFile);
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
                itemsList.setAdapter(adapter);

                break;

            case R.id.list_2_btn:
                listFile = "list2.dat";
                items = FileHelper.readData(this, listFile);
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
                itemsList.setAdapter(adapter);

                break;

            case R.id.list_3_btn:
                listFile = "list3.dat";
                items = FileHelper.readData(this, listFile);
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
                itemsList.setAdapter(adapter);

                break;


        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();

    }

}
