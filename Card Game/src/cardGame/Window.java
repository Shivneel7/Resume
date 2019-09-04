package cardGame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Window extends Canvas{

	private static final long serialVersionUID = -8255319694373975038L;
	private JMenuBar menuBar;	
	
	public Window(String name, int w, int h, Game game) {
		JFrame frame = new JFrame(name);
		frame.setResizable(false);
		game.setPreferredSize(new Dimension(w,h));
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		//frame.setLocation(1450, 100); //for my second monitor at home.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(addMenu());
		
		frame.setVisible(true);
		game.start();
	}

	public JMenuBar addMenu() {
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		JMenuItem restart = new JMenuItem("New Game");
		JMenuItem hint = new JMenuItem("Hint");
		hint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.SHIFT_MASK));
		
		restart.setActionCommand("restart");
		hint.setActionCommand("hint");
		
		menu.add(restart);
		menu.addSeparator();
		menu.add(hint);
		
		menuBar.add(menu);
		return menuBar;
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
}
