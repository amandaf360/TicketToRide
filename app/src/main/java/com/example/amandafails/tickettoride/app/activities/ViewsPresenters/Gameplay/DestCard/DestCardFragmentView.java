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

public class DestCardFragmentView extends Fragment implements IDestCardFragmentView {

    IDestCardFragmentPresenter presenter;

    // Recycler View stuff
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Message> lines;

    public DestCardFragmentView() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dest_and_history, container, false);

        presenter = new DestCardFragmentPresenter(this);

        lines = new ArrayList<>();
        mAdapter = new GameplayRecyclerViewAdaptor(lines);

        mRecyclerView = v.findViewById(R.id.gameplay_recycler_view);

        lines.addAll(presenter.getListDestCardMessages());
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void updateDestCardMessages() {
        lines.clear();
        lines.addAll(presenter.getListDestCardMessages());

        // display in recyclerview
        mAdapter = new GameplayRecyclerViewAdaptor(lines);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }
}
