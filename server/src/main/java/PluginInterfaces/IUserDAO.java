package PluginInterfaces;

import java.util.List;

public interface IUserDAO
{
    void addUser(byte[] s);
    void addAuthToken(String token);
    List<String> getAllTokens();
    List<byte[]> getAllUsers();
}
