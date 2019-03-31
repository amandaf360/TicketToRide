package servermodel;

import java.util.ArrayList;

public class Player
{
    private String color;
    private String authToken;
    private String name;
    private ArrayList<DestCard> destCards;
    private ArrayList<TrainCarCard> trainCarCards;
    private int numRoutes;
    private int numTrains;

    public Player()
    {
        destCards = new ArrayList<>();
        trainCarCards = new ArrayList<>();
        numRoutes = 0;
        numTrains = 15;
    }

    public Player(Player player)
    {
        this.color = player.color;
        this.authToken = player.authToken;
        this.name = player.name;
        destCards = new ArrayList<>();
        trainCarCards = new ArrayList<>();
        numRoutes = 0;
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

    public void addDestCard(DestCard card)
    {
        destCards.add(card);
    }

    public void addTrainCarCard(TrainCarCard card)
    {
        trainCarCards.add(card);
    }

    public ArrayList<DestCard> getDestCards()
    {
        return destCards;
    }

    public DestCard discardDestCard(String cityOne, String cityTwo)
    {
        for (int i = 0; i < destCards.size(); i++)
        {
            if (destCards.get(i).getCityOne().equals(cityOne))
            {
                if (destCards.get(i).getCityTwo().equals(cityTwo))
                {
                    DestCard card = destCards.get(i);
                    destCards.remove(i);
                    return card;
                }
            }
        }
        return null;
    }

    public TrainCarCard removeTrainCarCard(String color)
    {
        TrainCarCard card = null;
        for(int i = 0; i < trainCarCards.size(); i++)
        {
            if(trainCarCards.get(i).getColor().equals(color))
            {
                card = trainCarCards.get(i);
                trainCarCards.remove(i);
                break;
            }
        }
        return card;
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

    public int getNumTrains() {
        return numTrains;
    }

    public void setNumTrains(int numTrains) {
        this.numTrains = numTrains;
    }
}
