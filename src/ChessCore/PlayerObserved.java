
package ChessCore;

public class PlayerObserved implements PlayerObserver{
     @Override
     public Player updateSate(Player player)
     {
         if(player==Player.WHITE)
             return  Player.BLACK;
        else
             return Player.WHITE;
        
     }
    
}
