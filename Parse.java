

import java.util.Scanner;

/* 
 * Class for parsing (recognizing) simple prefix expressions
 * Recursive descent parser for grammar:
 * <expression> = <ID> | <operator> <expression> <expression>
 * <operator> = * | + | - | \
 * <ID> = a | b | c
 * 
 * Code written by Adele Howe for CS200
 * Edited by Tarequl
 */
public class Parse {
	private String nextIn;
	private Scanner scexp;
	
	public Parse(String sentence) {
		// set up iteration over symbols in the sentence
		scexp = new Scanner(sentence);
		nextToken();	
	}
	
	private boolean start() {
		if (expression()) {	
			if (nextIn.equals("")) { 
				return true;	
			}
			else { return false; }  
		}
		else { return false; }
	}
	

	
    /* Each method returns true or false for whether current part is legal
     * <expression> = <ID> | <operator> <expression> <expression>
     */
	private boolean expression() {
    	// try the two different parts of left hand side of rule		
    	if (ID()) { return true; }
    	else {
    		// rule <expression> = <operator> <expression> <expression>
    		if(operator() && expression() && expression()){
    			return true;
    		}else{
    			return false;
    		}
    	}
    }
    
	/* rule is
	 * <ID> = a | b | c
	 */
    private boolean ID() {    	
    	if (nextIn.equals("a") || nextIn.equals("b") || nextIn.equals("c")) {
        	nextToken();
        	return true;
    	}
	return false;
    }
    /* rule is
     * <operator> = + | - | * | /
     */
    private boolean operator() {
    	if (nextIn.equals("*") || nextIn.equals("-") || nextIn.equals("+")|| nextIn.equals("/")) {
    		nextToken();
    		return true;
    	}
	return false;
    }
    

    /* 
     * Main allows us to test the class
     */
	public static void main(String[] args) {
		// legal example
//		String string1 = "* a c";
		
		// illegal example
		//String string1 = "* a b c";
		
		// what about these...
		//String string1 = "a * b";
		String string1 = "* + a b - c / a b";
		//String string1 = "a ";
		Parse newInput = new Parse(string1);
		System.out.println("String " + string1 + " is legal? " + newInput.start());

	}


	private void nextToken(){
		if (scexp.hasNext()) {
        		nextIn = scexp.next();
        	} 
    		else { nextIn = ""; }
	}
	

	private boolean isInteger(String s){
		return s.matches("\\d+");
	}
}
