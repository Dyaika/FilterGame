package com.example.filtergame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pojo.Filter;
import pojo.ServerLevel;

public class Level1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_level);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //тут он как то должен понять какой уровень ему грузить именно
        ServerLevel lv = new ServerLevel(1L, "Dyaika", "10 06 2003", 7, 12, "30013213443130113132104444222010323210443122124411122230");
        //
        int main_height = lv.getHeight();
        int storage_height = main_height - 2;
        Filter[][] solution_matrix = new Filter[4][main_height];
        Filter[][] main_matrix = new Filter[4][main_height];
        Filter[][] storage_matrix = new Filter[3][main_height - 2];
        Log.d("MX", "matrixes were made");
        for(int j = 0; j < main_height; j++) {
            for (int i = 0; i < 4; i++) {
                solution_matrix[i][j] = new Filter();
                main_matrix[i][j] = new Filter();
            }
        }
        for(int j = 0; j < storage_height; j++) {
            for (int i = 0; i < 3; i++) {
                storage_matrix[i][j] = new Filter();
            }
        }
        Log.d("MX", "matrixes were set");
        //заполним матрицу решения и игровое поле данными из строки
        if(Filter.stringToMatrix(lv.getSolution_matrix_str(), main_matrix, main_height)){
            Filter.stringToMatrix(lv.getSolution_matrix_str(), solution_matrix, main_height);
            Log.d("MX", "matrixes were filled");
        } else {
            Log.d("MX", "matrixes were not filled");
            try{
                Toast toast = new Toast(this);
                toast.setText(R.string.level_corrupted);
                toast.show();
                Intent back_sys_intent = new Intent(Level1Activity.this, LevelsActivity.class);
                startActivity(back_sys_intent);
                finish();
            }catch (Exception e){

            }
        }
        //пока уровень полностью решен, теперь сделаем его играбельным
        Filter.shuffleMatrix(main_matrix, main_height, storage_matrix);

    }
}