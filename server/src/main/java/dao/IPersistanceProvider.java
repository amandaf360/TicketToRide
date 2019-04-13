package dao;

public interface IPersistanceProvider {
    boolean beginTransaction();
    boolean endTransaction();
    UserDAO getUserDAO();
    GameDAO getGameDAO();
    String getLabel();
    //pointer to DAO factory??
}
