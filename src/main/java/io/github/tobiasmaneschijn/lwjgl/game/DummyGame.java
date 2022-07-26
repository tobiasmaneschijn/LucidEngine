package io.github.tobiasmaneschijn.lwjgl.game;

import io.github.tobiasmaneschijn.lwjgl.engine.IGameLogic;
import io.github.tobiasmaneschijn.lwjgl.engine.MouseInput;
import io.github.tobiasmaneschijn.lwjgl.engine.Scene;
import io.github.tobiasmaneschijn.lwjgl.engine.Window;
import io.github.tobiasmaneschijn.lwjgl.engine.gameobjects.GameObject;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.*;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.lights.DirectionalLight;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.lights.SceneLight;
import io.github.tobiasmaneschijn.lwjgl.engine.hud.IGuiLayer;
import io.github.tobiasmaneschijn.lwjgl.game.player.PlayerController;
import io.github.tobiasmaneschijn.lwjgl.utils.loaders.assimp.StaticMeshLoader;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;

public class DummyGame implements IGameLogic {

    private static final int OFFSET = 2;
    private static final int SCALE = 2;

    private float deltaTime = 0;
    public Renderer getRenderer() {
        return renderer;
    }

    public Scene getScene() {
        return scene;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    private final Renderer renderer;
    private Scene scene;
    private PlayerController playerController;

    public float getLightAngle() {
        return lightAngle;
    }

    private float lightAngle;
    private float lightinc;
    private final GameGUI guiLayer;
    private boolean hasBoard = false;
    private boolean hasPieces = false;
    private List<GameObject>  gameObjects;

    public DummyGame() {
        renderer = new Renderer();
        guiLayer = new GameGUI(this);
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        scene = new Scene();
        playerController = new PlayerController();
        playerController.init(window);

        guiLayer.setDefaultStyle();

        gameObjects = new ArrayList<>();
      //  buildFloor( gameObjects);
       // buildPieces( gameObjects);




        // Setup Lights
        setupLights();

    }

    public void spawnBoard() throws Exception {
        if(hasBoard) return;
        buildFloor(gameObjects);
        scene.setGameItems(gameObjects.toArray(new GameObject[0]));
        hasBoard = true;
    }

    public void spawnPieces() throws Exception{
        if (hasPieces) return;
        buildPieces(gameObjects);
        hasPieces = true;
        scene.setGameItems(gameObjects.toArray(new GameObject[0]));
    }
    public void clearLevel(){
        gameObjects.clear();
        hasPieces = false;
        hasBoard = false;
        scene.clearScene();
    }

    private void buildFloor( List<GameObject> gameObjects) throws Exception {
        float reflectance = 0f;
        Mesh quadMesh = StaticMeshLoader.load("models/plane.obj", "")[0];
        Mesh quadMesh2 =  StaticMeshLoader.load("models/plane.obj", "")[0];
        Material mat1 = new Material(new Vector4f(0.1f, 0.1f, 0.1f, 1.0f), reflectance);
        Material mat2 = new Material(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), reflectance);
        quadMesh.setMaterial(mat1);
        quadMesh2.setMaterial(mat2);
        int size = (int) Math.sqrt(64);
        int offset = OFFSET;
        int scale = SCALE;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                GameObject quadGameItem = new GameObject((i + j) % 2 == 0 ? quadMesh : quadMesh2);
                quadGameItem.setScale(scale);
                quadGameItem.setPosition( (i * offset * scale), 0, j * offset * scale);
                gameObjects.add(quadGameItem);
            }
        }
    }

    private void buildPieces( List<GameObject> gameObjects) throws Exception {

        // Create material for the white pieces
        Material mat2 = new Material(new Vector4f(1f, .10f, .10f, 1.0f), .5f);
        Material mat1 = new Material(new Vector4f(0.15f, 0.15f, 0.15f, 1.0f), .8f);

        // Load the pieces
        Mesh kingBMesh = StaticMeshLoader.load("models/chess/king.obj", "")[0];
        Mesh queenBMesh = StaticMeshLoader.load("models/chess/queen.obj", "")[0];
        Mesh rookBMesh = StaticMeshLoader.load("models/chess/rook.obj", "")[0];
        Mesh bishopBMesh = StaticMeshLoader.load("models/chess/bishop.obj", "")[0];
        Mesh knightBMesh = StaticMeshLoader.load("models/chess/knight.obj", "")[0];
        Mesh pawnBMesh = StaticMeshLoader.load("models/chess/pawn.obj", "")[0];

        Mesh kingWMesh = StaticMeshLoader.load("models/chess/king.obj", "")[0];
        Mesh queenWMesh = StaticMeshLoader.load("models/chess/queen.obj", "")[0];
        Mesh rookWMesh = StaticMeshLoader.load("models/chess/rook.obj", "")[0];
        Mesh bishopWMesh = StaticMeshLoader.load("models/chess/bishop.obj", "")[0];
        Mesh knightWMesh = StaticMeshLoader.load("models/chess/knight.obj", "")[0];
        Mesh pawnWMesh = StaticMeshLoader.load("models/chess/pawn.obj", "")[0];

        // set the materials
        kingBMesh.setMaterial(mat1);
        queenBMesh.setMaterial(mat1);
        rookBMesh.setMaterial(mat1);
        bishopBMesh.setMaterial(mat1);
        knightBMesh.setMaterial(mat1);
        pawnBMesh.setMaterial(mat1);

        kingWMesh.setMaterial(mat2);
        queenWMesh.setMaterial(mat2);
        rookWMesh.setMaterial(mat2);
        bishopWMesh.setMaterial(mat2);
        knightWMesh.setMaterial(mat2);
        pawnWMesh.setMaterial(mat2);

        // create the game objects
        GameObject kingB = new GameObject(kingBMesh);
        GameObject queenB = new GameObject(queenBMesh);
        GameObject rookB1 = new GameObject(rookBMesh);
        GameObject rookB2 = new GameObject(rookBMesh);
        GameObject bishopB1 = new GameObject(bishopBMesh);
        GameObject bishopB2 = new GameObject(bishopBMesh);
        GameObject knightB1 = new GameObject(knightBMesh);
        GameObject knightB2 = new GameObject(knightBMesh);
        List<GameObject> pawnsB = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            GameObject pawn = new GameObject(pawnBMesh);
            pawnsB.add(pawn);
        }

        GameObject kingW = new GameObject(kingWMesh);
        GameObject queenW = new GameObject(queenWMesh);
        GameObject rookW1 = new GameObject(rookWMesh);
        GameObject rookW2 = new GameObject(rookWMesh);
        GameObject bishopW1 = new GameObject(bishopWMesh);
        GameObject bishopW2 = new GameObject(bishopWMesh);
        GameObject knightW1 = new GameObject(knightWMesh);
        GameObject knightW2 = new GameObject(knightWMesh);
        List<GameObject> pawnsW = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            GameObject pawn = new GameObject(pawnWMesh);
            pawnsW.add(pawn);
        }

        // set the positions
        rookB1.setPosition(0 * OFFSET * SCALE, 0, 0 * OFFSET * SCALE);
        rookB1.setRotation(0, 90, 0);
        rookB2.setPosition(7 * OFFSET * SCALE, 0, 0 * OFFSET * SCALE);
        rookB2.setRotation(0, 90, 0);
        bishopB1.setPosition(2 * OFFSET * SCALE, 0, 0 * OFFSET * SCALE);
        bishopB1.setRotation(0, 90, 0);
        bishopB2.setPosition(5 * OFFSET * SCALE, 0, 0 * OFFSET * SCALE);
        bishopB2.setRotation(0, 90, 0);
        knightB1.setPosition(1 * OFFSET * SCALE, 0, 0 * OFFSET * SCALE);
        knightB1.setRotation(0, 90, 0);
        knightB2.setPosition(6 * OFFSET * SCALE, 0, 0 * OFFSET * SCALE);
        knightB2.setRotation(0, 90, 0);
        kingB.setPosition(4 * OFFSET * SCALE, 0, 0 * OFFSET * SCALE);
        kingB.setRotation(0, 90, 0);
        queenB.setPosition(3 * OFFSET * SCALE, 0, 0 * OFFSET * SCALE);
        queenB.setRotation(0, 90, 0);
        for (int i = 0; i < 8; i++) {
            pawnsB.get(i).setPosition(i * OFFSET * SCALE, 0, 1 * OFFSET * SCALE);
            pawnsB.get(i).setRotation(0, 90, 0);
        }

        rookW1.setPosition(0 * OFFSET * SCALE, 0, 7 * OFFSET * SCALE);
        rookW1.setRotation(0, -90, 0);
        rookW2.setPosition(7 * OFFSET * SCALE, 0, 7 * OFFSET * SCALE);
        rookW2.setRotation(0, -90, 0);
        bishopW1.setPosition(2 * OFFSET * SCALE, 0, 7 * OFFSET * SCALE);
        bishopW1.setRotation(0, -90, 0);
        bishopW2.setPosition(5 * OFFSET * SCALE, 0, 7 * OFFSET * SCALE);
        bishopW2.setRotation(0, -90, 0);
        knightW1.setPosition(1 * OFFSET * SCALE, 0, 7 * OFFSET * SCALE);
        knightW1.setRotation(0, -90, 0);
        knightW2.setPosition(6 * OFFSET * SCALE, 0, 7 * OFFSET * SCALE);
        knightW2.setRotation(0, -90, 0);
        kingW.setPosition(4 * OFFSET * SCALE, 0, 7 * OFFSET * SCALE);
        kingW.setRotation(0, -90, 0);
        queenW.setPosition(3 * OFFSET * SCALE, 0, 7 * OFFSET * SCALE);
        queenW.setRotation(0, -90, 0);
        for (int i = 0; i < 8; i++) {
            pawnsW.get(i).setPosition(i * OFFSET * SCALE, 0, 6 * OFFSET * SCALE);
            pawnsW.get(i).setRotation(0, -90, 0);
        }



        // add to game objects
        gameObjects.add(kingB);
        gameObjects.add(queenB);
        gameObjects.add(rookB1);
        gameObjects.add(rookB2);
        gameObjects.add(bishopB1);
        gameObjects.add(bishopB2);
        gameObjects.add(knightB1);
        gameObjects.add(knightB2);
        gameObjects.addAll(pawnsB);

        gameObjects.add(kingW);
        gameObjects.add(queenW);
        gameObjects.add(rookW1);
        gameObjects.add(rookW2);
        gameObjects.add(bishopW1);
        gameObjects.add(bishopW2);
        gameObjects.add(knightW1);
        gameObjects.add(knightW2);
        gameObjects.addAll(pawnsW);






    }

    private void movePiece(GameObject piece, int x, int z) {
        piece.setPosition(x * OFFSET * SCALE, 0, z * OFFSET * SCALE);
    }

    private void setupLights() {
        SceneLight sceneLight = new SceneLight();
        scene.setSceneLight(sceneLight);

        // Ambient Light
        sceneLight.setAmbientLight(new Vector3f(0.3f, 0.3f, 0.3f));
        sceneLight.setSkyBoxLight(new Vector3f(1.0f, 1.0f, 1.0f));

        // Directional Light
        float lightIntensity = 1.0f;
        Vector3f lightDirection = new Vector3f(0, 1, 1);
        DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), lightDirection, lightIntensity);
        directionalLight.setShadowPosMult(10);
        directionalLight.setOrthoCords(-10.0f, 10.0f, -10.0f, 10.0f, -1.0f, 20.0f);
        sceneLight.setDirectionalLight(directionalLight);

    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        playerController.input(window, mouseInput);
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            lightinc -= 0.05f;
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            lightinc += 0.05f;
        } else {
            lightinc = 0;
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        deltaTime = interval;
        playerController.update(interval, mouseInput);

        lightAngle += lightinc;
        if (lightAngle < 0) {
            lightAngle = 0;
        } else if (lightAngle > 180) {
            lightAngle = 180;
        }
        float zValue = (float) Math.cos(Math.toRadians(lightAngle));
        float yValue = (float) Math.sin(Math.toRadians(lightAngle));
        Vector3f lightDirection = this.scene.getSceneLight().getDirectionalLight().getDirection();
        lightDirection.x = 0;
        lightDirection.y = yValue;
        lightDirection.z = zValue;
        lightDirection.normalize();
    }

    @Override
    public void render(Window window) {

        playerController.render(window);
        renderer.render(window, playerController.getCamera(), scene);
    }

    @Override
    public IGuiLayer getGuiLayer() {
        return guiLayer;
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        scene.cleanup();
    }

    public boolean isHasBoard() {
        return hasBoard;
    }

    public boolean isHasPieces() {
        return hasPieces;
    }

    public float getDeltaTime() {
        return deltaTime;
    }
}