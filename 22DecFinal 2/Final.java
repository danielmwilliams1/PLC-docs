import java.util.*;
import java.io.*;


// SIMILAR TO LazyLex-1 (main.c) / also font.c from the book
// kb = keyboard


public class Final {

	// Global Variables (Variables that are used in multiple functions) // similar to LazyLex-1 (main.c)
	public static char lexeme[] = new char[100];
	public static char nextChar;
	public static int lexLen;
	public static int token;
	public static int nextToken;
	public static String text;
	//charClass (Character Classes)
	public static int charClass;
	public static final int LETTER = 50; // letter
	public static final int DIGIT = 51; // digit
	public static final int UNKNOWN = 99; // unknown character
	public static final char EOF = '^'; // End of File character
	// Token Codes
	public static int MY_ADDOP = 1;	// add operator
	public static int MY_SUBOP = 2; // sub operator
	public static int MY_MULOP = 3; // mul operator
	public static int MY_DIVOP = 4; // div operator
	public static int MY_MODOP = 5; // mod operator
	public static int LTHAN = 6; // less than operator
	public static int GTHAN = 7; // greater than operator
	public static int LESSTHANOREQUAL = 8; // less than or equal to operator
	public static int GREATERTHANOREQUAL = 9; // greater than or equal to operator
	public static int EQUALS_OP = 10; // equal to operator
	public static int NOTEQUAL_OP = 11; // not equal to operator
	public static int ASSIGN_OP = 12;	// assignment operator
	public static int LEFT_PAREN = 13; // left parenthesis
	public static int RIGHT_PAREN = 14;	// right parenthesis
	public static int SEMICOLON = 15;	// semicolon
	public static int COMMA = 16;	// comma
	public static int LEFT_BRACE = 17;	// left brace
	public static int RIGHT_BRACE = 18;	// right brace
	public static int INT = 19;	// integer
	public static int INT_LIT = 20; // integer literal
	public static int IDENT = 21;	// identifier

	public static void main(String[] args) throws IOException {

		// try out the 4 additional test (a-b), first 1-4 r from prior test files
		/* 
        ///File testFile = new File("Tested1.txt"); // TestFile.txt
		//File testFile = new File("TestERROR_2.txt"); // TestERROR_2.txt
		File testFile = new File("test5.txt");	// TestERROR.txt
		//File testFile = new File("Tested2.txt");	// Tested2.txt
		//File testFile = new File("test4.txt");
        /* */
       // File testFile = new File("Testa.txt");
        //File testFile = new File("Testb.txt");
        //File testFile = new File("Testc.txt");
        File testFile = new File("Testd.txt");
		Scanner kb = new Scanner(testFile); // Scanner to read the file

		int row = 0;
		// Error if test file is empty
		if (testFile.length() == 0) {
			System.out.println("Error.... ERROR this is an unreadable file");
		} else {
			// Read the file line by line
			while (kb.hasNext()) { 		// while there is a next line
				text = kb.nextLine();	// get the next line
				System.out.println("Row " + row + ": " + text);	// print the line
				text = text + '^';	// add the end of file character
				row++;	// increment the row

				getChar();	// get the first character of the line

				do {	// do while the next character is not the end of file character
					lex();	// lex the line
					expr();	// parse the line
				} while (nextToken != '^');	// while the next character is not the end of file character

				System.out.println("*******************************************************");	// print a line to separate the rows
			}
		}
		kb.close();	// close the scanner
	} // end of main

	/***
	 * lookup(): Computes the token code for single-character tokens and arithmetic
	 * operators || look up operators and () and returns the token
	 */
	public static int lookup(char ch) {
		switch (ch) {

		case '(':
			addChar();
			nextToken = LEFT_PAREN;	// left parenthesis
			break;

		case ')':
			addChar();
			nextToken = RIGHT_PAREN; // right parenthesis
			break;

		case '+':
			addChar();
			nextToken = MY_ADDOP; // addition operator
			break;

		case '-':
			addChar();
			nextToken = MY_SUBOP; // subtraction operator
			break;

		case '*':
			addChar();
			nextToken = MY_MULOP; // multiplication operator
			break;

		case '/':
			addChar();
			nextToken = MY_DIVOP; // division operator
			break;

		case '=':
			addChar();
			nextToken = EQUALS_OP; // equal to operator
			break;

		case ';':
			addChar();
			nextToken = SEMICOLON; // semicolon
			break;

		case '<':
			addChar();
			nextToken = LTHAN;	// less than operator
			break;

		case '>':
			addChar();
			nextToken = GTHAN; // greater than operator
			break;

		default:
			addChar();
			nextToken = EOF; // EOF is the end of file character
			break;
		}
		return nextToken;
	}

