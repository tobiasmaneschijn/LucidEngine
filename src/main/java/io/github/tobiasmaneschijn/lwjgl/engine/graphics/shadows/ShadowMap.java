package io.github.tobiasmaneschijn.lwjgl.engine.graphics.shadows;


import io.github.tobiasmaneschijn.lwjgl.engine.graphics.Texture;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Shadow Map is used for rendering shadows by rendering the scene from the light's perspective.
 * @todo Implement Cascade Shadow Maps
 */
public class ShadowMap {

    /**
     * The shadow map texture width.
     */
    public static final int SHADOW_MAP_WIDTH = 2048;

    /**
     * The shadow map texture height.
     */
    public static final int SHADOW_MAP_HEIGHT = 2048;

    private final int depthMapFBO;

    private final Texture depthMap;

    /**
     * create a new shadow map
     * @throws Exception
     */
    public ShadowMap() throws Exception {
        // Create a FBO to render the depth map
        depthMapFBO = glGenFramebuffers();

        // Create the depth map texture
        depthMap = new Texture(SHADOW_MAP_WIDTH, SHADOW_MAP_HEIGHT, GL_DEPTH_COMPONENT);

        // Attach the the depth map texture to the FBO
        glBindFramebuffer(GL_FRAMEBUFFER, depthMapFBO);
        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, depthMap.getId(), 0);
        // Set only depth
        glDrawBuffer(GL_NONE);
        glReadBuffer(GL_NONE);

        if (glCheckFramebufferStatus(GL_FRAMEBUFFER) != GL_FRAMEBUFFER_COMPLETE) {
            throw new Exception("Could not create FrameBuffer");
        }

        // Unbind the FBO
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public Texture getDepthMapTexture() {
        return depthMap;
    }


    public int getDepthMapFBO() {
        return depthMapFBO;
    }

    /**
     * clean up the shadow map
     */
    public void cleanup() {
        glDeleteFramebuffers(depthMapFBO);
        depthMap.cleanup();
    }
}