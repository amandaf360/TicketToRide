package com.example.amandafails.tickettoride.app.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amandafails.tickettoride.R;

import java.util.List;

public class LobbyRecyclerViewAdaptor extends RecyclerView.Adapter<LobbyRecyclerViewAdaptor.ViewHolder> {

    private List<String> lines;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;
        ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.lobby_player_text);
            mImageView = v.findViewById(R.id.lobby_image);
        }
    }

    public LobbyRecyclerViewAdaptor(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public LobbyRecyclerViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lobby_player_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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