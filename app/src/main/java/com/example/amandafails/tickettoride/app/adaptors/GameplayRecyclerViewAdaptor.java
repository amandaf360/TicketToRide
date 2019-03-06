package com.example.amandafails.tickettoride.app.adaptors;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amandafails.tickettoride.R;

import java.util.List;

public class GameplayRecyclerViewAdaptor extends RecyclerView.Adapter<GameplayRecyclerViewAdaptor.ViewHolder> {
    private List<String> lines; // make lines a list of Message objects?? -- to hold the color too?

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String currentString = lines.get(position).toString();

        // *** FIX ME!!! (depending on player color) *** //
        holder.mTextView.setBackgroundColor(Color.parseColor("#FF4081"));
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
