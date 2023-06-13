package com.hothikieulinh.cau2d5c2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.hothikieulinh.cau2d5c2.databinding.ActivityMainBinding;
import com.hothikieulinh.models.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyDBHelper db=new MyDBHelper(this);
    Book selectBook=null;
    ArrayAdapter<Book> adapter;
    ArrayList<Book> books;

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
        binding.lvBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectBook=books.get(i);
                Intent intent=new Intent(MainActivity.this, Book_Dialog.class);
                intent.putExtra("BookInfo", selectBook);
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
        books=new ArrayList<>();
        books.addAll(db.getAllBook());
        adapter=new ArrayAdapter<Book>(MainActivity.this, android.R.layout.simple_list_item_1, books);
        binding.lvBook.setAdapter(adapter);
    }
}