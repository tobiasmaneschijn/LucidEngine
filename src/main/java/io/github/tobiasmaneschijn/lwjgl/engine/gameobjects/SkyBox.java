package io.github.tobiasmaneschijn.lwjgl.engine.gameobjects;

import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Material;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Mesh;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.OBJLoader;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Texture;

public class SkyBox extends GameObject {

    public SkyBox(String objModel, String textureFile) throws Exception {
        super();
        Mesh skyBoxMesh = OBJLoader.loadMesh(objModel);
        Texture skyBoxtexture = new Texture(textureFile);
        skyBoxMesh.setMaterial(new Material(skyBoxtexture, 0.0f));
        setMesh(skyBoxMesh);
        setPosition(0, 0, 0);
    }
}