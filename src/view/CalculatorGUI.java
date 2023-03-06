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
import javax.swing.SwingConstants;

import control.Pipe;
import main.data.types.DisplayData;
import main.data.types.DisplaySolution;
import main.data.types.DisplayTwoArrays;

public class CalculatorGUI implements ActionListener {
	private JFrame frame;
    private JLabel label;
    private JLabel labelA;
    private JLabel labelB;
    private JLabel description;
    private JTextField textFieldA, textFieldB;
    private Pipe<DisplaySolution> controlIn; 
	private Pipe<DisplayData> controlOut;
    private String LastButton;
    private String[]descriptions;

    public CalculatorGUI( JFrame frame,Pipe<DisplaySolution> cIn, Pipe<DisplayData> cOut) {
    	 this.frame = frame;
    	this.controlIn = cIn;
		this.controlOut = cOut;
		descriptions = new String[25];
		
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

        description = new JLabel("<html> </html>");
        description.setHorizontalAlignment(SwingConstants.CENTER);
        description.setFont(new Font("Arial", Font.BOLD, 20));
        answer.add(description, BorderLayout.NORTH);
        panel.add(answer);
        
        descriptions[0] = "<html>The Geometric PMF function will calculate the probability of an event occuring on a specific trial using<br> "
        		+ "the probability mass function for a geometric distribution.<br>"
        		+ "p = The probability of success on each independent trial.<br>"
        		+ "n = The number of trials until the first success. </html>";
        descriptions[1] ="<html>The Binomial PMF function will calculate the probability of  achieving a fixed number of successes over<br>"
        		+ " a fixed number of trials<br>"
        		+ "p = The probability of success on each independent trial.<br>"
        		+ "n = The total number of trials to be tested. <br>"
        		+ "k = The total number of successes we want to occur in 'n' number of trials. </html>";
        descriptions[2] = "<html>The Factorial function will calculate n!. For example, let n = 5<br>"
        		+ "			5! = 5 * 4 * 3 * 2 * 1 <br>"
        		+ "			5! = 120 </html>";
        descriptions[3] = "<html>The Mean function calculates the average of a set of numbers. </html>";
        descriptions[4] = "<html> <html> ";
        descriptions[5] = "<html> The Geometric Mean function will calculate the expected value, or the average number of trials until<br> "
        		+ "first success, for a given geometric probability distribution. <br> "
        		+ "p = The probability of success for each independent trial. </html>" ;
        descriptions[6] = "<html>The Binomial Mean function will calculate the number of successes to be expected over a fixed number of trials.<br>"
        		+ "p = The probability of success for each independent trial. <br>"
        		+ "n = The total number of trials to be tested. </html>" ;
        descriptions[7] = "<html>The Combination function will calculate how many different ways a set of items can be arranged in subsets<br> "
        		+ "of a certain size.  For example, this function could calculate how many different ways cards of a certain suit could be<br> "
        		+ "arranged into a 5 card hand by entering n = 13 (total number of hearts in a deck) and r = 5 (arrangements of 5 card hands). <br>"
        		+ "n = total number of items. <br>"
        		+ "r = The number of items in a group </html>";
        descriptions[8] = "<html> The Median function calculates the elemnt in a set of numbers that would be the middle most if the set was sorted </html>";
        descriptions[9] = "<html> The Intersection function finds a set who's elements exist in both Set A and Set B </html>";
        descriptions[10] = "<html> The Geometric Variance Intuitively, the variance of a geometric distribution represents how spread out the<br>"
        		+ "distribution is. Specifically, a higher variance indicates that the distribution is more spread out and<br>"
        		+ "that there is more variability in the number of trials needed to obtain the first success. A lower variance,<br>"
        		+ "on the other hand, indicates that the distribution is less spread out and that there is less variability in<br>"
        		+ "the number of trials needed to obtain the first success. <br>"
        		+ "p =  The probability of recording a success on each independent trial. </html>";
        descriptions[11] = "<html> Binomial Variance: Intuitively, the variance of a binomial distribution represents how much the actual<br>"
        		+ "outcomes in the trials are likely to deviate from the expected number of successes. Specifically, a higher<br> "
        		+ "variance indicates that there is more variability in the number of successes that may be observed in the n<br> "
        		+ "trials. A lower variance indicates that there is less variability and the outcomes are more likely to be<br> "
        		+ "close to the expected number of successes. <br>"
        		+ "p =  The probability of recording a success on each independent trial. <br>"
        		+ "n = The total number of trials to be run</html>";
        descriptions[12] = "<html>  A permutation is a way of arranging a set of objects in a specific order. The Permutation function will<br>"
        		+ "calculate how many unique arrangements there are with a total number objects 'n' can be arranfed <br>"
        		+ "into subsets of size r. <br>"
        		+ "n = The total number of objects in the set. <br>"
        		+ "r = The size of the subset the objects will be arranged into.</html>";
        descriptions[13] = "<html> The Mode function will calculate the value that appears the most often in a set of numbers. </html>";
        descriptions[14] = "<html> The Union function finds a set who's elemnts either exist in Set A, Set B, or both. </html>";
        descriptions[15] = "<html> The GeoMetric Standard Deviation function represents the degree of variability or spread in the number<br> "
        		+ "of trials needed to obtain the first success. Specifically, it tells us how much the actual number of<br> "
        		+ "trials is likely to differ from the expected value. The standard deviation is a useful measure of<br> "
        		+ "variability in the geometric distribution because it allows us to estimate the range of values that the<br>"
        		+ " number of trials needed to obtain the first success is likely to fall within. It is also used in<br> "
        		+ "hypothesis testing and confidence interval calculations involving the geometric distribution. </html>";
        descriptions[16] = "<html> The binomial Standard Deviation function </html>";
        descriptions[17] = "<html> The Probability of Event A Occuring Given Event B has Occured function will calculate the probability that<br>"
        		+ "event A will occur given event B has happened, and that events A and B are independent. <br>"
        		+ "A = the Probability of event A occuring. <br>"
        		+ "B = The probability of event B occuring. </html>";
        descriptions[18] = "<html> The Variance function will calculate   </html>";
        descriptions[19] = "<html> The compliment Function will find a set, whos elemnts  </html>";
        descriptions[20] = "<html> The Geometric Before N function will calculate the probability of a success occuring before a certain trial n </html>";
        descriptions[21] = "<html> The Binomial At Most </html>";
        descriptions[22] = "<html>  The Bayes Theorem function will </html>";
        descriptions[23] = "<html> The Standard Deviation </html>";
        descriptions[24] = "<html> ENTER </html>";
        descriptions[25] = "<html> BACK </html>";
        descriptions[26] = "<html> The Binomial At Least </html>";
        descriptions[27] = "<html> The Monty Hall function</html>";
        descriptions[28] = "<html> The Birthday Paradox</html>";
        descriptions[29] = "<html>ENTER  </html>";
        
        

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(1720, 300));
        buttonPanel.setLayout(new GridLayout(5, 5));
        String[] buttonNames = {"Geometric PMF",       "Binomial PMF",      "Factorial",       "Mean",             "CLEAR", 
        						"Geometric Mean",      "Binomial Mean",     "Combination",     "Median",           "Intersection", 
        						"Geometric Variance",  "Binomial Variance", "Permutation",     "Mode",             "Union", 
        						"Geometric Std Dev",   "Binomial Std Dev",  "P(A|B)",          "Variance",         "Compliment", 
        						"Geometric Before N",  "Binomial At Most",  "Bayes' Theorem",  "Std Deviation",    "ENTER",
        						"Back",                "Binomial At Least", "Monty Hall",      "Birthday Paradox", "ENTER",};
        
        JButton button = new JButton(buttonNames[0]);
        button.setToolTipText(" ");
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.addActionListener(this);
        buttonPanel.add(button);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        labelA = new JLabel("");
        labelA.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(labelA, c);
        c.gridx = 1;
        textFieldA = new JTextField(20);
        textFieldA.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(textFieldA, c);
        c.gridx = 0;
        c.gridy = 1;
        labelB = new JLabel("");
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
        if(buttonName.equals("Geometric PMF")) {
        	description.setText(descriptions[0]);
        	labelA.setText("p = ");
        	labelB.setText("n = ");
        }
        if (buttonName.equals("ENTER")) {
            String setA = textFieldA.getText();
            String setB = textFieldB.getText();
            String opperation = LastButton;
            DisplayData info = new DisplayTwoArrays(setA, setB, opperation);
            controlOut.put(info);
            new Thread(() -> {
                String answer = null;
                while(answer == null) {
                    if(controlIn.hasInput())
                        answer = (controlIn.take().getSolution());
                }
                System.out.println(answer);
                label.setText("Solution: " + answer);
            }).start();
        }
        LastButton = buttonName;
    }
}