package io.github.tobiasmaneschijn.lwjgl.engine;

import io.github.tobiasmaneschijn.lwjgl.engine.hud.IGuiLayer;

public interface IGameLogic {
    void init(Window window) throws Exception;

    void input(Window window, MouseInput mouseInput);

    void update(float interval, MouseInput mouseInput);

    void render(Window window);

    IGuiLayer getGuiLayer();

    void cleanup();
}
