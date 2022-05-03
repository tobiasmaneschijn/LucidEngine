package io.github.tobiasmaneschijn.lwjgl.engine;

public interface IHud {

    GameObject[] getGameItems();

    default void cleanup() {
        GameObject[] gameItems = getGameItems();
        for (GameObject gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}