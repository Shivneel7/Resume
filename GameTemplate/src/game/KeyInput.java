package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	private Audio test = new Audio("/test.wav");
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		for(int i = 0; i < keyDown.length;i++) {
			keyDown[i] = false;
		}
		
	}
	public void keyPressed(KeyEvent e) {
//		
//		if(e.getKeyCode() == e.VK_SPACE) {
//			test.play();
//		}
//		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			if(temp.getID() == ID.Player) {
				if(e.getKeyCode() == KeyEvent.VK_A) {
					temp.setdx(-5);
					keyDown[0] = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_D) {
					temp.setdx(5);
					keyDown[1] = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_W && !((Player) temp).isJumping()) {
					temp.setdy(-12);
					((Player) temp).setJumping(true);
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if(temp.getID() == ID.Player) {
				if(e.getKeyCode() == KeyEvent.VK_A) 
					keyDown[0] = false;
				
				if(e.getKeyCode() == KeyEvent.VK_D) 
					keyDown[1] = false;
				
				if(!keyDown[0] && !keyDown[1]) 
					temp.setdx(0);
			}
		}
	}

	public void keyTyped(KeyEvent e) {

	}

}
