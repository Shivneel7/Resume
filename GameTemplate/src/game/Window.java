package game;

import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Window{

	public Window(int w, int h, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setResizable(false);
		game.setPreferredSize(new Dimension(w,h));
		frame.add(game);
		frame.pack(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
//		JMenuBar menuBar = new JMenuBar();
//		JMenu menu = new JMenu("Menu");
//		JMenuItem mi = new JMenuItem("menu item");
//		menu.add(mi);
//		menuBar.add(menu);
//		frame.setJMenuBar(menuBar);
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");
		MenuItem mi = new MenuItem("menu item");
		menu.add(mi);
		menuBar.add(menu);
		frame.setMenuBar(menuBar);
		
		frame.setVisible(true);
		game.start();
	}
	
}
