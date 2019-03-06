package com.example.amandafails.tickettoride.app.adaptors;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amandafails.tickettoride.R;

import java.util.List;

public class GameplayRecyclerViewAdaptor extends RecyclerView.Adapter<GameplayRecyclerViewAdaptor.ViewHolder> {
    private List<String> lines;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.gameplay_list_text);
        }
    }

    public GameplayRecyclerViewAdaptor(List<String> lines) {
        this.lines = lines;
    }

    @NonNull
    @Override
    public GameplayRecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gameplay_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameplayRecyclerViewAdaptor.ViewHolder holder, int position) {
        final String currentString = lines.get(position);
        holder.mTextView.setText(currentString);
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
