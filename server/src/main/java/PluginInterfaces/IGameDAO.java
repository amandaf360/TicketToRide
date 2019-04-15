package PluginInterfaces;

import java.util.List;

public interface IGameDAO
{
    void addCommand(byte[] s, int gameNum);
    void setGameState(byte[] s, int gameNum, String label);
    void clearCommands();
    List<gameWrapper> getAllGames();
    List<byte[]> getAllGameCommands(int gameNum);

    //Add game functionality?
    //Should clearCommands take in a game num as one of its arguments?
}
