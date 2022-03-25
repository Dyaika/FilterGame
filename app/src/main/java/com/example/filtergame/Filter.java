package com.example.filtergame;

import android.util.Log;

public class Filter {
    private int color;
    private int shape;

    public Filter(int color, int shape) {
        if (color > 3 && color < 0){//0,1,2,3-синий, красный, зеленый, желтый
            this.color = color;
        }else{
            this.color = 4;//без цвета
        }
        if (shape > 3 && shape < 0){
            this.shape = shape;//0,1,2,3-пар, жидкость, твердое, частицы
        }else{
            this.shape = 4;//без формы
        }
    }

    public int getColor() {
        return color;
    }

    public int getShape() {
        return shape;
    }
    //задел для баффов
    public void trySetColor(int color) {
        if (this.color == 4 && this.shape != 4) {
            this.color = color;
        }else{
            Log.d("Filter", "There is no filter or it already has color");
        }
    }
    //задел для баффов
    public void trySetShape(int shape) {
        if (this.shape == 4 && this.color != 4) {
            this.shape = shape;
        }else{
            Log.d("Filter", "There is no filter or it already has shape");
        }
    }
}
