package com.example.lab31;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

public class CubeRenderer implements GLSurfaceView.Renderer {

    private Cube mCube;
    private float mTransY;
    private float mAngle;

    public CubeRenderer() {
        mCube = new Cube();
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();

        // Bouncing
        gl.glTranslatef(0.0f, (float)Math.sin(mTransY), -7.0f);

        // Rotations
        gl.glRotatef(mAngle, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(mAngle, 1.0f, 0.0f, 0.0f);

        // Draw cube
        mCube.draw(gl);

        mTransY += 0.075f;
        mAngle += 0.4f;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        float fieldOfView = 30.0f / 57.3f;
        float aspectRatio = (float) width / (float) height;
        float zNear = 0.1f;
        float zFar = 1000f;

        float size = zNear * (float)Math.tan(fieldOfView / 2.0f);

        gl.glFrustumf(-size, size,
                -size / aspectRatio,
                size / aspectRatio,
                zNear, zFar);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glDisable(GL10.GL_DITHER);

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_FASTEST);

        gl.glClearColor(0, 0, 0, 1);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
    }
}
