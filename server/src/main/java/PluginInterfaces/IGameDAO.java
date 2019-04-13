package PluginInterfaces;

import java.util.List;

public interface IGameDAO
{
    void addCommand(byte[] s, int gameNum);
    void setGameState(byte[] s, int gameNum);
    void clearCommands();
    List<byte[]> getAllGames();
    List<byte[]> getAllGameCommands(int gameNum);
}
