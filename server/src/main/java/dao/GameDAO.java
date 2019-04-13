package dao;

import java.util.List;

import commands.ICommand;
import servermodel.ActiveGame;

public interface GameDAO {
    void addCommand(byte[] s, int gameNum);
    void setGameState(byte[] s, int gameNum);
    void clearCommands();
    byte[] getAllGames();
    byte[] getAllGameCommands(int gameNum);
}
