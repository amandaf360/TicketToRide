package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.adaptors.GameplayRecyclerViewAdaptor;

import java.util.ArrayList;
import java.util.List;

import ClientModel.*;

public class GameHistoryFragment extends Fragment {

    private ClientModel clientModel = ClientModel.getInstance();

    // Recycler View stuff
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Message> lines;

    public GameHistoryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dest_and_history, container, false);

        lines = new ArrayList<>();
        mAdapter = new GameplayRecyclerViewAdaptor(lines);

        mRecyclerView = v.findViewById(R.id.gameplay_recycler_view);

        // display the chat already in the game
        // assuming each chat message has a string and a player -- to get the color

        // somehow get the dest. cards for each player?
        //List<destCardMessage> destCardMessages = clientModel.getActiveGame().getChatMessages();
        List<Message> chatMessages = new ArrayList<>(); // = clientModel.getActiveGame().getChatMessages();
        chatMessages.add(new Message("blue", "Hello. This is the first game history message"));
        chatMessages.add(new Message("yellow", "Hi. This is the second games history message"));
        lines.addAll(chatMessages);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        return v;
    }
}
