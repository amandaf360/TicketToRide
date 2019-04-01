package servermodel;

import java.util.ArrayList;
import java.util.Collections;

public class DestCardDeck
{
    private ArrayList<DestCard> deck;

    public DestCardDeck()
    {
        deck = new ArrayList<>();
        deck.add(new DestCard("Denver", "El Paso", 4));
        deck.add(new DestCard("Kansas City", "Houston", 5));
        deck.add(new DestCard("New York", "Atlanta", 6));
        deck.add(new DestCard("Chicago", "New Orleans", 7));
        deck.add(new DestCard("Calgary", "Salt Lake City", 7));
        deck.add(new DestCard("Helena", "Los Angeles", 8));
        deck.add(new DestCard("Duluth", "Houston", 8));
        deck.add(new DestCard("Sault St. Marie", "Nashville", 8));
        deck.add(new DestCard("Montreal", "Atlanta", 9));
        deck.add(new DestCard("Sault St. Marie", "Oklahoma City", 9));
        deck.add(new DestCard("Seattle", "Los Angeles", 9));
        deck.add(new DestCard("Chicago", "Santa Fe", 9));
        deck.add(new DestCard("Duluth", "El Paso", 10));
        deck.add(new DestCard("Toronto", "Miami", 10));
        deck.add(new DestCard("Portland", "Phoenix", 11));
        deck.add(new DestCard("Dallas", "New York City", 11));
        deck.add(new DestCard("Denver", "Pittsburgh", 11));
        deck.add(new DestCard("Winnipeg", "Little Rock", 11));
        deck.add(new DestCard("Winnipeg", "Houston", 12));
        deck.add(new DestCard("Boston", "Miami", 12));
        deck.add(new DestCard("Vancouver", "Santa Fe", 13));
        deck.add(new DestCard("Calgary", "Phoenix", 13));
        deck.add(new DestCard("Montreal", "New Orleans", 13));
        deck.add(new DestCard("Los Angeles", "Chicago", 16));
        deck.add(new DestCard("San Fransisco", "Atlanta", 17));
        deck.add(new DestCard("Portland", "Nashville", 17));
        deck.add(new DestCard("Vancouver", "Montreal", 20));
        deck.add(new DestCard("Los Angeles", "Miami", 20));
        deck.add(new DestCard("Los Angeles", "New York", 21));
        deck.add(new DestCard("Seattle", "New York", 22));
        shuffle();
    }

    public DestCard draw()
    {
        DestCard drawnCard = deck.get(0);
        deck.remove(0);
        return drawnCard;
    }

    public void shuffle()
    {
        Collections.shuffle(deck);
    }

    public void putOnBottom(DestCard discardedCard)
    {
        deck.add(discardedCard);
    }

    public int size()
    {
        return deck.size();
    }
}
