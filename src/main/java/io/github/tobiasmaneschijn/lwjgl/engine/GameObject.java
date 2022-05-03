package io.github.tobiasmaneschijn.lwjgl.engine;

import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Mesh;
import org.joml.Vector3f;

public class GameObject {

    private Mesh mesh;

    private final Vector3f position;

    private final Vector3f rotation;

    private float scale;

    public GameObject() {
        position = new Vector3f();
        scale = 1;
        rotation = new Vector3f();
    }

    public GameObject(Mesh mesh) {
        this();
        this.mesh = mesh;
    }
    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position.set(position);
    }
    public void setPosition(float x, float y, float z) {
        this.position.set(x, y, z);
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation.set(rotation);
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.set(x, y, z);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}
