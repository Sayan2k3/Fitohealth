import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPageFrame extends JFrame {
    public MainPageFrame() {
        setTitle("FITOHEALTH Main Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 4, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.BLACK);

        JPanel dietPlanningPanel = createClickableSectionPanel("Diet Planning", "Open");
        JPanel fitnessGoalsPanel = createSectionPanel("Fitness Goals");
        JPanel fitnessPlanningPanel = createSectionPanel("Fitness Planning");
        JPanel fitnessBlogsPanel = createClickableSectionPanel("Fitness Blogs", "Fitness Information");

        mainPanel.add(dietPlanningPanel);
        mainPanel.add(fitnessGoalsPanel);
        mainPanel.add(fitnessPlanningPanel);
        mainPanel.add(fitnessBlogsPanel);

        add(mainPanel);
    }

    private JPanel createSectionPanel(String title) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(getWidth(), getHeight());
                Color color1 = Color.BLACK; // Start color
                Color color2 = Color.DARK_GRAY; // End color
                GradientPaint gradient = new GradientPaint(start, color1, end, color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title.toUpperCase()));
        panel.setOpaque(false);

        JLabel label = new JLabel(getSectionText(title));
        label.setForeground(Color.WHITE);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 20f));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createClickableSectionPanel(String title, String buttonText) {
        JPanel panel = createSectionPanel(title);
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (title.equals("Diet Planning")) {
                    showDietOptions();
                } else if (title.equals("Fitness Blogs")) {
                    showFitnessBlogs();
                }
            }
        });

        JButton button = new JButton(buttonText);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (title.equals("Diet Planning")) {
                    showDietOptions();
                } else if (title.equals("Fitness Blogs")) {
                    showFitnessBlogs();
                }
            }
        });
        panel.add(button, BorderLayout.SOUTH);

        return panel;
    }

    private void showDietOptions() {
        JFrame optionsFrame = new JFrame();
        optionsFrame.setTitle("Diet Options");
        optionsFrame.setSize(400, 200);
        optionsFrame.setLocationRelativeTo(null);
        optionsFrame.setLayout(new GridLayout(2, 1));

        JButton weightGainButton = new JButton("Weight Gain");
        JButton weightLossButton = new JButton("Weight Loss");

        weightGainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWeightGainPlan();
            }
        });

        weightLossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWeightLossPlan();
            }
        });

        optionsFrame.add(weightGainButton);
        optionsFrame.add(weightLossButton);

        optionsFrame.setVisible(true);
    }

    private void showWeightGainPlan() {
        JFrame weightGainFrame = new JFrame();
        weightGainFrame.setTitle("Weight Gain Plan");
        weightGainFrame.setSize(800, 600);
        weightGainFrame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setEditable(false);

        // Insert weight gain plan here
        String weightGainPlan = "Weight Gain Diet Plan\n\n" +
        "Monday\tBreakfast\t2 vegetable stuffed parathas, 1 cup curd, handful of nuts\n" +
        "\tMid-morning\t1 glass lassi or mango shake\n" +
        "\tLunch\t1 cup dal/chicken curry, 1 cup seasonal vegetable, 2 chapattis, ½ cup rice, 1 cup salad\n" +
        "\tEvening\t1 glass fresh fruit juice, 2 whole-grain toasts\n" +
        "\tDinner\t1 cup vegetable, 2 chapattis, 1 cup salad\n\n" +

        "Tuesday\tBreakfast\t2 moong dal chilla (paneer stuffing optional), 1 cup curd, handful of nuts\n" +
        "\tMid-morning\tFruit smoothie\n" +
        "\tLunch\t1 cup dal, 2 chapattis, ½ cup rice, 1 cup curd, 1 cup salad\n" +
        "\tEvening\t1 bowl tomato soup, 1 cup aloo chat\n" +
        "\tDinner\t1 cup seasonal vegetable, 2 chapattis, 1 cup salad\n\n" +

        "Wednesday\tBreakfast\t1 cup bread upma, 1 cup milk, handful of nuts\n" +
        "\tMid-morning\t2 bananas\n" +
        "\tLunch\t1 cup lentils, 1 cup vegetables, 2 chapattis, 1 cup salad\n" +
        "\tEvening\t1 cup upma, 1 glass vegetable juice\n" +
        "\tDinner\t1 cup vegetable, 2 chapattis, 1 cup salad\n\n" +

        "Thursday\tBreakfast\t2 cucumber potato sandwiches, 1 cup orange juice, handful of nuts\n" +
        "\tMid-morning\t1 cup sweet potato chaat, 1 glass buttermilk\n" +
        "\tLunch\t1 cup lentils/chicken or fish curry, 2 cups rice, 1 cup salad\n" +
        "\tEvening\tMilk smoothie, 2 bananas\n" +
        "\tDinner\t1 cup seasonal vegetable, 2 chapattis, 1 cup salad\n\n" +

        "Friday\tBreakfast\t1 cup vegetable poha, 1 cup curd, handful of nuts\n" +
        "\tMid-morning\t1 glass fresh fruit juice\n" +
        "\tLunch\t1 cup dal, 1 cup vegetable, 2 chapattis, ½ cup rice, 1 cup salad\n" +
        "\tEvening\t2 besan chilla, 1 cup sprouts salad\n" +
        "\tDinner\t1 cup vegetable/chicken curry, 2 chapattis, 1 cup salad\n\n" +

        "Saturday\tBreakfast\t2 suji chilla, 1 glass strawberry shake, handful of nuts\n" +
        "\tMid-morning\t1 glass coconut water, 1 cup fruit\n" +
        "\tLunch\t1 cup dal, 1 cup vegetable/tofu/paneer curry, ½ cup rice, 2 chapattis, 1 cup salad\n" +
        "\tEvening\t1 cup fruit salad, 4-5 vegetable cutlets\n" +
        "\tDinner\t2 chapattis, 1 cup vegetable/chicken/fish curry, 1 cup salad\n\n" +

        "Sunday\tBreakfast\t2 eggs, 2 whole-grain bread slices, 1 cup milk, handful of nuts\n" +
        "\tMid-morning\t1 glass banana smoothie\n" +
        "\tLunch\t1 cup dal, 1 cup vegetable curry, 2 chapattis, ½ cup rice, 1 cup salad\n" +
        "\tEvening\t1 glass strawberry smoothie, 1 cup vegetable poha\n" +
        "\tDinner\t1 cup chicken curry, 2 chapattis, 1 cup salad";
                

        textArea.setText(weightGainPlan);

        weightGainFrame.add(new JScrollPane(textArea));
        weightGainFrame.setVisible(true);
    }

    private void showWeightLossPlan() {
        JFrame weightLossFrame = new JFrame();
        weightLossFrame.setTitle("Weight Loss Plan");
        weightLossFrame.setSize(800, 600);
        weightLossFrame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setEditable(false);

        // Insert weight loss plan here
        String weightLossPlan = "Weight Loss Diet Plan\n\n" +
        "Day\tMeal\tFood\n" +
        "Day 1\tBreakfast\tCucumber Detox Water (1 glass)\n" +
        "\t\t\tOats Porridge in Skimmed Milk (1 bowl)\n" +
        "\t\t\tMixed Nuts (25 grams)\n" +
        "\tMid-morning\tFruit smoothie\n" +
        "\tLunch\t\tSkimmed Milk Paneer (100 grams)\n" +
        "\t\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tDal (1 katori) Gajar Matar Sabzi (1 katori)\n" +
        "\t\t\tRoti (1 roti/chapati)\n" +
        "\n" +
        "Day 2\tBreakfast\tCucumber Detox Water (1 glass)\n" +
        "\t\t\tCurd (1.5 katori) Mixed Vegetable Stuffed Roti (2 pieces)\n" +
        "\tMid-morning\t\t\n" +
        "\tLunch\t\tSkimmed Milk Paneer (100 grams)\n" +
        "\t\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tLentil Curry (0.75 bowl) Methi Rice (0.5 katori)\n" +
        "\tMid-afternoon\tApple (0.5 small (2-3/4″ dia)) Buttermilk (1 glass)\n" +
        "\tSnack\t\tCoffee with Milk and Less Sugar (0.5 tea cup)\n" +
        "\tDinner\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tSauteed Vegetables with Paneer (1 katori) Roti (1 roti/chapati)\n" +
        "\t\t\tGreen Chutney (2 tablespoon)\n" +
        "\n" +
        "Day 3\tBreakfast\tCucumber Detox Water (1 glass)\n" +
        "\t\t\tSkim Milk Yoghurt (1 cup (8 fl oz)) Multigrain Toast (2 toast)\n" +
        "\tMid-morning\t\t\n" +
        "\tLunch\t\tSkimmed Milk Paneer (100 grams)\n" +
        "\t\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tSauteed Vegetables with Paneer (1 katori) Roti (1 roti/chapati)\n" +
        "\t\t\tGreen Chutney (2 tablespoon)\n" +
        "\tMid-afternoon\tBanana (0.5 small (6″ to 6-7/8″ long)) Buttermilk (1 glass)\n" +
        "\tSnack\t\tTea with Less Sugar and Milk (1 teacup)\n" +
        "\tDinner\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tLentil Curry (0.75 bowl) Methi Rice (0.5 katori)\n" +
        "\n" +
        "Day 4\tBreakfast\tCucumber Detox Water (1 glass)\n" +
        "\t\t\tFruit and Nuts Yogurt Smoothie (0.75 glass)\n" +
        "\t\t\tEgg Omelette (1 serve(one egg))\n" +
        "\tMid-morning\t\t\n" +
        "\tLunch\t\tSkimmed Milk Paneer (100 grams)\n" +
        "\t\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tGreen Gram Whole Dal Cooked (1 katori) Bhindi sabzi (1 katori)\n" +
        "\t\t\tRoti (1 roti/chapati)\n" +
        "\tMid-afternoon\tOrange (1 fruit (2-5/8″ dia)) Buttermilk (1 glass)\n" +
        "\tSnack\t\tCoffee with Milk and Less Sugar (0.5 teacup)\n" +
        "\tDinner\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tPalak Chole (1 bowl) Steamed Rice (0.5 katori)\n" +
        "\n" +
        "Day 5\tBreakfast\tCucumber Detox Water (1 glass)\n" +
        "\t\t\tSkimmed Milk (1 glass) Peas Poha (1.5 katori)\n" +
        "\tMid-morning\t\t\n" +
        "\tLunch\t\tSkimmed Milk Paneer (100 grams)\n" +
        "\t\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tLow Fat Paneer Curry (1.5 katori) Missi Roti (1 roti)\n" +
        "\tMid-afternoon\tPapaya (1 cup 1″ pieces) Buttermilk (1 glass)\n" +
        "\tSnack\t\tTea with Less Sugar and Milk (1 teacup)\n" +
        "\tDinner\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tCurd (1.5 katori) Aloo Baingan Tamatar Ki Sabzi (1 katori) Roti (1 roti/chapati)\n" +
        "\n" +
        "Day 6\tBreakfast\tCucumber Detox Water (1 glass)\n" +
        "\t\t\tMixed Sambar (1 bowl) Idli (2 idli)\n" +
        "\tMid-morning\t\t\n" +
        "\tLunch\t\tSkimmed Milk Paneer (100 grams)\n" +
        "\t\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tCurd (1.5 katori) Aloo Baingan Tamatar Ki Sabzi (1 katori) Roti (1 roti/chapati)\n" +
        "\tMid-afternoon\tCut Fruits (1 cup) Buttermilk (1 glass)\n" +
        "\tSnack\t\tCoffee with Milk and Less Sugar (0.5 tea cup)\n" +
        "\tDinner\t\tMixed Vegetable Salad (1 katori)\n" +
        "\t\t\tGreen Gram Whole Dal Cooked (1 katori) Bhindi sabzi (1 katori) Roti (1 roti/chapati)\n" +
        "\n" +
        "Day 7\tBreakfast\tCucumber Detox Water (1 glass)\n" +
        "\t\t\tBesan Chilla (2 cheela) Green Garlic Chutney (3 tablespoon)\n" +
        "\tMid-morning\t\t\n" +
        "\tLunch\t\tSkimmed Milk Paneer (100 grams)\n" +"";

        textArea.setText(weightLossPlan);

        weightLossFrame.add(new JScrollPane(textArea));
        weightLossFrame.setVisible(true);
    }

    private void showFitnessBlogs() {
        JFrame fitnessBlogsFrame = new JFrame();
        fitnessBlogsFrame.setTitle("Fitness Blogs");
        fitnessBlogsFrame.setSize(800, 600);
        fitnessBlogsFrame.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setEditable(false);

        // Insert fitness blogs information here
        String fitnessBlogsInfo = "7 Yoga Poses to Counteract the Effects of Prolonged Sitting\n\n" +
        "1. Cat-Cow Pose:\n" +
        "   - Start on hands and knees, arching and rounding the back in a fluid motion.\n" +
        "2. Child's Pose:\n" +
        "   - Kneel, sit back on heels, stretch arms forward, lowering forehead to mat.\n" +
        "3. Cobra Pose or Upward-Facing Dog:\n" +
        "   - Lie face down, lift chest and torso while pressing palms into the floor.\n" +
        "4. Lizard's Pose:\n" +
        "   - Deep hip-opening posture, with one foot forward in a low lunge position.\n" +
        "5. Warrior 2:\n" +
        "   - Step feet wide apart, bend front knee, extend arms, keeping shoulders relaxed.\n" +
        "6. Figure 4 Pose:\n" +
        "   - Lie on back, cross one ankle over opposite knee, clasping hands behind thigh.\n" +
        "7. Supine Twist:\n" +
        "   - Lie on back, bring knees to chest, let them fall to one side while keeping shoulders grounded.\n\n" +
        
                "";

        textArea.setText(fitnessBlogsInfo);

        fitnessBlogsFrame.add(new JScrollPane(textArea));
        fitnessBlogsFrame.setVisible(true);
    }

    private String getSectionText(String title) {
        return title;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainPageFrame().setVisible(true);
            }
        });
    }
}
