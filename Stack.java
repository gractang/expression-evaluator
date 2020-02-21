/*************************************************************
 * Class: Stack
 * Description: 
 * A ListNode-based FILO data structure.
 **************************************************************/
public class Stack 
{
	private ListNode top;
	
	/**
	 * Stack constructor
	 */
	public Stack() 
	{
		top = null;
	}
	
	/**
	 * pushes an object onto the Stack and updates the top pointer
	 * accordingly
	 * @param obj		the object to push onto the Stack
	 */
	public void push(Object obj) 
	{
		ListNode prev = top;
		ListNode ln = new ListNode(obj, prev);
		top = ln;
	}
	
	/**
	 * pops the topmost object off the Stack and returns it, then updates
	 * the top pointer accordingly
	 * @return the object that was removed
	 */
	public Object pop()
	{
		if (!isEmpty())
		{
			ListNode prevTop = top;
			top = prevTop.getNext();
			return prevTop.getValue();
		}
		return null;
	}
	
	/**
	 * returns the topmost object without removing it
	 * from the Stack
	 * @return the topmost object, or null if Stack is empty
	 */
	public Object peek()
	{
		return top.getValue();
	}
	
	/**
	 * checks if the Stack is empty
	 * @return true if empty false if not
	 */
	public boolean isEmpty()
	{
		return top == null;
	}
	
}
