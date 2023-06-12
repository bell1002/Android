package com.hothikieulinh.cau2d1c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.hothikieulinh.cau2d1c2.databinding.ActivityEmployeeDialogBinding;
import com.hothikieulinh.models.Employee;

public class Employee_Dialog extends AppCompatActivity {
ActivityEmployeeDialogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_employee_dialog);
        binding=ActivityEmployeeDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
    }

    private void getData() {
        Intent intent=getIntent();
        Employee e=(Employee) intent.getSerializableExtra("EmployeeInfo");
        binding.edtId.setText(e.getId());
        binding.edtName.setText(e.getName());
        binding.edtPrice.setText(String.valueOf(e.getAge()));

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper db=new MyDBHelper(Employee_Dialog.this);

              //  Employee e = (Employee) intent.getSerializableExtra("EmployeeInfo");
                db.deleteEmployee(e.getId());

                finish();

            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}