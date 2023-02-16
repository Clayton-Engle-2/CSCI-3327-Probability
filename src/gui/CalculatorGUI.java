public class CalculatorGUI extends JFrame {
    private JLabel resultLabel;

    public CalculatorGUI() {
        super("Calculator");

        // Set the size of the window to 960 x 1080
        setSize(new Dimension(1920, 1060));

        // Create the result label
        resultLabel = new JLabel("0", SwingConstants.RIGHT);
        resultLabel.setPreferredSize(new Dimension(1000, 100));
        resultLabel.setFont(new Font("Arial", Font.BOLD, 36));
        resultLabel.setBackground(Color.WHITE);
        resultLabel.setOpaque(true);
     // Create a label to hold the image
        JLabel label = new JLabel();

        // Load the image from a file
        BufferedImage image = loadImageFromFile("example.jpg");

        // Set the image on the label
        label.setIcon(new ImageIcon(image));

        // Add the label to the frame
        resultLabel.add(label);
       

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 8, 5, 5));
        buttonPanel.setPreferredSize(new Dimension(1910, 410));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // Create the buttons
        JButton[] buttons = new JButton[40];
        String[] buttonLabels = { "freq", "insct", "avg", "!", "C", "CE", "<-", "/", 
        						  "P(A|B)", "union", "med", "sqrt", "7", "8", "9", "*", 
        						  "bayes", "cplmt", "mode", "expo", "4", "5", "6", "-", 
        						  ")", "nPr", "var", "absV", "1", "2", "3", "+", 
        						  "]", "nCr", "sdev", "mod", "+/-", "0", ".", "=" };
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 24));
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
            buttonPanel.add(buttons[i]);
        }

        // Add the components to the window
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(5, 5));
        contentPane.setBackground(Color.GRAY);
        contentPane.add(resultLabel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(contentPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    private static BufferedImage loadImageFromFile(String filename) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

