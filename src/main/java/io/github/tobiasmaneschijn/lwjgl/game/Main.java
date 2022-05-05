package io.github.tobiasmaneschijn.lwjgl.game;


import io.github.tobiasmaneschijn.lwjgl.engine.GameEngine;
import io.github.tobiasmaneschijn.lwjgl.engine.IGameLogic;
import io.github.tobiasmaneschijn.lwjgl.engine.Window;

public class Main {
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            Window.WindowOptions opts = new Window.WindowOptions();
            opts.cullFace = true;
            opts.showFps = true;
            opts.compatibleProfile = true;
            opts.antialiasing = true;
            opts.hideCursor = true;
            GameEngine gameEng = new GameEngine("GAME", vSync, opts, gameLogic);
            gameEng.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}