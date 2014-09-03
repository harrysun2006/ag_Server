package com.test;

import javassist.ClassPool;

public class Test2 {

	public static void sayHello() {
		System.out.println("Hello Test2!");
	}

	public static ClassPool getClassPool() {
		return ClassPool.getDefault();
	}
}
