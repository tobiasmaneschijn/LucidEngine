package io.github.tobiasmaneschijn.lwjgl.game;


import io.github.tobiasmaneschijn.lwjgl.engine.GameEngine;
import io.github.tobiasmaneschijn.lwjgl.engine.IGameLogic;

public class Main {

    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("Toby's Test Env", 600, 480, vSync, gameLogic);
            gameEng.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}