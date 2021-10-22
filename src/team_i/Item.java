package team_i;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Item {
	private Image image;
	private int type;
	private int x;
	private int y;
	ArrayList<Item> imgList = new ArrayList<>();
	View view;
	private long prevtime = 0;
	public Item(Image image, int type, int x, int y) {
		this.image = image;
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	class test1{
		ImageIcon itemIc1 = new ImageIcon("image/block.png");
		Image  item1 = itemIc1.getImage();
	}

	public void itemSetting() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		test1 test1 = new test1();
		//Ư�� �ð� ���� ����
		if ((System.currentTimeMillis() - prevtime > 5000)) {
			imgList.add(new Item(test1.item1, 0, 1000, rand.nextInt(800)));
			prevtime = System.currentTimeMillis();
		}
	}
	public void itemDraw(Graphics g) {
		for (int i = 0; i < imgList.size(); i++) {
			g.drawImage(imgList.get(i).getImage(), imgList.get(i).getX(), imgList.get(i).getY(), view);
			System.out.println(imgList.get(i).getX());
		}
	}
	public void moveItem() {
		for (int i =0; i< imgList.size(); i++) {
			imgList.get(i).setX(imgList.get(i).getX()-1);
			if (imgList.get(i).move() == false)// ȭ���� ����� ���� �ϱ�
			{
				imgList.remove(i);
				break;
			}
		}
	}
	public boolean move() {
		if(x < 0 || x > Const.gamePan_W || y < 0 || y > Const.gamePan_H) {
			return false;				
		}
		return true;
	}
//	public Image setImage(int type) {
//		
//	}
	public Image getImage() {
		return image;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getType() {
		return type;
	}
	public void setImage(Image image) {
		this.image = image;
	} 
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setType(int type) {
		this.type = type;
	}
}
