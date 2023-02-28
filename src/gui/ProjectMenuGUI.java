package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import control.Pipe;
import main.data.types.TripleString;

public class ProjectMenuGUI {
	
	private Pipe<String[]> controlIn; 
	private Pipe<TripleString> controlOut;
	public JFrame frame;
	
	public ProjectMenuGUI(JFrame frame,Pipe<String[]> cIn, Pipe<TripleString> cOut) {
		this.controlIn = cIn;
		this.controlOut = cOut;
		this.frame =frame;;
	}
	
	public JFrame start() {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}

		frame.setPreferredSize(new Dimension(1920, 1080));
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(" "));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageIcon imageIcon = new ImageIcon(img);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(1920, 1080,  java.awt.Image.SCALE_SMOOTH);

		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(new ImageIcon(newimg));
		panel.add(label, BorderLayout.CENTER);

		JButton btn = new JButton("BEGIN");
		btn.setBounds(0, 0, 100, 150);
		btn.setBackground(Color.BLACK);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font(btn.getFont().getName(), Font.BOLD, 36));
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				
				CalculatorGUI calc = new CalculatorGUI(frame, controlIn, controlOut);
				calc.start();
				
			}});
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnPanel.setBackground(new Color(255, 255, 255, 128));
		btnPanel.add(btn);
		panel.add(btnPanel, BorderLayout.NORTH);
		
		
		frame.add(panel, BorderLayout.WEST);
		frame.setVisible(true);
		frame.setLocationRelativeTo(frame);
		frame.pack();
		return frame;
	}
} 

		
	
	


