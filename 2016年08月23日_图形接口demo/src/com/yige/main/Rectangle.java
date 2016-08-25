package com.yige.main;

public class Rectangle implements Shaper{
	private double a;
	private double b;
	public Rectangle (double a,double b){
		this.a = a;
		this.b = b;
	}
	
	public double area(){
		return a * b;
	}
	
	public double perimeter(){
		return 2 * ( a + b );
	}
}
