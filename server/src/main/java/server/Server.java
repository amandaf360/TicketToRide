package server;

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

import dao.DBPlugin;
import dao.PluginManager;


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

        // TODO: Choose which plugin to use based on command line args? - how to specify the plugin
        // directory, plugin jar name and plugin class name
        String pluginDir = System.getProperty("user.dir").toString() + "\\server";
        PluginManager manager = new PluginManager();
        try {
            DBPlugin dbPlugin = getDBPluginInstance(pluginDir, "i", "!");
            dbPlugin.getMessage();  // just displays what plugin was chosen ("toString" essentially)
        }
        catch (Exception e) {
            System.out.println("Unable to load desired plugin");
            return;
        }

        server.start();
        System.out.println("Server started");
    }

    private DBPlugin getDBPluginInstance(String pluginDirectory, String pluginJarName, String pluginClassName) throws Exception {
        // Get a class loader and set it up to load the jar file
        File pluginJarFile = new File(pluginDirectory, pluginJarName);
        URL pluginURL = pluginJarFile.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});

        // Load the jar file's plugin class, create and return an instance
        Class<? extends DBPlugin> dbPluginClass = (Class<DBPlugin>) loader.loadClass(pluginClassName);

        // TODO: might need to change the parameters for "getDeclaredConstructor"
        return dbPluginClass.getDeclaredConstructor(null).newInstance();
    }

    public static void main(String[] args)
    {
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