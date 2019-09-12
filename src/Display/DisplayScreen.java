package Display;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by AlexVR on 7/1/2018.
 */

public class DisplayScreen {

    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width, height;

    public DisplayScreen(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;



        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(Color.black);

        try {
            frame.setIconImage(ImageIO.read(new File("res/Sheets/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        String backgroundColor = Game.Entities.Dynamic.Player.Color;
        if(backgroundColor=="RED") {
    		canvas.setBackground(new Color(51, 255, 51));
    	}else if(backgroundColor=="ORANGE") {
    		canvas.setBackground(new Color(0, 0,255));
    	}else if(backgroundColor=="YELLOW") {
    		canvas.setBackground(new Color(255, 0, 255));
    	}else if(backgroundColor=="LIME") {
    		canvas.setBackground(new Color(255, 0, 0));
    	}else if(backgroundColor=="SKYBLUE") {
    		canvas.setBackground(new Color(0, 0, 0));
    	}else if(backgroundColor=="BLUE") {
    		canvas.setBackground(new Color(255, 153, 51));
    	}else if(backgroundColor=="INDIGO") {
    		canvas.setBackground(new Color(255, 153, 51));
    	}else if(backgroundColor=="PURPLE") {
    		canvas.setBackground(new Color(255, 153, 51));
    	}else if(backgroundColor=="BLACK") {
    		canvas.setBackground(new Color(255, 255, 255));
    	}else if(backgroundColor=="RAINBOW") {
    		canvas.setBackground(new Color(255, 255, 255));
    	}else {
    		canvas.setBackground(new Color(255, 0, 255));
    	}

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }

}
