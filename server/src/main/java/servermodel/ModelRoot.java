package servermodel;

import java.util.ArrayList;
import java.util.List;

public class ModelRoot
{
    private static ModelRoot model = new ModelRoot();

    private ModelRoot()
    {
        userList = new ArrayList<>();
    }

    List<User> userList;

    public void addUser(User user)
    {
        userList.add(user);
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public static ModelRoot getModel()
    {
        return model;
    }
}
