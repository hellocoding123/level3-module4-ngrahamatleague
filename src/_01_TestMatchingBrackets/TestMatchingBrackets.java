package _01_TestMatchingBrackets;

import java.util.Stack;

public class TestMatchingBrackets {
    /*
     * Use a Stack to complete the method for checking if every opening bracket
     * has a matching closing bracket
     */
    public static boolean doBracketsMatch(String b) {

    	Stack<Character> stack = new Stack<Character>();
    	
    	for(int i = 0; i < b.length(); i++) {
    		if(b.charAt(i) == '{') {
    			stack.push('{');
    		}
    		if(b.charAt(i) == '}') {
    			if(!stack.isEmpty()) {
    				stack.pop();
    			}
    			else {
    				return false;
    			}
    		}
    	}
    	if(stack.isEmpty())
    		return true;
    	else
    		return false;
    
    	
    	
    }
}