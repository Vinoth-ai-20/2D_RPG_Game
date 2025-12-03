package main;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 64x64 tile
    final int maxScreenColumns = 16;
    final int maxScreenRows = 12;
    final int screenWidth = maxScreenColumns * tileSize; // 16x64=1024 pixels
    final int screenHeight = maxScreenRows * tileSize; // 12x64=768 pixels

    Thread gameThread;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(18, 18, 18));
        this.setDoubleBuffered(true);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

    }
}
