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
		//���� commit�� �� ���¶� �Ʒ� push Head �����ε�
		//�̷��� �����ϰ� ������ �Ѵ���
		validate();
	}
}