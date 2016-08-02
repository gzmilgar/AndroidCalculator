package com.gzmilgar.calc;

import android.text.InputType;
import android.text.method.NumberKeyListener;

public class CalcNumberKeyListener extends android.text.method.NumberKeyListener {
	private final char[] acceptableChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'+', '-', Constants.DIV_CHAR, Constants.MULT_CHAR, '(', ')', '.', ',', Constants.SQRT_CHAR, Constants.PI_CHAR, 'e', '!', '^'};
	
    @Override
    protected char[] getAcceptedChars()
    {
        return acceptableChars;
    }

	public int getInputType()
	{
		return InputType.TYPE_NULL;
	}
}
