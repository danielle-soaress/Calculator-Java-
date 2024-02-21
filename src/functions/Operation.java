package functions;

import exceptions.CalculatorException;

public class Operation {
	public static String calculate(String fieldContent) throws CalculatorException {
		
		Character operator = null;
		Double result = 0.0;
		String num1 = "", num2 = "";
		
		for (int i=0; i<fieldContent.length(); i++) {
			if (i == fieldContent.length()-1 && operator == null) {
				throw new CalculatorException("Error: Operator wasn't found. Remeber: to negative numbers, use the (-) button.");
			}
			
			char c = fieldContent.charAt(i);
			
			if (c == '+' || c=='-' || c=='*' || c== '/') {
				if (i==0) {
					num1+= String.valueOf(c);
					continue;
				} else {
					operator = c;
					num2 = fieldContent.substring(i+1);
					break;
				}
			} else {
				num1+= String.valueOf(c);
			}
			
		}
		
		switch (operator) {
		case '+':
			result = Double.valueOf(num1) + Double.valueOf(num2);
			break;
		case '-':
			result = (Double.valueOf(num1)) - (Double.valueOf(num2));
			break;
		case '*':
			result = Double.valueOf(num1)* Double.valueOf(num2);
			break;
		case '/':
			result = Double.valueOf(num1) / Double.valueOf(num2);
			break;
		}
		
		return (result == Math.floor(result)) ? String.valueOf((int) Math.round(result)): String.valueOf(result);
	}
}
