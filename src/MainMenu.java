import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class MainMenu extends JPanel {
    private BufferedImage background;
    private JButton startButton;
    private JButton exitButton;

    public MainMenu(Runnable onStart) {
        try {
            // This will look for "menu_bg.png" in your project root
            background = ImageIO.read(new File("menu_bg.png"));
        } catch (Exception e) {
            System.out.println("Menu background not found, using dark color.");
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        // Font to match the pixel aesthetic
        Font pixelFont = new Font("Arial Black", Font.BOLD, 22);

        // Update Start Button Style
        startButton = new JButton("START GAME");
        startButton.setFont(pixelFont);
        startButton.setPreferredSize(new Dimension(220, 60)); // Made slightly bigger
        startButton.setBackground(new Color(0, 255, 0, 180)); // Neon Green (w/ Transparency)
        startButton.setForeground(Color.WHITE); // White Text
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // White Border
        startButton.setFocusPainted(false); // Removes the annoying text box outline
        startButton.addActionListener(e -> onStart.run());

        // Update Exit Button Style
        exitButton = new JButton("EXIT GAME");
        exitButton.setFont(pixelFont);
        exitButton.setPreferredSize(new Dimension(220, 60));
        exitButton.setBackground(new Color(255, 0, 0, 180)); // Neon Red
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> System.exit(0));

        // Place buttons in center
        gbc.gridy = 0;
        this.add(startButton, gbc);
        gbc.gridy = 1;
        this.add(exitButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            // Scales the background to fit the window
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(new Color(20, 20, 20));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}