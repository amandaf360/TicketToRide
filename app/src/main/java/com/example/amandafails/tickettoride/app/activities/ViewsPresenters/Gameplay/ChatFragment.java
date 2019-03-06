package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

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

import ThomasStuff.ClientModel;

public class ChatFragment extends Fragment {

    private ClientModel clientModel = ClientModel.getInstance();

    // Fragment elements
    private Button sendButton;
    private EditText chatEditText;

    // Recycler View stuff
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> lines;

    public ChatFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        sendButton = v.findViewById(R.id.chat_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                if(chatEditText.getText().toString().length() != 0) {
                    sendButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lines = new ArrayList<>();
        mAdapter = new GameplayRecyclerViewAdaptor(lines);

        mRecyclerView = v.findViewById(R.id.gameplay_recycler_view);

        // display the chat already in the game
        // assuming each chat message has a string and a player -- to get the color

        //List<ChatMessage> chatMessages = clientModel.getActiveGame().getChatMessages();
        List<String> chatMessages = new ArrayList<>();
        chatMessages.add("Hello. This is the first chat message");
        chatMessages.add("Hi, this is the second chat message");
        for(int i = 0; i < chatMessages.size(); i++) {
            lines.add(chatMessages.get(i));
        }

        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    public void onSendButtonClicked() {
        // send message to server model -- call chat service??
        // **** TO CHANGE!!! ***** //
        String message = chatEditText.getText().toString();
        lines.add(message);

        // display in recyclerview
        mAdapter = new GameplayRecyclerViewAdaptor(lines);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

        // clear the edit text field
        chatEditText.getText().clear();
    }
}
