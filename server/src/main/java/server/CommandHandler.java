package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import commands.ICommand;
import commands.LoginCommand;
import commands.RegisterCommand;
import communicationdata.BaseResponse;
import communicationdata.Request;

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
        Request request = serializer.deserializeRequest(builder.toString());

        String commandType = request.getCommandType();
        ICommand command;
        command = null;

        switch(commandType)
        {
            case "login":
                command = new LoginCommand(request.getStringAt(0), request.getStringAt(1));
                break;
            case "register":
                command = new RegisterCommand(request.getStringAt(0), request.getStringAt(1));
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
