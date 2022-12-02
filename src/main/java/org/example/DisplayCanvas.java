package org.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lombok.NonNull;


import static org.example.Constants.HEIGHT_IN_PIXELS;
import static org.example.Constants.WIDTH_IN_PIXELS;

public class DisplayCanvas extends JPanel {
    /**
     * This class handles the display.
     * The display matrix contains information about the
     * current state of the display.
     * The paintComponent draws the screen using this matrix.
     */
    private static final int RECT = 15;   // Size of each pixel
    private static int width = WIDTH_IN_PIXELS * RECT;
    private static int height = HEIGHT_IN_PIXELS * RECT;
    private int[][] displayMatrix = new int[HEIGHT_IN_PIXELS][WIDTH_IN_PIXELS];
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Drawing the Screen...");
//        this.printDisplayMatrix();
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(this);
        frame.setSize(new Dimension(width, height));
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
}
