package game;


import javax.swing.*;
import java.awt.*;
import model.*;
import graphics.*;

public class GameView extends JFrame {
    public Canvas canvas = new Canvas(this);
    private World world;

    int width = 1440, height = 900;

    public GameView (int width, int height)  {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(canvas);
        this.setVisible(true);
    }

    public void setWorld(World world) {
        this.world = world;
        canvas.setWorld(world);
    }

    public void update () {
        this.canvas.renderNextFrame();
    }
    public World getWorld() {
        return this.world;
    }

}

/** Draw some baseline???? */
class Canvas extends JPanel {
    private World world;
    private GameView view;
    public Canvas (GameView view) {
        this.view = view;
    }

    public void paintComponent(Graphics g) {
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1400, 800);
        for (Renderee renderee : view.getWorld().getRenderees() ) {
            renderee.getRenderer().render(g);
        }

    }

    public void renderNextFrame () {
        repaint();  /** calliing paintComponent() */
    }

    public void setWorld(World world) {
        this.world = world;
    }
}



