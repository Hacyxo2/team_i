package test;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Block {
	private int x = 0;
	private int y = 0;
	private Image image;
	
	public Block() {
		try {
			image = ImageIO.read(new File("image/block.png"));
			image = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g, View view) {
		g.drawImage(image, x, y, view);
	}
}