package com.b12.game.adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.b12.game.R;
import com.b12.game.getset.FirstGameItem;

import java.util.ArrayList;
import java.util.Collections;

public class FirstGameItemCountAdapter extends RecyclerView.Adapter<FirstGameItemCountAdapter.FirstGameItemCountViewHolder> {

    ArrayList<FirstGameItem> list;
    OnAnswerClickListener onAnswerClickListener;

    public FirstGameItemCountAdapter(ArrayList<FirstGameItem> list, OnAnswerClickListener onAnswerClickListener) {
        this.list = list;
        this.onAnswerClickListener = onAnswerClickListener;
    }

    @NonNull
    @Override
    public FirstGameItemCountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_game_item_count, parent, false);
        return new FirstGameItemCountViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull FirstGameItemCountViewHolder holder, int position) {
        Collections.shuffle(list);
        FirstGameItem item = list.get(position);
        holder.textView.setText(Integer.toString(item.getImageCount()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerClickListener.onAnswerClicked(item.getImageCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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

    public interface OnAnswerClickListener {
        void onAnswerClicked(int answer);
    }
}
