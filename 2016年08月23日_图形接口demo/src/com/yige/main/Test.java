package com.yige.main;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		System.out.print("圆的半径：" );
		double r;
		r=extracted().nextInt();
		Scanner x= extracted();
		System.out.print("矩形长：" );
		double a;
		a=x.nextInt();
		Scanner y = extracted();
		System.out.print("矩形宽：" );
		double b;
		b=y.nextInt();

		Circle circle = new Circle(r);
		Rectangle rectangle = new Rectangle(a,b);
		
		System.out.println("圆的面积：" + Utils.getArea(circle));
		System.out.println("圆的周长：" + Utils.getPerimeter(circle));
		System.out.println("矩形的面积：" + Utils.getArea(rectangle));
		System.out.println("矩形的周长：" + Utils.getPerimeter(rectangle));
	}

	private static Scanner extracted() {
		return new Scanner(System.in);
	}

}
