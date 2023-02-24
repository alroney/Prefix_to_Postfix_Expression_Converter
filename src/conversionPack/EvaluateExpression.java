package conversionPack;

/**
 * Author: Andrew Roney
 * Project Name: Prefix_to_Postfix_Expression_Converter. (Project 1)
 * Date: 8/17/2022
 * 
 * EvaluateExpression class contains the 2 conversion methods, prefix to postfix and postfix to prefix.
 */

import java.util.Stack;

public class EvaluateExpression {
	private static int operand;
	private static int operator;
	
//Method: Identify operators.
	private static boolean isOperator(char token) {
		if(token == '+' || token == '-' || token == '/' || token == '*' || token == '^') {
			return true;
		}
		
		else {
			return false;
		}
	}
	
//Method: Check the length of the expression entered to make sure something was entered.
	private static int checkLength(String expression) throws SyntaxError{
		if(expression.isBlank() || expression.isEmpty()) {
			throw new SyntaxError("No Expression Was Given!");
		}
		
		return expression.length();
	}

//Method: Check the size of the Stack and to throw an error when it is empty.
	private static void checkStackSize(int size) throws SyntaxError{
		if(size == 0) {
			throw new SyntaxError("Stack size is empty!");
		}
	}
	
	
//Method: Check the Operand to Operator ratio and throw error when there are too many operators.
	private static void checkOpRatio(int operandCount, int operatorCount) throws SyntaxError{
		 if((operatorCount >= operandCount) && ((operatorCount > 0) && (operandCount > 0))) {
			 throw new SyntaxError("Too many operators");
		 }
	}
	
	
	/*PREFIX TO POSTFIX CONVERSION*/
	public static String preToPost(String prefixExpression) throws SyntaxError{//Prefix in, Postfix out
		Stack<String> stack = new Stack<String>();//Create the stack
		prefixExpression = prefixExpression.replaceAll("\\s", "");
		int length = checkLength(prefixExpression);
		operator = 0;
		operand = 0;
		
		//Tokenizing the String from Right to Left
		for(int i = length - 1; i >= 0; i--) {
			
			if(isOperator(prefixExpression.charAt(i))) {
				operator++;
				checkStackSize(stack.size());
				checkOpRatio(operand, operator);
				
				
				//Pop two operands from the stack
				String op1 = stack.peek();
				stack.pop();
				String op2 = stack.peek();
				stack.pop();
				
				String temporary = op1 + op2 + prefixExpression.charAt(i);
				
				stack.push(temporary);
			}
			
			//If character is an operand
			else {
				
				stack.push(prefixExpression.charAt(i) + "");//Push the operand to the stack.
				operand++;
			}
			
		}

		
		return stack.pop().replace("", " ").trim();//Stack contains only the Postfix expression.
	}
/*End of Prefix to Postfix Conversion*/
	
	
	
/*POSTFIX TO PREFIX CONVERSION*/
	public static String postToPre(String postfixExpression) throws SyntaxError{//Postfix in, Prefix out
		Stack<String> stack = new Stack<String>();//Create the final conversion Stack.
		postfixExpression = postfixExpression.replaceAll("\\s", "");
		int length = checkLength(postfixExpression);
		operand = 0;
		operator = 0;
		
		//Tokenizing the String from Right to Left
		for(int i = 0; i < length; i++) {
			if(isOperator(postfixExpression.charAt(i))) {
				operator++;
				checkStackSize(stack.size());
				checkOpRatio(operand, operator);
				
				//Pop two operands from the stack.
				String op1 = stack.pop();
				String op2 = stack.pop();
				
				String temporary = postfixExpression.charAt(i) + op2 + op1;//String is holding the characters until ready to perform final pop.
				
				stack.push(temporary);
			}
			
			else {
				stack.push(postfixExpression.charAt(i) + "");//Push operand to the stack
				operand++;
			}
		}
		
		return stack.pop().replace("", " ");
	}
}
