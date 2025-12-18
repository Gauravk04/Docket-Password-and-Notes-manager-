package com.docket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PasswordManagerUI implements ActionListener {

    //Store password class reference
    HashtablePassword data = new HashtablePassword(15,0.5F,0);

    // GUI variables declaration
    JFrame frame;
    JFrame frame2;
    Container conn1,conn2;
    JLabel lAcc,lPass;
    JTextArea genePassArea, searchPassArea;
    JButton PassGeneBtn, PassStoreBtn, PassSearchBtn, AccAddBtn, PassDeleteBtn;
    JTextField tAcc,tPass;
    JButton addNoteBtn;
    JLabel addNoteLabel;
    JTextArea tNote;
    JButton addNote;
    JFrame conn3;

    ArrayList<String> notes = new ArrayList<>(); // to store the notes in an array list of string type

    @Override
    public void actionPerformed(ActionEvent e) { }

    //Frame settings
    public static void FrameGUI(JFrame frame){
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
    }

    // Material Design styled buttons with elevation and smooth animations
    public void GUIButtonsSetting(JButton btn) {
        btn.setBackground(new Color(66, 133, 244)); // Google Blue
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        btn.setFocusable(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Product Sans", Font.BOLD, 14));

        // Add rounded corners and shadow effect
        btn.setBorder(new javax.swing.border.AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw shadow
                g2d.setColor(new Color(0, 0, 0, 30));
                g2d.fillRoundRect(x + 2, y + 4, width - 4, height - 4, 25, 25);

                g2d.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(12, 24, 12, 24);
            }
        });

        // Hover and click animations
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            Timer hoverTimer;

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (hoverTimer != null) hoverTimer.stop();
                hoverTimer = new Timer(10, new ActionListener() {
                    float alpha = 0;
                    public void actionPerformed(ActionEvent e) {
                        alpha += 0.1f;
                        if (alpha >= 1.0f) {
                            alpha = 1.0f;
                            hoverTimer.stop();
                        }
                        btn.setBackground(new Color(71, 143, 255)); // Lighter blue
                        btn.repaint();
                    }
                });
                hoverTimer.start();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (hoverTimer != null) hoverTimer.stop();
                hoverTimer = new Timer(10, new ActionListener() {
                    float alpha = 1;
                    public void actionPerformed(ActionEvent e) {
                        alpha -= 0.1f;
                        if (alpha <= 0) {
                            alpha = 0;
                            hoverTimer.stop();
                        }
                        btn.setBackground(new Color(66, 133, 244)); // Original blue
                        btn.repaint();
                    }
                });
                hoverTimer.start();
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(51, 103, 214)); // Darker blue on press
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(71, 143, 255));
            }
        });
    }

    //GUI of Store password with modern Material Design
    public void StoringGUI()
    {
        frame2 = new JFrame("🔒 Store Your Passwords");
        frame2.setBounds(1400, 300, 500, 550);
        frame2.setSize(500,550);
        frame2.setUndecorated(true);
        FrameGUI(frame2);
        conn2 = frame2.getContentPane();
        conn2.setBackground(new Color(245, 245, 250)); // Light background
        conn2.setLayout(null);
        conn2.setVisible(true);

        // Modern header with gradient
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(66, 133, 244), getWidth(), 0, new Color(52, 168, 83));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setBounds(0, 0, 500, 80);
        headerPanel.setLayout(null);

        JLabel titleLabel = new JLabel("🔒 Store Password");
        titleLabel.setFont(new Font("Product Sans", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(140, 25, 300, 35);
        headerPanel.add(titleLabel);
        conn2.add(headerPanel);

        // Close button
        JButton closeBtn = new JButton("✕");
        closeBtn.setBounds(455, 10, 35, 35);
        closeBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setBackground(new Color(0, 0, 0, 0));
        closeBtn.setBorder(null);
        closeBtn.setFocusable(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> frame2.dispose());
        closeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeBtn.setBackground(new Color(255, 255, 255, 30));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeBtn.setBackground(new Color(0, 0, 0, 0));
            }
        });
        headerPanel.add(closeBtn);

        // Card panel for inputs
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Shadow
                g2d.setColor(new Color(0, 0, 0, 10));
                g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
        };
        cardPanel.setBounds(40, 120, 420, 350);
        cardPanel.setOpaque(false);
        cardPanel.setLayout(null);
        conn2.add(cardPanel);

        Font labelFont = new Font("Product Sans", Font.BOLD, 14);
        Font inputFont = new Font("Product Sans", Font.PLAIN, 15);

        //Account textField and label
        lAcc = new JLabel("🏢 Account Name");
        lAcc.setBounds(30, 30, 350, 25);
        lAcc.setFont(labelFont);
        lAcc.setForeground(new Color(60, 64, 67));
        cardPanel.add(lAcc);

        tAcc = new JTextField();
        tAcc.setBounds(30, 60, 360, 50);
        tAcc.setFont(inputFont);
        tAcc.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(218, 220, 224), 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        tAcc.setBackground(Color.WHITE);
        tAcc.setForeground(new Color(32, 33, 36));
        tAcc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tAcc.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(66, 133, 244), 2),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tAcc.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(218, 220, 224), 2),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
        });
        cardPanel.add(tAcc);

        //Account password textField and label
        lPass = new JLabel("🔑 Password");
        lPass.setBounds(30, 130, 350, 25);
        lPass.setFont(labelFont);
        lPass.setForeground(new Color(60, 64, 67));
        cardPanel.add(lPass);

        tPass = new JTextField();
        tPass.setBounds(30, 160, 360, 50);
        tPass.setFont(inputFont);
        tPass.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(218, 220, 224), 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        tPass.setBackground(Color.WHITE);
        tPass.setForeground(new Color(32, 33, 36));
        tPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tPass.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(66, 133, 244), 2),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tPass.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(218, 220, 224), 2),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
        });
        cardPanel.add(tPass);

        AccAddBtn = new JButton("💾 STORE PASSWORD");
        AccAddBtn.setBounds(80, 250, 260, 55);
        cardPanel.add(AccAddBtn);
        GUIButtonsSetting(AccAddBtn);
    }

    //for password generator and encryption with Material Design styling
    public void textArea(String Pass,JTextArea TA){
        TA.setText(Pass);
        Font fn = new Font("Product Sans", Font.PLAIN, 15);
        TA.setWrapStyleWord(true);
        TA.setLineWrap(true);
        TA.setCaretPosition(0);
        TA.setEditable(false);
        TA.setFont(fn);
        TA.setBackground(new Color(245, 245, 250));
        TA.setForeground(new Color(32, 33, 36));
        TA.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(218, 220, 224), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
    }

    //GUI of Password Manager with modern Google Material Design
    public PasswordManagerUI() {

        frame = new JFrame("🔐 Docket - Password & Notes Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520,800);
        frame.setResizable(false);

        FrameGUI(frame);

        conn1 = frame.getContentPane();
        conn1.setBackground(new Color(245, 245, 250)); // Light modern background
        conn1.setLayout(null);
        conn1.setVisible(true);

        // Add header panel with gradient effect
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(66, 133, 244), getWidth(), 0, new Color(52, 168, 83));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setBounds(0, 0, 520, 120);
        headerPanel.setLayout(null);

        JLabel titleLabel = new JLabel("🔐 DOCKET");
        titleLabel.setFont(new Font("Product Sans", Font.BOLD, 42));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(170, 25, 200, 50);
        headerPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Password & Notes Manager");
        subtitleLabel.setFont(new Font("Product Sans", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(255, 255, 255, 230));
        subtitleLabel.setBounds(135, 75, 300, 25);
        headerPanel.add(subtitleLabel);

        conn1.add(headerPanel);

        //Generator buttons settings
        PassGeneBtn = new JButton("🔑 GENERATE PASSWORD");
        PassGeneBtn.setBounds(135, 160, 250, 55);
        conn1.add(PassGeneBtn);
        GUIButtonsSetting(PassGeneBtn);

        //generating password
        PassGeneBtn.addActionListener(e -> {
                    if(PassGeneBtn ==e.getSource())
                    {
                        try{
                            int len = Integer.parseInt(JOptionPane.showInputDialog("Enter the password length"));
                            if(len>4)
                            {
                                //  password generator class reference
                                PasswordGenerator pass = new PasswordGenerator();
                                String passwd = pass.generatePassword(len);
                                genePassArea = new JTextArea(5,4);
                                textArea(passwd,genePassArea);
                                JOptionPane.showMessageDialog(conn1,new JScrollPane(genePassArea),"Copy your password",JOptionPane.INFORMATION_MESSAGE);

                            }
                            else JOptionPane.showMessageDialog (conn1,"Password length must be greater than 8!","Invalid Input Error",JOptionPane.WARNING_MESSAGE);

                        }
                        catch(Exception ex){JOptionPane.showMessageDialog(conn1,"Write something","EXIT!",JOptionPane.ERROR_MESSAGE);}
                    }
                }
        );

        // add a encryption button and action
        JButton EncryptBtn = new JButton("🔒 ENCRYPT TEXT");
        EncryptBtn.setBounds(135, 235, 250, 55);
        conn1.add(EncryptBtn);
        GUIButtonsSetting(EncryptBtn);
        EncryptBtn.addActionListener(e -> {
                    if(EncryptBtn ==e.getSource())
                    {
                        try{
                            String text = JOptionPane.showInputDialog("Enter the text to encrypt");
                            String secretKey = JOptionPane.showInputDialog("Enter the secret key");
                            if(text.length()>0 && secretKey.length()>0)
                            {
                                //  password generator class reference
                                com.docket.CryptoUtil pass1 = new com.docket.CryptoUtil();
                                String passwd = pass1.encrypt(secretKey, text); // encrypting the text
                                genePassArea = new JTextArea(5,4); // text area for the encrypted text
                                textArea(passwd,genePassArea); // setting the text area
                                JOptionPane.showMessageDialog(conn1,new JScrollPane(genePassArea),"Copy your password",JOptionPane.INFORMATION_MESSAGE); // showing the encrypted text

                            }
                            else JOptionPane.showMessageDialog (conn1,"Write something","Invalid Input Error",JOptionPane.WARNING_MESSAGE);

                        }
                        catch(Exception ex){JOptionPane.showMessageDialog(conn1,"Write something","EXIT!",JOptionPane.ERROR_MESSAGE);}
                    }
                }
        );

        // add a decryption button and action
        JButton DecryptBtn = new JButton("🔓 DECRYPT TEXT");
        DecryptBtn.setBounds(135, 310, 250, 55);
        conn1.add(DecryptBtn);
        GUIButtonsSetting(DecryptBtn);
        DecryptBtn.addActionListener(e -> {
                    if(DecryptBtn ==e.getSource())
                    {
                        try{
                            String text = JOptionPane.showInputDialog("Enter the text to decrypt"); // getting the encrypted text
                            String secretKey = JOptionPane.showInputDialog("Enter the secret key"); // getting the secret key
                            if(text.length()>0 && secretKey.length()>0) // checking if the text and secret key is not empty
                            {
                                //  password generator class reference
                                com.docket.CryptoUtil pass1 = new com.docket.CryptoUtil(); // creating a object of the CryptoUtil class
                                String passwd = pass1.decrypt(secretKey, text); // decrypting the text
                                genePassArea = new JTextArea(5,4); // text area for the decrypted text
                                textArea(passwd,genePassArea); // setting the text area
                                JOptionPane.showMessageDialog(conn1,new JScrollPane(genePassArea),"Decrypted text",JOptionPane.INFORMATION_MESSAGE); // showing the decrypted text

                            }
                            else JOptionPane.showMessageDialog (conn1,"Password length must be greater than 8!","Invalid Input Error",JOptionPane.WARNING_MESSAGE);

                        }
                        catch(Exception ex){JOptionPane.showMessageDialog(conn1,"Write something","EXIT!",JOptionPane.ERROR_MESSAGE);}
                    }
                }
        );

        //storing password using hashtable
        PassStoreBtn = new JButton("💾 STORE PASSWORD");
        PassStoreBtn.setBounds(135, 385, 250, 55);
        conn1.add(PassStoreBtn);
        GUIButtonsSetting(PassStoreBtn);
        //Store password action
        PassStoreBtn.addActionListener(e -> {
                    if(PassStoreBtn ==e.getSource())
                    {
                        try{
                            StoringGUI();
                            // action on the Store btn
                            AccAddBtn.addActionListener(e4 -> {
                                        if (AccAddBtn == e4.getSource()) {
                                            String account_name = tAcc.getText(); // getting the account name
                                            String acc_pass = tPass.getText(); // getting the password
                                            if (account_name.isEmpty() && acc_pass.isEmpty()) {
                                                JOptionPane.showMessageDialog(conn2,"unable to store your password!","ERROR",JOptionPane.ERROR_MESSAGE);
                                            }
                                            else{
                                                //calling put method of the hashtablePassword class
                                                data.add_Acc(account_name,acc_pass); // adding the account name and password to the hashtable
                                                JOptionPane.showMessageDialog(conn2, "Account added Successfully !");
                                                tAcc.setText(null);
                                                tPass.setText(null);
                                            }
                                        }
                                    }
                            );
                        }
                        catch(Exception ex) {JOptionPane.showMessageDialog(conn2,"Write something","EXIT",JOptionPane.ERROR_MESSAGE);}
                    }
                }
        );

        //searching password
        PassSearchBtn = new JButton("🔍 SEARCH PASSWORD");
        GUIButtonsSetting(PassSearchBtn);
        PassSearchBtn.setBounds(135, 460, 250, 55);
        conn1.add(PassSearchBtn);
        PassSearchBtn.addActionListener(e ->{
                    if (PassSearchBtn ==e.getSource()){
                        try{
                            String acc_name = JOptionPane.showInputDialog("Enter your Account Name"); // getting the account name
                            if (!acc_name.isBlank()) { // checking if the account name is not empty
                                Object pass = data.get_Acc(acc_name.toLowerCase()); // getting the password of the account name
                                if(pass!=null) { // checking if the password is not null
                                    searchPassArea = new JTextArea(4,5); // text area for the password
                                    textArea(String.valueOf(pass), searchPassArea); // setting the text area
                                    JOptionPane.showMessageDialog(conn1, new JScrollPane(searchPassArea), "Copy your password", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else JOptionPane.showMessageDialog(conn1, "Account not Found!");
                            }
                        }
                        catch (Exception ex){
                            JOptionPane.showMessageDialog(conn1,"Write something","EXIT",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );

        // deleting password
        PassDeleteBtn = new JButton("🗑️ DELETE PASSWORD");
        GUIButtonsSetting(PassDeleteBtn);
        PassDeleteBtn.setBounds(135, 535, 250, 55);
        conn1.add(PassDeleteBtn);
        PassDeleteBtn.addActionListener(e -> {
                    if (PassDeleteBtn == e.getSource()) {
                        try {
                            String acc_name = JOptionPane.showInputDialog("Enter the Account Name"); // getting the account name
                            if (!acc_name.isBlank()) {
                                data.remove_Acc(acc_name.toLowerCase()); // removing the account name and password from the hashtable
                                JOptionPane.showMessageDialog(conn1, "Delete successfully!"); // showing the message
                            }
                            else JOptionPane.showMessageDialog(conn1, "Account not found!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(conn1, "Write something", "EXIT", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }
        );
        // Adding Notes
        addNoteBtn = new JButton("📝 ADD NOTE");
        GUIButtonsSetting(addNoteBtn);
        addNoteBtn.setBounds(135, 610, 250, 55);
        conn1.add(addNoteBtn);
        addNoteBtn.addActionListener(e -> {
                    if (addNoteBtn == e.getSource()) {
                        try {
                            NoteGUI();
                            // action on the add note btn
                            addNote.addActionListener(e4 -> {
                                if (addNote == e4.getSource()) {
                                    String note = tNote.getText(); // getting the note
                                    if (note.isEmpty()) {
                                        JOptionPane.showMessageDialog(conn3, "unable to store your note!", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        //calling put method of the hashtablePassword class
                                        notes.add(note); // adding the note to the arraylist
                                        JOptionPane.showMessageDialog(conn3, "Note added Successfully !");
                                        conn3.setVisible(false);
                                        tNote.setText(null);
                                    }
                                }
                            });
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(conn3, "Write something", "EXIT", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );

        //get all notes
        JButton getNoteBtn = new JButton("📖 GET NOTE");
        GUIButtonsSetting(getNoteBtn);
        getNoteBtn.setBounds(135, 685, 250, 55);
        conn1.add(getNoteBtn);
        getNoteBtn.addActionListener(e -> {
                    if (getNoteBtn == e.getSource()) {
                        try {
                            String allNotes = notes.get(notes.size() - 1); // getting the last note added
                            if (allNotes.isEmpty()) { // checking if the note is empty or not
                                JOptionPane.showMessageDialog(conn1, "No note found!", "INFO", JOptionPane.INFORMATION_MESSAGE); // showing the message
                            } else {
                                searchPassArea = new JTextArea(4, 5); // text area for the note
                                textArea(allNotes, searchPassArea); // setting the text area
                                JOptionPane.showMessageDialog(conn1, new JScrollPane(searchPassArea), "Get your notes", JOptionPane.INFORMATION_MESSAGE); // showing the message
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(conn1, "Add a note before trying to retrive", "EXIT", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );

    }

    // method for setting the buttons and GUI for adding notes
    private void NoteGUI() {

        conn3 = new JFrame("📝 Add Your Note");
        conn3.setSize(500, 550);
        conn3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        conn3.setLocationRelativeTo(null);
        conn3.setLayout(null);
        conn3.getContentPane().setBackground(new Color(0xF5F7FA));
        conn3.setVisible(true);
        conn3.setResizable(false);

        // Modern title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(0, 0, 500, 70);
        titlePanel.setBackground(new Color(0x6A11CB)); // Purple header
        titlePanel.setLayout(null);

        addNoteLabel = new JLabel("📝 Add Your Note");
        addNoteLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        addNoteLabel.setForeground(Color.WHITE);
        addNoteLabel.setBounds(150, 20, 250, 35);
        titlePanel.add(addNoteLabel);
        conn3.add(titlePanel);

        //add note text area with modern styling
        tNote = new JTextArea(10, 10);
        tNote.setBounds(50, 100, 400, 350);
        tNote.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tNote.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0x6A11CB), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        tNote.setBackground(Color.WHITE);
        tNote.setForeground(new Color(0x333333));
        tNote.setLineWrap(true);
        tNote.setWrapStyleWord(true);
        conn3.add(tNote);

        //add note button
        addNote = new JButton("💾 SAVE NOTE");
        GUIButtonsSetting(addNote);
        addNote.setBounds(140, 470, 220, 45);
        conn3.add(addNote);
    }
}

