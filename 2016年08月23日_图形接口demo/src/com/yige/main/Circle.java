package com.yige.main;

public class Circle implements Shaper {
	private double r;
	public Circle(double r){
		this.r = r;
	}
	public double area(){
		return Math.PI * r * r;
	}
	
	public double perimeter(){
		return 2 * Math.PI * r;
	}
}
