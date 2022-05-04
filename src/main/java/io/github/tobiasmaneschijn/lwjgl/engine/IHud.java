package io.github.tobiasmaneschijn.lwjgl.engine;

import io.github.tobiasmaneschijn.lwjgl.engine.gameobjects.GameObject;

public interface IHud {

    GameObject[] getGameItems();

    default void cleanup() {
        GameObject[] gameItems = getGameItems();
        for (GameObject gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}