    // use Denotational semantics to parse the line
    public static void Finalexpr() throws IOException {
        term();
        while (nextToken == MY_ADDOP || nextToken == MY_SUBOP) {
            lex();
            term();
        }
    }   // end of expr

    // use Denotational semantics to parse the line
    public static void Finalterm() throws IOException {
        factor();
        while (nextToken == MY_MULOP || nextToken == MY_DIVOP || nextToken == MY_MODOP) {
            lex();
            factor();
        }
    }   // end of term

    // use Denotational semantics to parse the line
    public static void Finalfactor() throws IOException {
        if (nextToken == IDENT || nextToken == INT_LIT) {
            lex();
        } else if (nextToken == LEFT_PAREN) {
            lex();
            expr();
            if (nextToken == RIGHT_PAREN) {
                lex();
            } else {
                error();
            }
        } else {
            error();
        }
    }   // end of factor

    // use Denotational semantics to parse the line 
    public static void Finalrelational() throws IOException {
        expr();
        if (nextToken == LTHAN || nextToken == GTHAN || nextToken == LESSTHANOREQUAL || nextToken == GREATERTHANOREQUAL || nextToken == EQUALS_OP || nextToken == NOTEQUAL_OP) {
            lex();
            expr();
        } else {
            error();
        }
    }   // end of relational

    // use Denotational semantics to parse the line
    public static void Finalassignment() throws IOException {
        if (nextToken == IDENT) {
            lex();
            if (nextToken == EQUALS_OP) {
                lex();
                expr();
            } else {
                error();
            }
        } else {
            error();
        }
    }   // end of assignment

    // use Denotational semantics to parse the line

    

    
    public static void assignment() throws IOException {
        if (nextToken == IDENT) {
            lex();
            if (nextToken == EQUALS_OP) {
                lex();
                expr();
            } else {
                error();
            }
        } else {
            error();
        }
    }   // end of assignment

    public static void relational() throws IOException {
        expr();
        if (nextToken == LTHAN || nextToken == GTHAN || nextToken == LESSTHANOREQUAL || nextToken == GREATERTHANOREQUAL || nextToken == EQUALS_OP || nextToken == NOTEQUAL_OP) {
            lex();
            expr();
        } else {
            error();
        }
    }   // end of relational

    
        

	/*** addChar(): Adds the character in nextChar to the end of Array lexeme */
	public static void addChar() { // similar to LazyLex-1 (main.c)
		if (lexLen <= 98) { // 98 because we need to leave room for the null character
			lexeme[lexLen++] = nextChar; // add the character to the end of the lexeme
			lexeme[lexLen] = '\0'; // add the null character to the end of the lexeme
		} else {
			System.out.println("Error, lexeme is too long"); // error if the lexeme is too long
		}}
	/***
	 * getChar(): gets next input character and puts it in a Class variable named
	 * nextChar
	 */
	public static void getChar() throws IOException { // similar to LazyLex-1 (main.c)

		// Getting character will get the first character of the text string
		nextChar = text.charAt(0); // get the first character of the text string
		// Update the text string
		text = text.substring(1); // update the text string

		// Assign the character to the class it belongs to
		if (nextChar != EOF) { // if the next character is not the end of file character

			if (Character.isLetter(nextChar)) {	// if the next character is a letter
				charClass = LETTER; // assign the character to the letter class
			} else if (Character.isDigit(nextChar)) { // if the next character is a digit
				charClass = DIGIT;
			} else {
				charClass = UNKNOWN;
			}
		} else {
			charClass = EOF;}}

	/***
	 * getNonBlank() - function to call getChar until it returns a non-whitespace
	 * character
	 * 
	 * @throws IOException
	 */
	public static void Next_Non_Blank() throws IOException { // similar to LazyLex-1 (main.c)
		while (nextChar == ' ') { // while the next character is a space
			getChar(); // get the next character
		}}

