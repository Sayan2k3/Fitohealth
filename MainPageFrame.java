import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Declare weightField at the class level

public class MainPageFrame extends JFrame {
    private JTextField heightField; // Declare heightField at the class level
    private JTextField weightField; // Declare weightField at the class level

    public MainPageFrame() {
        setTitle("FITOHEALTH Main Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.BLACK);

        JPanel dietPlanningPanel = createClickableSectionPanel("Diet Planning", "Open");
        JPanel fitnessGoalsPanel = createClickableSectionPanel("Fitness Goals","set your goals");
        JPanel fitnessPlanningPanel = createClickableSectionPanel("Fitness Planning","Unlock plans");
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

        if (title.equals("Fitness Planning")) {
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    unlockFitness(panel);
                }
            });
        }

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
    private JPanel createWeightInputPanel(String title, String buttonText) {
        JPanel panel = createSectionPanel(title);
        panel.setLayout(new GridLayout(4, 1));

        JLabel heightLabel = new JLabel("Enter your height (cm): ");
        heightField = new JTextField();
        JLabel weightLabel = new JLabel("Enter your weight (kg): ");
        weightField = new JTextField();
        JButton calculateButton = new JButton(buttonText);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double bmi = calculateBMI(height, weight);
                String category = categorizeBMI(bmi);
                JOptionPane.showMessageDialog(null, "Your BMI category is: " + category);
            }
        });

        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(calculateButton);

        return panel;
    }

    private double calculateBMI(double height, double weight) {
        return weight / ((height / 100) * (height / 100));
    }

    private String categorizeBMI(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else {
            return "Overweight";
        }
    }

    private void unlockFitness(JPanel panel) {
        JPanel fitnessPanel = new JPanel(new BorderLayout());
    
        JPanel exercisePanel = new JPanel(new GridLayout(10, 1));
        JCheckBox[] exerciseCheckBoxes = new JCheckBox[10];
        String[] exercises = {
                "High Stepping", "Squats", "Side Hop", "Butt Bridge",
                "Plank", "Wall Pushups", "Cobra Stretch", "Glute Bridges",
                "Lunges with Right Leg", "Push Ups"
        };
    
        final int[] totalRepetitions = {0};
        final int[] totalSets = {0};
        final int[] totalTimeSeconds = {0}; // Changed to array
    
        for (int i = 0; i < exercises.length; i++) {
            exerciseCheckBoxes[i] = new JCheckBox(exercises[i]);
            exerciseCheckBoxes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTotalCredits(exerciseCheckBoxes);
                }
            });
            exercisePanel.add(exerciseCheckBoxes[i]);
        }
    
        JButton startButton = new JButton("Start Exercise");
        JLabel creditLabel = new JLabel("Total Credits Earned: 0");
    
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < exerciseCheckBoxes.length; i++) {
                    if (exerciseCheckBoxes[i].isSelected()) {
                        if (i < 3) {
                            totalRepetitions[0] += 10;
                            totalSets[0] += 3;
                            totalTimeSeconds[0] += 120;
                        } else if (i < 7) {
                            totalRepetitions[0] += 10;
                            totalSets[0] += 4;
                            totalTimeSeconds[0] += 180;
                        } else {
                            totalRepetitions[0] += 10;
                            totalSets[0] += 3;
                            totalTimeSeconds[0] += 120;
                        }
                    }
                }
    
                // Start the timer
                JLabel timerLabel = new JLabel("Time Left: " + totalTimeSeconds[0] + " seconds");
                Timer timer = new Timer(1000, new ActionListener() {
                    int timeLeft = totalTimeSeconds[0];
    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (timeLeft >= 0) {
                            timerLabel.setText("Time Left: " + timeLeft + " seconds");
                            timeLeft--;
                        } else {
                            ((Timer) e.getSource()).stop();
                        }
                    }
                });
                timer.start();
    
                JPanel timerPanel = new JPanel();
                timerPanel.add(timerLabel);
    
                fitnessPanel.add(timerPanel, BorderLayout.NORTH);
                fitnessPanel.revalidate();
                panel.add(fitnessPanel, BorderLayout.CENTER);
            }
        });
    
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int credits = calculateCredits(exerciseCheckBoxes);
                // Find the credit label and update its text
                Component[] components = panel.getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        JLabel label = (JLabel) component;
                        if (label.getText().startsWith("Total Credits Earned:")) {
                            label.setText("Total Credits Earned: " + credits);
                            break;
                        }
                    }
                }
            }
        });
    
        JPanel controlPanel = new JPanel();
        controlPanel.add(startButton);
        controlPanel.add(creditLabel);
        controlPanel.add(doneButton);
    
        fitnessPanel.add(exercisePanel, BorderLayout.CENTER);
        fitnessPanel.add(controlPanel, BorderLayout.SOUTH);
    
        panel.add(fitnessPanel, BorderLayout.CENTER);
        panel.revalidate();
    }

    private void updateTotalCredits(JCheckBox[] exerciseCheckBoxes) {
        int credits = calculateCredits(exerciseCheckBoxes);
        // Find the credit label and update its text
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getText().startsWith("Total Credits Earned:")) {
                    label.setText("Total Credits Earned: " + credits);
                    break;
                }
            }
        }
    }

    private int calculateCredits(JCheckBox[] exerciseCheckBoxes) {
        int credits = 0;
        for (JCheckBox checkBox : exerciseCheckBoxes) {
            if (checkBox.isSelected()) {
                credits++;
            }
        }
        return credits;
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
                "\t\t\tSkim Milk Yoghurt (1 cup (8 fl oz)) Vegetable Poha (1.5 katori)\n" +
                "\tMid-morning\t\t\n" +
                "\tLunch\t\tSkim Milk Curd (1 katori) Vegetable Khichdi (1.5 katori)\n" +
                "\t\t\tCabbage and Tomato (1 katori)\n" +
                "\tMid-afternoon\tOrange (1)\n" +
                "\tSnack\t\tTea with Less Sugar and Milk (0.5 tea cup)\n" +
                "\tDinner\t\tMixed Vegetable Salad (1 katori)\n" +
                "\t\t\tSoybean and Paneer Curry (1 katori) Roti (1 roti/chapati)\n" +
                "\t\t\tMint Chutney (2 tablespoon)\n";

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

        // Insert fitness blogs here
        String fitnessBlogs = "Fitness Blog\n\n" +
                "1. How to Build Muscles at Home Without Equipment\n" +
                "2. Top 10 Yoga Poses for Flexibility and Strength\n" +
                "3. 5 Effective Cardio Workouts for Weight Loss\n" +
                "4. Importance of Rest Days in Your Fitness Routine\n" +
                "5. Healthy Recipes for Pre and Post-Workout Meals\n" +
                "Importance of Rest Days in Your Fitness Routine\n\n" +
                "Given the diverse range of shapes and sizes among humans, it's only natural to consider that different eating plans and training routines might be more fitting or effective based on individual body types. Unfortunately, this perspective has given rise to numerous enduring misconceptions in the health and fitness industry.\n\n" +
                "Let's go back to the basics: Conversations surrounding somatotypes (the scientific term for human body types) often hinge on the idea that individuals can neatly fit into one of three distinct categories—ectomorph, mesomorph and endomorph. The belief is that somatotyping can offer general insights into how a person should approach exercise, diet, lifestyle and physical activity. However, the reality is that a person's somatotype characteristics can change over time, and many individuals embody a combination of all three somatotypes.\n\n" +
                "To clarify, while the three somatotypes—ectomorph, mesomorph and endomorph—are recognized in scientific literature, determining a person's placement in a specific category is more complex than it may initially appear. Transitioning from somatotyping to offering exercise recommendations and dietary guidelines often results in the dissemination of misinformation.\n\n" +
                "Here is a quick description of the three somatotypes:\n\n" +
                "Ectomorph: Characterized by a slim body frame, small shoulders and hips, long arms and legs, and less muscle mass\n\n" +
                "Mesomorph: Characterized by a medium build with higher-than-average muscular development, a low body-fat percentage, broad shoulders, and a muscular chest, shoulders and limbs.\n\n" +
                "Endomorph: Characterized by a larger, rounder body shape, high levels of body fat, a propensity to gain weight, and shorter arms and legs.\n\n" +
                "Common Myths about Mesomorphs\n\n" +
                "The following are some common myths that you may hear from clients or potential clients or read about in popular media. Your job is to sift through these types of statements and look for the evidence—which, in this case, you will not find.?\n\n" +
                "People with a mesomorph body type (and the other body types) should eat certain foods while avoiding others. The best approach to nutrition is to find a healthy eating plan that the client can stick to over the long haul. There are no hard and fast rules. And, the negatively worded guidance to “avoid” a favorite or preferred food is not likely to lead to success.?\n\n" +
                "Mesomorph body types represent individuals who don’t need to exercise to stay healthy. Because these individuals are more muscular and “fit looking,” many people make the mistake of thinking that they don’t need to exercise to maintain their appearance or stay healthy. Just as a person with an endomorph body type may be generally healthy, a person with a muscular appearance can have underlying health conditions that belie their appearance.\n\n" +
                "People with the mesomorph body type can eat anything they want without consequences. This is similar to the myth above, as it suggests that appearances equate to health. People with this body type can gain fat just like anyone else, so the notion that they can eat anything they want without it impacting their health or fitness level is simply false.\n\n" +
                "When a person has a mesomorph body type, they must have a great work ethic and love exercise and sports. Just because a person looks fit or muscular doesn’t mean that they will need less support when it comes to motivation and adherence, or with finding a type of physical activity they enjoy.\n\n" +
                "A Controversial History\n\n" +
                "The three somatotype classifications made their debut in the 1940s, courtesy of Dr. W.H. Sheldon—a psychologist who brought them into the limelight through his book, The Varieties of Human Physique. Dr. Sheldon's objective was to categorize individuals based on how closely they aligned with the three body types and then draw connections to traits like temperament and even criminal tendencies.\n\n" +
                "While Dr. Sheldon's theories related to constitutional psychology sparked controversy, his work’s enduring impact on physical education and the fitness industry is undeniable. However, this original method of categorization undermines the essence of personalized coaching and programming, which are integral components of effective health coaching and personal training. Relying solely on body types for decisions also opens the door to racial and cultural stereotypes. For instance, associating a particular race with higher endomorphy may lead to misguided assumptions about personality traits linked to body fat, perpetuating stereotypes like laziness, selfishness and a lack of self-control.\n\n" +
                "This serves as an excellent example of implicit bias that health coaches and exercise professionals must make ongoing efforts to recognize and address in their work. Unfortunately, individuals often attach personality traits to the three body types, fostering positive feelings for mesomorphs and ectomorphs while harboring negative stereotypes for endomorphs.\n\n" +
                "Final Thoughts\n\n" +
                "Categorizing people into the three somatotypes should not overshadow an individualized, client-centered approach to behavior change, as this practice provides no insight into how a person will respond to diet and exercise, let alone anything about their personality, temperament or level of self-control. A person's appearance should not dictate their macronutrient balance, the number of reps in a resistance-training set or their overall goals. Instead, let each client's unique goals and values guide your programming and approach to long-term lifestyle change. As a health coach or exercise professional, your objective is to deliver a tailored plan for each client, and making assumptions without evidence will only hinder that process.";

        textArea.setText(fitnessBlogs);

        fitnessBlogsFrame.add(new JScrollPane(textArea));
        fitnessBlogsFrame.setVisible(true);
    }

    private String getSectionText(String sectionTitle) {
        if (sectionTitle.equals("Fitness Goals")) {
            return "Set your fitness goals here!";
        } else if (sectionTitle.equals("Fitness Planning")) {
            return "Plan your fitness routines!";
        } else {
            return "Explore diet options!";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPageFrame().setVisible(true);
            }
        });
    }
}
