package com.gzmilgar.calc;

//M sınıfı fonksiyonları için
public class Memory {
	private static double value = 0.0;
	
	public static void plus(double val)
	{
		value += val;
	}
	
	public static void minus(double val)
	{
		value -= val;
	}
	
	public static void clear()
	{
		value = 0.0;
	}
	
	public static double recall()
	{
		return value;
	}
}
