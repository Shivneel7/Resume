package framework;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = -7150710923108249953L;
	
	public Window(int w, int h, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setResizable(false);
		game.setPreferredSize(new Dimension(w,h));
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		//frame.setLocation(1450, 100); //for my second monitor at home.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
		
		//System.out.println(frame.getWidth() + ":w  h:" +frame.getHeight() +" gw:"+ game.getWidth() + " Height:" + game.getHeight());
	}
}
