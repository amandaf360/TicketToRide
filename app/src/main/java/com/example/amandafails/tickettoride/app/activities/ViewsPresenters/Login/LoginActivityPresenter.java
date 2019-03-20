package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Login;

import java.util.Observable;
import java.util.Observer;

import services.LoginService;
import services.RegisterService;
import ClientModel.ClientModel;
import ClientModel.User;

/**
 * The LoginActivityPresenter class is used to provide the logic for the LoginActivityView class
 * Operations are provided for logging in, registering a new user, and performing necessary logic on
 * input from text fields in view.
 *
 * Domain:
 *      clientModel: ClientModel, instance of the client model
 *      view: ILoginView, Reference to the LoginView class
 *
 * @invariant clientModel = ClientModel.getInstance(), clientModel != null
 */

public class LoginActivityPresenter implements ILoginPresenter, Observer {

    private ClientModel clientModel = ClientModel.getInstance();
    private ILoginView view;

    /**
     * Initializes the presenter
     *
     * @param view, a reference to the connected LoginActivityView object
     *
     * @pre none
     *
     * @post view now references an instance of the LoginView class
     * @post presenter has been added as an observer to the client model
     */
    public LoginActivityPresenter(ILoginView view) {
        this.view = view;
        this.clientModel.addObserver(this);
    }

    /**
     * Attempts to login
     *
     * @pre view.getLoginUsername().length() > 0
     * @pre view.getLoginPassword().length() > 0
     *
     * @post Login button is disabled
     */
    @Override
    public void login() {
        // disable the login button
        view.setLoginEnabled(false);

        // call login service connected to model??
        LoginService loginService = new LoginService();
        // will get both username and password and attempt to login
        loginService.login(view.getLoginUsername(), view.getLoginPassword());
    }

    /**
     * Attempts to register
     *
     * @pre view.getRegisterUsername().length() > 0
     * @pre view.getRegisterPassword().length() > 0
     * @pre view.getRegisterPassword() == view.getRegisterConfirmPassword()
     *
     * @post Register button is disabled
     */
    @Override
    public void register() {
        // disable the register button
        view.setRegisterEnabled(false);

        // call register service connected to model??
        RegisterService registerService = new RegisterService();
        // will make sure both passwords are the same
        registerService.register(view.getRegisterUsername(), view.getRegisterPassword());

    }

    /**
     * Checks to see if the login button should be enabled or not
     *
     * @pre none
     *
     * @post button enabled if input has been given for both login username and password
     */
    @Override
    public void onLoginUsernameChanged() {
        // if both the username and password are filled in, enable the login button
        if(view.getLoginUsername().length() != 0 && view.getLoginPassword().length() != 0) {
            view.setLoginEnabled(true);
        }
    }

    /**
     * Checks to see if the login button should be enabled or not
     *
     * @pre none
     *
     * @post button enabled if input has been given for both login username and password
     */
    @Override
    public void onLoginPasswordChanged() {
        // if both the username and password are filled in, enable the login button
        if(view.getLoginUsername().length() != 0 && view.getLoginPassword().length() != 0) {
            view.setLoginEnabled(true);
        }
    }

    /**
     * Checks to see if the register button should be enabled or not
     *
     * @pre none
     *
     * @post button enabled if input has been given in register username, password, and confirm
     * password text field, and the two passwords given match
     */
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

    /**
     * Checks to see if the register button should be enabled or not
     *
     * @pre none
     *
     * @post button enabled if input has been given in register username, password, and confirm
     * password text field, and the two passwords given match
     */
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

    /**
     * Checks to see if the register button should be enabled or not
     *
     * @pre none
     *
     * @post button enabled if input has been given in register username, password, and confirm
     * password text field, and the two passwords given match
     */
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

    /**
     * Switches activities if login or register was successful
     *
     * @pre client model must update something and notify the observers
     *
     * @post activity has switched
     */
    @Override
    public void update(Observable o, Object arg) {
        // if user object is created, then login has succeeded
        // change to next screen
        if(arg.getClass() == User.class) {
            this.clientModel.deleteObserver(this);
            view.switchActivity();
        }
        else if(arg.getClass() == String.class) {
            // display the most recent error message
            view.displayErrorMessage(clientModel.getMessage());
        }
    }
}
