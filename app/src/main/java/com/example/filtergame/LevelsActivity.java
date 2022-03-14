package com.example.filtergame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        try{
            Intent back_sys_intent = new Intent(LevelsActivity.this, MainActivity.class);
            startActivity(back_sys_intent);
            finish();
        }catch (Exception e){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        Button buttonBack = (Button)findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent back_intent = new Intent(LevelsActivity.this, MainActivity.class);
                    startActivity(back_intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        TextView lvl1 = (TextView)findViewById(R.id.textView1);
        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent lvl1_intent = new Intent(LevelsActivity.this, Level1Activity.class);
                    startActivity(lvl1_intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}