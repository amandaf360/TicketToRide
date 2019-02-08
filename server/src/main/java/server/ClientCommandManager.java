package server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import commands.*;

public class ClientCommandManager
{
    Map<String, ArrayList<ICommand>> userMap;

    public ClientCommandManager()
    {
        userMap = new HashMap<String, ArrayList<ICommand>>();
        userMap.put("allCommands", new ArrayList<ICommand>());
    }

    public void addCommand(String identifier, ICommand command)
    {
        if(!identifier.equals("all"))
        {
            userMap.get(identifier).add(command);
        }
        else
        {
            Set<Map.Entry<String, ArrayList<ICommand>>> mapSet = userMap.entrySet();
            Iterator<Map.Entry<String, ArrayList<ICommand>>> iter = mapSet.iterator();
            while(iter.hasNext())
            {
                Map.Entry<String, ArrayList<ICommand>> entry = iter.next();
                entry.getValue().add(command);
            }
        }
    }

    public void addUser(String user)
    {
        ArrayList<ICommand> allCommands = userMap.get("allCommands");
        userMap.put(user, allCommands);
    }

    private void getGameList()
    {

    }

}
