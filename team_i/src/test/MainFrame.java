package test;

import java.awt.Dimension;
import java.awt.Toolkit;


import javax.swing.JFrame;

public class MainFrame extends JFrame{
	private View view = new View();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("tlqkf");
		setSize(Const.gamePan_W, Const.gamePan_H);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		int f_xpos = (int)(screen.getWidth() / 2 - Const.gamePan_W / 2);
		int f_ypos = (int)(screen.getHeight() / 2 - Const.gamePan_H / 2);
		
		setLocation(f_xpos, f_ypos);
		setResizable(false);
		setVisible(true);
		add(view);
		
		validate();
	}
}