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
	private View view = new View();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Audio backgroundMusic;
	
	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�׸�׸� ���ð���");
		setSize(Const.gamePan_W, Const.gamePan_H);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int f_xpos = (int)(screen.getWidth() / 2 - Const.gamePan_W / 2);
		int f_ypos = (int)(screen.getHeight() / 2 - Const.gamePan_H / 2);
		
		setLocation(f_xpos, f_ypos);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JButton start = new JButton("start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().add(view);
				panel.setVisible(false);
				start.setVisible(false);
			}
		});
		start.setBounds(461, 367, 57, 23);
		panel.add(start);
		setVisible(true);
		validate();
		backgroundMusic = new Audio("src/audio/sound1.wav", true);
        backgroundMusic.start();
	}
	
}