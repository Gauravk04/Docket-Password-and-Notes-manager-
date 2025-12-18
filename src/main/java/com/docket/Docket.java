package com.docket;

/**
 * Docket - Password and Notes Manager
 * Main entry point for the application
 *
 * @author Gaurav
 * @version 2.0
 *
 * To compile: javac -d bin src/main/java/com/docket/*.java
 * To run: java -cp bin com.docket.Docket
 */
public class Docket {

    public static void main(String[] args) {
        // Show splash screen and launch main application after loading
        new SplashScreen(() -> {
            try {
                new PasswordManagerUI();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Error launching Docket application: " + ex.getMessage());
            }
        });
    }
}

