package com.example.filtergame.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filtergame.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pojo.Filter;
import pojo.FilterBlock;

public class FilterBlock4Adapter extends RecyclerView.Adapter<FilterBlock4Adapter.FilterBlock4ViewHolder>{

    private List<FilterBlock> filterBlockList = new ArrayList<>();
    public void setItems(Collection<FilterBlock> filterBlocks){
        filterBlockList.addAll(filterBlocks);
        notifyDataSetChanged();
    }
    public void clearItems(){
        filterBlockList.clear();
        notifyDataSetChanged();
    }

    @Override
    public FilterBlock4ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_matrix_item, parent, false);
        return new FilterBlock4ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilterBlock4ViewHolder holder, int position) {
        holder.bind(filterBlockList.get(position));
    }

    @Override
    public int getItemCount() {
        return filterBlockList.size();
    }

    class FilterBlock4ViewHolder extends RecyclerView.ViewHolder{
        private boolean checking;//флаг для понимания, нажали ли мы кнопку проверки
        private int[] winHeight;//высота начиная с которой все фильтры правильно стоят
        private ImageView[] boxImg = new ImageView[4];
        private ImageView[] shapeImg = new ImageView[4];
        private ImageView[] colorImg = new ImageView[4];

        public void startChecking(boolean b, int[] winHeight){
            checking = b;
            for (int x = 0; x < 4; x++) {
                this.winHeight[x] = winHeight[x];///возможно тут ошибка
            }
        }
        public void bind(FilterBlock filterBlock){
            for (int x = 0; x < 4; x++){
                if (filterBlock.getColor(x) == 4 && filterBlock.getShape(x) == 4){
                    boxImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                    colorImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                    shapeImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                } else {
                    if (checking){
                        switch (filterBlock.getShape(x, winHeight[x])){
                            case 7:
                                if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                    shapeImg[x].setImageResource(R.drawable.right_top);
                                    colorImg[x].setImageResource(R.drawable.blank);
                                    boxImg[x].setImageResource(R.drawable.filter_top);
                                } else {
                                    shapeImg[x].setImageResource(R.drawable.right_bot);
                                    colorImg[x].setImageResource(R.drawable.blank);
                                    boxImg[x].setImageResource(R.drawable.filter_bot);
                                }
                                break;
                            case -7:
                                if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                    shapeImg[x].setImageResource(R.drawable.wrong_top);
                                    colorImg[x].setImageResource(R.drawable.blank);
                                    boxImg[x].setImageResource(R.drawable.filter_top);
                                } else {
                                    shapeImg[x].setImageResource(R.drawable.wrong_bot);
                                    colorImg[x].setImageResource(R.drawable.blank);
                                    boxImg[x].setImageResource(R.drawable.filter_bot);
                                }
                                break;
                            default:
                                boxImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                                colorImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                                shapeImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                                break;
                        }

                    } else {
                        switch (filterBlock.getShape(x)){
                            case 0:
                                if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                    shapeImg[x].setImageResource(R.drawable.steam_top);
                                } else {
                                    shapeImg[x].setImageResource(R.drawable.steam_bot);
                                }
                                break;
                            case 1:
                                if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                    shapeImg[x].setImageResource(R.drawable.fluid_top);
                                } else {
                                    shapeImg[x].setImageResource(R.drawable.fluid_bot);
                                }
                                break;
                            case 2:
                                if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                    shapeImg[x].setImageResource(R.drawable.ice_top);
                                } else {
                                    shapeImg[x].setImageResource(R.drawable.ice_bot);
                                }
                                break;
                            case 3:
                                if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                    shapeImg[x].setImageResource(R.drawable.parts_top);
                                } else {
                                    shapeImg[x].setImageResource(R.drawable.parts_bot);
                                }
                                break;
                            default:
                                boxImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                                colorImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                                shapeImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                                break;
                        }
                    }
                    switch (filterBlock.getColor(x)){
                        case 0:
                            if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                colorImg[x].setImageResource(R.drawable.blue_top);
                            } else {
                                colorImg[x].setImageResource(R.drawable.blue_bot);
                            }
                            break;
                        case 1:
                            if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                colorImg[x].setImageResource(R.drawable.red_top);
                            } else {
                                colorImg[x].setImageResource(R.drawable.red_bot);
                            }
                            break;
                        case 2:
                            if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                colorImg[x].setImageResource(R.drawable.green_top);
                            } else {
                                colorImg[x].setImageResource(R.drawable.green_bot);
                            }
                            break;
                        case 3:
                            if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                                colorImg[x].setImageResource(R.drawable.yellow_top);
                            } else {
                                colorImg[x].setImageResource(R.drawable.yellow_bot);
                            }
                            break;
                        default:
                            boxImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                            colorImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                            shapeImg[x].setImageResource(R.drawable.blank);//очень не уверен что альфа
                            break;
                    }
                    if (filterBlock.getCur_height() == filterBlock.getHeight() - 1){
                        boxImg[x].setImageResource(R.drawable.filter_top);
                    } else {
                        boxImg[x].setImageResource(R.drawable.filter_bot);
                    }
                }
            }
        }

        public FilterBlock4ViewHolder(View itemView) {
            super(itemView);
            winHeight = new int[4];
            checking = false;//балуюсь, должно быть фолс
            for (int i = 0; i < 4; i++) {
                winHeight[i] = 0;
            }
            boxImg[0] = itemView.findViewById(R.id.boxImageView4_0);
            boxImg[1] = itemView.findViewById(R.id.boxImageView4_1);
            boxImg[2] = itemView.findViewById(R.id.boxImageView4_2);
            boxImg[3] = itemView.findViewById(R.id.boxImageView4_3);

            shapeImg[0] = itemView.findViewById(R.id.shapeImageView4_0);
            shapeImg[1] = itemView.findViewById(R.id.shapeImageView4_1);
            shapeImg[2] = itemView.findViewById(R.id.shapeImageView4_2);
            shapeImg[3] = itemView.findViewById(R.id.shapeImageView4_3);

            colorImg[0] = itemView.findViewById(R.id.colorImageView4_0);
            colorImg[1] = itemView.findViewById(R.id.colorImageView4_1);
            colorImg[2] = itemView.findViewById(R.id.colorImageView4_2);
            colorImg[3] = itemView.findViewById(R.id.colorImageView4_3);
        }
    }
}
