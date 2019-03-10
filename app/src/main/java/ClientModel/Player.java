package ClientModel;

public class Player
{
    private String color;
    private String authToken;
    private String name;
    private PlayerHandDestinations playerHandDestinations;
    private PlayerHandTrains playerHandTrains;
    private int points;
    private int numTrains;
    private int numRoutes;
    private int numDestCards;

    public Player()
    {
        playerHandTrains = new PlayerHandTrains(0,0,0,0,0,0,0,0,0);
        playerHandDestinations = new PlayerHandDestinations();
        points = 0;
        numRoutes = 0;
        numTrains = 45;
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
        this.numDestCards = playerHandDestinations.getSize();
    }

    public PlayerHandTrains getPlayerHandTrains()
    {
        return playerHandTrains;
    }

    public void setPlayerHandTrains(PlayerHandTrains playerHandTrains)
    {
        this.playerHandTrains = playerHandTrains;
    }

    public void addTrainCardToHand(TrainCarCard trainCarCard)
    {
        playerHandTrains.addCard(trainCarCard);
    }

    public void addDestinationCardToPlayerHand(DestinationCards destinationCards)
    {
        playerHandDestinations.addCard(destinationCards);
        numDestCards = playerHandDestinations.getSize();
    }

    public void deleteDestinationCardFromPlayersHand(DestinationCards destinationCards)
    {
        playerHandDestinations.deleteCard(destinationCards);
        numDestCards = playerHandDestinations.getSize();
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public void addPoints(int add)
    {
        points += add;
    }

    public int getNumTrains()
    {
        return numTrains;
    }

    public void setNumTrains(int numTrains)
    {
        this.numTrains = numTrains;
    }

    public void takeTrains(int take)
    {
        numTrains -= take;
    }

    public int getNumRoutes()
    {
        return numRoutes;
    }

    public void setNumRoutes(int numRoutes)
    {
        this.numRoutes = numRoutes;
    }

    public void addRoute()
    {
        numRoutes++;
    }

    public int getNumCards()
    {
        return playerHandTrains.getTotalCards();
    }

    public int getNumDestCards() {
        return numDestCards;
    }

    public void setNumDestCards(int numDestCards) {
        this.numDestCards = numDestCards;
    }

    public void addDestCards(int numDestCards)
    {
        this.numDestCards += numDestCards;
    }
}
