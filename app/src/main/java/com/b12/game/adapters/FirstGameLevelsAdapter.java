package com.b12.game.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.b12.game.R;
import com.b12.game.getset.Level;

import java.util.ArrayList;

public class FirstGameLevelsAdapter extends RecyclerView.Adapter<FirstGameLevelsAdapter.FirstGameLevelsViewHolder> {
    ArrayList<Level> list;
    Context context;

    public FirstGameLevelsAdapter(ArrayList<Level> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FirstGameLevelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.levels_item, parent, false);
        return new FirstGameLevelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstGameLevelsViewHolder holder, int position) {
        Level level = list.get(position);
        holder.textView.setText(level.getLevelNumber());
        holder.imageView.setImageResource(level.getLevelStars());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FirstGameLevelsViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;
        LinearLayout layout;
        public FirstGameLevelsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.levels_card);
            imageView = itemView.findViewById(R.id.levels_stars);
            textView = itemView.findViewById(R.id.levels_number);
            layout = itemView.findViewById(R.id.levels_linear);

        }
    }
}
