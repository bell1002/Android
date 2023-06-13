package com.hothikieulinh.cau2d1c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.hothikieulinh.cau2d1c2.databinding.ActivityMainBinding;
import com.hothikieulinh.models.Employee;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyDBHelper db=new MyDBHelper(this);
     Employee selectEmployrr=null;
    ArrayAdapter<Employee> adapter;
    ArrayList<Employee> employees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prePareBD();

        addEvent();


    }

    private void addEvent() {
        binding.lvEmployee.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectEmployrr=employees.get(i);
                Intent intent= new Intent(getApplicationContext(), Employee_Dialog.class);
                intent.putExtra("EmployeeInfo",selectEmployrr);
                startActivity(intent);
                return false;
            }
        });
    }

    private void prePareBD(){
        db=new MyDBHelper(MainActivity.this);
        db.createSampleData();
    }

    @Override
    protected void onResume() {
        LoadData();
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    private void LoadData() {

        employees=new ArrayList<>();
        //employees.clear();
        employees.addAll(db.getAllEmployee());
        adapter=new ArrayAdapter<Employee>(MainActivity.this, android.R.layout.simple_list_item_1, employees);
        binding.lvEmployee.setAdapter(adapter);
    }
}