package graphics;

import java.awt.*;

public interface Renderer {
    void setPosition(int x, int y);
    void render(Graphics g);
}
