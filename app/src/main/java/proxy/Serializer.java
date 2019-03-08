package proxy;

import android.graphics.Paint;

import com.google.gson.Gson;

import requests.RequestWrapper;
import responses.*;

public class Serializer
{
    private Gson gson;

    public Serializer(){
        gson = new Gson();
    }

    public String serializeRequest(RequestWrapper request)
    {
        return gson.toJson(request);
    }

    public LoginResponse deserializeLoginResponse(String response)
    {
        return gson.fromJson(response, LoginResponse.class);
    }

    public RegisterResponse deserializeRegisterResponse(String response)
    {
        return gson.fromJson(response, RegisterResponse.class);
    }

    public CreateGameResponse deserializeCreateGameResponse(String response)
    {
        return gson.fromJson(response, CreateGameResponse.class);
    }

    public PollResponse deserializePollResponse(String response)
    {
        return gson.fromJson(response, PollResponse.class);
    }

    public JoinGameResponse deserializeJoinGameResponse(String response)
    {
        return gson.fromJson(response, JoinGameResponse.class);
    }

    public StartGameResponse deserializeStartGameResponse(String response)
    {
        return gson.fromJson(response, StartGameResponse.class);
    }

    public DrawDestResponse deserializeDrawDestResponse(String response)
    {
        return gson.fromJson(response, DrawDestResponse.class);
    }

}