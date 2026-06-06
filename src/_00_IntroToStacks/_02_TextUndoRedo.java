package _00_IntroToStacks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class _02_TextUndoRedo implements KeyListener{
    /* 
     * Create a JFrame with a JPanel and a JLabel.
     * 
     * Every time a key is pressed, add that character to the JLabel. It should
     * look like a basic text editor.
     * 
     * Make it so that every time the BACKSPACE key is pressed, the last
     * character is erased from the JLabel.
     * 
     * Save that deleted character onto a Stack of Characters.
     * 
     * Choose a key to be the Undo key. Make it so that when that key is
     * pressed, the top Character is popped  off the Stack and added back to
     * the JLabel.
     */
	
	
	String text = "";
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	
	Stack<Character> stack = new Stack<Character>();
	
	public static void main(String[] args) {
		
		_02_TextUndoRedo txt = new _02_TextUndoRedo();
		txt.start();		
	}
	
	void start() {
		frame.add(panel);
		panel.add(label);
		
		frame.addKeyListener(this);
		
		frame.setVisible(true);
		
		label.setText(text);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == '\b') {
			stack.push(text.charAt(text.length()-1));
			text = text.substring(0,text.length()-1);
			label.setText(text);
		}
		else if(e.getKeyChar() == '\u001A') {
			if(!stack.isEmpty()) {
				text += stack.pop();
				label.setText(text);
			}
		}
		
		else {
			text += e.getKeyChar();
			label.setText(text);
		}
		//System.out.println(e.getKeyChar());
		
				
	}}
