package com.bhz.utils;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WigetTools {
	
	public JPanel createInputWithLabelPanel(String label, JTextField inputField) {
		JPanel p = new JPanel(new FlowLayout());
		JLabel text = new JLabel(label);
		text.setSize(75, 24);
		p.add(text);
		inputField.setColumns(5);
		inputField.setSize(75, 25);
		p.add(inputField);
		return p;
	}
	
	public JPanel createInputWithLabelPanel(String label,JTextField inputField, String suffix) {
		JPanel p = createInputWithLabelPanel(label, inputField);
		p.add(new JLabel(suffix));
		return p;
	}
	
	public JPanel createComboBoxWithLabelPanel(String label,JComboBox<? extends Object> inputField, String suffix) {
		JPanel p = createComboBoxWithLabelPanel(label, inputField);
		p.add(new JLabel(suffix));
		return p;
	}
	
	public JPanel createComboBoxWithLabelPanel(String label, JComboBox<? extends Object> inputField) {
		JPanel p = new JPanel(new FlowLayout());
		JLabel text = new JLabel(label);
		text.setSize(75, 24);
		p.add(text);
		inputField.setSize(50, 25);
		p.add(inputField);
		return p;
	}

	public JPanel createViewWithLabelPanel(String label, String value) {
		JPanel p = new JPanel(new FlowLayout());
		JLabel text = new JLabel(label);
		text.setSize(75, 24);
		p.add(text);
		JLabel valueLabel = new JLabel(value);
		valueLabel.setSize(75, 24);
		p.add(valueLabel);
		return p;
	}
}
