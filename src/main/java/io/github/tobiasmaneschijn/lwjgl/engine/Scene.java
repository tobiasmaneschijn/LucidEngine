package io.github.tobiasmaneschijn.lwjgl.engine;

import io.github.tobiasmaneschijn.lwjgl.engine.gameobjects.GameObject;
import io.github.tobiasmaneschijn.lwjgl.engine.gameobjects.SkyBox;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Mesh;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.lights.SceneLight;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.weather.Fog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scene {

    private Map<Mesh, List<GameObject>> meshMap;

    private SkyBox skyBox;

    private Fog fog;

    private SceneLight sceneLight;

    public Scene() {

        meshMap = new HashMap();
        fog = Fog.NOFOG;
    }

    public Map<Mesh, List<GameObject>> getGameMeshes() {
        return meshMap;
    }

    public void clearScene(){
        for (Mesh mesh : meshMap.keySet()) {
            mesh.cleanUp();
        }
        meshMap.clear();
    }

    public void setGameItems(GameObject[] gameItems) {
        int numGameItems = gameItems != null ? gameItems.length : 0;
        for (int i=0; i<numGameItems; i++) {
            GameObject gameItem = gameItems[i];
            Mesh mesh = gameItem.getMesh();
            List<GameObject> list = meshMap.get(mesh);
            if ( list == null ) {
                list = new ArrayList<>();
                meshMap.put(mesh, list);
            }
            list.add(gameItem);
        }
    }

    public void cleanup() {
        for (Mesh mesh : meshMap.keySet()) {
            mesh.cleanUp();
        }
    }

    public SkyBox getSkyBox() {
        return skyBox;
    }

    public void setSkyBox(SkyBox skyBox) {
        this.skyBox = skyBox;
    }

    public SceneLight getSceneLight() {
        return sceneLight;
    }

    public void setSceneLight(SceneLight sceneLight) {
        this.sceneLight = sceneLight;
    }

    public Fog getFog() {
        return fog;
    }

    public void setFog(Fog fog) {
        this.fog = fog;
    }
}