#version 330

// Incredibly simple fragment shader.
// This just outputs the z coordinate as the depth value
void main()
{
    gl_FragDepth = gl_FragCoord.z;
}