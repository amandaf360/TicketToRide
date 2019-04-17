package server;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

import PluginManager.PluginManager;
import servermodel.ModelRoot;
import services.ClaimRouteService;


public class Server
{

    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;
    private String persistanceType;
    private int numCommandsBetweenCheckpoints;

    // default constructor
    private Server() {}

    // specify which database type and number commands between checkpoints desired
    private Server(String pType, int numCommands) {
        persistanceType = pType;
        numCommandsBetweenCheckpoints = numCommands;
    }

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

        if(persistanceType != null)
        {
            PluginManager manager = new PluginManager();
            try
            {
                ModelRoot.getModel().setDataBase(manager.loadPluginsThomasStyle(persistanceType));
                ModelRoot.getModel().setGameUpdateLimit(numCommandsBetweenCheckpoints);
            } catch (Exception e)
            {
                System.out.println("Unable to load plugin. Come on guys.\n");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Things are going to break pretty soon because you haven't " +
                    "provided a plugin or a database");
        }


        //We have to load the server here.
        ModelRoot.getModel().loadState();

        server.start();
        System.out.println("Server started");
    }

    public static void main(String[] args) {
        System.out.println("num args: " + args.length);
        // to get current working directory
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        if(args.length == 0)
        {
            new Server().run("3000");
        }
        else if(args.length == 2) {
            if(args[0].equals("sqlite") || args[0].equals("mongo")) {
                try {
                    Integer.parseInt(args[1]);
                    new Server(args[0], Integer.parseInt(args[1])).run("3000");
                } catch(Exception e) {
                    System.out.println("Invalid arguments given");
                }
            }
            else {
                System.out.println("Invalid arguments given");
            }
        }
        else if(args.length == 3) {
            if(args[1].equals("sqlite") || args[1].equals("mongo")) {
                try {
                    Integer.parseInt(args[2]);
                    new Server(args[1], Integer.parseInt(args[2])).run(args[0]);
                } catch(Exception e) {
                    System.out.println("Invalid arguments given");
                }
            }
            else {
                System.out.println("Invalid arguments given");
            }
        }

    }
}