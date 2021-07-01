package graphics;

/** 任何需要被繪出的 object 都必須實作這個 interface */
public interface Renderee {
    Renderer getRenderer();
}