package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to this 2D RPG Game!");

        JFrame window = new JFrame("2D RPG Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D RPG Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGame();
    }
}