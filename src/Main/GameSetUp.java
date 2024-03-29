package Main;

import Display.DisplayScreen;
import Game.Entities.Dynamic.Player;
import Game.GameStates.GameState;
import Game.GameStates.MenuState;
import Game.GameStates.PauseState;
import Game.GameStates.State;
import Game.GameStates.GameOverState;
import Input.KeyManager;
import Input.MouseManager;
import Resources.Images;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class GameSetUp implements Runnable {
    private DisplayScreen display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;



    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Handler
    private Handler handler;

    //States
    public State gameState;
    public State menuState;
    public State pauseState;
    public State gameoverState;

    //Res.music
    private InputStream audioFile;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;

    private BufferedImage loading;

    public GameSetUp(String title, int width, int height){

        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }

    private void init(){
        display = new DisplayScreen(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Images img = new Images();


        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        pauseState = new PauseState(handler);
        gameoverState = new GameOverState(handler);

        State.setState(menuState);

        try {

            audioFile = getClass().getResourceAsStream("/music/nature.wav");
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void reStart(){
        gameState = new GameState(handler);
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        //this runs the run method in this  class
        thread = new Thread(this);
        thread.start();
    }

    public void run(){

        //initiallizes everything in order to run without breaking
        init();

        //int fps = 60; THESE TWO LINES WERE MEANT TO BE DELETED BUTTT JUST IN CASE WE DO 
        //double timePerTick = 1000000000 / FPS; SPEED DIFFERENTLY, I KEPT IT IN A COMMENT
        
        
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
        	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            	GameState.setState(pauseState);//This pauses the game 
            }
        	if(Player.DeathApple==true) {
            	State.setState(gameoverState);
            	Player.DeathApple=false;
            	
        	}
            if(Player.collide==true) {
            	State.setState(gameoverState); // If the player collides with his body it will return to the main screen
            	
            }
        	
            //makes sure the games runs smoothly at 60 FPS
            now = System.nanoTime();
            double timePerTick = 1000000000 / Game.Entities.Dynamic.Player.Speed; //This is moved/added here so that it changes based on the increase of the Speed value. If we do speed differently we delete this.
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1){
                //re-renders and ticks the game around 60 times per second
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    private void tick(){
        //checks for key types and manages them
        keyManager.tick();

        //game states are the menus
        if(State.getState() != null)
            State.getState().tick();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);

        //Draw Here!

        g.drawImage(loading ,0,0,width,height,null);
        if(State.getState() != null)
            State.getState().render(g);
        Font Timesfont=new Font("TimesRoman",Font.BOLD,15);
        g.setFont(Timesfont);
        g.setColor(Color.black);
        g.drawString("Score: " + Game.Entities.Dynamic.Player.score, 5, 11); // This draws the score on the screen. 
        g.drawString("Apples: " + Game.Entities.Dynamic.Player.Counter, 5, 28); // This draws the number of apples collected on the screen.
        g.drawString("Moves: " + Game.Entities.Dynamic.Player.MoveCount+"/25", 5, 42);
        
        //End Drawing!
        bs.show();
        g.dispose();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}

