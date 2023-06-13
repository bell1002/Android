package com.hothikieulinh.cau2d4c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hothikieulinh.cau2d4c2.databinding.ActivityStudentDialogBinding;
import com.hothikieulinh.models.Student;

public class Student_Dialog extends AppCompatActivity {

    SQLiteDatabase dbl;
    ActivityStudentDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_student_dialog);
        binding=ActivityStudentDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();

    }

    private void getData() {
        Intent intent=getIntent();
        Student s=(Student) intent.getSerializableExtra("StudentInfo");



        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper db=new MyDBHelper(Student_Dialog.this);

               if( (db.insertStudent(binding.edtName.getText().toString(),binding.edtPrice.getText().toString(), binding.edtId.getText().toString()))>0){
                   Toast.makeText(Student_Dialog.this, "Successfull", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(Student_Dialog.this, "Fail", Toast.LENGTH_SHORT).show();
               }

                finish();
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();
            }
        });
        binding.btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}