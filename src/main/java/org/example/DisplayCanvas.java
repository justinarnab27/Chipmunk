package org.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


import static org.example.Constants.HEIGHT_IN_PIXELS;
import static org.example.Constants.WIDTH_IN_PIXELS;

public class DisplayCanvas extends JPanel {
    /**
     * This class handles the display.
     * The display matrix contains information about the
     * current state of the display.
     * The paintComponent draws the screen using this matrix.
     */
    private KeyLis listener;
    private static final int RECT = 15;   // Size of each pixel
    private static int width = WIDTH_IN_PIXELS * RECT;
    private static int height = HEIGHT_IN_PIXELS * RECT;

    private int[][] displayMatrix = new int[HEIGHT_IN_PIXELS][WIDTH_IN_PIXELS];

    @Getter
    @Setter
    private int keyBeingPressed = -1;
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Drawing the Screen...");
        for (int i = 0; i < this.displayMatrix.length; ++i) {
            for (int j = 0; j < this.displayMatrix[i].length; ++j) {
                if (this.displayMatrix[i][j] == 1) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(j * RECT, i * RECT, RECT, RECT);
            }
        }
    }

    public void createAndShowGui() {
        JFrame frame = new JFrame("Chipmunk");

        frame.getContentPane().add(this);
        frame.setSize(new Dimension(width, height + 25));
        frame.setVisible(true);
    }

    public void printDisplayMatrix() {
        //Prints the display matrix
        for (int[] row : this.displayMatrix) {
            for (int val : row) {
                System.out.print(val);
            }
            System.out.print("\n");
        }
    }

    public String getDisplayMatrixAsString() {
        //Gets the display matrix as string
        String s = "";
        for (int[] row : this.displayMatrix) {
            for (int val : row) {
                s += val + " ";
            }
            s += "\n";
        }
        return s;
    }

    public int getDisplayMatrixPixel(@NonNull final int r, @NonNull final int c) {
        return displayMatrix[r][c];
    }

    public void setDisplayMatrixPixel(int r, int c, int val) {
        displayMatrix[r][c] = val;
    }

    public void clearDisplayMatrix() {
        // Sets all the elements to zero
        for (int i = 0; i < this.displayMatrix.length; ++i) {
            for (int j = 0; j < this.displayMatrix[i].length; ++j) {
                this.displayMatrix[i][j] = 0;
            }
        }
    }

    public void updateScreen() {
        this.repaint();
    }

    private class KeyLis extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() >= 48 && e.getKeyCode() <= 57) {
                DisplayCanvas.this.setKeyBeingPressed(e.getKeyCode() - 48);
            } else if(e.getKeyCode() >= 65 && e.getKeyCode() <= 70) {
                DisplayCanvas.this.setKeyBeingPressed(e.getKeyCode() - 65 + 10);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            DisplayCanvas.this.setKeyBeingPressed(-1);
        }
    }

    public DisplayCanvas() {
        listener = new KeyLis();
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(listener);
    }
}
