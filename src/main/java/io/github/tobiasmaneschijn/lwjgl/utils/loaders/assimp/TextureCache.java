package io.github.tobiasmaneschijn.lwjgl.utils.loaders.assimp;

import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureCache {

    private static TextureCache INSTANCE;

    private Map<String, Texture> texturesMap;

    private TextureCache() {
        texturesMap = new HashMap<>();
    }

    public static synchronized TextureCache getInstance() {
        if ( INSTANCE == null ) {
            INSTANCE = new TextureCache();
        }
        return INSTANCE;
    }

    public Texture getTexture(String path) throws Exception {
        Texture texture = texturesMap.get(path);
        if ( texture == null ) {
            texture = new Texture(path);
            texturesMap.put(path, texture);
        }
        return texture;
    }
}