package com.yige.main;

public class Singleton {

	private static Singleton instance = new Singleton();

	private Singleton() {
		System.out.println("11");
	}

	public static Singleton getInstance() {
		return instance;
	}
}
