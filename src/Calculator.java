import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
	
	JFrame frame;
	JTextField textField;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[8];
	JButton addButton, subButton, mulButton, divButton, decButton, equButton, 
	delButton, cirButton;
	JPanel panel;
	
	Font myFont = new Font("Roboto", Font.BOLD,30);
	
	double num1=0, num2=0, result=0;
	char operaton;
	
	public Calculator() {
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,550);
		frame.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(50, 25, 300, 50);
		textField.setFont(myFont);
		
		frame.add(textField);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
	}
}
