package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.Chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.adaptors.GameplayRecyclerViewAdaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.*;
import services.CreateChatMessageService;

public class ChatFragmentView extends Fragment implements IChatFragmentView {

    private IChatFragmentPresenter presenter;

    // Fragment elements
    private Button sendButton;
    private EditText chatEditText;

    // Recycler View stuff
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Message> lines;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        presenter = new ChatFragmentPresenter(this);

        sendButton = v.findViewById(R.id.chat_send_button);
        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onSendButtonClicked();
            }
        });

        chatEditText = v.findViewById(R.id.chat_edit_text);
        // add text changed listener to login username
        chatEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                sendButton.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (chatEditText.getText().toString().length() != 0) {
                    sendButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        lines = new ArrayList<>();
        mAdapter = new GameplayRecyclerViewAdaptor(lines);

        mRecyclerView = v.findViewById(R.id.gameplay_recycler_view);

        // display the chat already in the game
        // assuming each chat message has a string and a player -- to get the color

        List<Message> chatMessages = presenter.getChatMessages();
        lines.addAll(chatMessages);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void updateChatMessages() {
        List<Message> chatMessages = presenter.getChatMessages();
        lines.clear();
        lines.addAll(chatMessages);

        // display in recyclerview
        mAdapter = new GameplayRecyclerViewAdaptor(lines);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSendButtonClicked() {
        presenter.sendMessage();
    }

    @Override
    public String getChatMessage() {
        return chatEditText.getText().toString();
    }

    @Override
    public void clearChatMessage() {
        chatEditText.getText().clear();
    }

    @Override
    public void onResume() {
        updateChatMessages();
        super.onResume();
    }
}