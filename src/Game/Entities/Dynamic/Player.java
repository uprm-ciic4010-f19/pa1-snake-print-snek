package Game.Entities.Dynamic;

import Main.Handler;
import java.util.Random;

import java.awt.*;
import java.awt.Taskbar.State;
import java.awt.event.KeyEvent;

import Game.Entities.Static.Apple;
import Game.GameStates.GameState;


/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {
	
	public static String Color="DEFAULT";
    public int lenght;
    public boolean justAte;
    private Handler handler;
    public static int MoveCount;
    public static int Counter; // Score
    public static double CurrentSore; // Score
    public static double score;
    public static int Speed = 60; //Frames Per Second

    public int xCoord;
    public int yCoord;

    public int moveCounter;

    public String direction;//is your first name one?

    public Player(Handler handler){
        this.handler = handler;
        xCoord = 0;
        yCoord = 0;
        moveCounter = 0;
        direction= "Right";
        justAte = false;
        lenght= 1;

    }

    public void tick(){
        moveCounter++;
        if(moveCounter>=5) {
            checkCollisionAndMove();
            moveCounter=0;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && (direction !="Down")){
        	MoveCount+=1;
            direction="Up";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN) && (direction !="Up")){
        	MoveCount+=1;
        	direction="Down";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && (direction !="Right")){
        	MoveCount+=1;
        	direction="Left";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && (direction !="Left")){
        	MoveCount+=1;
        	direction="Right";
        	
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)){
        	Color="RED";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)){
        	Color="ORANGE";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)){
        	Color="YELLOW";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)){
        	Color="LIME";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)){
        	Color="SKYBLUE";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_6)){
        	Color="BLUE";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_7)){
        	Color="INDIGO";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_8)){
        	Color="PURPLE";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_9)){
        	Color="BLACK";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_0)) {
        	Color="RAINBOW";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)) {
        	Speed += 5;
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)) {
        	Speed -= 5;	
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {
        	lenght++;
            Tail tail= null;
            handler.getWorld().appleLocation[xCoord][yCoord]=false;
            handler.getWorld().appleOnBoard=true;
            if( handler.getWorld().body.isEmpty()){
                if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                    tail = new Tail(this.xCoord+1,this.yCoord,handler);
                }else{
                    if(this.yCoord!=0){
                        tail = new Tail(this.xCoord,this.yCoord-1,handler);
                    }else{
                        tail =new Tail(this.xCoord,this.yCoord+1,handler);
                    }
                }
            }else{
                if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                    tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                    }else{
                        tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                    }
                }
            }
            handler.getWorld().body.addLast(tail);
            handler.getWorld().playerLocation[tail.x][tail.y] = false;
            
            
            
            
            
        }

    }

    public void checkCollisionAndMove(){
    	
        handler.getWorld().playerLocation[xCoord][yCoord]=false;
        int x = xCoord;
        int y = yCoord;
        
        if(handler.getWorld().player.xCoord==Tail.xTail) {
        	if(handler.getWorld().player.yCoord==Tail.yTail) {
        		System.out.println("collide");
        	}
        }
       
        switch (direction){
            case "Left":
                if(xCoord==0){
                    
                    handler.getWorld().player.xCoord += 59;
                }else{
                    xCoord--;
                }
                break;
            case "Right":
                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                	
                    handler.getWorld().player.xCoord -= 59;
                    
                }else{
                    xCoord++;
                }
                break;
            case "Up":
                if(yCoord==0){
                    
                    handler.getWorld().player.yCoord += 59;
                }else{
                    yCoord--;
                }
                break;
            case "Down":
                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                    
                    handler.getWorld().player.yCoord -= 59;
                }else{
                    yCoord++;
                }
                break;
        }
        handler.getWorld().playerLocation[xCoord][yCoord]=true;


        if(handler.getWorld().appleLocation[xCoord][yCoord]){
        	Counter++;// Counter keeps track of score
        	
        	if (Apple.isGood==true){//Checks if apple is good and does the calc
	        	CurrentSore = (double) Math.sqrt(2*Counter+1);
	        	score=score+CurrentSore;
	        	Speed += 5; // This speeds up the frames per second and therefore the tickspersecond
	            Eat();
        	}
        	else {
	        	CurrentSore = (double) Math.sqrt(2*Counter+1);
	        	score=score-CurrentSore;
	        	Speed += 5; // This speeds up the frames per second and therefore the tickspersecond
	            Eat();
        	}
        }

        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler));
        }

    }
    public static int generateRandomInt(int upperRange){
        Random random = new Random();
        return random.nextInt(upperRange);
    }
    
    public void render(Graphics g,Boolean[][] playeLocation){
		int a = generateRandomInt(255);
		int b = generateRandomInt(255);
		int c = generateRandomInt(255);
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
            	if(Color=="RED") {
            		g.setColor(new Color(255 ,0, 0));
            	}else if(Color=="ORANGE") {
            		g.setColor(new Color(255, 153, 51));
            	}else if(Color=="YELLOW") {
            		g.setColor(new Color(255 ,255, 51));
            	}else if(Color=="LIME") {
            		g.setColor(new Color(51 ,255, 51));
            	}else if(Color=="SKYBLUE") {
            		g.setColor(new Color(0 ,204, 204));
            	}else if(Color=="BLUE") {
            		g.setColor(new Color(0 , 0, 255));
            	}else if(Color=="INDIGO") {
            		g.setColor(new Color(75 ,0, 130));
            	}else if(Color=="PURPLE") {
            		g.setColor(new Color(255 ,0, 255));
            	}else if(Color=="BLACK") {
            		g.setColor(new Color(0 ,0, 0));
            	}else if(Color=="RAINBOW") {
            		g.setColor(new Color(a ,b, c));
            	}else {
            		g.setColor(new Color(0 ,255, 0));
            	}
                if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }

            }
        }


    }

    public void Eat(){
    	//kill();
        lenght++;
        Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false;
        handler.getWorld().appleOnBoard=false;
        
        switch (direction){
            case "Left":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail = new Tail(this.xCoord+1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail = new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail =new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=0){
                        tail=new Tail(this.xCoord-1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail=new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=0){
                        tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        } System.out.println("Tu biscochito");
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
        }
        if(Apple.isGood==false) {
	       handler.getWorld().body.pollLast();
	       handler.getWorld().playerLocation[tail.x][tail.y] = true;
        }
        else {
 	       handler.getWorld().body.addLast(tail);
 	       handler.getWorld().playerLocation[tail.x][tail.y] = true;
         }
    }
    
    public void kill(){
        lenght = 0;
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;

            }
        }
    }

    public boolean isJustAte() {
        return justAte;
    }

    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }
}
