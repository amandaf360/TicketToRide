package com.example.amandafails.tickettoride.app.adaptors;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ClientModel.*;

import com.example.amandafails.tickettoride.R;

import java.util.List;

public class GameplayRecyclerViewAdaptor extends RecyclerView.Adapter<GameplayRecyclerViewAdaptor.ViewHolder> {
    private List<Message> lines;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.gameplay_list_text);
        }
    }

    public GameplayRecyclerViewAdaptor(List<Message> lines) {
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
        final Message currentMessage = lines.get(position);

        holder.mTextView.setBackgroundColor(Color.parseColor(stringToHex(currentMessage.getColor())));
        holder.mTextView.setText(currentMessage.getMessage());
        if(currentMessage.getColor() == "black") {
            holder.mTextView.setTextColor(Color.WHITE);
        }

    }

    public String stringToHex(String color) {
        String hexColor = "";
        switch (color) {
            case "blue":
                hexColor = "#0000ff";
                break;
            case "red":
                hexColor = "#ff0000";
                break;
            case "green":
                hexColor = "#008000";
                break;
            case "yellow":
                hexColor = "ffff00";
                break;
            case "black":
                hexColor = "000000";
                break;
        }


        return hexColor;
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
