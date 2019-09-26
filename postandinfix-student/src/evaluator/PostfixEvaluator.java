package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class PostfixEvaluator extends Evaluator {
	
	private LinkedStack<Integer> stack = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<Integer> getStack() { return stack; }
	
	/** This method performs one step of evaluation of a postfix expression.
	 *  The input is a token. Follow the postfix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			// TODO: What do we do if the token is an operand?
			stack.push(Integer.parseInt(token));
			
		} else {
			if (token.equals("+")) {
				Integer a = stack.pop();
				Integer b = stack.pop();
				if (a ==null || b==null) {
					throw new Exception("too few operands");
				}
				else {
					stack.push(a+b);
				}
			}
			else if (token.equals("-")) {
				Integer a = stack.pop();
				Integer b = stack.pop();
				if (a ==null || b==null) {
					throw new Exception("too few operands");
				}
				else {
					stack.push(b-a);
				}
			}
			else if (token.equals("*")) {
				Integer a = stack.pop();
				Integer b = stack.pop();
				if (a ==null || b==null) {
					throw new Exception("too few operands");
				}
				else {
					stack.push(a*b);
				}
			}
			else if (token.equals("/")) {
				Integer a = stack.pop();
				Integer b = stack.pop();
				if (a ==null || b==null) {
					throw new Exception("too few operands");
				}
				else if (a == 0){
					throw new Exception("division by zero");
				}
				else {
					stack.push(b/a);
				}
			}
			else if (token.equals("!")) {
				Integer a =  stack.pop();
				if (a ==null) {
					throw new Exception("too few operands");
				}
				else {
					stack.push(-a);
				}
			}
			else {
				throw new Exception ("invalid operator");
			}
		
			/* TODO: What do we do if the token is an operator?
			   If the expression is invalid, make sure you throw
			   an exception with the correct message
			 */ 
		}		
	}
	/** This method evaluates a postfix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception
	 *  if the postfix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {
		
		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}
		// The stack should have exactly one operand after evaluation is done
		if(stack.size()>1) {
			throw new Exception("too many operands");
		} else if (stack.size()<1) {
			throw new Exception("too few operands");
		}
		return stack.pop(); 
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new PostfixEvaluator().evaluate("50 25 ! / 3 +"));
	}
}