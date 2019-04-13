package dao;

import java.util.List;

public interface UserDAO {
    void addUser(byte[] s);
    List<byte[]> getAllUsers();
}
