package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.Pipe;
import main.data.types.TripleString;

public class CalculatorGUI implements ActionListener {
	private JFrame frame;
    private JLabel label;
    private JTextField textFieldA, textFieldB;
    private Pipe<String[]> controlIn; 
	private Pipe<TripleString> controlOut;
    private String LastButton;

    public CalculatorGUI( JFrame frame,Pipe<String[]> cIn, Pipe<TripleString> cOut) {
    	 this.frame = frame;
    	this.controlIn = cIn;
		this.controlOut = cOut;
		
    }
     
    public void start() {
        frame.setSize(1920, 1080);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        label = new JLabel("Welcome to My GUI");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(label, BorderLayout.NORTH);
        
        JPanel answer = new JPanel();
        answer.setLayout(new BorderLayout());

        JLabel solution = new JLabel("Solution: ");
        solution.setHorizontalAlignment(JLabel.CENTER);
        solution.setFont(new Font("Arial", Font.BOLD, 30));
        answer.add(solution, BorderLayout.NORTH);
        panel.add(answer);
        

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(1720, 300));
        buttonPanel.setLayout(new GridLayout(5, 5));
        String[] buttonNames = {"Binomial PMF",       "Intersection",     "Factorial",       "Mean",            "CLEAR", 
        						"Binomial E(x)",      "Union",            "Combination",     "Median",          "Geometric PMF", 
        						"Binomial At Most",   "Complement",       "Permutation",     "Mode",            "Geometric E(x)", 
        						"Binomial At Least",  "Birthday Paradox", "P(A|B)",          "Variance",        "GeoMetric Varience", 
        						"Binomial Variance ",  "Monty Hall",      " Bayes' Theorem", "Std Deviation",   "ENTER",};
        for (int i = 0; i < buttonNames.length; i++) {
            JButton button = new JButton(buttonNames[i]);
            button.setToolTipText(" ");
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        JLabel labelA = new JLabel("Set A:");
        labelA.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(labelA, c);
        c.gridx = 1;
        textFieldA = new JTextField(20);
        textFieldA.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(textFieldA, c);
        c.gridx = 0;
        c.gridy = 1;
        JLabel labelB = new JLabel("Set B:");
        labelB.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(labelB, c);
        c.gridx = 1;
        textFieldB = new JTextField(20);
        textFieldB.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(textFieldB, c);
        panel.add(inputPanel, BorderLayout.WEST);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String buttonName = ((JButton) e.getSource()).getText();
        if (buttonName.equals("=")) {
            String setA = textFieldA.getText();
            String setB = textFieldB.getText();
            String opperation = LastButton;
            TripleString info = new TripleString(setA, setB, opperation);
            controlOut.put(info);
            new Thread(() -> {
                String answer = null;
                while(answer == null) {
                    if(controlIn.hasInput())
                        answer = controlIn.take()[0];
                }
                System.out.println(answer);
                label.setText("Solution: " + answer);
            }).start();
        }
        LastButton = buttonName;
    }
}
