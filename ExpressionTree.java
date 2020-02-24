/*************************************************************
 * Class: ExpressionTree
 * Description: Data structure that stores Tokens according to 
 * the postfix expression entered
 **************************************************************/
public class ExpressionTree 
{
	private TreeNode root;
	
	/**
	 * ExpressionTree constructor, initializes root TreeNode according
	 * to Token type
	 * @param rootToken
	 * @param leftExpr
	 * @param rightExpr
	 */
	public ExpressionTree(Token rootToken, ExpressionTree leftExpr, ExpressionTree rightExpr)
	{
		if (rootToken.getType() == Token.NUMBER)
		{
			root = new TreeNode(rootToken, null, null);
		}
		else if (rootToken.getType() == Token.UNARY)
		{
			root = new TreeNode(rootToken, leftExpr.root, null);
		}
		else if (rootToken.getType() == Token.BINARY)
		{
			root = new TreeNode(rootToken, leftExpr.root, rightExpr.root);
		}
		else	//shouldn't ever happen
		{
			root = null;
		}
		
	}
	
	/**
	 * @return String representation of the ExpressionTree
	 */
	public String toString()
	{
		return toStringPostOrder();
	}
	
	/**
	 * public toStringPostOrder
	 * @return post-order String representation
	 */
	public String toStringPostOrder()
	{
		return auxToStringPostOrder(root);
	}
	
	/**
	 * Auxiliary toStringPostOrder
	 * @param t			the TreeNode on which to begin the recursion
	 * @return String	the post-order String representation
	 */
	private String auxToStringPostOrder(TreeNode t)
	{
		if (t == null)
		{
			return "";
		}
		else
		{
			return 	auxToStringPostOrder(t.getLeft()) +  
					auxToStringPostOrder(t.getRight()) + 
					t.getValue().toString() + '\n';
		}
	}

	/**
	 * evaluates a given ExpressionTree
	 * @return double 	the result of the evaluation
	 */
	public double evaluate()
	{
		return auxEvaluate(root);
	}
	
	/**
	 * auxiliary evaluate method
	 * @param t			the current TreeNode
	 * @return double 	the result of the evaluation
	 */
	private double auxEvaluate(TreeNode t)
	{
		if (t == null)
		{
			return 0;
		}
		Token tk = (Token) t.getValue();
		if (tk.getType() == Token.NUMBER)
		{
			return tk.getNum();
		}
		int symbol = tk.getOp();
		double leftChild = auxEvaluate(t.getLeft());
		if (tk.getType() == Token.UNARY)
		{
			if (symbol == Token.MINUS)
			{
				return -1*leftChild;
			}
			return leftChild; //i guess unary plus just doesn't do anything... but okay
		}
		
		//at this point the only other one is binary (hopefully... if not, program will crash probably)
		double rightChild = auxEvaluate(t.getRight());
		if (symbol == Token.ADD)
		{
			return leftChild + rightChild;
		}
		if (symbol == Token.SUBTRACT)
		{
			return rightChild - leftChild;
		}
		if (symbol == Token.MULTIPLY)
		{
			return leftChild * rightChild;
		}
		//only divide left
		return rightChild/leftChild;
	}

}
