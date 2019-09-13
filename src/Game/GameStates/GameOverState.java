package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

import Game.Entities.Dynamic.Player;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameOverState extends State {

    private int count = 0;
    private UIManager uiManager;

    public GameOverState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);

        uiManager.addObjects(new UIImageButton(335, 180, 128, 64, Images.Restart, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().gameState);
            Player.score=0;
            Player.Counter=0;
            Player.MoveCount=0;
            if(Player.collide==true) {
            	Player.collide=false;
            }
        }));

        uiManager.addObjects(new UIImageButton(335,520,128, 64, Images.BTitle, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
            Player.score=0;
            Player.Counter=0;
            Player.MoveCount=0;
            if(Player.collide==true) {
            	Player.collide=false;
            }
        }));
    }
    



    

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        if( count>=30){
            count=30;
        }
        if(handler.getKeyManager().pbutt && count>=30){
            count=0;

            State.setState(handler.getGame().gameState);
        }


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.gameover,0,0,800,800,null);
        uiManager.Render(g);

    }
}
