package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CardLayoutClass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cardPanel, jp1, jp2, buttonPanel;
	private JLabel jl1, jl2;
	private JButton btn1, btn2;
	private CardLayout cardLayout = new CardLayout();

	public CardLayoutClass() {
		setTitle("Test med CardLayout");
		setSize(400, 300);
		cardPanel = new JPanel();
		buttonPanel = new JPanel();
		cardPanel.setLayout(cardLayout);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jl1 = new JLabel("Card 1");
		jl2 = new JLabel("Card 2");
		jp1.add(jl1);
		jp2.add(jl2);
		cardPanel.add(jp1, "1");
		cardPanel.add(jp2, "2");
		btn1 = new JButton("Show Card 1");
		btn1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "1");
			}
		});
		btn2 = new JButton("Show Card 2");
		btn2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "2");
			}
		});
		buttonPanel.add(btn1);
		buttonPanel.add(btn2);
		add(cardPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
	}


}