package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

public interface ILoginView {
    void on_login_clicked();
    void on_register_clicked();
    void set_login_enabled(boolean enabled);
    void set_register_enabled(boolean enabled);
    String get_login_username();
    String get_login_password();
    String get_register_username();
    String get_register_password();
    String get_confirm_password();
    void set_login_username(String username);
    void set_login_password(String password);
    void display_error_message(String error);
}
