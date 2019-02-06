package com.example.amandafails.tickettoride.app.activities.ViewsPresenters;

import android.content.Context;
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

//        TextWatcher loginWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                loginButton.setEnabled(false);
//                registerButton.setEnabled(false);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (loginUsernameEdit.getText().toString().length() != 0 && loginPasswordEdit.getText().toString().length() != 0) {
//                    loginButton.setEnabled(true);
//                }
//                else {
//                    loginButton.setEnabled(false);
//                }
//            }
//        };

//        userName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                presenter.updateFullName(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                hideProgressBar();
//            }
//        });
//
//        email.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                presenter.updateEmail(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                hideProgressBar();
//            }
//        });

    }

    @Override
    public void onLoginClicked() {
        /*login.setEnabled(false);
        returnLoginResult();*/
        // for now, just show a toast
        Context context = Objects.requireNonNull(this).getApplicationContext();
        CharSequence text = "Login Pressed!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // call login in presenter
        presenter.login();
    }

    @Override
    public void onRegisterClicked() {
        // for now, just show a toast
        Context context = Objects.requireNonNull(this).getApplicationContext();
        CharSequence text = "Register Pressed!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

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

    // DO WE NEED THESE??
//    @Override
//    public void setLoginUsername(String username) {
//        loginPasswordEdit.setText("username");
//    }
//
//    @Override
//    public void setLoginPassword(String password) {
//
//    }

    @Override
    public void displayErrorMessage(String error) {
        // give a toast displaying error message
        Context context = Objects.requireNonNull(this).getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, error, duration);
        toast.show();
    }


