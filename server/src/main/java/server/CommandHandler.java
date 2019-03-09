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

        if(!commandType.equals("poll"))
        {
            int i = 0;
        }

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
                command = new PollCommand(username, false);
                break;
            case "firstPoll":
                String name = wrappedRequest.getStringList().get(0);
                command = new PollCommand(name, true);
                break;
            case "startGame":
                int gameNum = Integer.parseInt(wrappedRequest.getStringList().get(0));
                command = new StartGameCommand(gameNum);
                break;
            case "createGame":
                ArrayList<String> createList = wrappedRequest.getStringList();
                command = new CreateGameCommand(createList.get(0), Integer.parseInt(createList.get(1)), createList.get(2));
                break;
            case "clearPoll":
                ArrayList<String> clearPollList = wrappedRequest.getStringList();
                command = new ClearPollCommand(clearPollList.get(0));
                break;
            case "joinGame":
                ArrayList<String> joinGameList = wrappedRequest.getStringList();
                command = new JoinGameCommand(Integer.parseInt(joinGameList.get(0)), joinGameList.get(1));
                break;
            case "sendChatMessage":
                ArrayList<String> messageData = wrappedRequest.getStringList();
                command = new SendMessageCommand(messageData.get(0), messageData.get(1), messageData.get(2), Integer.parseInt(messageData.get(3)));
                break;
            case "drawDestCards":
                ArrayList<String> drawList = wrappedRequest.getStringList();
                command = new DrawDestCommand(Integer.parseInt(drawList.get(0)), drawList.get(1));
                break;
            case "discardDestCard":
                ArrayList<String> discardDestList = wrappedRequest.getStringList();
                command = new DiscardDestCommand(discardDestList.get(0), discardDestList.get(1), Integer.parseInt(discardDestList.get(2)),
                        discardDestList.get(3));
                break;
            case "drawTrainCarCard":
                ArrayList<String> drawTrainList = wrappedRequest.getStringList();
                command = new DrawTrainCommand(drawTrainList.get(0), Integer.parseInt(drawTrainList.get(1)));
                break;

        }

        BaseResponse response = command.execute();
        String responseString = serializer.serializeResponse(response);
        if(commandType.equals("poll"))
        {
            ClientCommandManager.getCommandManager().pollClear(wrappedRequest.getStringList().get(0));
        }
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
