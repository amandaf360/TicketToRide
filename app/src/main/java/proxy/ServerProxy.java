package DallinStuff.proxy;

import android.os.AsyncTask;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/*
 * How to use (2 options):
 * 1. ServerProxy proxy = new ServerProxy(new OnTaskCompleted(){
 *          @Override
 *          public void completeTask(Object o)
 *          {
 *              (Your object) whatever = (Your object) o; <--cast it
 *              do stuff
 *          }
 * });
 *
 * 2. Have your class implement the OnTaskCompleted interface and override the copmleteTask method
 *
 *  ServerProxy proxy = new ServerProxy(this);
 *
 */
public class ServerProxy extends AsyncTask<Request, Void, Object>
{

    public void login(String username, String password)
    {
        LoginRequest request = new LoginRequest(username, password);
    }

    OnTaskCompleted callerClass;
    public ServerProxy(OnTaskCompleted caller) {
        callerClass = caller;
    }
    @Override
    protected Object doInBackground(Request... requests)
    {
        Request theRequest = requests[0];
        try
        {
            Serializer serializer = new Serializer();
            URL myUrl = new URL("http://10.24.216.101:3000");//CHANGE IP ADDRESS HERE
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            String requestString = serializer.serializeRequest(requests[0]);
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(requestString);
            osw.flush();
            osw.close();
            os.close();

            connection.connect();

            StringBuilder resultBuilder = new StringBuilder();
            InputStreamReader is = new InputStreamReader(connection.getInputStream());

            int currentChar;
            while ((currentChar = is.read()) != -1)
            {
                resultBuilder.append((char) currentChar);
            }
            return resultBuilder.toString();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object o)
    {
        callerClass.completeTask(o);
    }
}