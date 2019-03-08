package ClientModel;

public class Player
{
    private String color;
    private String authToken;
    private String name;
    private PlayerHandDestinations playerHandDestinations;
    private PlayerHandTrains playerHandTrains;

    public Player()
    {

    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public PlayerHandDestinations getPlayerHandDestinations()
    {
        return playerHandDestinations;
    }

    public void setPlayerHandDestinations(PlayerHandDestinations playerHandDestinations)
    {
        this.playerHandDestinations = playerHandDestinations;
    }

    public PlayerHandTrains getPlayerHandTrains()
    {
        return playerHandTrains;
    }

    public void setPlayerHandTrains(PlayerHandTrains playerHandTrains)
    {
        this.playerHandTrains = playerHandTrains;
    }

    public void addTrainCardToHand(TrainCards trainCards)
    {
        playerHandTrains.addCard(trainCards);
    }

    public void addDestinationCardToPlayerHand(DestinationCards destinationCards)
    {
        playerHandDestinations.addCard(destinationCards);
    }

    public void deleteDestinationCardFromPlayersHand(DestinationCards destinationCards)
    {
        playerHandDestinations.deleteCard(destinationCards);
    }


}
