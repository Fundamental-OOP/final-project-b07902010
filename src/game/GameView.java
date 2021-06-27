package game;


import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    public Canvas canvas = new Canvas();
    

    int width = 800, height = 600;

    public GameView ()  {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(canvas);
        this.setVisible(true);
        
    }

    public void update () {
        this.canvas.renderNextFrame();
    }

}

/** Draw some baseline???? */
class Canvas extends JPanel {

    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 600, 600);
    }

    public void renderNextFrame () {
        repaint();  /** calliing paintComponent() */
    }

}



