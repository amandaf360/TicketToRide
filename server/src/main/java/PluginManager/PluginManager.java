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
        // TODO: scan directory and load all plugins?
        // Getting the jar URL which contains target class
        URL[] classLoaderUrls = new URL[]{new URL(System.getProperty("user.dir") + "/server/PluginJars/*.jar")};
        for(URL u : classLoaderUrls) {
            // Create a new URLClassLoader
            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

            // Load the target class
            Class<?> beanClass = urlClassLoader.loadClass("com.jcg.Bean");

            // Create a new instance from the loaded class
            Constructor<?> constructor = beanClass.getConstructor();
            Object beanObj = constructor.newInstance();
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
//        Class<? extends IPersistanceProvider> dbPluginClass = (Class<IPersistanceProvider>) loader.loadClass(className);
//
//        // TODO: might need to change the parameters for "getDeclaredConstructor"
//        return dbPluginClass.getDeclaredConstructor(null).newInstance();

        // FIX THIS
        return plugins.get(0);
    }



}



