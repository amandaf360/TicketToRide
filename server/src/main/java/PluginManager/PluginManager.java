package PluginManager;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import PluginInterfaces.IPersistanceProvider;

public class PluginManager {
    private List<IPersistanceProvider> plugins;

    // load ALL plugins in a directory
    public void loadPlugins() throws Exception {
        String className = "Plugin";
         //TODO: scan directory and load all plugins?
        // Getting the jar URL which contains target class
        URL[] classLoaderUrls = new URL[]{new URL(System.getProperty("user.dir") + "/server/PluginJars/*.jar")};
        for(URL u : classLoaderUrls) {

            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

            Class<? extends IPersistanceProvider> dbPluginClass = (Class<IPersistanceProvider>) urlClassLoader.loadClass(className);


            IPersistanceProvider object = dbPluginClass.getDeclaredConstructor(null).newInstance();
            plugins.add(object);
        }
    }

    // return a certain plugin
    public IPersistanceProvider selectPlugin(String jarName) throws Exception {
//        File pluginJarFile = new File(System.getProperty("user.dir") + "\\server\\PluginJars", jarName);
//        URL pluginURL = pluginJarFile.toURI().toURL();
//        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});
//
//        String className = "Plugin";
//        // Load the jar file's plugin class, create and return an instance
//
//        // TODO: might need to change the parameters for "getDeclaredConstructor"

        // FIX THIS

        for(IPersistanceProvider plugin : plugins)
        {
            if(plugin.getLabel().equals(jarName))
            {
                return plugin;
            }
        }

        System.out.println("THIS SHOULD NOT EVER PRINT OUT EVER\nYOU\'RE PROBABLY PUTTING IN THE WRONG JARNAME");
        return plugins.get(0);
    }



}



