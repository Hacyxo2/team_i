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
		setSize(1000, 600);
		setPreferredSize(getPreferredSize());
		setVisible(true);
		System.out.println("test");
		add(new Screen());
		//현재 commit을 한 상태라 아래 push Head 상태인데
		//이렇게 설정하고 저장을 한다음
		validate();
	}
}