package team_i;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Play Car");
		setSize(1200, 600);
		setPreferredSize(getPreferredSize());
		setVisible(true);
		
		add(new Screen());
		
		validate();
	}
}