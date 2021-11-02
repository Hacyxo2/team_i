package team_i;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BackMove {
	static int stage = 1;
	static int bmSpeed = 2;
	ImageIcon backIc = new ImageIcon("image/background1.png");
	ImageIcon backIc2 = new ImageIcon("image/background2.png");
	Image backImg = backIc.getImage();
	Image backImg2 = backIc2.getImage();
	Color backFade = new Color(0, 0, 0, 0);
	View view;
	int back1X = 0;
	int back2X = backImg.getWidth(null);
	
	public void backgroundMove() {
		back1X--;
		back2X--;
		if(back1X < -(backImg.getWidth(null)-bmSpeed)) {
			back1X = backImg.getWidth(null);
		}
		if(back2X < -(backImg.getWidth(null)-bmSpeed)) {
			back2X = backImg.getWidth(null);
		}
	}
	public void background(Graphics g) {
		g.drawImage(backImg, back1X, 0, view);
		g.drawImage(backImg2, back2X, 0, view);
	}

	public void fadeOutRed() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 256; i += 2) {
					backFade = new Color(0, 0, 0, i);
					try {
						System.out.println("tlqkf");
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 255; i >= 0; i -= 2) {
					backFade = new Color(0,0,0,i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void fadeOutGraphics(Graphics g) {
		g.setColor(backFade);
		g.fillRect(0, 0, Const.gamePan_W, Const.gamePan_H);
	}
}