	/***
	 * lex(): lexical analyzer for arithmetic expressions
	 * 
	 * @return nextToken
	 * @throws IOException 	* @throws IOException
	 */
	public static int lex() throws IOException { 		// similar to LazyLex-1 (main.c)
		lexLen = 0; // reset the lexeme length
		Next_Non_Blank(); // get the next non-blank character

		switch (charClass) { // switch on the character class
		case LETTER:
			addChar();
			getChar();
			while (charClass == LETTER || charClass == DIGIT) {
				addChar();
				getChar();
			}
			nextToken = IDENT;
			break;

		case DIGIT:
			addChar();
			getChar();
			while (charClass == DIGIT) {
				addChar();
				getChar();
			}
			nextToken = INT_LIT;
			break;

		case UNKNOWN:
			// Find the unknown character
			lookup(nextChar);
			getChar();
			break;

		case EOF:
			nextToken = EOF;
			lexeme[0] = 'E';
			lexeme[1] = 'O';
			lexeme[2] = 'F';
			lexeme[3] = '\0';
			break;
		}

		System.out.print("Next token is: " + String.valueOf(nextToken) + "---> " + tokenCodetoName(nextToken));
		printLexeme();
		return nextToken;
	}

	// Develop Parser class
    public static void parser() throws IOException {
        lex();
        // Call the function to parse the program
        program();
    }
    
    // Develop the program function
    public static void program() throws IOException {
        System.out.println("Enter <program>");
        // Call the function to parse the declaration list
        declaration_list();
        // Call the function to parse the statement list
        statement_list();
        System.out.println("Exit <program>");
    }

    // Develop the declaration list function

    public static void declaration_list() throws IOException {
        System.out.println("Enter <declaration_list>");
        // Check if the next token is an int
        if (nextToken == INT) {
            // Call the function to parse the declaration
            declaration();
            // Call the function to parse the declaration list
            declaration_list();
        }
        System.out.println("Exit <declaration_list>");
    }

    // Develop the declaration function

    public static void declaration() throws IOException {
        System.out.println("Enter <declaration>");
        // Check if the next token is an int
        if (nextToken == INT) {
            // Call the function to parse the int
            match(INT);
            // Call the function to parse the identifier
            match(IDENT);
            // Check if the next token is a semicolon
            if (nextToken == SEMICOLON) {
                // Call the function to parse the semicolon
                match(SEMICOLON);
            } else {
                // Error if the next token is not a semicolon
                error();
            }
        } else {
            // Error if the next token is not an int
            error();
        }
        System.out.println("Exit <declaration>");
    }

    // Develop the statement list function 
    
    public static void statement_list() throws IOException {
        System.out.println("Enter <statement_list>");
        // Check if the next token is an identifier
        if (nextToken == IDENT) {
            // Call the function to parse the statement
            statement();
            // Call the function to parse the statement list
            statement_list();
        }
        System.out.println("Exit <statement_list>");
    }
    public static void statement () throws IOException {
        System.out.println("Enter <statement>");
        // Call the function to parse the identifier
        match(IDENT);
        // Check if the next token is an assignment operator
        if (nextToken == ASSIGN_OP) {
            // Call the function to parse the assignment operator
            match(ASSIGN_OP);
            // Call the function to parse the expression
            expression();
            // Check if the next token is a semicolon
            if (nextToken == SEMICOLON) {
                // Call the function to parse the semicolon
                match(SEMICOLON);
            } else {
                // Error if the next token is not a semicolon
                error();
            }
        } else {
            // Error if the next token is not an assignment operator
            error();
        }
        System.out.println("Exit <statement>");
    }

    // Develop the expression function
    public static void match (int token) throws IOException {
        System.out.println("Enter <match>");
        // Check if the next token is the same as the token
        if (nextToken == token) {
            // Call the function to parse the next token
            lex();
        } else {
            // Error if the next token is not the same as the token
            error();
        }
        System.out.println("Exit <match>");
    }

    public static void expression() throws IOException {
        System.out.println("Enter <expression>");
        // Call the function to parse the term
        term();
        // Call the function to parse the expression prime
        expression_prime();
        System.out.println("Exit <expression>");
    }

