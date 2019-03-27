package com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.DrawDestCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import ClientModel.ClientModel;
import ClientModel.DestinationCards;
import services.DiscardDestCardService;
import services.DrawDestCardService;
import services.EndTurnService;

public class DrawDestFragmentPresenter implements IDrawDestFragmentPresenter, Observer
{
    private IDrawDestFragmentView view;
    private ClientModel clientModel;
    private List<String> added;

    public DrawDestFragmentPresenter(IDrawDestFragmentView view) {
        this.view = view;
        this.clientModel = ClientModel.getInstance();
        this.clientModel.addObserver(this);
        this.added = new ArrayList<String>();
    }



    @Override
    public void drawCards()
    {
        DrawDestCardService drawDestCardService = new DrawDestCardService();
        drawDestCardService.drawCards(3);
    }

    @Override
    public void clickCard(int index)
    {
        if(index == 0)
        {
            addCard(Integer.toString(0));
        }
        if(index == 1)
        {
            addCard(Integer.toString(1));
        }
        if(index == 2)
        {
            addCard(Integer.toString(2));
        }

    }

    private void addCard(String str)
    {
        boolean has = false;
        String remove = "";
        for(String string : added)
        {
            if(string.equals(str))
            {
                has = true;
                remove = string;
            }
        }
        if(has)
        {
            added.remove(remove);
            view.changeCardColor(Integer.parseInt(str));
        }
        else
        {
            added.add(str);
            view.changeCardColor(Integer.parseInt(str));
            if(added.size() >= 3)
            {
                unSelectBadCard();
            }
        }
    }

    private void unSelectBadCard()
    {
        String remove = added.get(0);
        added.remove(0);

        view.changeCardColor(Integer.parseInt(remove));
    }

    @Override
    public void doneButtonClicked()
    {
        DiscardDestCardService discardDestCardService = new DiscardDestCardService();
        ArrayList<DestinationCards> cards = clientModel.getMainPlayer().getPlayerHandDestinations()
                .getCardList();
        ArrayList<DestinationCards> toDelete = new ArrayList<>();
        for(String str : added)
        {

            toDelete.add(cards.get((cards.size() - 1) - (2 - Integer.parseInt(str))));

        }

        for(DestinationCards card : toDelete)
        {
            discardDestCardService.discardCard(card);
            clientModel.deleteMainPlayersDestinationCardFromHand(card);
        }

        deleteObserver();
        view.popFragment();

        EndTurnService end = new EndTurnService();
        end.endTurn();
    }


    //@Override
    public void deleteObserver() {
        clientModel.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg)
    {


        setCards();

    }

    private void setCards()
    {
        ArrayList<DestinationCards> list;
        list = clientModel.getNewlyAddedDestinationCardsFromMainPlayer();
        ArrayList<String> pass = new ArrayList<>();

        for(DestinationCards destinationCards : list)
        {
            pass.add(destinationCards.toString());
        }

        view.setCards(pass);
    }


}
