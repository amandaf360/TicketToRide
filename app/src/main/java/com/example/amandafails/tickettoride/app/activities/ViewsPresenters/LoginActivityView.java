package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amandafails.tickettoride.R;

import java.util.Objects;

import ThomasStuff.ClientModel;

public class LoginActivityView extends AppCompatActivity implements ILoginView {
    private LoginActivityPresenter presenter;

    private EditText loginUsernameEdit;
    private EditText loginPasswordEdit;
    private EditText registerUsernameEdit;
    private EditText registerPasswordEdit;
    private EditText confirmPasswordEdit;

    private Button loginButton;
    private Button registerButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginActivityPresenter(this);

        loginUsernameEdit = findViewById(R.id.login_username_edit_text);
        loginPasswordEdit = findViewById(R.id.login_password_edit_text);
        registerUsernameEdit = findViewById(R.id.register_username_edit_text);
        registerPasswordEdit = findViewById(R.id.register_password_edit_text);
        confirmPasswordEdit = findViewById(R.id.register_confirm_edit_text);

        loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClicked();
            }
        });
        registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterClicked();
            }
        });

        loginButton.setEnabled(false);
        registerButton.setEnabled(false);

        // add text changed listener to login username
        loginUsernameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setLoginEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onLoginUsernameChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // add text changed listener to login password
        loginPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setLoginEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onLoginPasswordChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // add text changed listener to register username
        registerUsernameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setLoginEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onRegisterUsernameChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // add text changed listener to register password
        registerPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setLoginEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onRegisterPasswordChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // add text changed listener to confirm password
        confirmPasswordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                setLoginEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onRegisterConfirmChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onLoginClicked() {
        // call login in presenter
        presenter.login();
    }

    @Override
    public void onRegisterClicked() {
        // call register in presenter
        presenter.register();
    }

    @Override
    public void setLoginEnabled(boolean enabled) {
        loginButton.setEnabled(enabled);
    }

    @Override
    public void setRegisterEnabled(boolean enabled) {
        registerButton.setEnabled(enabled);
    }

    @Override
    public String getLoginUsername() {
        return loginUsernameEdit.getText().toString();
    }

    @Override
    public String getLoginPassword() {
        return loginPasswordEdit.getText().toString();
    }

    @Override
    public String getRegisterUsername() {
        return registerUsernameEdit.getText().toString();
    }

    @Override
    public String getRegisterPassword() {
        return registerPasswordEdit.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return confirmPasswordEdit.getText().toString();
    }

    @Override
    public void switchActivity() {
        Intent i = new Intent(LoginActivityView.this, GamesRoomView.class);
        startActivity(i);
    }

    @Override
    public void displayErrorMessage(String error) {
        // give a toast displaying error message
        Context context = Objects.requireNonNull(this).getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, error, duration);
        toast.show();
    }



}
