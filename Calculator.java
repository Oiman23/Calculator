package GUIProgramming;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.*;

public class Calculator { // organize numbers, read other comments on numbers, maybe borderLayout a and
							// move panel left to right.
	JFrame frame;
	Button buttonPeriod;
	Button buttonPlus;
	Button buttonSub;
	Button buttonMult;
	Button buttonDiv;
	Button buttonEqual;
	int answer;
	int numberHolder;
	int decimalDigit;
	boolean decimal;
	char sign;

	public Calculator() {
		// Frame
		frame = new JFrame();
		frame.setTitle("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(370, 500);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setResizable(false);

		// Top Panel
		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.setBackground(Color.WHITE);
		textPanel.setPreferredSize(new Dimension(5, 50));

		// Answer Text
		JLabel answerText = new JLabel();
		answerText.setText(Integer.toString(answer));
		answerText.setOpaque(true);
		answerText.setBounds(0, 0, 30, 30);
		Border BevelRaised = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		answerText.setBorder(BevelRaised);
		textPanel.add(answerText, BorderLayout.CENTER);

		// Clear Button
		Button clearButton = new Button("Clear");
		clearButton.addActionListener((ActionEvent e) -> {
			resetHolderLabel();
			answer = 0;
			sign = 'x';
			updateAnswerText(answerText, numberHolder);
		});
		textPanel.add(clearButton, BorderLayout.SOUTH);

		// Number Panel
		JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));
		panel.setBackground(Color.LIGHT_GRAY);
		frame.add(panel);

		// Numbered Buttons
		for (int i = 0; i < 10; i++) {
			Button buttonNumber = new Button(Integer.toString(i));
			panel.add(buttonNumber);
			buttonNumber.addActionListener((ActionEvent e) -> {
				String Strtemp = buttonNumber.getLabel();
				int current = Integer.parseInt(Strtemp);
				numberHolder *= 10;
				numberHolder += current;
				updateAnswerText(answerText, numberHolder);
			});
		}
		// Decimal Button
		buttonPeriod = new Button(".");
		buttonPeriod.addActionListener((ActionEvent e) -> {
			decimal = true;
		});

		// Sign Buttons
		buttonPlus = new Button("+");
		buttonPlus.addActionListener((ActionEvent e) -> {
			updateMath('+');
			updateAnswerText(answerText, answer);
		});
		buttonSub = new Button("-");
		buttonSub.addActionListener((ActionEvent e) -> {
			updateMath('-');
			updateAnswerText(answerText, answer);

		});
		buttonMult = new Button("*");
		buttonMult.addActionListener((ActionEvent e) -> {
			updateMath('*');
			updateAnswerText(answerText, answer);
		});
		buttonDiv = new Button("/");
		buttonDiv.addActionListener((ActionEvent e) -> {
			updateMath('/');
			updateAnswerText(answerText, answer);

		});
		buttonEqual = new Button("=");
		buttonEqual.addActionListener((ActionEvent e) -> {
			System.out.println("answer " + answer);
			System.out.println("NumberHolder " + numberHolder);
			System.out.println("Sign: " + sign);
			updateMath('x');
			System.out.println("= " + answer);
			updateAnswerText(answerText, answer);
		});

		panel.add(buttonPeriod);
		panel.add(buttonPlus);
		panel.add(buttonSub);
		panel.add(buttonMult);
		panel.add(buttonDiv);
		panel.add(buttonEqual);

//		frame.pack();
		frame.add(textPanel, BorderLayout.NORTH);
		frame.setVisible(true);

	}

	private void updateMath(char signT) {

		System.out.println("Hold " + sign);
		if (answer == 0) {
			answer = numberHolder;
		}
		if (sign == '+') { // add longer addition result (Another int before it, add hte carry bit to LSB
							// of second number )
			answer += numberHolder;
		} else if (sign == '-') { // add longer subtraction
			answer -= numberHolder;
		} else if (sign == '*') { // add longer multiple signs, make compatible with decimals
			answer *= numberHolder;
		} else if (sign == '/' && numberHolder != 0) { // add decimals, make compatible with decimals
			answer /= numberHolder;
		}
		sign = signT;
		resetHolderLabel();
	}

	private void updateAnswerText(JLabel answerLabel, int label) {
		answerLabel.setText(Integer.toString(label));
	}

	private void resetHolderLabel() {
		numberHolder = 0;
		decimal = false;
		decimalDigit = 0;
	}

	public static void main(String args[]) {
		new Calculator();

	}

}
