package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameHistory;

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

public class GameHistoryFragmentView extends Fragment implements IGameHistoryFragmentView {

    private IGameHistoryFragmentPresenter presenter;

    // Recycler View stuff
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Message> lines;

    public GameHistoryFragmentView() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dest_and_history, container, false);

        presenter = new GameHistoryFragmentPresenter(this);

        lines = new ArrayList<>();
        mAdapter = new GameplayRecyclerViewAdaptor(lines);

        mRecyclerView = v.findViewById(R.id.gameplay_recycler_view);

        List<Message> gameHistoryMessages = presenter.getGameHistory();
        //gameHistoryMessages.add(new Message("blue", "Hello. This is the first game history message"));
        //gameHistoryMessages.add(new Message("yellow", "Hi. This is the second games history message"));
        lines.addAll(gameHistoryMessages);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void updateGameHistory() {
        List<Message> gameHistoryMessages = presenter.getGameHistory();
        lines.clear();
        lines.addAll(gameHistoryMessages);

        // display in recyclerview
        mAdapter = new GameplayRecyclerViewAdaptor(lines);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }
}
