package dao;

import java.util.List;

import commands.ICommand;
import servermodel.ActiveGame;

public interface GameDAO {
    void addCommand(String s);
    void setGameState(String s);
    void clearCommands();
    List<ActiveGame> getAllGames();
    List<ICommand> getAllGameCommands(String s);
}
