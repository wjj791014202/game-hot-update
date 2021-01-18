package com.xinyue.gameserver;

public class TestHot {

	public void print() {
		System.out.println("2");
		print2();
		Test2 test2=new Test2();
		test2.test();
	}

	private static void print2(){
		System.out.println("print2");
	}
}
