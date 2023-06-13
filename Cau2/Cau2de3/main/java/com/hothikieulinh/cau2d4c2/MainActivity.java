package com.hothikieulinh.cau2d4c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hothikieulinh.cau2d4c2.databinding.ActivityMainBinding;
import com.hothikieulinh.models.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyDBHelper db=new MyDBHelper(this);
    Student selectStudent=null;
    ArrayList<Student> students;
    ArrayAdapter<Student> adapter;
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
        binding.lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectStudent=students.get(i);
                Intent intent=new Intent(getApplicationContext(), Student_Dialog.class);
                intent.putExtra("StudentInfo", selectStudent);
                startActivity(intent);
                return false;
            }
        });
    }

    private void prePareBD() {
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
        students=new ArrayList<>();
        students.addAll(db.getAllEmployee());
        adapter=new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_list_item_1, students);
        binding.lvStudent.setAdapter(adapter);
    }
}