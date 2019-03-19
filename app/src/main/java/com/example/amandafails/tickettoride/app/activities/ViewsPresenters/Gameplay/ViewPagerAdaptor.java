package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.amandafails.tickettoride.R;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.Chat.ChatFragmentView;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.DestCard.DestCardFragmentView;
import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameHistory.GameHistoryFragmentView;

public class ViewPagerAdaptor extends FragmentPagerAdapter {

    private Context mContext;
    private int numTabs = 3;

    public ViewPagerAdaptor(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ChatFragmentView();
        }
        else if(position == 1){
            return new GameHistoryFragmentView();
        }
        else {
            return new DestCardFragmentView();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return numTabs;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.chat);
            case 1:
                return mContext.getString(R.string.gameHistory);
            case 2:
                return mContext.getString(R.string.destCards);
            default:
                return null;
        }
    }

}