package com.hothikieulinh.cau1d2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


import com.hothikieulinh.cau1d2.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
   Random random=new Random();
   LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
   int randomNum;
   Handler handler=new Handler();
   int position;
   Runnable foregroundThread=new Runnable() {
       @Override
       public void run() {
           if(position%2==0){
               Button btn=new Button(MainActivity.this);
               btn.setText(Integer.toString(randomNum));
               btn.setLayoutParams(params);
               binding.layoutDrawButton.addView(btn);
           }
           else{
               EditText edt=new EditText(MainActivity.this);
               edt.setText(Integer.toString(randomNum));
               edt.setLayoutParams(params);
               binding.layoutDrawButton.addView(edt);
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvent();
    }

    private void addEvent() {
        binding.btnDrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawUi();
            }
        });
    }

    private void drawUi() {
        int num=Integer.parseInt(binding.edtNumber.getText().toString());
        binding.layoutDrawButton.removeAllViews();
        Thread backgroundThread=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=num;i++){
                    position=i;
                    randomNum=random.nextInt(100);
                    handler.post(foregroundThread);
                    SystemClock.sleep(100);
                }
            }
        });
        backgroundThread.start();
    }

}