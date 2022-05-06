package com.example.filtergame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MoreLevelsActivity extends AppCompatActivity {

    private RecyclerView levelsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_levels);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //initRecyclerView();
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

    private void initRecyclerView(){
        //levelsRecyclerView = findViewById(R.id.levels_recycler_view);
        levelsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}