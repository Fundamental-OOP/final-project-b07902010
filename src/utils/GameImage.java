package utils;
import java.awt.*;
import java.util.List;

public class GameImage {
    static public List<Image> numbers;
    public GameImage() {
        GameImage.numbers = ImageReader.readImagesFromPrefix("../img/number", "number");
    }
    static public Image getNumberImage(int i) {
        if (i < 10 && i >= 0) return numbers.get(i);
        return null;
    }
}