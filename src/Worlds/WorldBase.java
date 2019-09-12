package Worlds;

import Game.Entities.Dynamic.Player;
import Game.Entities.Dynamic.Tail;
import Game.Entities.Static.Apple;
import Main.Handler;

import java.awt.*;
import java.util.LinkedList;


/**
 * Created by AlexVR on 7/2/2018.
 */
public abstract class WorldBase {

    //How many pixels are from left to right
    //How many pixels are from top to bottom
    //Must be equal
    public int GridWidthHeightPixelCount;

    //automatically calculated, depends on previous input.
    //The size of each box, the size of each box will be GridPixelsize x GridPixelsize.
    public int GridPixelsize;

    public Player player;

    protected Handler handler;


    public Boolean appleOnBoard;
    protected Apple apple;
    public Boolean[][] appleLocation;


    public Boolean[][] playerLocation;

    public LinkedList<Tail> body = new LinkedList<>();


    public WorldBase(Handler handler){
        this.handler = handler;

        appleOnBoard = false;


    }
    public void tick(){



    }

    public void render(Graphics g){
    	String backgroundColor = Game.Entities.Dynamic.Player.Color;
        for (int i = 0; i <= 800; i = i + GridPixelsize) {
//        	if(backgroundColor=="RED") {
//        		g.setColor(new Color(51 , 255, 51));
//        	}else if(backgroundColor=="ORANGE") {
//        		g.setColor(new Color(0, 0,255));
//        	}else if(backgroundColor=="YELLOW") {
//        		g.setColor(new Color(255 , 0, 255));
//        	}else if(backgroundColor=="LIME") {
//        		g.setColor(new Color(255 ,0, 0));
//        	}else if(backgroundColor=="SKYBLUE") {
//        		g.setColor(new Color(0, 0, 0));
//        	}else if(backgroundColor=="BLUE") {
//        		g.setColor(new Color(255, 153, 51));
//        	}else if(backgroundColor=="INDIGO") {
//        		g.setColor(new Color(255, 153, 51));
//        	}else if(backgroundColor=="PURPLE") {
//        		g.setColor(new Color(255, 153, 51));
//        	}else if(backgroundColor=="BLACK") {
//        		g.setColor(new Color(255, 255, 255));
//        	}else if(backgroundColor=="RAINBOW") {
//        		g.setColor(new Color(255, 255, 255));
//        	}else {
//        		g.setColor(new Color(255 ,0, 255));
//        	} //WIP
        	g.setColor(new Color(255, 0, 255));
            g.drawLine(0, i, handler.getWidth() , i);
            g.drawLine(i,0,i,handler.getHeight());

        }



    }

}