    public static void expression_prime() throws IOException {
        System.out.println("Enter <expression_prime>");
        // Check if the next token is an addop
        if (nextToken == MY_ADDOP || nextToken == MY_SUBOP) {
            // Call the function to parse the addop
            MY_ADDOP();
            // Call the function to parse the term
            term();
            // Call the function to parse the expression prime
            expression_prime();
        }
        System.out.println("Exit <expression_prime>");
    }


    public static void MY_ADDOP() throws IOException {
        System.out.println("Enter <addop>");
        // Check if the next token is an addop
        if (nextToken == MY_ADDOP || nextToken == MY_SUBOP) {
            // Call the function to parse the next token
            lex();
        } else {
            // Error if the next token is not an addop
            error();
        }
        System.out.println("Exit <addop>");
    }

    public static void term_prime() throws IOException {
        System.out.println("Enter <term_prime>");
        // Check if the next token is a mulop
        if (nextToken == MY_MULOP) {
            // Call the function to parse the mulop
            mulop();
            // Call the function to parse the factor
            factor();
            // Call the function to parse the term prime
            term_prime();
        }
        System.out.println("Exit <term_prime>");
    }
    

	private static void mulop() throws IOException {
                System.out.println("Enter <mulop>");
                // Check if the next token is a mulop
                if (nextToken == MY_MULOP) {
                    // Call the function to parse the next token
                    lex();
                } else {
                    // Error if the next token is not a mulop
                    error();
                }
                System.out.println("Exit <mulop>");
            }

    private static void error() {
        System.out.println("Error");
        System.exit(0);
    }


    // Prints the lexeme contained in the array
	public static void printLexeme() {
		System.out.print(" || next lexeme: ");

		for (int i = 0; i < lexeme.length; i++) {
			if (lexeme[i] == '\0') {
				break;
			}
			System.out.print(lexeme[i]);
		}
		System.out.println();
	}

	// Convert token code to its token name
	public static String tokenCodetoName(int nextToken) {

		String nextTokenName = "";
		switch (nextToken) {

		case 1:
			nextTokenName = "MY_ADDOP";
			break;

		case 2:
			nextTokenName = "MY_SUBOP";
			break;

		case 3:
			nextTokenName = "MY_MULOP";
			break;

		case 4:
			nextTokenName = "MY_DIVOP";
			break;

		case 5:
			nextTokenName = "MY_MODOP";
			break;

		case 6:
			nextTokenName = "LTHAN";
			break;

		case 7:
			nextTokenName = "GTHAN";
			break;

		case 10:
			nextTokenName = "EQUALS_OP";
			break;

		case 13:
			nextTokenName = "LEFT_PAREN";
			break;

		case 14:
			nextTokenName = "RIGHT_PAREN";
			break;

		case 15:
			nextTokenName = "SEMICOLON";
			break;

		case '^':
			nextTokenName = "EOF";
			break;

		case 20:
			nextTokenName = "INT_LIT";
			break;

		case 21:
			nextTokenName = "IDENT";
			break;
		}
		return nextTokenName;
	}

	// Order of Operations: PESDMA (Parenthesis, Exponents, Division, Multiplication,
	public static void expr() throws IOException { // similar to LazyLex-1 (main.c)
		System.out.println(" ---> <expr>"); // print the current function
		term(); // call the term function

		// Addition or Multiplication
		while (nextToken == MY_ADDOP || nextToken == MY_MULOP) { // while the next token is an addition or multiplication operator
			lex();
			term();
		}

		System.out.println("x <expr>");
		System.out.println("- - - - - -");
	}

	public static void term() throws IOException {
		System.out.println(" --> <term>");
		factor();

		// Division or Subtraction
		while (nextToken == MY_DIVOP || nextToken == MY_SUBOP) {
			lex();
			factor();
		}
		System.out.println("x <term>");
	}

	public static void factor() throws IOException {
		System.out.println(" --> <factor>");

		// id
		if (nextToken == IDENT) {
			lex();
		} else if (nextToken == INT_LIT) {
			lex();
		} else {
			// (expr)
			if (nextToken == LEFT_PAREN) {
				lex();
				if (nextToken == RIGHT_PAREN) {
					lex();
				}
			}
		}
		System.out.println("x <factor>");
	}
}
