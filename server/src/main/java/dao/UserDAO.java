package dao;

import java.util.List;

import servermodel.User;

public interface UserDAO {
    void addUser(String s);
    List<User> getAllUsers();
}
