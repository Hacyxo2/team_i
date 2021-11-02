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
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Component;
import java.awt.Insets;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Audio backgroundMusic;
	private JPanel endpanel;

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
		
		endpanel = new JPanel();
		JPanel panel = new JPanel();
		
		JTextPane GameOver = new JTextPane();
		JTextPane txtpnScore = new JTextPane();
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
		endpanel.setBackground(Color.BLACK);
		endpanel.setVisible(false);
		endpanel.setLayout(null);
		GameOver.setMargin(new Insets(10, 10, 10, 10));
		GameOver.setAlignmentX(Component.LEFT_ALIGNMENT);
		GameOver.setFont(new Font("Bahnschrift", Font.PLAIN, 59));
		GameOver.setText("GAME OVER");
		GameOver.setForeground(Color.WHITE);
		GameOver.setBackground(Color.BLACK);
		GameOver.setBounds(300, 200, 350, 100);
		endpanel.add(GameOver);
		
		
		txtpnScore.setText("SCORE :"+ view.player[0].getScore());
		txtpnScore.setMargin(new Insets(10, 3, 10, 10));
		txtpnScore.setForeground(Color.WHITE);
		txtpnScore.setFont(new Font("Bahnschrift", Font.PLAIN, 38));
		txtpnScore.setBackground(Color.BLACK);
		txtpnScore.setAlignmentX(0.0f);
		txtpnScore.setBounds(310, 310, 350, 100);
		endpanel.add(txtpnScore);
		
		setVisible(true);
		validate();
		
	}
	public JPanel getEndpanel() {
		return endpanel;
	}
	public Audio geta() {
		return backgroundMusic;
	}
}