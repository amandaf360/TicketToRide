package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

public interface ILoginPresenter {
    void login();
    void register();
    void on_login_username_changed();
    void on_login_password_changed();
    void on_register_username_changed();
    void on_register_password_changed();
    void on_register_confirm_changed();
}
