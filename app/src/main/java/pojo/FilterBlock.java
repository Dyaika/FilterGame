package pojo;

public class FilterBlock {
    private Filter[] filterBlock;
    private int width;
    private int height;//высота натуральным числом (1..100)
    private int cur_height;//высота индексом (0..height - 1)

    public FilterBlock(int height, int cur_height, int width, Filter[][] matrix) {
        this.width = width;
        filterBlock = new Filter[width];
        for (int x = 0; x < width; x++){
            filterBlock[x] = new Filter();
            filterBlock[x].setColorAndShape(matrix[x][cur_height]);
        }
        this.height = height;
        this.cur_height = cur_height;
    }
    //смотрит на матрицу игры и перестраивает себя в соответствии с ней
    public void updateFilterBlock(Filter[][] matrix) {
        for (int x = 0; x < width; x++){
            filterBlock[x].setColorAndShape(matrix[x][cur_height]);
        }
    }
    public void updateFilterBlock(Filter[][] matrix, int cur_height) {//костыль для List
        for (int x = 0; x < width; x++){
            filterBlock[x].setColorAndShape(matrix[x][cur_height]);
        }
        this.cur_height = cur_height;
    }

    public int getShape(int x){
        return filterBlock[x].getShape();
    }

    public int getShape(int x, int winHeight){//winHeight это индекс наинизшего фильтра который должен быть с галочкой
        if(filterBlock[x].isSpace()){
            return filterBlock[x].getShape();
        }
        if (cur_height < winHeight){
            return -7;//код красного крестика
        } else {
            return 7;//код зеленой галочки
        }
    }
    public int getColor(int x){
        return filterBlock[x].getColor();
    }

    public int getHeight() {
        return height;
    }

    public int getCur_height() {
        return cur_height;
    }
}
