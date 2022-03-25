package com.example.filtergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private long lastTime = 0;
    private Toast pressAgainToast;
    @Override
    public void onBackPressed() {

        if (lastTime + 2000 <= System.currentTimeMillis()){
            pressAgainToast = Toast.makeText(getBaseContext(), getString(R.string.pressAgain_toast_text), Toast.LENGTH_SHORT);
            pressAgainToast.show();
        } else {
            pressAgainToast.cancel();
            super.onBackPressed();
        }
        lastTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonPlay = (Button)findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent_play = new Intent(MainActivity.this, LevelsActivity.class);
                    startActivity(intent_play);
                    finish();
                }catch (Exception e){

                }
            }
        });
        Button buttonSettings = (Button)findViewById(R.id.button_settings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent_settings = new Intent(MainActivity.this, SettingsActivity.class);//открыть настройки (PULL test)
                    startActivity(intent_settings);
                    finish();
                }catch (Exception e){

                }
            }
        });
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}