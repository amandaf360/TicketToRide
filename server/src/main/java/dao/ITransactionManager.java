package dao;

public interface ITransactionManager {
    boolean beginTransaction();
    boolean endTransaction();
    UserDAO getUserDAO();
    GameDAO getGameDAO();
    String getLabel();
    //pointer to DAO factory
}
