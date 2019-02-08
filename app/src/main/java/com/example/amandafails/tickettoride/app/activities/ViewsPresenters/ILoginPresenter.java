package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

public interface ILoginPresenter {
    void login();
    void register();
    void onLoginUsernameChanged();
    void onLoginPasswordChanged();
    void onRegisterUsernameChanged();
    void onRegisterPasswordChanged();
    void onRegisterConfirmChanged();
}
