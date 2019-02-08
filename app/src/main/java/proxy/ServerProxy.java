package proxy;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import requests.*;
import commands.*;
import responses.*;


public class ServerProxy extends AsyncTask<RequestWrapper, Void, String>
{
    private OnTaskCompleted callerClass;
    private Serializer serializer;

    public void login(String username, String password)
    {
        LoginRequest request = new LoginRequest(username, password);
        RequestWrapper wrapper = new RequestWrapper("login", request);
        callerClass = new OnTaskCompleted()
        {
            @Override
            public void completeTask(String responseJson)
            {
                LoginResponse response = serializer.deserializeLoginResponse(responseJson);
                LoginCommand command = new LoginCommand(response.getUsername(), response.getErrorMessage());
                command.execute();
            }
        };
        execute(wrapper);
    }

    public void register(String username, String password)
    {
        RegisterRequest request = new RegisterRequest(username, password);
        RequestWrapper wrapper = new RequestWrapper("register", request);
        callerClass = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson)
            {
                RegisterResponse response = serializer.deserializeRegisterResponse(responseJson);
                RegisterCommand command = new RegisterCommand(response.getUsername(), response.getErrorMessage());
                command.execute();
            }
        };
        execute(wrapper);
    }

    public void poll()
    {
        RequestWrapper wrapper = new RequestWrapper("poll", null);
        callerClass = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson)
            {

            }
        };
    }

    public void join_game(int gameNumber)
    {

    }

    public void create_game(String username, String gameName)
    {

    }

    public void leave_game(String username, String gameName)
    {

    }

    public void begin_game(String gameName)
    {

    }

    public ServerProxy()
    {
        serializer = new Serializer();
    }
    @Override
    protected String doInBackground(RequestWrapper... requests)
    {
        RequestWrapper theRequest = requests[0];
        try
        {
            Serializer serializer = new Serializer();
            URL myUrl = new URL("http://10.24.216.101:3000");//CHANGE IP ADDRESS HERE
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            String requestString = serializer.serializeRequest(requests[0]);
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(requestString);
            osw.flush();
            osw.close();
            os.close();

            connection.connect();

            StringBuilder resultBuilder = new StringBuilder();
            InputStreamReader is = new InputStreamReader(connection.getInputStream());

            int currentChar;
            while ((currentChar = is.read()) != -1)
            {
                resultBuilder.append((char) currentChar);
            }
            return resultBuilder.toString();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s)
    {
        callerClass.completeTask(s);
    }
}