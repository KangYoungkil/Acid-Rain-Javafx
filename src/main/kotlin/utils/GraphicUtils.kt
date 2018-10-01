package utils

import java.awt.GraphicsEnvironment
import java.awt.Point



object GraphicUtils{
    fun getDisplay(): Point {
        val graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice
        val width = graphicsEnvironment.displayMode.width
        val height = graphicsEnvironment.displayMode.height
        return Point(width,height)
    }
}