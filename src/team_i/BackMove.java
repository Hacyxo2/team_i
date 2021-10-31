package team_i;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BackMove {
	int type = 1;
	ImageIcon backIc = new ImageIcon("image/background1.png");
	ImageIcon backIc2 = new ImageIcon("image/background2.png");
	Image backImg = backIc.getImage();
	Image backImg2 = backIc2.getImage();
	View view;
	int back1X = 0;
	int back2X = backImg.getWidth(null);
	Color backFade = new Color(0,0,0,0);
	private AlphaComposite alphaComposite; 
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
		Graphics2D g2 = (Graphics2D)g;
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)255/255);
		g2.setComposite(alphaComposite);
		g.drawImage(backImg, back1X, 0, view);
		g.drawImage(backImg2, back2X, 0, view);
	}

	public void fadeOut() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (true) {
					for (int i = 0; i < 256; i += 20) {
						backFade = new Color(150, 150, 150, i);
						try {
							setImageIcon();
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					for (int i = 255; i >= 0; i -= 10) {
						backFade = new Color(150, 150, 150, i);
						try {
							setImageIcon();
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
	public void setImageIcon() {
		this.backIc = new ImageIcon("image/background1.png");
		this.backImg = backIc.getImage();
	}
	public void fadeOutGraphics(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)255/255);
		g2.setComposite(alphaComposite);
		g.setColor(backFade);
		g.fillRect(0, 0, Const.gamePan_W, Const.gamePan_H);
	}
}