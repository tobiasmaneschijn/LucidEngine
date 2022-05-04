package io.github.tobiasmaneschijn.lwjgl.game;

import io.github.tobiasmaneschijn.lwjgl.engine.gameobjects.GameObject;
import io.github.tobiasmaneschijn.lwjgl.engine.IHud;
import io.github.tobiasmaneschijn.lwjgl.engine.Window;
import io.github.tobiasmaneschijn.lwjgl.engine.gameobjects.TextItem;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.*;
import org.joml.Vector4f;

import java.awt.*;


public class Hud implements IHud {

    private static final Font FONT = new Font("Arial", Font.PLAIN, 20);

    private static final String CHARSET = "ISO-8859-1";

    private final GameObject[] gameItems;

    private final TextItem statusTextItem;

    private final GameObject compassItem;

    public Hud(String statusText) throws Exception {
        FontTexture fontTexture = new FontTexture(FONT, CHARSET);
        this.statusTextItem = new TextItem(statusText, fontTexture);
        this.statusTextItem.getMesh().getMaterial().setAmbientColour(new Vector4f(0, 1, 0, 1));

        // Create compass
        Mesh mesh = OBJLoader.loadMesh("/models/compass.obj");
        Material material = new Material();
        material.setAmbientColour(new Vector4f(1, 1, 1, .8f));
        mesh.setMaterial(material);
        compassItem = new GameObject(mesh);
        compassItem.setScale(50);
        // Rotate to transform it to screen coordinates
        compassItem.setRotation(0f, 0f, 180f);

        // Create list that holds the items that compose the HUD
        gameItems = new GameObject[]{statusTextItem, compassItem};
    }

    public void setStatusText(String statusText) {
        this.statusTextItem.setText(statusText);
    }

    public void rotateCompass(float angle) {
        this.compassItem.setRotation(0, 0, 180 + angle);
    }

    @Override
    public GameObject[] getGameItems() {
        return gameItems;
    }

    public void updateSize(Window window) {
        this.statusTextItem.setPosition(10f, window.getHeight() - 50f, 0);
        this.compassItem.setPosition(window.getWidth() / 2, 75, 0);
    }

    public void updateText(String text) {
        this.statusTextItem.setText(text);
    }
}