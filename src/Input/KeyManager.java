package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class KeyManager implements KeyListener {

	private boolean[] keys,justPressed,cantPress;
	public boolean up=false, down=false, left=false, right=false, debug_eat=false, debug_speedplus=false, debug_speedslow=false,changetoblue=false,changetored=false,changetoorange=false,changetoblack=false,changetorainbow=false,changetoindigo=false;
	public boolean pbutt=false;


	public KeyManager(){

		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];

	}

	public void tick(){
		for(int i =0; i < keys.length;i++){
			if(cantPress[i] && !keys[i]){
				cantPress[i]=false;

			}else if(justPressed[i]){
				cantPress[i]=true;
				justPressed[i] =false;
			}
			if(!cantPress[i] && keys[i]){
				justPressed[i]=true;
			}
		}

		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		debug_eat = keys[KeyEvent.VK_N];
		debug_speedplus = keys[KeyEvent.VK_EQUALS];
		debug_speedslow = keys[KeyEvent.VK_MINUS];
		pbutt = keys[KeyEvent.VK_ESCAPE];
		changetoblue = keys[KeyEvent.VK_1];
		changetored = keys[KeyEvent.VK_2];
		changetoorange=keys[KeyEvent.VK_3];
		changetoblack=keys[KeyEvent.VK_4];
		changetorainbow=keys[KeyEvent.VK_5];
		changetoindigo=keys[KeyEvent.VK_6];

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}

}
