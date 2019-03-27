package proxy;

import android.os.AsyncTask;

import com.example.amandafails.tickettoride.R;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ClientModel.DestinationCards;
import ClientModel.Message;
import ClientModel.Player;
import requests.*;
import commands.*;
import responses.*;


/**
 * Sends requests to the server to carry out the many possible commands required by the client
 *
 * @invariant serializer != null
 */
public class ServerProxy extends AsyncTask<RequestWrapper, Void, String>
{
    /**
     * The callback given to the asynctask which is set depending on which method on ServerProxy is called
     * and it will be called from onPostExecute in the main thread after the response comes back from
     * the server.
     */
    private OnTaskCompleted callBack;

    /**
     * The class that the doInBackground will use to serialize the RequestWrapper class before sending
     * it, and the class that most callBacks will use to deserialize the server repsonse.
     */
    private Serializer serializer;

    /**
     * Sends username and password to server to see whether the user already exists and will log the
     * user in if they user exists and the password is correct.
     *
     *
     * @param username The name of the user trying to log in.
     * @param password The password that
     *
     * @pre username != null
     * @pre password != null
     * @post LoginCommand filled with appropriate data from server will be executed
     */
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

    /**
     * Attempts to register the user on the server
     *
     * @param username The name of the user trying to register
     * @param password The password the user desires to be associated with their username
     *
     * @pre username != null
     * @pre password != null
     *
     * @post RegisterCommand filled with appropriate data from server wlil be executed.
     */
    public void register(String username, String password) {
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

    /**
     * Fetches commands from the server that have not yet been seen by the client.
     *
     * @param pollType The type of poll that the client wants back
     * @param username The username associated with the current client polling
     *
     * @pre pollType == "poll" || pollType == "firstPoll"
     * @pre username != null
     *
     * @post A PollCommand filled with the data associated with username on the server will be executed
     */
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

    /**
     * Attempts to have join the game with the specified gameNumber
     *
     * @param gameNumber The number of the game the client is trying to join
     * @param username The username associated with the logged in state of the client
     *
     * @pre gameNumber > 0
     * @pre username != null
     *
     * @post JoinGameCommand will be executed
     */
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

    /**
     * Sends a request to the server to create a game
     *
     * @param username Name of the user creating the game
     * @param numPlayers The number of players that will be allowed in the game once created
     * @param gameName The name of the game which will be displayed in the lobby and gameJoin screen
     *
     * @pre numPlayers > 1 && numPlayers < 6
     * @pre username != null
     * @pre gameName != null
     *
     * @post Will execute a createGameResponse
     */
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


    /**
     * Starts a game with the specified game number
     *
     * @param gameNum The number of the game to be started
     *
     * @pre gameNum > 0
     *
     * @post A StartGameCommand will be executed
     */
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

    /**
     * Sends a chat message to the server. The poller handles sending the response back, so this method
     * has no response.
     *
     * @param username Name of the user
     * @param message Message object to be displayed in the chat window
     * @param gameNum The number of the game the user is currently in
     *
     * @pre username != null
     * @pre message != null
     * @pre gameNum > 0
     */
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

    /**
     * Specified user draws the specified number of destination cards
     *
     * @param numCards The number of cards to be drawn
     * @param username Name of the user drawing cards
     *
     * @pre numCards > 0 && numCards < 4
     * @pre username != null
     *
     * @post Will execute a DrawDestCommand populated with the appropriate info from the server.
     */
    public void drawDestCards(final int numCards, final String username)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(Integer.toString(numCards));
        stringList.add(username);
        RequestWrapper wrapper = new RequestWrapper("drawDestCards", stringList);

        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {
                if(responseJson != null) {
                    DrawDestResponse response = serializer.deserializeDrawDestResponse(responseJson);
                    DrawDestCommand command = new DrawDestCommand(response.getCardsDrawn());
                    command.execute();
                }
                else
                {
                    ServerProxy proxy = new ServerProxy();
                    proxy.drawDestCards(numCards, username);
                }
            }
        };
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    /**
     * Claims the route of the specified index, assigning it to the user of the name specified
     *
     * @param index Index of the route to be claimed in the respective data structure on the server model
     * @param name name of the user drawing
     *
     * @pre index > -1 && index < 96
     * @pre name != null
     *
     * @post A ClaimRouteCommand will be executed.
     */
    public void claimRoute(int index, String name)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(Integer.toString(index));
        stringList.add(name);
        RequestWrapper wrapper = new RequestWrapper("claimRoute", stringList);

        callBack = new OnTaskCompleted()
        {
            @Override
            public void completeTask(String responseJson)
            {
                ClaimRouteResponse response = serializer.deserializeClaimRouteResponse(responseJson);
                ClaimRouteCommand command = new ClaimRouteCommand(response.getIndex(), response.getName());
                command.execute();
            }
        };

        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    /**
     * Sends request to the server to discard specified destination card
     *
     * @param card The destination card to be discarded
     * @param username The name of the user discarding the card
     *
     * @pre card != null
     * @pre username != null
     */
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

    /**
     * Sends request to server to draw
     *
     * @param username Name of the user
     * @param faceUpIndex Index of the card being drawn in the face up card pile (or -1 if drawing from
     *                    face down deck
     *
     * @pre faceUpIndex >= -1 && faceUpIndex <= 4
     * @pre username != null
     */
    public void drawTrainCarCard(String username, int faceUpIndex)//if from deck, index should be -1
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(username);
        stringList.add(Integer.toString(faceUpIndex));
        RequestWrapper wrapper = new RequestWrapper("drawTrainCarCard", stringList);

        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson)
            {
                DrawTrainResponse response = serializer.deserializeDrawTrainResponse(responseJson);
                DrawTrainCardCommand command = new DrawTrainCardCommand(response.getCardColor());
                command.execute();
            }
        };

        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    public void sendGameHistoryMessage(String username, Message message)
    {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(username);
        stringList.add(message.getColor());
        stringList.add(message.getMessage());
        RequestWrapper wrapper = new RequestWrapper("gameHistory", stringList);

        callBack = new OnTaskCompleted() {
            @Override
            public void completeTask(String responseJson) {

            }
        };

        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, wrapper);
    }

    /**
     *  Initializes the ServerProxy object, and sets the serializer.
     * @post serializer will not be null
     */
    public ServerProxy() {
        serializer = new Serializer();
    }

    /**
     * @throws IOException
     *
     * @param requests While the method accepts any number of RequestWrapper objects, only requests[0]
     *                 will be serialized and sent to the server
     *
     * @return The string value of the deserialized response from the server
     * @pre requests.length != 0
     * @post Will connect to the server or print out why the connection was unsuccesful
     */
    @Override
    protected String doInBackground(RequestWrapper... requests)
    {
        RequestWrapper theRequest = requests[0];
        try {
            Serializer serializer = new Serializer();
            URL myUrl = new URL("http://10.37.161.197:3000");//CHANGE IP ADDRESS HERE

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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method is called after the doInBackground method finishes. Has no preconditions because
     * the callback can expect a variety of things from null to specific serialized objects
     *
     * @param response The response string from the server after it has been
     *
     * @post The callback will be called
     */
    @Override
    protected void onPostExecute(String response) {
        callBack.completeTask(response);
    }
}