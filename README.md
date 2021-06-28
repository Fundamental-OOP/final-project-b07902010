# FOOP-FinalProject

## 裡面有啥
```
  cele
    ├── README.md
    ├── src/
    │   ├── graphics -> 畫遊戲用的
    │   └── utils    -> 方便寫 code 的 utilities
    ├── images/
    │   └── ... (測試ㄉ照片)
    └── tmp/ 
        └── ... (暫存ㄉ東西)
```
## Usage

### Renderer and Renderee

```java
import graphics.*;
/** Renderee: 被畫的, Renderer: 畫 renderee 的 **/

class Corgi implements Renderee {
    AnimationRenderer renderer = new AnimationRenderer("./corgi", "sprite");

    renderer.setPosition(50, 20); 

    Renderer getRenderer() {
        return renderer; 
}

class Background implements Renderee {
    ImageRenderer renderer = new ImageRenderer("./images/backgroud.png");

    Renderer getRenderer() {
        return renderer;  
}
```

### Selector
```java
import selector.*;
import selector.Button; // 會有 ambiguous 所以要特別 import Button

Selector selector = new Selector();
Selector selector = new Selector(10); // 指定 button 數量

/* 傳入 按鈕名稱、按鈕 icon 路徑、滑鼠 preview 圖路徑 */
seletor.addSelection("MiMiMaoMao", "./img/.../icon.png", "./img/.../preview.png");

seletor.getCurrentSelection() // 回傳目前選擇的按鈕（e.g. "MiMiMaoMao"）

```
![Position Example](./position_example.png)
