package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import commands.*;
import responses.BaseResponse;
import requests.*;
import responses.*;

public class CommandHandler implements HttpHandler
{

    @Override
    public void handle(HttpExchange exchange)
    {
        StringBuilder builder = new StringBuilder();

        InputStreamReader reader = new InputStreamReader(exchange.getRequestBody());
        try
        {
            int currentChar;
            while((currentChar = reader.read()) != -1)
            {
                builder.append((char)currentChar);
            }
        }
        catch(IOException e)
        {
            System.out.println("Failed to read request");
        }

        Serializer serializer = new Serializer();
        RequestWrapper wrappedRequest = serializer.deserializeRequestWrapper(builder.toString());

        String commandType = wrappedRequest.getRequestType();
        ICommand command;
        command = null;

        switch(commandType)
        {
            case "login":
                LoginRequest loginRequest = (LoginRequest)wrappedRequest.getRequest();
                command = new LoginCommand(loginRequest.getUsername(), loginRequest.getPassword());
                break;
            case "register":
                RegisterRequest registerRequest = (RegisterRequest)wrappedRequest.getRequest();
                command = new RegisterCommand(registerRequest.getUsername(), registerRequest.getPassword());
                break;
            case "poll":
                break;
            case "startGame":
                StartGameRequest startRequest = (StartGameRequest)wrappedRequest.getRequest();
                command = new StartGameCommand();
        }

        BaseResponse response = command.execute();
        String responseString = serializer.serializeResponse(response);

        writeResponse(exchange, HttpURLConnection.HTTP_OK, responseString);
    }


    private void writeResponse(HttpExchange exchange, int statusCode, String response)
    {
        try
        {
            exchange.sendResponseHeaders(statusCode, 0);
            PrintWriter respWriter = new PrintWriter(exchange.getResponseBody());
            respWriter.write(response);
            respWriter.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
