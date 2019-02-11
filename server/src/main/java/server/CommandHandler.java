package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import commands.*;
import responses.BaseResponse;
import requests.*;
import responses.*;

public class CommandHandler implements HttpHandler
{

    @Override
    public void handle(HttpExchange exchange)
    {
        System.out.println("Connected");
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
        System.out.println("Command type: " + commandType);

        switch(commandType) {
            case "login":
                ArrayList<String> loginList = wrappedRequest.getStringList();
                command = new LoginCommand(loginList.get(0), loginList.get(1));
                break;
            case "register":
                ArrayList<String> argumentList = wrappedRequest.getStringList();
                command = new RegisterCommand(argumentList.get(0), argumentList.get(1));
                break;
            case "poll":
                String username = wrappedRequest.getStringList().get(0);
                System.out.println("received poll request from : " + username);
                command = new PollCommand(username);
                break;
            case "startGame":
                // StartGameRequest startRequest = (StartGameRequest)wrappedRequest.getRequest();
                command = new StartGameCommand();
                break;
            case "createGame":
                ArrayList<String> createList = wrappedRequest.getStringList();
                break;

        }

        System.out.println("Executing");
        BaseResponse response = command.execute();
        String responseString = serializer.serializeResponse(response);
        System.out.println("writing response");
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
