import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	JFrame frame;	
	
	public Window(int w, int h, String name, Game game) {
		frame = new JFrame(name);
		frame.setResizable(false);
		game.setPreferredSize(new Dimension(w,h));
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		//frame.setLocation(1450, 100); //for my second monitor at home.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
	}
}
