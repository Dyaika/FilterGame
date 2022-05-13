package com.example.filtergame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filtergame.adapter.FilterBlock3Adapter;
import com.example.filtergame.adapter.FilterBlock4Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import pojo.Filter;
import pojo.FilterBlock;
import pojo.ServerLevel;

public class Level1Activity extends AppCompatActivity {

    private ServerLevel lv;
    private RecyclerView filterBlock4RecyclerView, filterBlock3RecyclerView;
    private FilterBlock4Adapter filterBlock4Adapter;
    private FilterBlock3Adapter filterBlock3Adapter;
    private int storage_cur_height;
    private int [] winHeight;
    private Filter[][] solution_matrix;
    private Filter[][] main_matrix;
    private Filter[][] storage_matrix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_level);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //тут он как то должен понять какой уровень ему грузить именно
        lv = new ServerLevel(1L, "Dyaika", "10 06 2003", 7, 12, "30013213443130113132104444222010323210443122124411122230");
        //
        int main_height = lv.getHeight();
        int storage_height = main_height - 2;
        solution_matrix = new Filter[4][main_height];
        main_matrix = new Filter[4][main_height];
        storage_matrix = new Filter[3][main_height - 2];
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
        Toast toast = new Toast(this);
        Log.d("MX", "matrixes were set");
        //заполним матрицу решения и игровое поле данными из строки
        if(Filter.stringToMatrix(lv.getSolution_matrix_str(), main_matrix, main_height)){
            Filter.stringToMatrix(lv.getSolution_matrix_str(), solution_matrix, main_height);
            Log.d("MX", "matrixes were filled");
        } else {
            Log.d("MX", "matrixes were not filled");
            try{
                toast.setText(R.string.level_corrupted);
                toast.show();
                Intent back_sys_intent = new Intent(Level1Activity.this, LevelsActivity.class);
                startActivity(back_sys_intent);
                finish();
            }catch (Exception e){

            }
        }
        //пока уровень полностью решен, теперь сделаем его играбельным
        //Filter.shuffleMatrix(main_matrix, main_height, storage_matrix);//временно убрал для слежки за отображением матрицы
        storage_cur_height = storage_height;//не индекс а именно высота
        Log.d("MX", "matrixes were shuffled");
        //теперь надо его отобразить
        initRecyclerViews();
        Log.d("RecV", "main matrix recycler view was initialized");
        loadFilterBlocks4(main_height, main_matrix);
        Log.d("RecV", "main matrix recycler view was filled with blocks");
        //loadFilterBlocks3(storage_height, storage_matrix);





        winHeight = new int[4];
        for (int x = 0; x < 4; x++){
            winHeight[x] = 0;
        }
        TextView checkBtn = findViewById(R.id.textViewCheck);
        storage_cur_height = 0;
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storage_cur_height == 0){
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            Filter.checkForWinHeight(main_matrix, main_height, winHeight);
                        }
                    };
                    thread.start();
                    checkBtn.setText(winHeight[0] + " " + winHeight[1] + " " +winHeight[2] + " " + winHeight[3]);

                } else {
                    toast.setText(R.string.storage_isnt_null);
                    toast.show();
                }
            }
        });



    }

    private void initRecyclerViews(){
        filterBlock4RecyclerView = findViewById(R.id.mainMatrixRecyclerView);
        filterBlock3RecyclerView = findViewById(R.id.storageMatrixRecyclerView);
        filterBlock4RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        filterBlock3RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        filterBlock4Adapter = new FilterBlock4Adapter();
        filterBlock4RecyclerView.setAdapter(filterBlock4Adapter);
    }

    private void loadFilterBlocks4(int height, Filter[][] main_matrix){
        Collection<FilterBlock> filterBlocks = getFilterBlocks4(height, main_matrix);
        Log.d("RecV", "main matrix collection for rv was made");
        filterBlock4Adapter.setItems(filterBlocks);
        Log.d("RecV", "main matrix items were set");
    }

    private void loadFilterBlocks3(int height, Filter[][] storage_matrix){
        Collection<FilterBlock> filterBlocks = getFilterBlocks3(height, storage_matrix);
        Log.d("RecV", "storage matrix collection for rv was made");
        filterBlock3Adapter.setItems(filterBlocks);
        Log.d("RecV", "storage matrix items were set");
    }

    private Collection<FilterBlock> getFilterBlocks3(int height, Filter[][] storage_matrix){
        List<FilterBlock> fb = new ArrayList<>();
        Log.d("RecV", "storage matrix list for rv was made");
        for(int y = height - 1; y >= 0; y--) {//бред костыль поменять или переделать все (хотя не надо пока

            Log.d("RecV", "storage matrix elem added - " + y);
            fb.add(new FilterBlock(height, y, 3, storage_matrix));
        }

        Log.d("RecV", "storage matrix list for rv was filled");
        return fb;
    }

    private Collection<FilterBlock> getFilterBlocks4(int height, Filter[][] main_matrix){
        List<FilterBlock> fb = new ArrayList<>();
        Log.d("RecV", "main matrix list for rv was made");
        for(int y = height - 1; y >= 0; y--) {//бред костыль поменять или переделать все

            Log.d("RecV", "main matrix elem added - " + y);
            fb.add(new FilterBlock(height, y, 4, main_matrix));
        }

        Log.d("RecV", "main matrix list for rv was filled");
        return fb;
    }
}