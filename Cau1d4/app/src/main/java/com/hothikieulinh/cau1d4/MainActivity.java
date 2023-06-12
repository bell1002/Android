package com.hothikieulinh.cau1d4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hothikieulinh.cau1d4.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    Random random = new Random();

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LinearLayout.LayoutParams txtparams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    int randomNumb;

    Handler handler = new Handler();

    int count = 1;

    LinearLayout linearLayout;


    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            if(count <= 3){
                Button btn = new Button(MainActivity.this);
                btn.setText(Integer.toString(randomNumb));
                btn.setLayoutParams(buttonParams);
                //setUpButtonParam(btn);

                if(randomNumb % 2 == 0){
                    btn.setBackgroundColor(Color.GREEN);
                }
                else{
                    btn.setBackgroundColor(Color.GRAY);
                }
                linearLayout.addView(btn);

                if(count == 1 || count == 2){
                    TextView txt = new TextView(MainActivity.this);
                    txt.setLayoutParams(txtparams);
                    //setUpTextViewParam(txt);
                    linearLayout.addView(txt);
                }
            }

            if(count == 3){
                binding.layoutDraw.addView(linearLayout);
                setUpLinearLayout();
                count = 0;
            }

            count++;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //params.weight = 1;12
        buttonParams.weight = 1.53333333f;
        txtparams.weight = 0.2f;
        params.topMargin = 30;
        setUpLinearLayout();
        addEvents();

    }

    private void addEvents() {
        binding.btndrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawUI();
            }
        });
    }

    private void drawUI() {
        String check = binding.edtNumber.getText().toString();
        if(!Character.isDigit(check.charAt(0))){
            Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập số!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else{
            int numb = Integer.parseInt(binding.edtNumber.getText().toString());
            if(numb % 3 == 0){
                binding.layoutDraw.removeAllViews();
                Thread backgroundThreat = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i <= numb; i++){
                            randomNumb = random.nextInt(10);
                            handler.post(foregroundThread);
                            SystemClock.sleep(100);
                        }
                    }
                });
                backgroundThreat.start();
            }
            else{
                Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập số chia hết cho 3!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }

    private void setUpLinearLayout() {
        linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(5);
        linearLayout.setLayoutParams(params);
    }
}