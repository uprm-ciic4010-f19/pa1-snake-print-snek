package Game.Entities.Static;

import java.awt.Color;
import java.awt.Graphics;

import Game.Entities.Dynamic.Player;
import Main.Handler;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Apple {

    private Handler handler;

    public int xCoord;
    public int yCoord;
    public static boolean isGood=true;

    public Apple(Handler handler,int x, int y){
        this.handler=handler;
        this.xCoord=x;
        this.yCoord=y;
        Player.MoveCount=0;
        isGood=true;
    
        
    }
    	
    
    // This renders the apple and colors it red	
    public void render(Graphics g,Boolean[][] appleLocation){
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
            	g.setColor(Color.red);
                if(appleLocation[i][j]||handler.getWorld().appleLocation[i][j]){
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                    		if(Player.MoveCount>=25) {//If the player has made 25 key presses the apple changes color and sets the isGood to false which changes the points value of the apple.
	                    		g.setColor(new Color(128,0,0));
	                            if(appleLocation[i][j]||handler.getWorld().appleLocation[i][j]){
	                                g.fillRect((i*handler.getWorld().GridPixelsize),
	                                        (j*handler.getWorld().GridPixelsize),
	                                        handler.getWorld().GridPixelsize,
	                                        handler.getWorld().GridPixelsize);
	                                		isGood=false;
	                                		
	                                		
	                            }
                    
                    		
                    		}		 
                    		
                    		}
            							
                }
            }
        }
    	
}
