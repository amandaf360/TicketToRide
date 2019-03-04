package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Login;

public interface ILoginView {
    void onLoginClicked();
    void onRegisterClicked();
    void setLoginEnabled(boolean enabled);
    void setRegisterEnabled(boolean enabled);
    String getLoginUsername();
    String getLoginPassword();
    String getRegisterUsername();
    String getRegisterPassword();
    String getConfirmPassword();
    void switchActivity();
    void displayErrorMessage(String error);
}
