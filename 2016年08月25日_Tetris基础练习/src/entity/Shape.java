package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class Shape {
	public static Image LImage,IImage,JImage,OImage,SImage,TImage,ZImage;
	public static final int CELL_SIZE=26;
	public static final int WALL_OFFSET=15; 
	static{
		try {
			LImage = ImageIO.read(Shape.class.getResource("L.png"));
			IImage = ImageIO.read(Shape.class.getResource("I.png"));
			JImage = ImageIO.read(Shape.class.getResource("J.png"));
			OImage = ImageIO.read(Shape.class.getResource("O.png"));
			SImage = ImageIO.read(Shape.class.getResource("S.png"));
			TImage = ImageIO.read(Shape.class.getResource("T.png"));
			ZImage = ImageIO.read(Shape.class.getResource("Z.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected List<Cell> cells = new ArrayList<Cell>();
	public static Shape getShape(){
		int index = new Random().nextInt(7);
		if(index==0) return new L();
		if(index==1) return new I();
		if(index==2) return new J();
		if(index==3) return new O();
		if(index==4) return new S();
		if(index==5) return new T();
		if(index==6) return new Z();
		return null;
	}
	public void draw(Graphics g) {		
		for(Cell c : this.cells){
			if(c.getX()<0||c.getX()>9)continue;
			if(c.getY()<0||c.getY()>19)continue;
			g.drawImage(c.getImage(), c.getX() * CELL_SIZE+WALL_OFFSET, c.getY()* CELL_SIZE+WALL_OFFSET,null);			
		}
	}
	public void down() {
		for(Cell c : this.cells) c.setY(c.getY()+1);		
	}
}

class L extends Shape{
	public L() {
		super.cells.add(new Cell(4,1,LImage));
		super.cells.add(new Cell(4,0,LImage));
		super.cells.add(new Cell(4,-1,LImage));
		super.cells.add(new Cell(5,1,LImage));
	}
}

class T extends Shape{
	public T() {
		super.cells.add(new Cell(4,1,TImage));
		super.cells.add(new Cell(3,1,TImage));
		super.cells.add(new Cell(5,1,TImage));
		super.cells.add(new Cell(4,0,TImage));
	}
}

class O extends Shape{
	public O() {
		super.cells.add(new Cell(4,1,OImage));
		super.cells.add(new Cell(4,0,OImage));
		super.cells.add(new Cell(5,1,OImage));
		super.cells.add(new Cell(5,0,OImage));
	}
}

class J extends Shape{
	public J() {
		super.cells.add(new Cell(4,1,JImage));
		super.cells.add(new Cell(5,1,JImage));
		super.cells.add(new Cell(4,2,JImage));
		super.cells.add(new Cell(4,3,JImage));
	}
}

class I extends Shape{
	public I() {
		super.cells.add(new Cell(4,1,IImage));
		super.cells.add(new Cell(4,0,IImage));
		super.cells.add(new Cell(4,2,IImage));
		super.cells.add(new Cell(4,3,IImage));
	}
}

class S extends Shape{
	public S() {
		super.cells.add(new Cell(4,1,SImage));
		super.cells.add(new Cell(3,1,SImage));
		super.cells.add(new Cell(3,0,SImage));
		super.cells.add(new Cell(4,2,SImage));
	}
}

class Z extends Shape{
	public Z() {
		super.cells.add(new Cell(4,1,ZImage));
		super.cells.add(new Cell(4,0,ZImage));
		super.cells.add(new Cell(3,1,ZImage));
		super.cells.add(new Cell(3,2,ZImage));
	}
}

