import java.awt.*;
import java.io.File;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import java.util.ArrayList;
import java.util.List;

import graphics.*;
import graphics.Renderer;
import utils.*;

public class Main {
    public static void main(String[] args) {
        GameView view = new GameView();
       
        while (true) {
            try {
                Thread.sleep(30); // speed ??
                view.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}



class GameView extends JFrame {
    public Canvas canvas = new Canvas();
    
    int width = 1600, height = 900;

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


class Canvas extends JPanel implements MouseInputListener {
    

    public Image img = ImageReader.readImageFromPath("../Images/corgi_character.png");
    public List<AnimationRenderer> animation = new ArrayList<>();

    public CorgiButton cb = new CorgiButton();
    public CorgiButton cb1 = new CorgiButton();
    public CorgiButton cb2 = new CorgiButton();
    public CorgiButton cb3 = new CorgiButton();
    public CorgiButton cb4 = new CorgiButton();
    public CorgiButton cb5 = new CorgiButton();
    public CorgiButton cb6 = new CorgiButton();
    public CorgiButton cb7 = new CorgiButton();
    public Canvas () {
        this.add(cb);
        this.add(cb1);
        this.add(cb2);
        this.add(cb3);
        this.add(cb4);
        this.add(cb5);
        this.add(cb6);
        this.add(cb7);
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        this.renderBackground(g);
        this.renderForeground(g);
    }

    public void renderForeground(Graphics g) {
        if (cb.isSelected()) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            g.drawImage(img, p.x-50, p.y-100, null);
        }
        for (AnimationRenderer a: animation)
            a.render(g);
    }

    public void renderNextFrame () {
        repaint();
    }

    public void renderBackground(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(300, 150, 900, 600);
        g.setColor(Color.BLACK);
        for (int i = 0; i < 9; i ++) {
            for (int j =  0; j < 5; j++) {
                g.drawRect(300+i*100, 150+j*120, 100, 120);
                // g.drawImage(img, 300+i*100, 150+j*120+20, null);
            }
        }
        g.setColor(Color.PINK);
        g.fillRect(20, 150, 260, 600);
        g.setColor(Color.BLACK);
        g.fillRect(300, 20, 900, 120);
        g.fillRect(1220, 20, 180, 120);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        System.out.println("Clicked!");
        AnimationRenderer corgi = new AnimationRenderer("../Images/corgi_middle_finger", "sprite", p.x-50, p.y-100);
        cb.cancel();
        animation.add(corgi);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }




}

class CorgiButton extends JButton implements ActionListener {

    public Image icon_image = ImageReader.readImageFromPath("../Images/corgi_button_2.png");
    public Image icon_image_2 = ImageReader.readImageFromPath("../Images/corgi_character.png");
    public ImageIcon icon = new ImageIcon(icon_image);
    public ImageIcon icon_2 = new ImageIcon(icon_image_2);

    public boolean selected;

    public CorgiButton () {
        this.setIcon(icon);
        this.setOpaque(false);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.selected) {
            this.setIcon(icon_2);
            this.selected = true;
        }
        else {
            this.setIcon(icon);
            this.selected = false;
        }
            
    }

    public boolean isSelected() {
        return selected;
    }
    public void cancel() {
        this.selected = false;
    }

}







// class ImagePosition {
//     public static Point getCenterPosition(Image image, int x, int y) {

//     }
// }