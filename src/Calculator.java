import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.CalculatorException;
import functions.Operation;

public class Calculator implements ActionListener {

	JFrame frame;
	JTextField textField;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[9];
	JButton addButton, subButton, mulButton, divButton, decButton, eqButton, delButton, clrButton, negButton;
	JPanel panel;

	Font myFont = new Font("Roboto", Font.BOLD, 30);
	
	String result;
	int numberOfOperators = 0;
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
	}
	
	public Calculator() {
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setLayout(null);
		frame.setResizable(false);

		textField = new JTextField();
		textField.setBounds(50, 25, 300, 50);
		textField.setFont(myFont);
		textField.setEditable(false);

		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		eqButton = new JButton("=");
		delButton = new JButton("Del");
		clrButton = new JButton("CLR");
		negButton = new JButton("(-)");

		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = eqButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;

		for (int i = 0; i < 9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}

		for (int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}

		negButton.setBounds(50, 430, 100, 50);
		delButton.setBounds(150, 430, 100, 50);
		clrButton.setBounds(250, 430, 100, 50);

		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 10, 10));

		for (int i = 1; i <= 3; i++) {
			panel.add(numberButtons[i]);
		}

		panel.add(addButton);

		for (int i = 4; i <= 6; i++) {
			panel.add(numberButtons[i]);
		}

		panel.add(subButton);

		for (int i = 7; i <= 9; i++) {
			panel.add(numberButtons[i]);
		}

		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(eqButton);
		panel.add(divButton);

		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textField);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			for (int i = 0; i < 10; i++) {
				if (e.getSource() == numberButtons[i]) {
					textField.setText(textField.getText().concat(String.valueOf(i)));
				}
			}

			if (e.getSource() == decButton) {
				textField.setText(textField.getText().concat("."));
			}

			if (e.getSource() == subButton || e.getSource() == addButton || e.getSource() == divButton || e.getSource() == mulButton) {
				String symbol = ((AbstractButton) e.getSource()).getText();
				String content = textField.getText();

				if (numberOfOperators == 0) {
					numberOfOperators++;
					textField.setText(textField.getText().concat(symbol));
				} else if (numberOfOperators == 1) {
					char firstChar = content.substring(0).charAt(0);
					char lastChar = content.substring(content.length() - 1).charAt(0);
					
					if (firstChar == '-') {
						numberOfOperators = 0;
					} else if (symbol.charAt(0) != lastChar && (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/')) {
						textField.setText(content.substring(0, content.length() - 1) + symbol);
					} else {
						result = Operation.calculate(textField.getText());
						textField.setText(result + symbol);
					}
				} else if (numberOfOperators > 1 || numberOfOperators < 0) {
					numberOfOperators = 0;
					throw new CalculatorException("Error: There is more than one operator.");
				}
			} else if (e.getSource() == eqButton) {
				result = Operation.calculate(textField.getText());
				textField.setText(result);
				numberOfOperators=0;
			} else if (e.getSource() == clrButton) {
				textField.setText("");
				numberOfOperators = 0;
			} else if (e.getSource() == delButton) {
				String string = textField.getText();

				textField.setText("");

				for (int i = 0; i < string.length() - 1; i++) {
					textField.setText(textField.getText() + string.charAt(i));
				}
			} else if (e.getSource() == negButton) {
				double temp = Double.parseDouble(textField.getText());
				temp *= -1;
				textField.setText(String.valueOf(temp));
			}

		} catch (CalculatorException e1) {
			System.out.println(e1.getMessage());
		}
	}

}
