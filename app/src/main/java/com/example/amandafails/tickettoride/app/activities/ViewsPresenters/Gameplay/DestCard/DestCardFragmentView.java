package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.DestCard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.adaptors.GameplayRecyclerViewAdaptor;

import java.util.ArrayList;
import java.util.List;

import ClientModel.*;

public class DestCardFragmentView extends Fragment {
    private ClientModel clientModel = ClientModel.getInstance();

    // Recycler View stuff
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Message> lines;

    public DestCardFragmentView() {}

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

        ClientModel model = ClientModel.getInstance();
        ArrayList<DestinationCards> cards = model.getMainPlayer().getPlayerHandDestinations().getCardList();
        List<Message> destinationMessages = new ArrayList<>(); // = clientModel.getActiveGame().getChatMessages();
        for(int i = 0; i < cards.size(); i++)
        {
            Message message = new Message(model.getMainPlayer().getColor(), cards.get(i).toString());
            destinationMessages.add(message);
        }
        lines.addAll(destinationMessages);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        return v;
    }
}