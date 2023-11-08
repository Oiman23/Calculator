package GUIProgramming;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.*;

public class Calculator {
	JFrame frame;
	Button buttonPeriod;
	Button buttonPlus;
	Button buttonSub;
	Button buttonMult;
	Button buttonDiv;
	Button buttonEqual;
	String answerString = "0";
	String holderString = "0";
	BigDecimal answerNumber;
	BigDecimal holderNumber;
	MathContext mc = new MathContext(10);
	char sign;

	public Calculator() {
		// Frame
		frame = new JFrame();
		frame.setTitle("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(400, 600);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setResizable(false);

		// Top Panel
		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.setBackground(Color.WHITE);
		textPanel.setPreferredSize(new Dimension(5, 50));

		// Answer Text
		JLabel answerText = new JLabel();
		answerText.setText(answerString);
		answerText.setOpaque(true);
		answerText.setBounds(0, 0, 40, 40);
		answerText.setFont(new Font("Ariel", Font.PLAIN, 20));
		textPanel.add(answerText, BorderLayout.CENTER);

		// Clear Button
		Button clearButton = new Button("Clear");
		changeFont(clearButton);
		clearButton.addActionListener((ActionEvent e) -> {
			resetHolderLabel();
			answerString = "0";
			answerNumber = new BigDecimal(answerString);
			sign = 'x';
			updateAnswerText(answerText, holderString);
		});
		textPanel.add(clearButton, BorderLayout.SOUTH);

		// Number Panel
		JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		frame.add(panel);

		// 1-3 buttons
		for (int i = 1; i < 4; i++) {
			Button buttonNumber = new Button(Integer.toString(i));
			changeFont(buttonNumber);
			panel.add(buttonNumber);
			buttonNumber.addActionListener((ActionEvent e) -> {
				if (holderString.equals("0")) {
					holderString = buttonNumber.getLabel();
				} else {
					holderString += buttonNumber.getLabel();
				}
				updateAnswerText(answerText, holderString);
			});
		}

		// + button
		buttonPlus = new Button("+");
		changeFont(buttonPlus);
		buttonPlus.addActionListener((ActionEvent e) -> {
			updateMath('+');
			updateAnswerText(answerText, answerString);
		});
		panel.add(buttonPlus);

		// 4-6 buttons
		for (int i = 4; i < 7; i++) {
			Button buttonNumber = new Button(Integer.toString(i));
			changeFont(buttonNumber);
			panel.add(buttonNumber);
			buttonNumber.addActionListener((ActionEvent e) -> {
				if (holderString.equals("0")) {
					holderString = buttonNumber.getLabel();
				} else {
					holderString += buttonNumber.getLabel();
				}
				updateAnswerText(answerText, holderString);
			});
		}

		// Sub button
		buttonSub = new Button("-");
		changeFont(buttonSub);
		buttonSub.addActionListener((ActionEvent e) -> {
			updateMath('-');
			updateAnswerText(answerText, answerString);
		});
		panel.add(buttonSub);

		// 7-9 buttons
		for (int i = 7; i < 10; i++) {
			Button buttonNumber = new Button(Integer.toString(i));
			changeFont(buttonNumber);
			panel.add(buttonNumber);
			buttonNumber.addActionListener((ActionEvent e) -> {
				if (holderString.equals("0")) {
					holderString = buttonNumber.getLabel();
				} else {
					holderString += buttonNumber.getLabel();
				}
				updateAnswerText(answerText, holderString);
			});
		}

		// Decimal Button
		buttonPeriod = new Button(".");
		changeFont(buttonPeriod);
		buttonPeriod.addActionListener((ActionEvent e) -> {
			holderString += ".";
		});
		Button buttonZero = new Button(Integer.toString(0));
		changeFont(buttonZero);
		panel.add(buttonZero);
		buttonZero.addActionListener((ActionEvent e) -> {
			if (holderString.equals("0")) {
				holderString = buttonZero.getLabel();
			} else {
				holderString += buttonZero.getLabel();
			}
			updateAnswerText(answerText, holderString);
		});
		panel.add(buttonPeriod);

		// * button
		buttonMult = new Button("*");
		changeFont(buttonMult);
		buttonMult.addActionListener((ActionEvent e) -> {
			updateMath('*');
			updateAnswerText(answerText, answerString);
		});

		// '/' button
		buttonDiv = new Button("/");
		changeFont(buttonDiv);
		buttonDiv.addActionListener((ActionEvent e) -> {
			updateMath('/');
			updateAnswerText(answerText, answerString);
		});

		// = button
		buttonEqual = new Button("=");
		changeFont(buttonDiv);
		buttonEqual.addActionListener((ActionEvent e) -> {
			updateMath('x');
			updateAnswerText(answerText, answerString);
		});

		panel.add(buttonMult);
		panel.add(buttonDiv);
		panel.add(buttonEqual);
		frame.add(textPanel, BorderLayout.NORTH);
		frame.setVisible(true);
	}

	private void changeFont(Button button) {
		button.setFont(new Font("Arial", Font.PLAIN, 20));
	}

	private void updateMath(char signT) {
		answerNumber = new BigDecimal(answerString);
		holderNumber = new BigDecimal(holderString);
		if (answerNumber.toString().equals("0")) {
			answerNumber = holderNumber;
		}
		if (sign == '+') {
			answerNumber = answerNumber.add(holderNumber, mc);
		} else if (sign == '-') {
			answerNumber = answerNumber.subtract(holderNumber, mc);
		} else if (sign == '*') {
			answerNumber = answerNumber.multiply(holderNumber, mc);
		} else if (sign == '/') {
			try {
				answerNumber = answerNumber.divide(holderNumber, mc);
			} catch (ArithmeticException e1) {
				answerNumber = holderNumber.add(holderNumber, mc);
				;
			}
		}
		answerString = answerNumber.toPlainString();
		sign = signT;
		resetHolderLabel();
	}

	private void updateAnswerText(JLabel answerLabel, String label) {
		answerLabel.setText(label);
	}

	private void resetHolderLabel() {
		holderString = "0";
		holderNumber = new BigDecimal(holderString);
	}

	public static void main(String args[]) {
		new Calculator();

	}

}
