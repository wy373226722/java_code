package entity;

import java.awt.Image;

public class Cell {
	private int x;
	private int y;
	private Image image;
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
	
	public Cell(int x, int y, Image image) {
		super();
		this.x = x;
		this.y = y;
		this.image = image;
	}
	public Cell() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	
}
