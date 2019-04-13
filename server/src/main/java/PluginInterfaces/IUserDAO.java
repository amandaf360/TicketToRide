package PluginInterfaces;

import java.util.List;

public interface IUserDAO
{
    void addUser(byte[] s);
    List<byte[]> getAllUsers();
}
