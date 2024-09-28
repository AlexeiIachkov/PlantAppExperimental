import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class PlantAppMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PlantAppMain::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        PlantManager plantManager = PlantManager.getInstance();

        JFrame frame = new JFrame("Plantus Mapsamusse V1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Header
        JLabel titleLabel = new JLabel("Plantus Mapsamusse V1.0");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(34, 139, 34)); // Green color
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Input fields with clear prompts
        JTextField plantNameField = createInputField("Enter Plant Name", "What is the plant's name?");
        JTextField lightConditionField = createInputField("Enter Light Condition", "Room light conditions (e.g., bright, low)");
        JTextField waterScheduleField = createInputField("Enter Water Schedule", "Watering schedule (e.g., every 3 days)");
        JTextField fertilizerScheduleField = createInputField("Enter Fertilizing Schedule", "Fertilizing schedule (e.g., every month)");
        JTextField categoryField = createInputField("Enter Plant Category", "Plant category (e.g., succulent, fern)");

        JLabel imagePathLabel = new JLabel("No image selected");
        imagePathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton uploadImageButton = new JButton("Upload Plant Image");
        uploadImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePathLabel.setText(selectedFile.getAbsolutePath());
            }
        });

        // Add components to main panel
        mainPanel.add(plantNameField);
        mainPanel.add(lightConditionField);
        mainPanel.add(waterScheduleField);
        mainPanel.add(fertilizerScheduleField);
        mainPanel.add(categoryField);
        mainPanel.add(uploadImageButton);
        mainPanel.add(imagePathLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Buttons with improved styling
        JButton addPlantButton = createStyledButton("Add Plant");
        addPlantButton.addActionListener(e -> {
            String plantName = plantNameField.getText();
            String lightCondition = lightConditionField.getText();
            String waterSchedule = waterScheduleField.getText();
            String fertilizerSchedule = fertilizerScheduleField.getText();
            String category = categoryField.getText();
            String imagePath = imagePathLabel.getText();

            Plant newPlant = PlantFactory.createPlant(plantName, lightCondition, waterSchedule, fertilizerSchedule, imagePath, category);
            plantManager.addPlant(newPlant);

            JOptionPane.showMessageDialog(frame, "Plant added:\n" + newPlant.displayDetails());
        });

        // Updated display plants button with consistent styling
        JButton displayPlantsButton = createStyledButton("Display Plants");
        displayPlantsButton.addActionListener(e -> displayPlantDetails(plantManager));

        mainPanel.add(addPlantButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(displayPlantsButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static JTextField createInputField(String placeholder, String tooltip) {
        JTextField textField = new JTextField(10);
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setToolTipText(tooltip);
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        // Add focus listener for clearing placeholder text
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });

        textField.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 2));
        textField.setOpaque(false); // Set transparent background

        return textField;
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded corners
                super.paintComponent(g);
            }
        };
        button.setBackground(new Color(34, 139, 34)); // Dark green
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        return button;
    }

    private static void displayPlantDetails(PlantManager plantManager) {
        JFrame plantDetailsFrame = new JFrame("Plant Details");
        plantDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        plantDetailsFrame.setSize(400, 700);
        plantDetailsFrame.setLocationRelativeTo(null);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(new Color(240, 240, 240));
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        JLabel detailsTitleLabel = new JLabel("Plant Details");
        detailsTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        detailsTitleLabel.setForeground(new Color(34, 139, 34));
        detailsTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsPanel.add(detailsTitleLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // List of plants with delete buttons
        for (Plant plant : plantManager.getPlants()) {
            JPanel plantPanel = new JPanel();
            plantPanel.setLayout(new BoxLayout(plantPanel, BoxLayout.Y_AXIS));
            plantPanel.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 2));
            plantPanel.setBackground(Color.WHITE);

            JTextArea plantTextArea = new JTextArea(plant.displayDetails());
            plantTextArea.setEditable(false);
            plantTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
            plantTextArea.setBackground(Color.WHITE);
            plantTextArea.setLineWrap(true);
            plantTextArea.setWrapStyleWord(true);
            plantTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JButton deleteButton = new JButton("Delete Plant");
            deleteButton.setBackground(Color.RED);
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setFocusPainted(false);
            deleteButton.addActionListener(e -> {
                plantManager.getPlants().remove(plant);
                JOptionPane.showMessageDialog(plantDetailsFrame, "Plant deleted.");
                displayPlantDetails(plantManager); // Refresh the plant details view
            });

            plantPanel.add(plantTextArea);
            plantPanel.add(deleteButton);
            detailsPanel.add(plantPanel);
            detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        plantDetailsFrame.add(detailsPanel);
        plantDetailsFrame.setVisible(true);
    }
}
