package PluginInterfaces;

public interface IPersistanceProvider {
    boolean beginTransaction();
    boolean endTransaction();
    IUserDAO getUserDAO();
    IGameDAO getGameDAO();
    String getLabel();
    void clearAll();
}
