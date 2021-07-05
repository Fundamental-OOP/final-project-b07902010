package selector;

import javax.swing.*;
import java.awt.event.ActionListener;

import java.awt.event.*;


public class Button extends JButton implements ActionListener {

    private final Selector selector;
    private int index;
    private boolean selected;


    public Button (Selector selector, int index) {
        this.index = index;
        this.setEnabled(false);
        this.selector = selector;
        this.setOpaque(false);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.selected) {
            this.selected = true;
            this.selector.setCurrentSelection(this.index);
        }
        else {
            this.selected = false;
            this.selector.resetSelection();
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void cancel() {
        this.selected = false;
    }

}
