/*
 * Postfix
 *
 * Version 1.0
 *
 * All Copyright Reserved by 刘彦超 
 */
import java.io.*;

class Parser {
    
	/**
	 * Lex parser class
	 */
    
    static int lookahead;
    
    public Parser() throws IOException {
	lookahead = System.in.read();
    }
    
    void expr() throws IOException {
	term();
	rest();
    }
    
    //tail recursion to while statement 
    void rest() throws IOException {
	
	while (lookahead != 13) {
	    if (lookahead == '+') {
		match('+');
		term();
		System.out.write('+');
		// rest();
	    } else if (lookahead == '-') {
		match('-');
		term();
		System.out.write('-');
		// rest();
	    } else {
		// extra space resolve
		if ((char) lookahead == ' ')
		    match(' ');
		else {
		    // Missing Operator error resolve
		    System.out.print((char) lookahead);
		    System.out.print("(MissingOperatorError)");
		    match(lookahead);
		}
	    }
	}
    }
    
    void term() throws IOException {
	// extra space resolve
	while ((char) lookahead == ' ') {
	    match(' ');
	}
	// Check operand & error resolve
	if (Character.isDigit((char) lookahead)) {
	    System.out.write((char) lookahead);
	    match(lookahead);
	} else if ((char) lookahead == '+' || (char) lookahead == '-'
		   || lookahead == 13) {
	    System.out.print("(MissingOperandError)");
	} else {
	    System.out.print("(IllegalSymbolError)");
	    match(lookahead);
	}
    }
    
    void match(int t) throws IOException {
	if (lookahead == t)
	    lookahead = System.in.read();
	else
	    throw new Error("syntax error");
    }
}

public class Postfix {
    
    /**
     * Main Class
     */
    public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	System.out
	    .println("Input an infix expression and output its postfix notation:");
	new Parser().expr();
	System.out.println("\nEnd of program.");
	
    }
    
}
