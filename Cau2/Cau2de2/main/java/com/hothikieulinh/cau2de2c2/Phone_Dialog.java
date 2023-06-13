package com.hothikieulinh.cau2de2c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hothikieulinh.cau2de2c2.databinding.ActivityPhoneDialogBinding;
import com.hothikieulinh.models.Phone;

public class Phone_Dialog extends AppCompatActivity {
    ActivityPhoneDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_phone_dialog);
        binding=ActivityPhoneDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
    }

    private void getData() {
        Intent intent=getIntent();
        Phone p=(Phone) intent.getSerializableExtra("PhoneInfo");
        binding.ediId.setText(p.getId());
        binding.edtname.setText(p.getName());
        binding.edtPrice.setText(String.valueOf(p.getPrice()));

        binding.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper db=new MyDBHelper(Phone_Dialog.this);
                if((db.deletePhone(p.getId()))>0){
                    Toast.makeText(Phone_Dialog.this, "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Phone_Dialog.this, "Fail", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}