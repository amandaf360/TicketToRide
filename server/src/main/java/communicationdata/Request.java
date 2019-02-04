package communicationdata;

import java.util.ArrayList;

public class Request
{
    String commandType;
    ArrayList<String> data;

    public String getStringAt(int index)
    {
        return data.get(index);
    }

    public String getCommandType() {
        return commandType;
    }
}
