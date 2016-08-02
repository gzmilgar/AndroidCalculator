package com.gzmilgar.calc;
import android.content.Context;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.widget.ImageButton;

public class Button extends ImageButton {
	private final int haptic = HapticFeedbackConstants.VIRTUAL_KEY;
	
	public Button(Context context) {
		super(context);
	}

	public Button(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Button(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		//titreşim özülliği
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			this.performHapticFeedback(haptic);
		}
		
		return super.onTouchEvent(event);
	}
}
