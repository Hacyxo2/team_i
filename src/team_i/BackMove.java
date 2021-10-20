package team_i;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BackMove {
	ImageIcon backIc = new ImageIcon("image/background1.png");
	ImageIcon backIc2 = new ImageIcon("image/background2.png");
	Image backImg = backIc.getImage();
	Image backImg2 = backIc2.getImage();
	View view;
	int back1X = 0;
	int back2X = backImg.getWidth(null);
	
	public void backgroundMove() {
		back1X--;
		back2X--;
		if(back1X < -(backImg.getWidth(null)-2)) {
			back1X = backImg.getWidth(null);
		}
		if(back2X < -(backImg.getWidth(null)-2)) {
			back2X = backImg.getWidth(null);
		}
	}
	public void background(Graphics g) {
		g.drawImage(backImg, back1X, 0, view);
		g.drawImage(backImg2, back2X, 0, view);
	}
}
