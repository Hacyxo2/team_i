package team_i;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	private View view = new View();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public MainFrame() {
		JButton start = new JButton("ㅎㅇ");
		start.setBounds(0,0,10,10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("tlqkf");
		setSize(Const.gamePan_W, Const.gamePan_H);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		add(start);
		int f_xpos = (int)(screen.getWidth() / 2 - Const.gamePan_W / 2);
		int f_ypos = (int)(screen.getHeight() / 2 - Const.gamePan_H / 2);
		
		setLocation(f_xpos, f_ypos);
		setResizable(false);
		setVisible(true);
		validate();
		start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	start.setVisible(false);
            	add(view);
                 // 창 안보이게 하기 
            }
        });
	}
}