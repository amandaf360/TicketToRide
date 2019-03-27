package ClientModel;

import android.os.AsyncTask;

import com.example.amandafails.tickettoride.app.activities.ViewsPresenters.Gameplay.GameplayPresenter;


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
            /*Thread.sleep(1000);
            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Changing main player points");
                }
            });
            Thread.sleep(3000);


            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    clientModel.getMainPlayer().addPoints(1000);
                    clientModel.manuallyNotifyObservers();
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Adding locomotive to main player's hand");
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    clientModel.getMainPlayer().addTrainCardToHand(new TrainCarCard("locomotive"));
                    clientModel.manuallyNotifyObservers();
                }
            });
            Thread.sleep(3000);*/

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    //presenter.displayToast("Claiming route");
                    //ClaimRouteService helper = new ClaimRouteService(25);
                    //helper.claimRoute();
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    //clientModel.claimRouteByIndex(3, clientModel.getActiveGame().getPlayers().get(0).getName());
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Adding cards to main player and opponent hands");
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {

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
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Decreasing opponent train cars");
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    if(clientModel.getActiveGame().getPlayers().size() > 1)
                    {
                        clientModel.getActiveGame().getPlayers().get(1).setNumTrains(30);
                    }
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Increasing destination cards");
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    if(clientModel.getActiveGame().getPlayers().size() > 1)
                    {
                        clientModel.getActiveGame().getPlayers().get(1).setNumDestCards(10);
                        clientModel.manuallyNotifyObservers();
                    }
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Changing face up cards to all locomotives");
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    clientModel.setFaceUpCardByIndex(0, new TrainCarCard("locomotive"));
                    clientModel.setFaceUpCardByIndex(1, new TrainCarCard("locomotive"));
                    clientModel.setFaceUpCardByIndex(2, new TrainCarCard("locomotive"));
                    clientModel.setFaceUpCardByIndex(3, new TrainCarCard("locomotive"));
                    clientModel.setFaceUpCardByIndex(4, new TrainCarCard("locomotive"));
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Changing number of cards in train car deck");
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {

                    clientModel.setActiveGameTrainCards(999);
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Changing number of cards in destination deck");
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {

                    clientModel.setActiveGameDestCards(642);
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Adding message to chat");
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    clientModel.addMessageToChat(new Message("blue","Hello from the demo"));
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    presenter.displayToast("Going to next player's turn");
                }
            });
            Thread.sleep(3000);

            publishProgress(new DemoCallback() {
                @Override
                public void execute() {
                    if(clientModel.getActiveGame().getPlayers().size() > 1)
                    {
                        clientModel.endCurrentTurn();
                    }
                }
            });
            Thread.sleep(3000);

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
