package main;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 64x64 tile
    final int maxScreenColumns = 16;
    final int maxScreenRows = 12;
    final int screenWidth = maxScreenColumns * tileSize; // 16x64=1024 pixels
    final int screenHeight = maxScreenRows * tileSize; // 12x64=768 pixels

    int FPS = 60;
    private static final long NANOS_TO_MILLIS = 1000000;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    private static final Logger LOGGER = Logger.getLogger(GamePanel.class.getName());

    //Player Coordinates
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(18, 18, 18));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerTick = (double) 1000000000 / FPS;
        double nextTick = System.nanoTime() + timePerTick;


        while (gameThread != null) {

            update();

            repaint();

            double remainingTime = nextTick - System.nanoTime();
            if (remainingTime > 0) {
                try {
                    Thread.sleep((long) remainingTime / NANOS_TO_MILLIS);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.WARNING, "Game Thread was Interrupted");
                    Thread.currentThread().interrupt();
                }
            }
            nextTick += timePerTick;
        }

    }

    public void update() {
        if (keyHandler.upPressed) {
            playerY -= playerSpeed;
        }
        if (keyHandler.downPressed) {
            playerY += playerSpeed;
        }
        if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        }
        if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(playerX, playerY, tileSize, tileSize);
        g2d.dispose();
    }
}



