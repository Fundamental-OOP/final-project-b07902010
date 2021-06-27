package game;


public class GameLoop {
    public GameView view  = new GameView();

    public void start() {
        while (true) {
            try {
                Thread.sleep(100); // spped ??
                view.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
