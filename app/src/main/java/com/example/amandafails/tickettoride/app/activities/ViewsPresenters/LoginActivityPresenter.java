package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import android.content.Intent;

import java.util.Observable;
import java.util.Observer;

import services.LoginService;
import services.RegisterService;
import ThomasStuff.ClientModel;
import ThomasStuff.User;

public class LoginActivityPresenter implements ILoginPresenter, Observer {

//    private User user;
//    private View view;
//
//    public LoginActivityPresenter(View v) {
//        this.user = new User();
//        this.view = v;
//    }

    private ClientModel clientModel = ClientModel.getInstance();
    private ILoginView view;

    public LoginActivityPresenter(ILoginView view) {
        this.view = view;
        this.clientModel.addObserver(this);
    }

    @Override
    public void login() {
        // disable the login button
        view.setLoginEnabled(false);

        // call login service connected to model??
        LoginService loginService = new LoginService();
        // will get both username and password and attempt to login
        loginService.login(view.getLoginUsername(), view.getLoginPassword());

        /* TO START A NEW ACTIVITY!!
        Intent i = new Intent(context, EventActivity.class);
                    i.putExtra("event", eventID);
                    context.startActivity(i);
         */
    }

    @Override
    public void register() {
        // disable the register button
        view.setRegisterEnabled(false);
        view.switchActivity();
        /*
        // call register service connected to model??
        RegisterService registerService = new RegisterService();
        // will make sure both passwords are the same
        registerService.register(view.getRegisterUsername(), view.getRegisterPassword());
        */
    }

    @Override
    public void onLoginUsernameChanged() {
        // if both the username and password are filled in, enable the login button
        if(view.getLoginUsername().length() != 0 && view.getLoginPassword().length() != 0) {
            view.setLoginEnabled(true);
        }
    }

    @Override
    public void onLoginPasswordChanged() {
        // if both the username and password are filled in, enable the login button
        if(view.getLoginUsername().length() != 0 && view.getLoginPassword().length() != 0) {
            view.setLoginEnabled(true);
        }
    }

    @Override
    public void onRegisterUsernameChanged() {
        // if the username and password are filled in, and password == confirm password,
        // then enable the register button
        if(view.getRegisterUsername().length() != 0 && view.getRegisterPassword().length() != 0 &&
                view.getConfirmPassword().length() != 0 &&
                view.getConfirmPassword().equals(view.getRegisterPassword())) {
            view.setRegisterEnabled(true);
        }
        // else if a field is empty or passwords no longer match, disable the register button
        else {
            view.setRegisterEnabled(false);
        }
    }

    @Override
    public void onRegisterPasswordChanged() {
        // if the username and password are filled in, and password == confirm password,
        // then enable the register button
        if(view.getRegisterUsername().length() != 0 && view.getRegisterPassword().length() != 0 &&
                view.getConfirmPassword().length() != 0 &&
                view.getConfirmPassword().equals(view.getRegisterPassword())) {
            view.setRegisterEnabled(true);
        }
        // else if a field is empty or passwords no longer match, disable the register button
        else {
            view.setRegisterEnabled(false);
        }
    }

    @Override
    public void onRegisterConfirmChanged() {
        // if the username and password are filled in, and password == confirm password,
        // then enable the register button
        if(view.getRegisterUsername().length() != 0 && view.getRegisterPassword().length() != 0 &&
                view.getConfirmPassword().length() != 0 &&
                view.getConfirmPassword().equals(view.getRegisterPassword())) {
            view.setRegisterEnabled(true);
        }
        // else if a field is empty or passwords no longer match, disable the register button
        else {
            view.setRegisterEnabled(false);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        // if user object is created, then login has succeeded
        // change to next screen
        if(arg.getClass() == User.class) {
            view.switchActivity();
        }
        else {
            view.displayErrorMessage(arg.toString());
            //clientModel.deleteMessage(arg.toString());
        }
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
}
