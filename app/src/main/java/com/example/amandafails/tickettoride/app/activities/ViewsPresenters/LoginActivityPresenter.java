package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import ChrisStuff.LoginService;
import ChrisStuff.RegisterService;

public class LoginActivityPresenter implements ILoginPresenter {

//    private User user;
//    private View view;
//
//    public LoginActivityPresenter(View v) {
//        this.user = new User();
//        this.view = v;
//    }

    //private User user;
    private ILoginView view;

    public LoginActivityPresenter(ILoginView view) {
        this.view = view;
    }

    @Override
    public void login() {
        // disable the login button
        view.setLoginEnabled(false);

        // call login service connected to model??
        LoginService loginService = new LoginService();
        // will get both username and password and attempt to login
        loginService.login(view.getLoginUsername(), view.getLoginPassword());

        // create a method called "Update" implemented from observer
        // login succeeds if the user object is not null
            // should change screens
        // login fails if error message is updated
            // call displayErrorMessage("Login failed");

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

        // call register service connected to model??
        RegisterService registerService = new RegisterService();
        // will make sure both passwords are the same
        registerService.register(view.getRegisterUsername(), view.getRegisterPassword());

        // if register failed, call displayErrorMessage("Register failed");
        // HOW TO SEE IF IT FAILS????
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
