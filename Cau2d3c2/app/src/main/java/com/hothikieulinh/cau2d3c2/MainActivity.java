package com.hothikieulinh.cau2d3c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hothikieulinh.cau2d3c2.databinding.ActivityMainBinding;
import com.hothikieulinh.models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyDBHelper db=new MyDBHelper(this);
    Product selectProduct=null;
    ArrayAdapter<Product> adapter;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        perPareDB();
        addEvent();
    }

    private void addEvent() {
      binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
              selectProduct=products.get(i);
              Intent intent=new Intent(getApplicationContext(), Product_Dialog.class);
              intent.putExtra("ProductInfo", selectProduct);
              startActivity(intent);
              return false;
          }
      });
    }

    private void perPareDB() {
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
        products=new ArrayList<>();
        products.addAll(db.getAllProduct());
        adapter=new ArrayAdapter<Product>(MainActivity.this, android.R.layout.simple_list_item_1, products);
        binding.lvProduct.setAdapter(adapter);
    }
}