package services;

import ClientModel.ClientModel;
import proxy.ServerProxy;

public class EndTurnService
{
   public void endTurn()
   {
       ServerProxy proxy = new ServerProxy();
       proxy.endCurrentTurn(ClientModel.getInstance().getMainPlayer().getName());
   }
}
