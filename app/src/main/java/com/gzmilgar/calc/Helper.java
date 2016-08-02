package com.gzmilgar.calc;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import cern.jet.stat.*;


public class Helper
{
	//karakter dizisindeki karakter nedir
	public static boolean charInList(char check, char[] list)
    {
    	boolean retVal = false;
    	
    	for (char c : list)
    	{
    		if (c == check)
    		{
    			retVal = true;
    			break;
    		}
    	}
    	
    	return retVal;
    }
    
	//tamamlanmamış bir dizinin başına veya sonuna parantez ekler
    public static String addParentheses(String text)
    {
    	int numOpenParens = 0, numCloseParens = 0;
    	
    	//Parantez için arama dizesi
    	for (char c : text.toCharArray())
    	{
    		if (c == '(')
    		{
    			numOpenParens++;
    		}
    		else if (c == ')')
    		{
    			numCloseParens++;
    		}
    	}
    	
    	int diffParens = numOpenParens - numCloseParens;
    	
    	//sayısına bağlı olarak parantez açma / kapama  için bir dizi oluştur
    	String textCorrected = "";
    	if (diffParens == 0)
    	{
    		textCorrected = text;
    	}
    	else if (diffParens < 0)
    	{
    		//Başlangıçta açık parantez ekleme
    		String parens = repeatingChars('(', -diffParens);
    		textCorrected = parens + text;
    	}
    	else
    	{
    		//sonuna kapalı parantez ekleme
    		String parens = repeatingChars(')', diffParens);
    		textCorrected = text + parens;
    	}
    	
    	return textCorrected;
    }
    
    //desimal tür dönüşümü
    public static double DecimalToDouble(String dec)
    {

    	String commaReplaced = dec.replace(',', '.');
    	return Double.parseDouble(commaReplaced);
    }
    
    //karakterleri geri döndürür
    public static String repeatingChars(char toRepeat, int number)
    {
    	char[] chars = new char[number];
    	Arrays.fill(chars, toRepeat);
    	return new String(chars);
    }

	//belirli bir hassasiyetle double a yuvarlar
    public static double round(double d, int decimalPlace)
    {
    	try
    	{
	        BigDecimal bd = new BigDecimal(Double.toString(d));
	        bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
	        return bd.doubleValue();
    	}
    	catch (NumberFormatException e)
    	{
    		// +/- geri döndürüyor
    		return d;
    	}
    }
    

    public static double boundedGamma(double val)
    {
    	double retVal = Double.NaN;
    	
    	if (val < 0.0)
    	{
    		retVal = Double.NaN;
    	}
    	else if (val > 200.0)
    	{
    		retVal = Double.POSITIVE_INFINITY;
    	}
    	else
    	{
    		retVal = Gamma.gamma(val);
    	}
    	
    	return retVal;
    }
    
    //faktöriyel
    public static double factorial(double val, int n)
    {
    	double retVal = boundedGamma(val + 1.0);
    	
    	if (n > 1)
    	{
    		//yenileniyor
    		retVal = factorial(retVal, n - 1);
    	}
    	
    	return retVal;
    }
    
    //karekök
    public static double squareRoot(double val, int n)
    {
    	double retVal = Math.sqrt(val);
    	
    	if (n > 1)
    	{

    		retVal = squareRoot(retVal, n - 1);
    	}
    	
    	return retVal;
    }
    
    //çarpma
    public static double multiplyList(List<Double> list)
    {

    	double retVal = 1.0;
    	

    	for (double val : list)
    	{
    		retVal *= val;
    	}
    	
    	return retVal;
    }
    

    public static String doubleFormatted(double val)
    {

    	if (Double.isNaN(val))
    	{
    		return Constants.NAN_STRING;
    	}
    	
	    NumberFormat formatter;
	    double absVal = Math.abs(val);
	    if (absVal != 0.0 && (absVal > 10000000000000.0 || absVal < 0.000000000001))
	    {
	    	formatter = new DecimalFormat("#.#########E0");
	    }
	    else
	    {
	    	formatter = new DecimalFormat("#.###########");
	    }
	    
	    return formatter.format(val);
    }
}
