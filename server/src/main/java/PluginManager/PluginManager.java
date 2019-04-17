package PluginManager;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import PluginInterfaces.IPersistanceProvider;

public class PluginManager {


    public IPersistanceProvider loadPlugins(String urlName) throws Exception
    {
        String className = "Plugin";
        String test = System.getProperty("user.dir");
        URL[] classLoaderUrls = new URL[]{new URL(System.getProperty("user.dir") + "\\server\\PluginJars\\" + urlName + ".jar")};


        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

        Class<? extends IPersistanceProvider> dbPluginClass = (Class<IPersistanceProvider>) urlClassLoader.loadClass(className);


        IPersistanceProvider plugin = dbPluginClass.getDeclaredConstructor(null).newInstance();

        return plugin;

    }


    public IPersistanceProvider loadPluginsThomasStyle(String urlName) throws Exception
    {
        File pluginJarFile = new File(System.getProperty("user.dir") + "\\server\\PluginJars", urlName + ".jar");
        URL pluginURL = pluginJarFile.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});

        Class<? extends IPersistanceProvider> returnClass = (Class<IPersistanceProvider>) loader.loadClass("Plugin.Plugin");

        return returnClass.getDeclaredConstructor().newInstance();
    }


}



