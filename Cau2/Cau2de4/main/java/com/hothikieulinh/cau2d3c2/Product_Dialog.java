package com.hothikieulinh.cau2d3c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hothikieulinh.cau2d3c2.databinding.ActivityProductDialogBinding;
import com.hothikieulinh.models.Product;

public class Product_Dialog extends AppCompatActivity {

    ActivityProductDialogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_dialog);
        binding=ActivityProductDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();

    }

    private void getData() {
        Intent intent=getIntent();
        Product p=(Product) intent.getSerializableExtra("ProductInfo");
        binding.edtId.setText(p.getId());
        binding.edtName.setText(p.getName());
        binding.edtPrice.setText(String.valueOf(p.getPrice()));

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper db=new MyDBHelper(Product_Dialog.this);
                if(db.updateProduct(binding.edtId.getText().toString(), binding.edtName.getText().toString(), Double.parseDouble(binding.edtPrice.getText().toString()))>0){
                    Toast.makeText(Product_Dialog.this, "Successfull", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Product_Dialog.this, "Fail!", Toast.LENGTH_SHORT).show();
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