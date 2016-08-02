package com.gzmilgar.calc;

import java.util.Arrays;
import java.util.HashSet;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.KeyEvent;
import android.view.View;

public class Calc extends Activity {
	private final char[] repChars = {'+', Constants.DIV_CHAR, Constants.MULT_CHAR, '^'};
	private final char[] nonClearChars = {'+', '-', Constants.DIV_CHAR, Constants.MULT_CHAR, '^', '!'};
	private final char[] doubles = {'.'};
	private final String[] masterClearValues = { Constants.NAN_STRING, Character.toString(Constants.INFINITY_CHAR) };
	
	private final CalcNumberKeyListener keyL = new CalcNumberKeyListener();
	
	private HashSet<String> masterClearSet;
	private boolean clearItFirst = false;
	private boolean masterClear = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        EditText output = (EditText)findViewById(R.id.output);

        //anahtar geçersiz kılma
        output.setKeyListener(keyL);

        //odaklma
        output.setSelected(true);


        Typeface font = Typeface.createFromAsset(getAssets(), "outputfont.ttf");
        output.setTypeface(font);


        masterClearSet = new HashSet<String>(Arrays.asList(masterClearValues));
    }


    public void buttonClickListener(View v)
    {
    	//girdi olarak yeni karakter alma
    	ImageButton b = (ImageButton)v;
    	String newChars = b.getTag().toString();
    	char lastNewChar = newChars.charAt(newChars.length() - 1);
    	char firstNewChar = newChars.charAt(0);


    	if (masterClear || (clearItFirst && !Helper.charInList(firstNewChar, nonClearChars)))
    	{
    		clearListener(v);
    	}

    	masterClear = false;
    	clearItFirst = false;


    	EditText output = (EditText) findViewById(R.id.output);


    	String currText = output.getText().toString();


    	int start = Math.min(output.getSelectionStart(), output.getSelectionEnd());
    	int end = Math.max(output.getSelectionStart(), output.getSelectionEnd());


    	if (currText.length() > 0)
    	{
    		char toCheck = output.getText().charAt(Math.max(0, end - 1));
    		if (!addOrReplace(toCheck, lastNewChar))
    		{

	    		start--;
    		}
    	}


    	output.setKeyListener(null);


    	try
    	{
    		output.getText().replace(start, end, newChars);
    	}
    	catch(Exception e)
    	{

    	}


    	output.setKeyListener(keyL);
    }


    public void equalsListener(View v)
    {

    	if (masterClear)
    	{
    		clearListener(v);
    	}
    	
    	setOutput(getCalcResult());
    }
    

    public void clearListener(View v)
    {    	
    	EditText output = (EditText) findViewById(R.id.output);
    	output.setText("");
    }
    

    public void backspaceListener(View v)
    {    	
    	EditText output = (EditText) findViewById(R.id.output);
    	keyL.backspace(output, output.getEditableText(), 0, new KeyEvent(0, 0));
    }
    
    //MC butonu
    public void MCListener(View v)
    {
    	Memory.clear();
    }
    
    //MR butonu
    public void MRListener(View v)
    {    	
    	setOutput(Helper.doubleFormatted(Memory.recall()));
    }
    
    //M+ butonu
    public void MPlusListener(View v)
    {
    	//=
    	equalsListener(v);

    	try
    	{
    		Memory.plus(Double.parseDouble(getCalcText()));
		}
		catch (Exception e)
		{

			clearListener(v);
		}
    }

    //M- butonu
    public void MMinusListener(View v)
    {
    	//=
    	equalsListener(v);
    	
    	try
    	{
    		Memory.minus(Double.parseDouble(getCalcText()));
    	}
    	catch (Exception e)
    	{

    		clearListener(v);
    	}
    }
    

    private void setOutput(String val)
    {
    	EditText output = (EditText) findViewById(R.id.output);


    	output.setKeyListener(null);
    	
    	try
    	{

	    	output.setText(val);
	    	output.setSelection(output.getText().length());
		}
		catch(Exception e)
		{
			
		}
    	

    	output.setKeyListener(keyL);
    	

    	clearItFirst = true;
    	masterClear = masterClearSet.contains(output.getText().toString());
    }
    

    private String getCalcResult()
    {

    	String correctedText = Helper.addParentheses(getCalcText());
    	

    	return CalcGrammar.evalGrammar(correctedText);
    }
    

    private String getCalcText()
    {

    	EditText output = (EditText) findViewById(R.id.output);
    	String calcText = output.getText().toString();

    	return calcText;
    }


    private boolean addOrReplace(char lastChar, char toAdd)
    {
    	boolean add = true;
    	

    	if (Helper.charInList(lastChar, repChars) && Helper.charInList(toAdd, repChars))
    	{
    		add = false;
    	}
    	

    	for (char c : doubles)
    	{
    		if (c == lastChar && c == toAdd)
    		{
    			add = false;
    			break;
    		}
    	}
    	
    	return add;
    }
}