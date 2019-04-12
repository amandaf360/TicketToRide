package dao;

public interface DAOFactory {
    UserDAO getUserDAO();
    GameDAO getGameDAO();
}
