package io.github.tobiasmaneschijn.lwjgl.game.player;

import io.github.tobiasmaneschijn.lwjgl.engine.IGameLogic;
import io.github.tobiasmaneschijn.lwjgl.engine.MouseInput;
import io.github.tobiasmaneschijn.lwjgl.engine.Window;
import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Camera;
import org.joml.Math;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;

public class PlayerController implements IGameLogic {

    private static final float MOUSE_SENSITIVITY = 0.3f;
    private static final float CAMERA_POS_STEP = 0.05f;
    private static final float MOUSE_SPEED = 5f;

    private Camera camera;
    private Vector3f cameraInc;


    // Movement
    private boolean hasInput = false;
    private static final float DECELERATE_SPEED = 5f;
    private static final float ACCELERATE_SPEED = 20f * 4f;
    private static final float MAX_SPEED = 40f;
    private Vector3f currentVelocity;
    private Vector3f targetVelocity;

    // Mouse movement
    private Vector2f rotVec;
    private Vector2f targetRotVec;

    public PlayerController () {
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
        currentVelocity = new Vector3f(0.0f, 0.0f, 0.0f);
        targetVelocity = new Vector3f(0.0f, 0.0f, 0.0f);
        rotVec = new Vector2f(0.0f, 0.0f);
        targetRotVec = new Vector2f(0.0f, 0.0f);
    }

    @Override
    public void init(Window window) throws Exception {
        camera.setPosition(-1.0f, 20.0f, 2.0f);
        camera.setRotation(50f, 45f, 0f);
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        hasInput = false;
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
            hasInput = true;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
            hasInput = true;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
            hasInput = true;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
            hasInput = true;
        } else

        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
            hasInput = true;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
            hasInput = true;
        }
        addAcceleration(cameraInc);
    }

    private void addAcceleration(Vector3f acceleration) {
        targetVelocity = targetVelocity.add(acceleration.mul(ACCELERATE_SPEED));
        if (targetVelocity.length() > MAX_SPEED) {
            targetVelocity.normalize(MAX_SPEED);
        }
    }
    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera based on mouse

        if (mouseInput.isRightButtonPressed()) {
            targetRotVec = mouseInput.getDisplVec();
        }
        else {
            targetRotVec.set(0f, 0f);
        }
        rotVec.lerp(targetRotVec, interval * MOUSE_SPEED);
        camera.moveRotation(0, rotVec.y * MOUSE_SENSITIVITY, 0);
        updateSpeed(interval);
        camera.movePosition(currentVelocity.x * interval , 0, currentVelocity.z * interval );
    }

    private void updateSpeed(float interval) {
        currentVelocity = currentVelocity.lerp(targetVelocity, interval * 20f);
        if (!hasInput) targetVelocity.lerp(new Vector3f(0, 0, 0), interval * DECELERATE_SPEED);
        // limit target velocity to more than 0
        if (Math.abs(targetVelocity.x) < 0f) {
            targetVelocity.x = 0f;
        }
        if (Math.abs(targetVelocity.y)< 0f) {
            targetVelocity.y = 0f;
        }
    }

    @Override
    public void render(Window window) {

    }

    @Override
    public void cleanup() {

    }

    public Camera getCamera() {
        return camera;
    }
}
