package com.b12.game.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.b12.game.R;

import java.util.HashMap;

public class FirstGameItemCountAdapter extends RecyclerView.Adapter<FirstGameItemCountAdapter.FirstGameItemCountViewHolder> {

    HashMap<Integer, Integer> hashMap;

    public FirstGameItemCountAdapter(HashMap<Integer, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @NonNull
    @Override
    public FirstGameItemCountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_game_item_count, parent, false);
        return new FirstGameItemCountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstGameItemCountViewHolder holder, int position) {
        holder.textView.setText(hashMap.get(position));
    }

    @Override
    public int getItemCount() {
        return hashMap.size();
    }

    public class FirstGameItemCountViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;

        public FirstGameItemCountViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.first_game_answer_card);
            textView = itemView.findViewById(R.id.first_game_answer_txt);
        }
    }
}
