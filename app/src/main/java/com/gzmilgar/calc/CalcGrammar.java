//class to contain all ANTLR grammar references
package com.gzmilgar.calc;

import org.antlr.runtime.*;

public class CalcGrammar {
	
	public CalcGrammar() {}

	public static String evalGrammar(String input)
	{
		String expr = "";
		
		//ayrıştırmak için
    	CharStream cs = new ANTLRStringStream(input);
    	com.gzmilgar.calc.CalcLexer cLex = new com.gzmilgar.calc.CalcLexer(cs);
    	CommonTokenStream tokens = new CommonTokenStream();
    	tokens.setTokenSource(cLex);
    	com.gzmilgar.calc.CalcParser cParse = new com.gzmilgar.calc.CalcParser(tokens);
    	
    	try {
    		//ve geri dizeye
			cParse.ultimate();
			expr = cParse.result;
		} catch (RecognitionException e) {
			expr = "Error";
		}
		
		return expr;
	}
}
