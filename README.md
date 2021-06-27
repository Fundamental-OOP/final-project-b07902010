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


```java
import graphics.*;
/** Renderee: 被畫的, Renderer: 畫 renderee 的 **/

class Corgi implements Renderee {
    AnimationRenderer renderer = new AnimationRenderer("./corgi", "sprite");
    /* make animation from ./corgi/sprite_0.png, ./corgi/sprite_1.png, ... */

    renderer.setPosition(50, 20);  /* 畫在 (x, y) = (50, 20) 的位置, default (0, 0), 可以一直更新 */

    /* Must implement: 讓 GUI 的每一幀 frame 可以 update 用的 function */
    Renderer getRenderer() {
        return renderer;  /* 如果會報錯: return (Renderer) renderer; */
    }
}

class Background implements Renderee {
    ImageRenderer renderer = new ImageRenderer("./images/backgroud.png");
    /* 只讀取一張圖片，extension 不限 (e.g. png, jpg...) */

    /* Must implement: 讓 GUI 的每一幀 frame 可以 update 用的 function */
    Renderer getRenderer() {
        return renderer;   /* 如果會報錯: return (Renderer) renderer; */
    }
}
```
![Position Example](./position_example.png)
