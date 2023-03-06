package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import control.Pipe;
import main.data.types.DisplayData;
import main.data.types.DisplaySolution;

public class MainMenuGUI {
	private Pipe<DisplaySolution> controlIn; 
	private Pipe<DisplayData> controlOut;
	public JFrame frame;
	
	public MainMenuGUI(Pipe<DisplaySolution> cIn, Pipe<DisplayData> cOut) {
		this.controlIn = cIn;
		this.controlOut = cOut;
		frame = new JFrame("Example GUI");
	}

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setPreferredSize(new Dimension(1920, 1080));
				frame.setResizable(false);
				frame.setLayout(new BorderLayout());

				BufferedImage img = null;
				try {
					img = ImageIO.read(new File("C:\\Users\\Clayton\\Desktop\\Pictures\\welcome.jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				ImageIcon imageIcon = new ImageIcon(img);
				Image image = imageIcon.getImage();
				Image newimg = image.getScaledInstance(1920, 1080,  java.awt.Image.SCALE_SMOOTH);

				JPanel panel = new JPanel(new BorderLayout());
				JLabel label = new JLabel(new ImageIcon(newimg));
				panel.add(label);

				JButton btn = new JButton("BEGIN");
				btn.setBackground(Color.BLACK);
				btn.setForeground(Color.WHITE);
				btn.setFont(new Font(btn.getFont().getName(), Font.BOLD, 36));
				btn.setHorizontalAlignment(SwingConstants.CENTER);
				btn.setVerticalAlignment(SwingConstants.CENTER);
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().removeAll();
						
						CalculatorGUI calc = new CalculatorGUI(frame, controlIn, controlOut);
						calc.start();
						
					}});
				btn.setPreferredSize(new Dimension(200, 100));
				frame.add(panel, BorderLayout.CENTER);
				panel.add(btn, BorderLayout.SOUTH);
				frame.setVisible(true);
				frame.setLocationRelativeTo(frame);
				frame.pack();
				
				
			}
		});
	}
}

