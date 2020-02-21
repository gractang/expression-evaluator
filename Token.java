/*************************************************************
 * Class: Token
 * Description:
 * A Token used to evaluate an ExpressionTree. A Token can be
 * one of four types: a NUMBER, an UNARY operator, a BINARY
 * operator, or an END token. The Token class contains necessary
 * accessor methods. 
 * The possible Token types and the corresponding contents of 
 * the value or operation fields are shown below:
 * 
 * type 			meaning 				value
 * -----------------------------------------------------------
 * NUMBER		Token is a number 		num contains the num-
 * 										erical value, op is 
 * 										empty
 * 
 * UNARY	Token is a unary operator	num is empty, op con-
 * 										tains PLUS or MINUS
 * 
 * BINARY	Token is a binary operator	num is empty, op con-
 * 										tains ADD, SUBTRACT, 
 * 										MULTIPLY, or DIVIDE
 * 
 * END			End of tokens			num, op both empty
 * 
 * The Token class also contains a series of final ints rep-
 * resenting the various symbols, as well as a String of 
 * symbols in which the index of the symbol corresponds
 * to its integer value.
 **************************************************************/
public class Token
{
	public static final int NUMBER = 1; 
	public static final int UNARY = 2; 
	public static final int BINARY = 3; 
	public static final int END = 4;
	
	public static final int PLUS = 5; 
	public static final int MINUS = 6;
	
	public static final int ADD = 7; 
	public static final int SUBTRACT = 8;
	public static final int MULTIPLY = 9;
	public static final int DIVIDE = 10;
	
	int type;
	double num;
	int op;
	
	public static final String SYMBOLS = "0#ube+-+-*/";
	
	/**
	 * Token constructor for a NUMBER Token
	 * @param typeIn	will, under normal circumstances, be equal to NUMBER
	 * @param numIn		the number value of the Token
	 */
	public Token(int typeIn, double numIn)
	{
		type = typeIn;
		num = numIn;
		op = 0;
	}
	
	/**
	 * Token constructor for an operator Token (either UNARY or BINARY)
	 * @param typeIn	the operator type
	 * @param opIn		the operator symbol
	 */
	public Token(int typeIn, int opIn)
	{
		type = typeIn;
		num = 0;
		op = opIn;
	}
	
	/**
	 * Token constructor for an END Token
	 * @param typeIn	will, under normal circumstances, be equal to END
	 */
	public Token(int typeIn)
	{
		type = typeIn;
		num = 0;
		op = 0;
	}
	
	/**
	 * @return the String representation of the Token -- the number value if
	 * 		   the Token is a NUMBER, and the symbol otherwise
	 */
	public String toString()
	{
		if (type == NUMBER)
		{
			return num + "";
		}
		return  SYMBOLS.charAt(op) + "";
	}
	
	/**
	 * accessor method to get the type of the Token
	 * @return type		the Token's type
	 */
	public int getType() 
	{
		return type;
	}
	
	/**
	 * accessor method to get the number of the Token
	 * @return num		the Token's number
	 */
	public double getNum() 
	{
		return num;
	
	}
	
	/**
	 * accessor method to get the operator of the Token
	 * @return op		the Token's operator
	 */
	public int getOp() 
	{
		return op;
	}
	
}
