package com.example.filtergame;

import android.util.Log;

public class Filter {
    private int color;
    private int shape;

    public Filter(int color, int shape) {
        if (color <= 3 && color >= 0){//0,1,2,3-синий, красный, зеленый, желтый
            this.color = color;
        }else{
            this.color = 4;//без цвета
        }
        if (shape <= 3 && shape >= 0){
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
    //Проверка фильтра на верность значений. Вернет true, если это фильтр или пустота, если нет - неверные поля заменятся на пустоты и вернет false
    public boolean isFilterCorrect(){
        int color, shape;
        boolean check = true;
        color = getColor();
        shape = getShape();
        if (color > 3 || color < 0){
            this.color = 4;
            check = false;
        }
        if (shape > 3 || shape < 0){
            this.shape = 4;
            check = false;
        }
        return check;
    }
    public boolean isSpace(){
        if (getColor() == 4 && getShape() == 0){
            return true;
        }
        return false;
    }
    //Проверка матрицы на решаемость. Предполагается, что размеры и сами фильтры ее верны. Пусть пока будет здесь, потом перенесем
    //0123-индексы x
    //(0-пустые ячейки,1-верхние объекты, 2-фильтры, 3-лунки)
    //1111  4
    //0222  3
    //2220  2
    //2220  1
    //3333  0-индексы y

    public static boolean isMatrixCorrect(Filter[][] matrix, int height){
        int width = 4;
        int width_count = 0, max_count = 0;//для подряд идущих не пустот
        boolean check = true;//?
        for (int i = 0; i < width; i++){
            if (matrix[i][0].isSpace() || matrix[i][height - 1].isSpace()){//сверху и снизу не должно быть пустот
                return false;
            }
        }
        //стартуем движение сверху вниз
        for (int y = height - 2; y > 0; y--) {//ряды фильтров
            width_count = 0;
            max_count = 0;
            for (int x = 0; x < width; x++) {

                if (matrix[x][y].isSpace()){
                    max_count = width_count;
                    width_count = 0;
                }else{
                    width_count += 1;
                }
            }
            max_count = Math.max(max_count, width_count);
            if (max_count != 3){//убеждаемся что подряд идущих непустот 3, если не так то false
                return false;
            }
        }

        return isMatrixSolvable(matrix, height);//проверка что матрица решится на 4/4
    }
    //проверка на решаемость матрицы на 3 звезды
    public static boolean isMatrixSolvable(Filter[][] matrix, int height){//проверка что матрица решится на 4/4
        int width = 4;
        Filter[] row = new Filter[width];//строка проверки будет идти сверху вниз, нужна для зрения сквозь пустоты
        for (int i = 0; i < width; i++){
            row[i] = matrix[i][height - 1];
        }
        for (int y = height - 2; y >= 0; y--){
            for (int x = 0; x < width; width++){
                if(!matrix[x][y].isSpace()){
                    if(row[x].getShape() != matrix[x][y].getShape() && row[x].getColor() != matrix[x][y].getColor()){//ни цвет, ни форма не совпали
                        return false;
                    }
                    row[x] = matrix[x][y];
                }
            }
        }
        return true;
    }
    //Проверка матрицы на стандартность полей. Предполагается, что размеры ее верны. Пусть пока будет здесь, потом перенесем
    public static boolean standardizeMatrix(Filter[][] matrix, int height){
        int width = 4;//ширина матрицы
        boolean check = true;//вернет true если уже была стандартной
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                if (!matrix[x][y].isFilterCorrect()){
                    check = false;
                }
            }
        }
        return check;
    }
}
