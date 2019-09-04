package derpyBurd;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = 74560832650794206L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.add(game);
		game.requestFocus();
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		game.start();
	}
	
	
}
