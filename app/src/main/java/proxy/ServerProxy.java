package proxy;

import android.os.AsyncTask;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ClientModel.DestinationCards;
import ClientModel.Message;
import requests.*;
import commands.*;
import responses.*;



public class ServerProxy extends AsyncTask<RequestWrapper, Void, String> {
    private OnTaskCompleted callBack;
    private Serializer serializer;

    public void login(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add(username);
        stringList.add(password);
        RequestWrapper wrapper = new RequestWrapper("login", stringList);
        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {
                LoginResponse response = serializer.deserializeLoginResponse(responseJson);
                LoginCommand command = new LoginCommand(response.getUsername(), response.getErrorMessage());
                command.execute();
            }
        };
        execute(wrapper);
    }

    public void register(String username, String password) {
        System.out.println("In register proxy command");
        RegisterRequest request = new RegisterRequest(username, password);
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add(username);
        stringList.add(password);
        RequestWrapper wrapper = new RequestWrapper("register", stringList);
        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {
                RegisterResponse response = serializer.deserializeRegisterResponse(responseJson);
                RegisterCommand command = new RegisterCommand(response.getUsername(), response.getErrorMessage());
                command.execute();
            }
        };
        execute(wrapper);
    }

    public void poll(String pollType, String username) {

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(username);
        RequestWrapper wrapper = new RequestWrapper(pollType, stringList);
        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {
                PollResponse response = serializer.deserializePollResponse(responseJson);
                PollCommand command = new PollCommand(response);
                ServerProxy proxy = new ServerProxy();
                command.execute();
            }
        };
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public void joinGame(int gameNumber, String username)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(Integer.toString(gameNumber));
        stringList.add(username);
        RequestWrapper wrapper = new RequestWrapper("joinGame", stringList);
        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {
                JoinGameResponse response = serializer.deserializeJoinGameResponse(responseJson);
                if(response != null) {
                    JoinGameCommand command = new JoinGameCommand(response.getErrorMessage());
                    command.execute();
                }
            }
        };
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public void createGame(String username, int numPlayers, String gameName)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(username);
        stringList.add(Integer.toString(numPlayers));
        stringList.add(gameName);
        RequestWrapper wrapper = new RequestWrapper("createGame", stringList);
        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson)
            {
                CreateGameResponse gameResponse = serializer.deserializeCreateGameResponse(responseJson);
                if(responseJson != null)
                {
                    CreateGameCommand command = new CreateGameCommand(gameResponse.getErrorMessage());
                    command.execute();
                }
            }
        };
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public void clearPoll(String username)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(username);
        RequestWrapper wrapper = new RequestWrapper("clearPoll", stringList);

        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson)
            {
            }
        };
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public void leaveGame(String username, String gameName)
    {

    }

    public void beginGame(int gameNum)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(Integer.toString(gameNum));
        RequestWrapper wrapper = new RequestWrapper("startGame", stringList);

        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson)
            {
                StartGameResponse response = serializer.deserializeStartGameResponse(responseJson);
                StartGameCommand command = new StartGameCommand(response.getErrorMessage());
                command.execute();
            }
        };
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public void sendChatMessage(String username, Message message, int gameNum)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(username);
        stringList.add(message.getMessage());
        stringList.add(message.getColor());
        stringList.add(Integer.toString(gameNum));
        RequestWrapper wrapper = new RequestWrapper("sendChatMessage", stringList);

        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {

            }
        };
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public void drawDestCards(int numCards, String username)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(Integer.toString(numCards));
        stringList.add(username);
        RequestWrapper wrapper = new RequestWrapper("drawDestCards", stringList);

        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {
                DrawDestResponse response = serializer.deserializeDrawDestResponse(responseJson);
                DrawDestCommand command = new DrawDestCommand(response.getCardsDrawn());
                command.execute();
            }
        };
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public void discardDestCard(DestinationCards card, String username)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(card.getCityOne());
        stringList.add(card.getCityTwo());
        stringList.add(Integer.toString(card.getPoints()));
        stringList.add(username);
        RequestWrapper wrapper = new RequestWrapper("discardDestCard", stringList);
        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {
                //no callback necessary because discarding always has the same effect on the client.
            }
        };

        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public void drawTrainCarCard(String username, int faceUpIndex)//if from deck, index should be -1
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(username);
        stringList.add(Integer.toString(faceUpIndex));
        RequestWrapper wrapper = new RequestWrapper("drawTrainCarCard", stringList);

        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {

            }
        };

        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public ServerProxy() {
        serializer = new Serializer();
    }

    @Override
    protected String doInBackground(RequestWrapper... requests) {
        RequestWrapper theRequest = requests[0];
        try {
            Serializer serializer = new Serializer();
            URL myUrl = new URL("http://10.24.198.110:3000");//CHANGE IP ADDRESS HERE

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
            while ((currentChar = is.read()) != -1) {
                resultBuilder.append((char) currentChar);
            }
            is.close();
            return resultBuilder.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        callBack.completeTask(s);
    }
}