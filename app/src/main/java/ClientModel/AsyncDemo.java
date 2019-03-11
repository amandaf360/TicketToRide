package ClientModel;

import android.os.AsyncTask;

import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameplayPresenter;

import PossiblyHelpful.ClaimRouteHelper;
import requests.RequestWrapper;


/*
    Just in case the demo needs to happen without us pressing the button each time.
    Without this, the

 */
public class AsyncDemo extends AsyncTask<Void, DemoCallback, Void>
{
    private GameplayPresenter presenter;
    private ClientModel clientModel;

    public AsyncDemo(GameplayPresenter presenter)
    {
        this.presenter = presenter;
        clientModel = ClientModel.getInstance();
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        try
        {
            Thread.sleep(2500);
            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Changing main player points");
                    clientModel.getMainPlayer().addPoints(1000);
                    clientModel.manuallyNotifyObservers();
                }
            });
            Thread.sleep(5000);


            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Adding locomotive to main player's hand");
                    clientModel.getMainPlayer().addTrainCardToHand(new TrainCarCard("locomotive"));
                    clientModel.manuallyNotifyObservers();
                }
            });
            Thread.sleep(5000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Claiming route");
                    ClaimRouteHelper claim;
                    claim = new ClaimRouteHelper(
                            new Route("Denver", "Oklahoma City", "red", 4));
                    claim.claimRoute();
                }
            });
            Thread.sleep(5000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Adding cards to main player and opponent hands");
                    clientModel.getActiveGame().getPlayers().get(0).addTrainCardToHand(new TrainCarCard("locomotive"));
                    clientModel.getActiveGame().getPlayers().get(0).addTrainCardToHand(new TrainCarCard("locomotive"));
                    if(clientModel.getActiveGame().getPlayers().size() > 1)
                    {
                        clientModel.getActiveGame().getPlayers().get(1).addTrainCardToHand(new TrainCarCard("locomotive"));
                        clientModel.getActiveGame().getPlayers().get(1).addTrainCardToHand(new TrainCarCard("locomotive"));
                    }
                    clientModel.manuallyNotifyObservers();
                }
            });
            Thread.sleep(5000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    if(clientModel.getActiveGame().getPlayers().size() > 1)
                    {
                        presenter.displayToast("Decreasing opponent train cars");
                        clientModel.getActiveGame().getPlayers().get(1).setNumTrains(30);
                    }
                }
            });
            Thread.sleep(5000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    if(clientModel.getActiveGame().getPlayers().size() > 1)
                    {
                        presenter.displayToast("Increasing opponent destination cards");
                        clientModel.getActiveGame().getPlayers().get(1).setNumDestCards(10);
                        clientModel.manuallyNotifyObservers();
                    }
                }
            });
            Thread.sleep(5000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Changing face up cards to all locomotives");
                    clientModel.setFaceUpCardByIndex(0, new TrainCarCard("locomotive"));
                    clientModel.setFaceUpCardByIndex(1, new TrainCarCard("locomotive"));
                    clientModel.setFaceUpCardByIndex(2, new TrainCarCard("locomotive"));
                    clientModel.setFaceUpCardByIndex(3, new TrainCarCard("locomotive"));
                    clientModel.setFaceUpCardByIndex(4, new TrainCarCard("locomotive"));
                }
            });
            Thread.sleep(5000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Changing number of cards in train car deck");
                    clientModel.setActiveGameTrainCards(999);
                }
            });
            Thread.sleep(5000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Changing number of cards in destination deck");
                    clientModel.setActiveGameDestCards(642);
                }
            });
            Thread.sleep(5000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Adding message to chat");
                    clientModel.addMessageToChat(new Message("blue","Hello from the demo"));
                }
            });
            Thread.sleep(5000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    if(clientModel.getActiveGame().getPlayers().size() > 1)
                    {
                        presenter.displayToast("Going to next player's turn");
                        clientModel.endCurrentTurn();
                    }
                }
            });
            Thread.sleep(5000);

        }
        catch(InterruptedException e)
        {
            System.out.println("Interrupted");
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(DemoCallback... callbacks)
    {
        callbacks[0].execute();
    }
}
