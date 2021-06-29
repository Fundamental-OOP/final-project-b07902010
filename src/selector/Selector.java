package selector;

import java.awt.*;

/** Implement renderee */
public class Selector {

    public final Button[] buttons = new Button[8];
    public String[] button_names = new String[8];
    public int num_selections = 0;
    public int max_selections = 8;
    public int current_selection = -1;
    
    public Selector () {
        for (int i = 0; i < max_selections; i++)
            buttons[i] = new Button(this, i);
    }
    
    public Button[] getButtons() {
        return this.buttons;
    }

    public void reset() {
        num_selections = 0;
        for (Button button: buttons)
            button.reset();
    }

    public void addSelection(String button_name, String image_path, String preview_path) {
        if (num_selections >= max_selections)
            System.out.println("[Selector] in addSelection(): maximum selection exceeds.");
        else {
            System.out.println("[Selector] in addSelection(): set new ally selection. " + button_name);
            this.buttons[num_selections].set(button_name, image_path, preview_path);
            this.button_names[num_selections] = button_name;
            num_selections += 1;
        }         
    }

    public void setCurrentSelection(int index) {
        this.current_selection = index;
    }

    public String getCurrentSelection() {
        if (current_selection == -1) {
            System.out.println("no selected ally!");
            return null;
        }
        else {
            System.out.println("get current selection " + this.buttons[current_selection].getName());
            return this.button_names[current_selection];
        }

    }

    public Image getSelectionPreview() {
        if (current_selection == -1)
            return null;
        else
            return this.buttons[current_selection].getPreviewImage();
    }

    public void cleanSelection() {
        this.current_selection = -1;
        for (Button button: buttons)
            button.cancel();
    }
}


