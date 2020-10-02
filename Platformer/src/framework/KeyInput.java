package framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gameObjects.Player;

public class KeyInput implements KeyListener, Constants {

	private Player player;
	
	private boolean[] keyDown = new boolean[4];


	public KeyInput(Handler handler) {
		player = handler.player;
	}

	public void keyPressed(KeyEvent e) {

		//		if(e.getKeyCode() == KeyEvent.VK_M) {
		//			try {
		//				BufferedWriter bw = new BufferedWriter(new FileWriter("res/lev.txt"));
		//				bw.write("" + Handler.LEVEL);
		//				System.out.println("saved");
		//				bw.close();
		//			} catch (IOException e) {
		//				e.printStackTrace();
		//			}
		//		}
		if(player.isFly()) {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				player.setDy(-5);
				keyDown[2] = true;

			}else if(e.getKeyCode() == KeyEvent.VK_S) {
				player.setDy(5);
				keyDown[3] = true;
			}
		}else {
			if((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_SPACE) &&  !player.isJumping()) {
				player.setDy(JUMP_HEIGHT);
				player.setJumping(true);

			}
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			player.setDx(-5);
			player.setDirection(-1);
			keyDown[0] = true;

		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			player.setDx(5);
			player.setDirection(1);
			keyDown[1] = true;
		}
	}

	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_A) {
			keyDown[0] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			keyDown[1] = false;
		}
		if(!keyDown[0] && !keyDown[1]) {
			player.setDx(0);
		}
		
		if(player.isFly()) {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				keyDown[2] = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				keyDown[3] = false;
			}
			if(!keyDown[2] && !keyDown[3]) {
				player.setDy(0);
			}
		}
	}

	public void keyTyped(KeyEvent e) {

	}
}
