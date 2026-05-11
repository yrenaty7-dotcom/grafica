package com.example.bouncycube

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10
import kotlin.math.sqrt

class Cube {

    private var mVertexBuffer: FloatBuffer
    private var mColorBuffer: FloatBuffer
    private var mIndexBuffer: ByteBuffer
    private var mNormalBuffer: FloatBuffer

    init {
        val vertices = floatArrayOf(
            -1f,  1f,  1f,
            1f,  1f,  1f,
            1f, -1f,  1f,
            -1f, -1f,  1f,
            -1f,  1f, -1f,
            1f,  1f, -1f,
            1f, -1f, -1f,
            -1f, -1f, -1f
        )

        val colors = floatArrayOf(
            1f, 0f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 0f, 1f, 1f,
            1f, 1f, 0f, 1f,
            1f, 0f, 1f, 1f,
            0f, 1f, 1f, 1f,
            1f, 1f, 1f, 1f,
            0.5f, 0.5f, 0.5f, 1f
        )

        val v = 1.0f / sqrt(3.0f)

        val normals = floatArrayOf(
            -v,  v,  v,
            v,  v,  v,
            v, -v,  v,
            -v, -v,  v,

            -v,  v, -v,
            v,  v, -v,
            v, -v, -v,
            -v, -v, -v
        )

        val indices = byteArrayOf(
            0, 1, 2, 0, 2, 3,
            4, 5, 6, 4, 6, 7,
            0, 4, 7, 0, 7, 3,
            1, 5, 6, 1, 6, 2,
            0, 1, 5, 0, 5, 4,
            3, 2, 6, 3, 6, 7
        )

        val vbb = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        mVertexBuffer = vbb.asFloatBuffer()
        mVertexBuffer.put(vertices)
        mVertexBuffer.position(0)

        val cbb = ByteBuffer.allocateDirect(colors.size * 4)
        cbb.order(ByteOrder.nativeOrder())
        mColorBuffer = cbb.asFloatBuffer()
        mColorBuffer.put(colors)
        mColorBuffer.position(0)

        val nbb = ByteBuffer.allocateDirect(normals.size * 4)
        nbb.order(ByteOrder.nativeOrder())
        mNormalBuffer = nbb.asFloatBuffer()
        mNormalBuffer.put(normals)
        mNormalBuffer.position(0)

        mIndexBuffer = ByteBuffer.allocateDirect(indices.size)
        mIndexBuffer.put(indices)
        mIndexBuffer.position(0)
    }

    fun draw(gl: GL10) {
        gl.glFrontFace(GL10.GL_CCW)

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY)
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY)

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer)
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer)
        gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer)

        gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, mIndexBuffer)

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY)
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY)
    }
}