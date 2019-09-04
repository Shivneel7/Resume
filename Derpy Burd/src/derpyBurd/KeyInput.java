package derpyBurd;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import derpyBurd.Game.STATE;

public class KeyInput implements KeyListener {

	Bird bird;
	Game game;
	
	public KeyInput(Bird bird, Game game) {
		this.bird = bird;
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			bird.jump();
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(Game.gameState == STATE.Game){
				Game.gameState = STATE.Paused;
				game.stopBackground();
			}else if(Game.gameState == STATE.Paused) {
				Game.gameState = STATE.Game;
				game.restartBackground();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	
	public void keyTyped(KeyEvent e) {
		
	}
	
}
