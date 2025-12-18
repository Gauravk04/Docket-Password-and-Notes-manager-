package com.docket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Modern Google-inspired antigravity loading screen with floating animations
public class SplashScreen extends JPanel {
    JFrame frame;
    JLabel text, subtitle, message;
    JProgressBar progressBar;
    JPanel[] floatingDots = new JPanel[12];
    Timer animationTimer;
    double[] angles = new double[12];
    int animationFrame = 0;

    public SplashScreen(Runnable onComplete) {
        createGUI();
        addFloatingElements();
        addText();
        addProgressBar();
        startAnimations();
        runningPBar(onComplete);
    }

    public void createGUI() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Create main panel with custom gradient paint
        this.setLayout(null);
        this.setBounds(0, 0, 600, 500);
        frame.add(this);

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create animated gradient background
        GradientPaint gp = new GradientPaint(
            0, 0, new Color(66, 133, 244),  // Google Blue
            600, 500, new Color(219, 68, 55)  // Google Red
        );
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, 600, 500);

        // Add overlay gradient for depth
        GradientPaint overlay = new GradientPaint(
            300, 0, new Color(234, 67, 53, 30),  // Semi-transparent
            300, 500, new Color(66, 133, 244, 30)
        );
        g2d.setPaint(overlay);
        g2d.fillRect(0, 0, 600, 500);
    }

    public void addFloatingElements() {
        // Create floating circular elements
        int[] sizes = {8, 12, 10, 15, 9, 13, 11, 14, 10, 8, 12, 9};
        Color[] colors = {
            new Color(255, 255, 255, 180),
            new Color(251, 188, 5, 200),  // Google Yellow
            new Color(52, 168, 83, 200),  // Google Green
            new Color(255, 255, 255, 150),
            new Color(251, 188, 5, 180),
            new Color(255, 255, 255, 200),
            new Color(52, 168, 83, 180),
            new Color(255, 255, 255, 160),
            new Color(251, 188, 5, 150),
            new Color(255, 255, 255, 190),
            new Color(52, 168, 83, 150),
            new Color(255, 255, 255, 170)
        };

        for (int i = 0; i < floatingDots.length; i++) {
            floatingDots[i] = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(getBackground());
                    g2d.fillOval(0, 0, getWidth(), getHeight());
                }
            };
            floatingDots[i].setOpaque(false);
            floatingDots[i].setBackground(colors[i]);
            floatingDots[i].setSize(sizes[i], sizes[i]);
            angles[i] = Math.random() * Math.PI * 2;
            this.add(floatingDots[i]);
        }
    }

    public void addText() {
        // Main title with shadow effect
        text = new JLabel("🔐 DOCKET", SwingConstants.CENTER);
        text.setFont(new Font("Product Sans", Font.BOLD, 48));
        text.setForeground(Color.WHITE);
        text.setBounds(150, 180, 300, 60);
        this.add(text);

        // Subtitle with modern font
        subtitle = new JLabel("Password & Notes Manager", SwingConstants.CENTER);
        subtitle.setFont(new Font("Product Sans", Font.PLAIN, 18));
        subtitle.setForeground(new Color(255, 255, 255, 230));
        subtitle.setBounds(150, 240, 300, 30);
        this.add(subtitle);

        // Loading message
        message = new JLabel("Initializing...", SwingConstants.CENTER);
        message.setFont(new Font("Product Sans", Font.PLAIN, 14));
        message.setForeground(new Color(255, 255, 255, 200));
        message.setBounds(150, 400, 300, 25);
        this.add(message);
    }

    public void addProgressBar() {
        // Custom styled progress bar
        progressBar = new JProgressBar(0, 100) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background track
                g2d.setColor(new Color(255, 255, 255, 80));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Progress fill with gradient
                int fillWidth = (int) ((getWidth() * getValue()) / 100.0);
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(251, 188, 5),  // Google Yellow
                    fillWidth, 0, new Color(52, 168, 83)  // Google Green
                );
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, fillWidth, getHeight(), 20, 20);
            }
        };
        progressBar.setBounds(150, 320, 300, 12);
        progressBar.setBorderPainted(false);
        progressBar.setOpaque(false);
        progressBar.setValue(0);
        this.add(progressBar);
    }

    public void startAnimations() {
        animationTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationFrame++;

                // Animate floating dots in circular patterns
                for (int i = 0; i < floatingDots.length; i++) {
                    angles[i] += 0.02 + (i * 0.001);
                    int radius = 180 + (i * 15);
                    int centerX = 300;
                    int centerY = 250;

                    int x = (int) (centerX + radius * Math.cos(angles[i]));
                    int y = (int) (centerY + radius * Math.sin(angles[i]));

                    floatingDots[i].setLocation(x, y);
                }

                // Pulsing effect for text
                float scale = (float) (1.0 + 0.05 * Math.sin(animationFrame * 0.05));
                text.setFont(text.getFont().deriveFont(48f * scale));

                repaint();
            }
        });
        animationTimer.start();
    }

    public void runningPBar(Runnable onComplete) {
        new Thread(() -> {
            int i = 0;
            String[] messages = {
                "🔄 Loading modules...",
                "🔒 Initializing security...",
                "🔐 Setting up encryption...",
                "✨ Preparing interface...",
                "✅ Complete!"
            };

            while (i <= 100) {
                try {
                    Thread.sleep(30);
                    progressBar.setValue(i);

                    // Update loading message
                    if (i < 20) message.setText(messages[0]);
                    else if (i < 40) message.setText(messages[1]);
                    else if (i < 60) message.setText(messages[2]);
                    else if (i < 85) message.setText(messages[3]);
                    else message.setText(messages[4]);

                    i++;
                    if (i == 100) {
                        Thread.sleep(500);
                        animationTimer.stop();
                        frame.dispose();
                        // Execute callback after splash screen completes
                        if (onComplete != null) {
                            SwingUtilities.invokeLater(onComplete);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

