package selector;

import java.awt.*;
import utils.*;
import javax.swing.ImageIcon;

import model.LevelWorld;
import unit.AllyConstructor;

/** 選擇 Ally 的 selector，固定最多有 8 種選項，不足 8 個會有空白的欄位 */
public class AllySelector extends Selector {

    private String[] types = new String[8];
    private Image default_icon_image = ImageReader.readImageFromPath("../img/default_button.png");
    private ImageIcon default_icon = new ImageIcon(default_icon_image); // 可能要做一個存所有圖片的 class ，減少 IO 負擔
    private ImageIcon[] icons = new ImageIcon[8];
    private Image[] preview_images = new Image[8];
    private LevelWorld world;

    public AllySelector (LevelWorld world) {
        super(8);
        this.default_icon = new ImageIcon( this.default_icon_image );
        this.world = world;
        for (Button button: buttons) {
            button.setIcon(default_icon);
            button.setEnabled(false);
        }
        this.setLayout();
    }
    
    public Button[] getButtons() {
        return this.buttons;
    }

    @Override
    public void clear() {
        num_selections = 0;
        for (Button button: buttons) {
            button.cancel();
            button.setIcon(default_icon);
            button.setEnabled(false);
        }
    }
    

    public String getCurrentSelectionType() {
        if (current_selection == -1) {
            System.out.println("[AllySelector] getCurrentSelectionType(): No selected ally.");
            return null;
        }
        else {
            System.out.println("[AllySelector] getCurrentSelectionType(): Current selection " + this.types[current_selection]);
            return this.types[current_selection];
        }

    }

    public Image getCurrentSelectionPreview() {
        if (current_selection == -1) return null;
        else return this.preview_images[current_selection];
    }

    public void update () {
        for (int i = 0; i < num_selections; i++) {
            if ( this.world.getPoop().enough( AllyConstructor.getNeededPoop(types[i]) ) ) {
                
                buttons[i].setEnabled(true);
            }
            else {
                buttons[i].setEnabled(false);
            }
        }
        for (int i = num_selections; i < max_selections; i++) {
            buttons[i].setEnabled(false);
        }    
    }


    @Override
    /** 加入一種 selection, 
     * 傳入 5 個參數: type, invalid image path,  valid image path, 
     *              selected image path, preview image path      */
    public void addSelection(Object... o) {
        
        types[num_selections] = (String) o[0];
        icons[num_selections] = new ImageIcon(ImageReader.readImageFromPath((String) o[1]));
        buttons[num_selections].setIcon(icons[num_selections]);
        // invalid_icons[num_selections] = new ImageIcon(ImageReader.readImageFromPath((String) o[1]));
        // valid_icons[num_selections] = new ImageIcon(ImageReader.readImageFromPath((String) o[2]));
        // selected_icons[num_selections] = new ImageIcon(ImageReader.readImageFromPath((String) o[3]));
        preview_images[num_selections] = ImageReader.readImageFromPath((String) o[2]);
        num_selections++;
        
        System.out.println("[AllySelector] addSelection(): " + (String)o[0] + " " + (String)o[1] + " " + (String)o[2]);
        
        // else System.out.println("[AllySelector] addSelection(): Wrong type or wrong number of arguments.");
        
    }

    @Override
    public void setLayout() {
        int w = this.default_icon_image.getWidth(null), h = this.default_icon_image.getHeight(null);
        for (int i = 0; i < max_selections; i++)
            buttons[i].setBounds(300+i*100, 20, w, h);
    }
}


