package team_i;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Item {
	private Image image;
	private int type;
	private int x;
	private int y;
	ArrayList<Item> imgList = new ArrayList<>();
	View view;
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
//	public void itemSetting(){
		
//	}
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
	
	public void itemDraw(Graphics g) {
		for (int i = 0; i < imgList.size(); i++) {
			g.drawImage(imgList.get(i).getImage(), imgList.get(i).getX(), imgList.get(i).getY(), view);
			System.out.println(imgList.get(i).getX());
		}
	}
	
	
	public void moveItem() {
		for (int i =0; i< imgList.size(); i++) {
			imgList.get(i).setX(imgList.get(i).getX()-1);
			if (imgList.get(i).move() == false)// 화면을 벗어나면 삭제 하기
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
}
