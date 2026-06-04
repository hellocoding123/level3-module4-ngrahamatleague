package _00_IntroToStacks;

import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class _01_IntroToStack {
    public static void main(String[] args) {
        // 1. Create a Stack of Doubles
        //    Don't forget to import the Stack class
    	while(true) {
	    	Stack<Double> stack = new Stack<Double>();
	    	int counter = 0;
	        // 2. Use a loop to push 100 random doubles between 0 and 100 to the Stack.
	    	Random r = new Random();
	    	for(int i = 0; i < 100; i++) {
	    		stack.push(r.nextDouble()*100);
	    	}
	        // 3. Ask the user to enter in two numbers between 0 and 100, inclusive. 
	    	int min = Integer.parseInt(JOptionPane.showInputDialog("Enter 2 numbers between 1-100, enter the first number here:"));
	    	int max = Integer.parseInt(JOptionPane.showInputDialog("Enter the last number here:"));
	        // 4. Pop all the elements off of the Stack. Every time a double is popped that is
	        //    between the two numbers entered by the user, print it to the screen.
	    	while(!stack.isEmpty()) {
	    		if(stack.peek() >= min && stack.peek() <= max) {
	    			System.out.println(stack.pop());
	    			counter++;
	    		}
	    		else {
	    			stack.pop();
	    		}
	    	}
	    	System.out.println(counter);
    	}
        // EXAMPLE:
        // NUM 1: 65
        // NUM 2: 75

        // Popping elements off stack...
        // Elements between 65 and 75:
        // 66.66876846
        // 74.51651681
        // 70.05110654
        // 69.21350456
        // 71.54506465
        // 66.47984807
        // 74.12121224
    }
}
