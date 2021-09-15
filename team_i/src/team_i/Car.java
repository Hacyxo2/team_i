package team_i;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Car1 {
		private int x = 0;
		private int y = 0;
		private BufferedImage image;
		
		public Car1(int x, int y, String imagePath){
			this.x = x;
			this.y = y;
			try {
				this.image =ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public BufferedImage getImage() {
			return image;
		}
		public void setX(int x) {
			this.x = x;
		
		}
		public void setY(int y) {
			this.y = y;
		}
		public void setImage(BufferedImage image) {
			this.image = image;
		}
}
