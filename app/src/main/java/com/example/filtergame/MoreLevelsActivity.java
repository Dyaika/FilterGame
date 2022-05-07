package com.example.filtergame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filtergame.adapter.ServerLevelAdapter;

import java.util.Arrays;
import java.util.Collection;

import pojo.ServerLevel;

public class MoreLevelsActivity extends AppCompatActivity {

    private RecyclerView levelsRecyclerView;
    private ServerLevelAdapter serverLevelAdapter;
    private TextView randomText;
    private TextView addText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_levels);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initRecyclerView();
        randomText = findViewById(R.id.random);//кнопка генерации случ уровня
        addText = findViewById(R.id.add);//кнопка редактора уровней
        loadLevels();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        try{
            Intent back_sys_intent = new Intent(MoreLevelsActivity.this, LevelsActivity.class);
            startActivity(back_sys_intent);
            finish();
        }catch (Exception e){

        }
    }

    private void loadLevels(){
        Collection<ServerLevel> serverLevels = getLevels();
        serverLevelAdapter.setItems(serverLevels);
    }

    private Collection<ServerLevel> getLevels(){
        return Arrays.asList(
                new ServerLevel(1L, "Dyaika", "10 06 2003", 11, 12, "123242422141204012041"),
                new ServerLevel(2L, "Edward", "12 02 2009", 12, -2, "1232342132432141204012041"),
                new ServerLevel(3L, "Albert", "0 12 2077", 11, 99990, "1232424533212041")
        );
    }

    private void initRecyclerView(){
        levelsRecyclerView = findViewById(R.id.server_levels_recycler_view);
        levelsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        serverLevelAdapter = new ServerLevelAdapter();
        levelsRecyclerView.setAdapter(serverLevelAdapter);
    }
}