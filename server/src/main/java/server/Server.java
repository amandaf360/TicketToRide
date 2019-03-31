package server;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class Server
{

    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    private void run(String portNumber) {

        try {

            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        server.createContext("/", new CommandHandler());

        server.start();
        System.out.println("Server started");
    }

    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            new Server().run("3000");
        }
        else {
            new Server().run(args[0]);
        }

    }
}
