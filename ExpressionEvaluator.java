import java.util.Scanner;
public class ExpressionEvaluator 
{
	public static final Scanner CONSOLE = new Scanner(System.in); 
	public static void main(String[] args) 
	{
		System.out.println("run program? (y/n)");
		String response = CONSOLE.nextLine();
		while (response != null && response.equals("y"))
		{
			ExpressionTree et = generateExpressionTree();
			if (et != null)
			{
				System.out.println(et.toStringPostOrder());
				System.out.println(et.evaluate());
			}
			System.out.println("run again? (y/n)");
			response = CONSOLE.nextLine();
		}
		System.out.println("fin.");
	}
	
	/**
	 * gets a Token from the user
	 * @return Token 	a Token consisting of a type and either a number or an operator
	 */
	public static Token getToken()
	{
		System.out.println("enter Token type");
		String str = CONSOLE.nextLine();
		
		//if string is too short, too long, or just straight up isn't a token type
		while (str.length() < 1 || str.length() > 1 || str.equals("0") || Token.TYPES.indexOf(str) == -1)
		{
			System.out.println("not a valid Token type, please try again!");
			str = CONSOLE.nextLine();
		}
		
		if (str.charAt(0) == Token.SYMBOLS.charAt(Token.NUMBER))
		{
			System.out.println("enter number");
			double d = CONSOLE.nextDouble();
			CONSOLE.nextLine();
			return new Token(Token.NUMBER, d);
		}
		else if (str.charAt(0) == Token.SYMBOLS.charAt(Token.UNARY))
		{
			System.out.println("enter unary operator");
			str = CONSOLE.nextLine();
			while (Token.UNARIES.indexOf(str) == -1)
			{
				System.out.println("not a valid unary operator, please try again!");
				str = CONSOLE.nextLine();
			}
			return new Token(Token.UNARY, Token.SYMBOLS.indexOf(str));
		}
		else if (str.charAt(0) == Token.SYMBOLS.charAt(Token.BINARY))
		{
			System.out.println("enter binary operator");
			str = CONSOLE.nextLine();
			while (Token.BINARIES.indexOf(str) == -1)
			{
				System.out.println("not a valid binary operator, please try again!");
				str = CONSOLE.nextLine();
			}
			for (int i = Token.ADD; i <= Token.DIVIDE; i++)
			{
				if (str.charAt(0) == Token.SYMBOLS.charAt(i))
				{
					return new Token(Token.BINARY, i);
				}
			}
		}
		
		//if not any of the above, must be end token
		return new Token(Token.END);
	}
	
	/**
	 * generates an ExpressionTree based on the Tokens entered by the user
	 * by popping and pushing ExpressionTrees on a Stack
	 * the general idea is as follows:
	 * number token – make it an ExpressionTree, push
	 * unary op token – pop, make an ExpressionTree with the op as root and the
	 * 					popped ExpressionTree as left child, push
	 * binary op token – pop, pop, make an ExpressionTree with the op as root and the
	 *					popped ExpressionTrees as right and left children, push end 
	 *					token – pop and return
	 * @return ExpressionTree 		the generated expression tree
	 */
	public static ExpressionTree generateExpressionTree()
	{
		Stack s = new Stack();
		while(true)
		{
			Token t = getToken();
			while (t == null)	//should not ever happen
			{
				t = getToken();
			}
			
			int type = t.getType();
			if (type == Token.NUMBER)
			{
				s.push(new ExpressionTree(t, null, null));
			}
			else if (type == Token.UNARY)
			{
				ExpressionTree et = (ExpressionTree) s.peek();
				if (et == null)
				{
					System.out.println("no expression to apply this unary operator to!");
				}
				else
				{
					et = (ExpressionTree) s.pop();
					s.push(new ExpressionTree(t, et, null));
				}
			}
			else if (type == Token.BINARY)
			{
				ExpressionTree left = (ExpressionTree) s.pop();
				ExpressionTree right = (ExpressionTree) s.pop();
				if (left == null || right == null)
				{
					System.out.println("not enough expressions to apply this binary operator to!");
				}
				else
				{
					s.push(new ExpressionTree(t, left, right));
				}
			}
			else if (type == Token.END)
			{
				return (ExpressionTree) s.pop();
			}
			else
			{
				return null;
			}
		}
	}

}
