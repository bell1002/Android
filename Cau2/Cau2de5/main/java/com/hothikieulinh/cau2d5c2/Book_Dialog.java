package com.hothikieulinh.cau2d5c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.widget.Toast;

import com.hothikieulinh.cau2d5c2.databinding.ActivityBookDialogBinding;
import com.hothikieulinh.models.Book;

public class Book_Dialog extends AppCompatActivity {
    ActivityBookDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_book_dialog);
        binding=ActivityBookDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
    }

    private void getData() {
        Intent intent=getIntent();
        Book b=(Book) intent.getSerializableExtra("BookInfo");
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper db=new MyDBHelper(Book_Dialog.this);
                if((db.insertBook(binding.edtId.getText().toString(), binding.edtName.getText().toString(), Double.parseDouble(binding.edtPrice.getText().toString())))>0){
                    Toast.makeText(Book_Dialog.this, "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Book_Dialog.this, "Fail", Toast.LENGTH_SHORT).show();
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