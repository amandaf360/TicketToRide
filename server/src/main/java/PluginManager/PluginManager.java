package PluginManager;

import java.net.URL;
import java.net.URLClassLoader;

import PluginInterfaces.IPersistanceProvider;

public class PluginManager {

    public IPersistanceProvider loadPlugins(String urlName) throws Exception
    {
        String className = "Plugin";
        URL[] classLoaderUrls = new URL[]{new URL(System.getProperty("user.dir") + "/server/PluginJars/" + urlName + ".jar")};


        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

        Class<? extends IPersistanceProvider> dbPluginClass = (Class<IPersistanceProvider>) urlClassLoader.loadClass(className);


        IPersistanceProvider plugin = dbPluginClass.getDeclaredConstructor(null).newInstance();

        return plugin;

    }

}



