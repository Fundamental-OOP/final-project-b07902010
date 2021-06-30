package selector;


/** Implement renderee */
public abstract class Selector {

    protected final Button[] buttons;
    protected int num_selections = 0, max_selections = 0, current_selection = -1;
 
    public Selector (int max_selections) {
        this.max_selections = max_selections;
        this.buttons = new Button[max_selections];
        for (int i = 0; i < max_selections; i++)
            buttons[i] = new Button(this, i);
        for (int i = 0; i < max_selections; i++)
            if (buttons[i] == null) System.out.println("[Selector] null button");
            else                    System.out.println("[Selector] normal button");
    }
    
    public Button[] getButtons() {
        return this.buttons;
    }


    public void clear() {
        num_selections = 0;
        current_selection = -1;
        for (Button button: buttons) button.cancel();
    }

    public abstract void addSelection (Object... o);

    public abstract void setLayout();

    public void setCurrentSelection (int index) {
        this.current_selection = index;
    }

    public int getCurrentSelection () {
        return current_selection;
    }

    public void resetSelection() {
        this.current_selection = -1;
        for (Button button: buttons)  button.cancel();
    }

}