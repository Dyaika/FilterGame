package com.example.filtergame.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filtergame.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pojo.ServerLevel;

public class ServerLevelAdapter extends RecyclerView.Adapter<ServerLevelAdapter.ServerLevelViewHolder> {

    private List<ServerLevel> serverLevelList = new ArrayList<>();
    class ServerLevelViewHolder extends RecyclerView.ViewHolder{
        private TextView user_name_h_tv;
        private TextView height_h_tv;
        private TextView views_h_tv;
        private ImageView play_img_iv;
        private TextView user_name_tv;
        private TextView date_tv;
        private TextView height_tv;
        private TextView views_tv;

        public ServerLevelViewHolder(View itemView){
            super(itemView);
            user_name_h_tv = itemView.findViewById(R.id.userTextView);
            user_name_tv = itemView.findViewById(R.id.nameTextView);
            height_h_tv = itemView.findViewById(R.id.heightTextView);
            height_tv = itemView.findViewById(R.id.lengthTextView);
            views_h_tv = itemView.findViewById(R.id.viewsTextView);
            views_tv = itemView.findViewById(R.id.countTextView);
            date_tv = itemView.findViewById(R.id.dateTextView);
            play_img_iv = itemView.findViewById(R.id.playImageView);

        }

        public void bind(ServerLevel serverLevel){
            user_name_tv.setText(serverLevel.getUserName());
            height_tv.setText(String.valueOf(serverLevel.getHeight()));
            views_tv.setText(String.valueOf(serverLevel.getViews()));
            date_tv.setText(serverLevel.getDate());
        }
    }
    public void setItems(Collection<ServerLevel> serverLevels){
        serverLevelList.addAll(serverLevels);
        notifyDataSetChanged();
    }

    public void clearItems(){
        serverLevelList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ServerLevelViewHolder holder, int position) {
        holder.bind(serverLevelList.get(position));
    }

    @Override
    public ServerLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.server_level_item_view, parent, false);
        return new ServerLevelViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return serverLevelList.size();
    }
}