//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_login, container, false);
//
//        hostEdit = v.findViewById(R.id.server_host_edit_text);
//        portEdit = v.findViewById(R.id.server_port_edit_text);
//        usernameEdit = v.findViewById(R.id.username_edit_text);
//        passwordEdit = v.findViewById(R.id.password_edit_text);
//        firstnameEdit = v.findViewById(R.id.firstname_edit_text);
//        lastnameEdit = v.findViewById(R.id.lastname_edit_text);
//        emailEdit = v.findViewById(R.id.email_edit_text);
//
//        male = v.findViewById(R.id.male_button);
//        female = v.findViewById(R.id.female_button);
//
//        RadioGroup genderButtons = v.findViewById(R.id.radio_group);
//
//        login = v.findViewById(R.id.button_login);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onLoginClicked();
//            }
//        });
//
//        register = v.findViewById(R.id.button_register);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onRegisterClicked();
//            }
//        });
//
//        login.setEnabled(false);
//        register.setEnabled(false);
//
//        TextWatcher watcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                login.setEnabled(false);
//                register.setEnabled(false);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (hostEdit.getText().toString().length() != 0 && portEdit.getText().toString().length() != 0 &&
//                        usernameEdit.getText().toString().length() != 0 && passwordEdit.getText().toString().length() != 0 &&
//                        firstnameEdit.getText().toString().length() != 0 && lastnameEdit.getText().toString().length() != 0 &&
//                        emailEdit.getText().toString().length() != 0 && (male.isChecked() || female.isChecked())) {
//                    login.setEnabled(true);
//                    register.setEnabled(true);
//                }
//                else if(hostEdit.getText().toString().length() != 0 && portEdit.getText().toString().length() != 0 &&
//                        usernameEdit.getText().toString().length() != 0 && passwordEdit.getText().toString().length() != 0) {
//                    login.setEnabled(true);
//                }
//                else {
//                    login.setEnabled(false);
//                    register.setEnabled(false);
//                }
//            }
//        };
//
//        hostEdit.addTextChangedListener(watcher);
//        portEdit.addTextChangedListener(watcher);
//        usernameEdit.addTextChangedListener(watcher);
//        passwordEdit.addTextChangedListener(watcher);
//        firstnameEdit.addTextChangedListener(watcher);
//        lastnameEdit.addTextChangedListener(watcher);
//        emailEdit.addTextChangedListener(watcher);
//
//        //Listener -- setOnCheckedChangeListener???
//        RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if (hostEdit.getText().toString().length() != 0 && portEdit.getText().toString().length() != 0 &&
//                        usernameEdit.getText().toString().length() != 0 && passwordEdit.getText().toString().length() != 0 &&
//                        firstnameEdit.getText().toString().length() != 0 && lastnameEdit.getText().toString().length() != 0 &&
//                        emailEdit.getText().toString().length() != 0 && (male.isChecked() || female.isChecked())) {
//                    login.setEnabled(true);
//                    register.setEnabled(true);
//                }
//                else if(hostEdit.getText().toString().length() != 0 && portEdit.getText().toString().length() != 0 &&
//                        usernameEdit.getText().toString().length() != 0 && passwordEdit.getText().toString().length() != 0) {
//                    login.setEnabled(true);
//                }
//                else {
//                    login.setEnabled(false);
//                    register.setEnabled(false);
//                }
//            }
//        };
//        genderButtons.setOnCheckedChangeListener(listener);
//
//
//        return v;
//    }
//
//    public void onSuccess() {
//        login.setEnabled(true);
//        register.setEnabled(true);
//        String userPersonID = Model.get().getUserPersonIDLoggedIn();
//        String authToken = Model.get().getAuthToken();
//        PersonRequest personRequest = new PersonRequest(userPersonID, authToken);
//        EventRequest eventRequest = new EventRequest(authToken);
//
//        Model.get().setHost(hostEdit.getText().toString());
//        Model.get().setPort(portEdit.getText().toString());
//
//        GetEventsTask eventsTask = new GetEventsTask();
//        eventsTask.setCallback(this);
//        eventsTask.setHost(hostEdit.getText().toString());
//        eventsTask.setPort(portEdit.getText().toString());
//        eventsTask.execute(eventRequest);
//
//        GetPeopleTask task = new GetPeopleTask();
//        task.setCallback(this);
//        task.setHost(hostEdit.getText().toString());
//        task.setPort(portEdit.getText().toString());
//        task.execute(personRequest);
//    }
//
//    public void onLoginFailed() {
//        //can display the failed toast right away
//        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
//        CharSequence text = "Login Failed!";
//        int duration = Toast.LENGTH_LONG;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//    }
//
//    public void onRegisterFailed() {
//        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
//        CharSequence text = "Register Failed!";
//        int duration = Toast.LENGTH_LONG;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//    }
//
//    public void personFailed() {
//        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
//        CharSequence text = "Failed to get your name";
//        int duration = Toast.LENGTH_LONG;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//    }
//
//    public void switchFragments() {
//        ((LoginActivity) Objects.requireNonNull(getActivity())).switchFragments();
//    }
//
//    private void onLoginClicked() {
//        login.setEnabled(false);
//        returnLoginResult();
//    }
//
//    public LoginRequest getLoginRequest() {
//        String username = usernameEdit.getText().toString();
//        String password = passwordEdit.getText().toString();
//
//        return new LoginRequest(username, password);
//    }
//
//    private void returnLoginResult() {
//        LoginRequest request = getLoginRequest();
//
//        LoginTask task = new LoginTask();
//        task.setCallback(this);
//        task.setHost(hostEdit.getText().toString());
//        task.setPort(portEdit.getText().toString());
//        task.execute(request);
//    }
//
//    private void onRegisterClicked() {
//        register.setEnabled(false);
//        returnRegisterResult();
//    }
//
//    public RegisterRequest getRegisterRequest() {
//        String username = usernameEdit.getText().toString();
//        String password = passwordEdit.getText().toString();
//        String firstname = firstnameEdit.getText().toString();
//        String lastname = lastnameEdit.getText().toString();
//        String email = emailEdit.getText().toString();
//        char gender;
//        if(male.isChecked()) {
//            gender = 'm';
//        }
//        else if(female.isChecked()) {
//            gender = 'f';
//        }
//        else {
//            gender = ' ';
//        }
//
//        return new RegisterRequest(username, password, email, firstname, lastname, gender);
//    }
//
//    private void returnRegisterResult() {
//        RegisterRequest registerRequest = getRegisterRequest();
//
//        RegisterTask task = new RegisterTask();
//        task.setCallback(this);
//        task.setHost(hostEdit.getText().toString());
//        task.setPort(portEdit.getText().toString());
//        task.execute(registerRequest);
//
//    }

}
