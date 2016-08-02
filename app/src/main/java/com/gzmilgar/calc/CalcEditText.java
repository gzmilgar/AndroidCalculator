package com.gzmilgar.calc;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

public class CalcEditText extends EditText {
	private boolean drawn = false;
	
	public CalcEditText(Context context) {
		super(context);
	}

	public CalcEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CalcEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		

		if (!drawn)
		{
			drawn = true;
			this.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int)(0.6 * (double)this.getHeight()));
		}
	}
}
