package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import android.view.View;

import ThomasStuff.User;

public class LoginActivityPresenter {

    private User user;
    private View view;

    public LoginActivityPresenter(View view) {
        this.user = new User();
        this.view = view;
    }



//    public void updateFullName(String fullName){
//        user.setFullName(fullName);
//        view.updateUserInfoTextView(user.toString());
//
//    }
//
//    public void updateEmail(String email){
//        user.setEmail(email);
//        view.updateUserInfoTextView(user.toString());
//
//    }
//
    public interface View{

        //void updateUserInfoTextView(String info);
        //void showProgressBar();
        //void hideProgressBar();

    }
}
