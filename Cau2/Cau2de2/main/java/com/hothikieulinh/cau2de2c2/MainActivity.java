package com.hothikieulinh.cau2de2c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hothikieulinh.cau2de2c2.databinding.ActivityMainBinding;
import com.hothikieulinh.models.Phone;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    MyDBHelper db=new MyDBHelper(this);
    Phone selectPhone=null;
    ArrayAdapter<Phone> adapter;
    ArrayList<Phone> phones;

    SQLiteDatabase dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prePareDB();
        addEvent();
    }

    private void addEvent() {
       binding.lvPhone.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               selectPhone=phones.get(i);
               Intent intent=new Intent(getApplicationContext(), Phone_Dialog.class);
               intent.putExtra("PhoneInfo", selectPhone);
               startActivity(intent);
               return false;
           }
       });
    }

    @Override
    protected void onResume() {
        LoadData();
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    private void LoadData() {
        phones=new ArrayList<Phone>();
        phones.addAll(db.getAllPhone());
        adapter=new ArrayAdapter<Phone>(MainActivity.this, android.R.layout.simple_list_item_1, phones);
        binding.lvPhone.setAdapter(adapter);

    }

    private void prePareDB() {
        db=new MyDBHelper(MainActivity.this);
        db.createSampleData();
    }
}