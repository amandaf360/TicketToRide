package DallinStuff.proxy;

import com.google.gson.Gson;

public class Serializer
{
    Gson gson;

    public Serializer(){
        gson = new Gson();
    }

    public String serializeRequest(Request request)
    {
        return gson.toJson(request);
    }

}