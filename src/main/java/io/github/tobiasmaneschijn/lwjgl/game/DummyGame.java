package io.github.tobiasmaneschijn.lwjgl.game;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glViewport;

import io.github.tobiasmaneschijn.lwjgl.engine.GameObject;
import io.github.tobiasmaneschijn.lwjgl.engine.IGameLogic;
import io.github.tobiasmaneschijn.lwjgl.engine.MouseInput;
import io.github.tobiasmaneschijn.lwjgl.engine.Window;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Camera;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Mesh;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.OBJLoader;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class DummyGame implements IGameLogic {


    private static final float MOUSE_SENSITIVITY = 0.2f;

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

    private GameObject[] gameItems;

    private static final float CAMERA_POS_STEP = 0.2f;
    public DummyGame() {
        renderer = new Renderer();
        cameraInc = new Vector3f(0, 0, 0);
        camera = new Camera();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);

        //Mesh mesh = OBJLoader.loadMesh("/models/bunny.obj");
        Mesh mesh = OBJLoader.loadMesh("/models/bunny.obj");
        Texture texture = new Texture("textures/grassblock.png");
        mesh.setTexture(texture);

        GameObject gameItem = new GameObject(mesh);
        gameItem.setScale(0.5f);
        gameItem.setPosition(0, 0, -2);
        //gameItems[x + y * 10] = gameItem;
        gameItems = new GameObject[]{gameItem};

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {

            }
        }


    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP,
                cameraInc.y * CAMERA_POS_STEP,
                cameraInc.z * CAMERA_POS_STEP);

        // Update camera based on mouse
        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameItems);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameObject gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}
