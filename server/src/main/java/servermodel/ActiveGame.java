package servermodel;

import java.util.ArrayList;

public class ActiveGame
{
    private DestCardDeck destinationDeck;
    private TrainCarDeck trainDeck;
    private ArrayList<Player> players;

    public ActiveGame()
    {
        destinationDeck = new DestCardDeck();
        trainDeck = new TrainCarDeck();
        players = new ArrayList<>();
    }

    /*
        draw dest card, discard dest card
        drawfaceup, draw from deck, discard traincards,
        chat send message,

     */



}
