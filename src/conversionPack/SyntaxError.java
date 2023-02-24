package conversionPack;

/**
 * Author: Andrew Roney
 * Project Name: Prefix_to_Postfix_Expression_Converter. (Project 1)
 * Date: 8/17/2022
 * 
 * SyntaxError class creates is a user defined Exception handler with a user defined message.
 */

public class SyntaxError extends Exception{
	
	public SyntaxError(String msg) {
		super(msg);//super class is Exception
	}
}
