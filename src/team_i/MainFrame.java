package team_i;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;


public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Audio backgroundMusic;

	public MainFrame() {
		View view = new View();
		backgroundMusic = new Audio("audio/Challenger.wav", true);
		timer = new Timer();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("네모네모 슈팅게임");
		setSize(Const.gamePan_W, Const.gamePan_H);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int)(screen.getWidth() / 2 - Const.gamePan_W / 2);
		int f_ypos = (int)(screen.getHeight() / 2 - Const.gamePan_H / 2);
		
		setLocation(f_xpos, f_ypos);
		setResizable(false);
	
		JPanel panel = new JPanel();
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		panel.setBackground(Color.BLACK);
		
		getContentPane().add(panel);
		
		panel.setLayout(null);
		JButton start = new JButton("start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        backgroundMusic.start();
				timer.start();
				panel.setVisible(false);
				start.setVisible(false);
				
				getContentPane().add(view);
				view.start();
			}
		});
		start.setBounds(400, 327, 157, 23);
		panel.add(start);
		setVisible(true);
		validate();
		
	}
}